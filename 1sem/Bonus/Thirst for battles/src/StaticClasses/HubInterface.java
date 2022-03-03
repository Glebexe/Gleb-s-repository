package StaticClasses;

import Basic_classes.Team;
import Basic_classes.Unit;
import Enums.Commands;
import GameControllers.Hub;
import GameProcess.Command;
import GameProcess.Level;
import Teams.HumanTeam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Enums.Commands.*;
import static Enums.EquipmentType.Shield;
import static Enums.UnitStats.*;

public class HubInterface {

    private static Scanner sc;

    public static void drawHomeLocation(HumanTeam humanTeam, List<Level> levels){
        sc = new Scanner(System.in);
        drawHumanTeam(humanTeam);
        drawUpgradingPoints(humanTeam);
        drawInventory(humanTeam);
        drawLevels(levels);
    }

    //Check correctness of commands
    public static Command commandProcessing(HumanTeam humanTeam){
        System.out.println("Команды можно писать как полностью слова так и только по первой букве, \n" +
                "за исключением защиты и здоровья, для них нужно писать две первые буквы.");
        System.out.println("Доступные команды:");
        System.out.println("\"Запустить уровень 1\",\"Запустить уровень 2\", итд. ");
        System.out.println("\"Вложить очко юниту (напишите номер юнита которому вы хотите что-то прокачать) в (напишите на выбор: атаку, защиту, здоровье)\"");
        System.out.println("\"Дать снаряжение (напишите номер свободного снаряжения) юниту (напишите номер юнита которому вы хотите дать это снаряжение)\"");
        System.out.println("\"Завершить игру\"");
        while(true){
            String[] input = sc.nextLine().split(" ");
            if(finishGameCheck(input))
                return new Command(FinishGame);
            else if(loadLevelCheck(input))
                return new Command(LoadLevel, new int[]{Integer.parseInt(input[2])});
            else if(useUpgradingPointCheck(input,humanTeam)) {
                switch(input[5]) {
                    case "атаку","а":
                        return new Command( UseUpgradingPoints, Attack, new int[]{Integer.parseInt(input[3])});
                    case "защиту","за":
                        return new Command( UseUpgradingPoints, Armor, new int[]{Integer.parseInt(input[3])});
                    case "здоровье","зд":
                        return new Command( UseUpgradingPoints, HP, new int[]{Integer.parseInt(input[3])});
                }
            }
            else if(putEquipmentCheck(input,humanTeam))
                return new Command(PutEquipment, new int[]{Integer.parseInt(input[2]),
                        Integer.parseInt(input[4])});
            else
                System.out.println("Недопустимая команда!");
        }
    }

    private static boolean finishGameCheck(String[] input){
        return input.length == 2 && (input[0].equals("Завершить") || input[0].equals("З")) &&
                (input[1].equals("игру") || input[1].equals("и"));
    }

    //Check correctness if user want to start a level
    private static boolean loadLevelCheck(String[] input){
        if (input.length == 3 && (input[0].equals("Запустить")|| input[0].equals("З"))
                && (input[1].equals("уровень") || input[1].equals("у"))){
            if (new Scanner(input[2]).hasNextInt()) {
                int level = Integer.parseInt(input[2]);
                return level > 0 && level < 8;
            }
        }
        return false;
    }

    //Check correctness if user want to put equipment on a unit
    private static boolean putEquipmentCheck(String[] input, HumanTeam humanTeam){
        if (input.length == 5 && (input[0].equals("Дать") || input[0].equals("Д"))
                && (input[1].equals("снаряжение") || input[1].equals("с"))
                && (input[3].equals("юниту") || input[3].equals("ю"))){
            if (new Scanner(input[2]).hasNextInt()) {
                if (new Scanner(input[4]).hasNextInt()) {

                    int equipment = Integer.parseInt(input[2]);
                    int unit = Integer.parseInt(input[4]);

                    if (equipment > 0 && equipment <= humanTeam.getEquipment().size())
                        return unit > 0 && unit <= humanTeam.getUnits().size();
                }
            }
        }
        return false;
    }

