package StaticClasses;

import Basic_classes.Team;
import Basic_classes.Unit;

import java.util.Scanner;

public final class BattleInterface {
    private static Scanner sc;

    public static void drawInterfaceOfBattle(Team humanTeam, Team aiTeam){
        StringBuilder output = new StringBuilder();
        output.append("Команда " + String.format("%-15s",humanTeam.getName()) +
                "                                                               Команда " + aiTeam.getName() + "\n");
        output.append("============================================================================================================================" + "\n");
        for(int i = 0; i < humanTeam.getUnits().size(); i++){
            output.append(String.format("%-14s",i+1 + ". " + getUnit(humanTeam,i).getName()));
            output.append(String.format("%-4s", getUnit(humanTeam,i).getHp()));
            output.append(String.format("%-4s","hp"));
            output.append(String.format("%-3s", getUnit(humanTeam,i).getFullArmor()));
            output.append(String.format("%-7s", "Броня"));
            output.append(String.format("%-3s", getUnit(humanTeam,i).getFullDamage()));
            output.append(String.format("%-50s", "Атака"));
            output.append(String.format("%-14s", i+1 + ". " + getUnit(aiTeam,i).getName()));
            output.append(String.format("%-4s", getUnit(aiTeam,i).getHp()));
            output.append(String.format("%-4s", "hp"));
            output.append(String.format("%-3s", getUnit(aiTeam,i).getFullArmor()));
            output.append(String.format("%-7s", "Броня"));
            output.append(String.format("%-3s", getUnit(aiTeam,i).getFullDamage()));
            output.append(String.format("%-6s", "Атака\n"));
            output.append(String.format("%-85s","(Меч: " + (getUnit(humanTeam,i).getSword() != null ?
                    getUnit(humanTeam,i).getSword().getName() + " +" + getUnit(humanTeam,i).getSword().getBonus() + " урона":"нет")
                    + ".  " + "Щит: " + (getUnit(humanTeam,i).getShield() != null ?
                    getUnit(humanTeam,i).getShield().getName() + " +" + getUnit(humanTeam,i).getShield().getBonus() + " защиты":"нет")
                    + ".  " + "Заклинания: " + (getUnit(humanTeam,i).getSpell() != null ? getUnit(humanTeam,i).getSpell().getName():"нет") + ".)"));
            output.append("(Меч: " + (getUnit(aiTeam,i).getSword() != null ?
                    getUnit(aiTeam,i).getSword().getName()  + " +" + getUnit(aiTeam,i).getSword().getBonus() + " урона":"нет")
                    + ".  " + "Щит: " + (getUnit(aiTeam,i).getShield() != null ?
                    getUnit(aiTeam,i).getShield().getName() + " +" + getUnit(aiTeam,i).getShield().getBonus() + " защиты":"нет")
                    + ".  " + "Заклинания: " + (getUnit(aiTeam,i).getSpell() != null ? getUnit(aiTeam,i).getSpell().getName():"нет") + ".)\n");
        }
        System.out.println(output);
    }

    public static void battleLogs(Team attackingTeam, Team defendingTeam, int attackingUnit, int defendingUnit, int dealtDamage){
        System.out.println(getUnit(attackingTeam, attackingUnit).getName() +
                " из команды " + attackingTeam.getName() + " атаковал " + getUnit(defendingTeam,defendingUnit).getName() +
                " и нанёс " + dealtDamage + " урона.");
    }

    public static int checkUnitInput(Team team){

        sc = new Scanner(System.in);

        int unit;
        while (true) {
            boolean input = sc.hasNextInt();
            if (input) {
                unit = sc.nextInt();
                if (unit > 0 && unit <= team.getUnits().size())
                    if (team.getUnits().get(unit - 1).getHp() != 0)
                        return unit;
                    else
                        System.out.println("Этот юнит убит!");
                else
                    System.out.println("Юнита с таким номером не существует!");
            } else {
                System.out.println("Вы должны ввести число!");
                sc.next();
            }
        }
    }

    public static void results(Team team){
        if(team.isTeamAlive()){
            System.out.println("Ты победил!");
        }else {
            System.out.println("Ты проиграл!");
        }
    }

    //Return certain unit
    private static Unit getUnit(Team team, int unitNum){
        return team.getUnits().get(unitNum);
    }
}
