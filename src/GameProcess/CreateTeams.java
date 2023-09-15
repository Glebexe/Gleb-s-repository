package GameProcess;

import Basic_classes.Team;
import Basic_classes.Unit;
import Factories.UnitFactory;
import Enums.UnitType;
import StaticClasses.BattleInterface;
import StaticClasses.HubInterface;
import Teams.HumanTeam;

import java.util.*;

import static Enums.UnitType.*;

public class CreateTeams {

    private HumanTeam human;
    private UnitFactory unitFactory;

    public CreateTeams(){
        unitFactory = new UnitFactory();
        initialisation();
    }

    private String createTeamName(){

        Scanner sc = new Scanner(System.in);

        System.out.println("Назовите свою команду. Длина имени должна быть не меньше трёх и не больше пятнадцати символов.");

        String name;
        while (true) {
            name = sc.nextLine();
            if (name.length() >= 3 && name.length() < 16) {
                return name;
            } else {
                System.out.println("Длина имени должна быть не меньше трёх и не больше пятнадцати символов!");
            }
        }
    }

    private void initialisation(){
        human = humanTeamInit();
    }

    //Human team initialization
    private HumanTeam humanTeamInit(){

        List<Unit> teamUnits = new ArrayList<>();
        int[] unitsNum = new int[3];
        StringBuilder output = new StringBuilder();
        List<Unit> exampleUnits = new ArrayList();
        exampleUnits.add(unitFactory.createUnit(Warrior,0,0,0));
        exampleUnits.add(unitFactory.createUnit(Archer,0,0,0));
        exampleUnits.add(unitFactory.createUnit(Wizard,0,0,0));
        String teamName = createTeamName();
        for(int i = 0; i < 3; i++){
            output.append(String.format("%-14s", i +1 + ". " + exampleUnits.get(i).getName()));
            output.append(String.format("%-4s", exampleUnits.get(i).getHp()));
            output.append(String.format("%-4s","hp"));
            output.append(String.format("%-3s", exampleUnits.get(i).getFullArmor()));
            output.append(String.format("%-7s", "Броня"));
            output.append(String.format("%-3s", exampleUnits.get(i).getFullDamage()));
            output.append(String.format("%-7s", "Атака       \n"));
        }
        System.out.println(output);

        System.out.println("Введите номер первого юнита, которого вы бы хотели добавить себе в команду:");
        unitsNum[0] = checkInput();

        System.out.println("Введите номер второго юнита, которого вы бы хотели добавить себе в команду:");
        unitsNum[1] = checkInput();

        System.out.println("Введите номер третьего юнита, которого вы бы хотели добавить себе в команду:");
        unitsNum[2] = checkInput();

        fillingTeam(unitsNum,teamUnits);

        return new HumanTeam(teamName,teamUnits);
    }

    // Adding in a team units
    private void fillingTeam(int[] unitsNum, List<Unit> team){

        for(int i = 0; i < 3; i++){
            switch(unitsNum[i]){
                case 1:
                    team.add(unitFactory.createUnit(Warrior,0,0,0));
                    break;
                case 2:
                    team.add(unitFactory.createUnit(Archer,0,0,0));
                    break;
                case 3:
                    team.add(unitFactory.createUnit(Wizard,0,0,0));
            }
        }
    }

    //Check of input correctness when player chooses units for team
    private int checkInput(){

        Scanner sc = new Scanner(System.in);

        int unit;
        while (true) {
            if (sc.hasNextInt()) {
                unit = sc.nextInt();
                if (unit > 0 && unit <= 3)
                    return unit;
                else
                    System.out.println("Юнита с таким номером не существует!");
            } else {
                System.out.println("Вы должны ввести число!");
                sc.next();
            }
        }
    }

    public HumanTeam getHumanTeam() {
        return human;
    }
}
