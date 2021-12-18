package StaticClasses;

import Basic_classes.Team;
import Basic_classes.Unit;
import Enums.Commands;
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
        System.out.println("Доступные команды:");
        System.out.println("\"Запустить уровень 1\",\"Запустить уровень 2\", итд. ");
        System.out.println("\"Вложить очко юниту (напишите номер юнита которому вы хотите что-то прокачать) в (напишите на выбор: атаку, защиту, здоровье)\"");
        System.out.println("\"Дать снаряжение (напишите номер свободного снаряжения) юниту (напишите номер юнита которому вы хотите дать это снаряжение)\"");
        System.out.println("\"Завершить игру\"");
        while(true){
            String input = sc.nextLine();
            if(finishGameCheck(input))
                return new Command(FinishGame);
            else if(loadLevelCheck(input))
                return new Command(LoadLevel, new int[]{Integer.parseInt(input.split(" ")[2])});
            else if(useUpgradingPointCheck(input,humanTeam)) {
                switch(input.split(" ")[5]) {
                    case "атаку","а":
                        return new Command( UseUpgradingPoints, Attack, new int[]{Integer.parseInt(input.split(" ")[3])});
                    case "защиту","за":
                        return new Command( UseUpgradingPoints, Armor, new int[]{Integer.parseInt(input.split(" ")[3])});
                    case "здоровье","зд":
                        return new Command( UseUpgradingPoints, HP, new int[]{Integer.parseInt(input.split(" ")[3])});
                }
            }
            else if(putEquipmentCheck(input,humanTeam))
                return new Command(PutEquipment, new int[]{Integer.parseInt(input.split(" ")[2]),
                        Integer.parseInt(input.split(" ")[4])});
            else
                System.out.println("Недопустимая команда!");
        }
    }

    private static boolean finishGameCheck(String input){
        return input.equals("Завершить игру") || input.equals("З и");
    }

    //Check correctness if user want to start a level
    private static boolean loadLevelCheck(String input){
        if (input.split(" ").length == 3 && (input.split(" ")[0].equals("Запустить")|| input.split(" ")[0].equals("З"))
                && (input.split(" ")[1].equals("уровень") || input.split(" ")[1].equals("у"))){
            if (new Scanner(input.split(" ")[2]).hasNextInt()) {
                int level = Integer.parseInt(input.split(" ")[2]);
                return level > 0 && level < 8;
            }
        }
        return false;
    }

    //Check correctness if user want to put equipment on a unit
    private static boolean putEquipmentCheck(String input, HumanTeam humanTeam){
        if (input.split(" ").length == 5 && (input.split(" ")[0].equals("Дать") || input.split(" ")[0].equals("Д"))
                && (input.split(" ")[1].equals("снаряжение") || input.split(" ")[1].equals("с"))
                && (input.split(" ")[3].equals("юниту") || input.split(" ")[3].equals("ю"))){
            if (new Scanner(input.split(" ")[2]).hasNextInt()) {
                if (new Scanner(input.split(" ")[4]).hasNextInt()) {

                    int equipment = Integer.parseInt(input.split(" ")[2]);
                    int unit = Integer.parseInt(input.split(" ")[4]);

                    if (equipment > 0 && equipment <= humanTeam.getEquipment().size())
                        return unit > 0 && unit <= humanTeam.getUnits().size();
                }
            }
        }
        return false;
    }

    //Check correctness if user want to use upgrading points
    private static boolean useUpgradingPointCheck(String input, HumanTeam humanTeam){
        if(humanTeam.getUpgradingPoints() > 0) {
            if (input.split(" ").length == 6 && (input.split(" ")[0].equals("Вложить") || input.split(" ")[0].equals("В")) &&
                    (input.split(" ")[1].equals("очко") || input.split(" ")[1].equals("о"))
                    && (input.split(" ")[2].equals("юниту") || input.split(" ")[2].equals("ю"))
                    && input.split(" ")[4].equals("в") && (input.split(" ")[5].equals("атаку")
                    || input.split(" ")[5].equals("а") || input.split(" ")[5].equals("защиту")
                    || input.split(" ")[5].equals("за") || input.split(" ")[5].equals("здоровье")
                    || input.split(" ")[5].equals("зд"))) {
                if (new Scanner(input.split(" ")[3]).hasNextInt()) {
                    int unit = Integer.parseInt(input.split(" ")[3]);
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
            output.append(String.format("%-25s","(Меч: " + (getUnit(humanTeam,i).getSword() != null ?
                    getUnit(humanTeam,i).getSword().getName() + " +" + getUnit(humanTeam,i).getSword().getBonus() + " урона":"нет")
                    + ".  " + "Щит: " + (getUnit(humanTeam,i).getShield() != null ?
                    getUnit(humanTeam,i).getShield().getName() + " +" + getUnit(humanTeam,i).getShield().getBonus() + " защиты":"нет")
                    + ".  " + "Заклинания: " + (getUnit(humanTeam,i).getSpell() != null ? getUnit(humanTeam,i).getSpell().getName():"нет") + ".)\n"));
        }
        System.out.println(output);
    }
    private static void drawUpgradingPoints(HumanTeam humanTeam){
        System.out.println("Свободных очков: " + humanTeam.getUpgradingPoints());
        System.out.println("Одно очко даёт или +5 к урону, или +5 к защите, или +5 к здоровью у выбранного юнита.");
        System.out.println();
    }
    private static void drawInventory(HumanTeam humanTeam){
        StringBuilder output = new StringBuilder();
        output.append("Свободное снаряжение: \n");
        if(humanTeam.getEquipment().size() == 0){
            output.append("У вас нет свободного снарежения");
        }
        for(int i = 0; i < humanTeam.getEquipment().size(); i++){
            output.append((i+1) + ". " + humanTeam.getEquipment().get(i).getName());
            if(humanTeam.getEquipment().get(i).getEquipmentType() == Shield){
                output.append(" +" + humanTeam.getEquipment().get(i).getBonus() + " к защите");
            }else{
                output.append(" " + humanTeam.getEquipment().get(i).getBonus() + " к урону");
            }
        }
        output.append("\n");
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