    //Check correctness if user want to use upgrading points
    private static boolean useUpgradingPointCheck(String[] input, HumanTeam humanTeam){
        if(humanTeam.getUpgradingPoints() > 0) {
            if (input.length == 6 && (input[0].equals("Вложить") || input[0].equals("В")) &&
                    (input[1].equals("очко") || input[1].equals("о"))
                    && (input[2].equals("юниту") || input[2].equals("ю"))
                    && input[4].equals("в") && (input[5].equals("атаку")
                    || input[5].equals("а") || input[5].equals("защиту")
                    || input[5].equals("за") || input[5].equals("здоровье")
                    || input[5].equals("зд"))) {
                if (new Scanner(input[3]).hasNextInt()) {
                    int unit = Integer.parseInt(input[3]);
                    return unit > 0 && unit <= humanTeam.getUnits().size();
                }
            }
        }
        return false;
    }

    private static void drawHumanTeam(HumanTeam humanTeam){
        StringBuilder output = new StringBuilder();
        output.append("Команда " + String.format("%-15s",humanTeam.getName()) + "\n");
        output.append("========================================================================================" + "\n");
        for(int i = 0; i < humanTeam.getUnits().size(); i++){
            output.append(String.format("%-14s",i+1 + ". " + getUnit(humanTeam,i).getName()));
            output.append(String.format("%-4s", getUnit(humanTeam,i).getHp()));
            output.append(String.format("%-4s","hp"));
            output.append(String.format("%-3s", getUnit(humanTeam,i).getFullArmor()));
            output.append(String.format("%-7s", "Броня"));
            output.append(String.format("%-3s", getUnit(humanTeam,i).getFullDamage()));
            output.append(String.format("%-7s", "Атака"));
            output.append(String.format("%-24s","(Меч: " + (getUnit(humanTeam,i).getSword() != null ?
                    getUnit(humanTeam,i).getSword().getName() + " +" + getUnit(humanTeam,i).getSword().getBonus() + " урона":"нет")
                    + ".  " + "Щит: " + (getUnit(humanTeam,i).getShield() != null ?
                    getUnit(humanTeam,i).getShield().getName() + " +" + getUnit(humanTeam,i).getShield().getBonus() + " защиты":"нет")
                    + ".)    \n"));
        }
        System.out.println(output);
    }
    private static void drawUpgradingPoints(HumanTeam humanTeam){
        System.out.println("Свободных очков: " + humanTeam.getUpgradingPoints());
        System.out.println("Одно очко даёт или +" + Hub.getDamageUpgradingBonus() + " к урону, или +" +
                Hub.getArmorUpgradingBonus() + " к защите, или +" + Hub.getHpUpgradingBonus() + " к здоровью у выбранного юнита.");
        System.out.println();
    }
    private static void drawInventory(HumanTeam humanTeam){
        StringBuilder output = new StringBuilder();
        output.append("Свободное снаряжение: \n");
        if(humanTeam.getEquipment().size() == 0){
            output.append("У вас нет свободного снаряжения.\n");
        }
        for(int i = 0; i < humanTeam.getEquipment().size(); i++){
            output.append((i+1) + ". " + humanTeam.getEquipment().get(i).getName());
            if(humanTeam.getEquipment().get(i).getEquipmentType() == Shield){
                output.append(" +" + humanTeam.getEquipment().get(i).getBonus() + " к защите");
            }else{
                output.append(" +" + humanTeam.getEquipment().get(i).getBonus() + " к урону");
            }
            output.append("\n");
        }
        System.out.println(output);
    }
    private static void drawLevels(List<Level> levels) {
        StringBuilder output = new StringBuilder();
        output.append("Доступные уровни:\n");
        for(int i = 0; i < levels.size(); i++) {
            output.append("Уровень " + (i+1));
            if(levels.get(i).isCompleted())
                output.append(" Пройден");
            output.append("\n");
        }
        System.out.println(output);
    }

    private static Unit getUnit(Team team, int unitNum){
        return team.getUnits().get(unitNum);
    }
}
