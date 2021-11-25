package StaticClasses;

import Basic_classes.Equipment;
import Basic_classes.Team;
import Basic_classes.Unit;
import GameProcess.Level;
import Teams.HumanTeam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HubInterface {

    private static Scanner sc;

    public static void drawHomeLocation(HumanTeam humanTeam, List<Level> levels){
        sc = new Scanner(System.in);
        drawHumanTeam(humanTeam);
        drawInventory(humanTeam);
        drawLevels(levels);
    }

    public static String commandProcessing(HumanTeam humanTeam){
        System.out.println("Доступные команды:");
        System.out.println("\"Запустить уровень 1\",\"Запуситить уровень 2\", итд. ");
        System.out.println("\"Дать снаряжение (напишите номер свободного снаряжения) юниту (напишите номер юнита которому вы хотите дать это снаряжение)");
        System.out.println("\"Завершить игру\"");
        while(true){
            String input = sc.nextLine();
            if(input.equals("Завершить игру") || loadLevelCheck(input) || putEquipmentCheck(input,humanTeam))
                return input;
            else
                System.out.println("Недопутимая команда!");
        }
    }

    private static boolean loadLevelCheck(String input){
        if (input.split(" ").length == 3 && input.split(" ")[0].equals("Запустить") && input.split(" ")[1].equals("уровень")) {
            if (new Scanner(input.split(" ")[2]).hasNextInt()) {
                int level = Integer.parseInt(input.split(" ")[2]);
                if (level > 0 && level < 2)
                    return true;
            }
        }
        return false;
    }

    private static boolean putEquipmentCheck(String input, HumanTeam humanTeam){
        if (input.split(" ").length == 5 && input.split(" ")[0].equals("Дать")
                && input.split(" ")[1].equals("снаряжение") && input.split(" ")[3].equals("юниту")) {
            if (new Scanner(input.split(" ")[2]).hasNextInt()) {
                if (new Scanner(input.split(" ")[4]).hasNextInt()) {

                    int equipment = Integer.parseInt(input.split(" ")[2]);
                    int unit = Integer.parseInt(input.split(" ")[4]);

                    if (equipment > 0 && equipment <= humanTeam.getEquipment().size())
                        if (unit > 0 && unit <= humanTeam.getUnits().size())
                            return true;
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
            output.append(String.format("%-3s", getUnit(humanTeam,i).getArmor()));
            output.append(String.format("%-7s", "Броня"));
            output.append(String.format("%-3s", getUnit(humanTeam,i).getDamage()));
            output.append(String.format("%-7s", "Атака"));
            output.append(String.format("%-25s","(Меч: " + (getUnit(humanTeam,i).getSword() != null ?
                    getUnit(humanTeam,i).getSword().getName() + " +" + getUnit(humanTeam,i).getSword().getDamage() + " урона":"нет")
                    + ".  " + "Щит: " + (getUnit(humanTeam,i).getShield() != null ?
                    getUnit(humanTeam,i).getShield().getName() + " +" + getUnit(humanTeam,i).getShield().getDefence() + " защиты":"нет")
                    + ".  " + "Заклинания: " + (getUnit(humanTeam,i).getSpell() != null ? getUnit(humanTeam,i).getSpell().getName():"нет") + ".)\n"));
        }
        System.out.println(output);
    }

    private static void drawInventory(HumanTeam humanTeam){
        StringBuilder output = new StringBuilder();
        output.append("Свободное снаряжение: \n");
        for(int i = 0; i < humanTeam.getEquipment().size(); i++){
            output.append((i+1) + ". " + humanTeam.getEquipment().get(i).getName() + "\n");
        }
        System.out.println(output);
    }

    private static void drawLevels(List<Level> levels) {
        StringBuilder output = new StringBuilder();
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
