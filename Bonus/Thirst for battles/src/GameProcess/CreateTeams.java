package GameProcess;

import Basic_classes.Unit;
import Factories.UnitFactory;
import Factories.UnitType;
import StaticClasses.BattleInterface;
import Teams.AITeam;
import Teams.HumanTeam;

import java.util.*;

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
            name = sc.next();
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

        String teamName = createTeamName();

        System.out.println("1. Воин \n2. Лучник \n3. Волшебник");

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
                    team.add(unitFactory.createUnit(UnitType.Warrior,0,0,0));
                    break;
                case 2:
                    team.add(unitFactory.createUnit(UnitType.Archer,0,0,0));
                    break;
                case 3:
                    team.add(unitFactory.createUnit(UnitType.Wizard,0,0,0));
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
