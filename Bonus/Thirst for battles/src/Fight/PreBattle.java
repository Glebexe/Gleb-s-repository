package Fight;

import Basic_classes.Unit;
import Factories.UnitFactory;
import Factories.UnitType;
import StaticClasses.ConsoleInteraction;
import Teams.AITeam;
import Teams.HumanTeam;

import java.util.*;

public class PreBattle {

    private HumanTeam human;
    private AITeam ai;
    private UnitFactory unitFactory;

    public PreBattle(){
        unitFactory = new UnitFactory();
        initialisation();
    }

    private void initialisation(){
        human = humanTeamInit();
        ai = aITeamInit();
    }

    //Human team initialization
    private HumanTeam humanTeamInit(){

        List<Unit> teamUnits = new ArrayList<>();
        int[] unitsNum = new int[3];

        String teamName = ConsoleInteraction.createTeamName();

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

    //AI team initialization
    private AITeam aITeamInit(){

        List<Unit> teamUnits = new ArrayList<>();
        int[] unitsNum = new int[3];

        Random random = new Random();

        unitsNum[0] = random.nextInt(3)+1;
        unitsNum[1] = random.nextInt(3)+1;
        unitsNum[2] = random.nextInt(3)+1;

        fillingTeam(unitsNum,teamUnits);

        return new AITeam("Компьютер", teamUnits);
    }

    // Adding in a team units
    private void fillingTeam(int[] unitsNum, List<Unit> team){

        for(int i = 0; i < 3; i++){
            switch(unitsNum[i]){
                case 1:
                    team.add(unitFactory.createUnit(UnitType.Warrior));
                    break;
                case 2:
                    team.add(unitFactory.createUnit(UnitType.Archer));
                    break;
                case 3:
                    team.add(unitFactory.createUnit(UnitType.Wizard));
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

    public AITeam getAiTeam() {
        return ai;
    }

    public HumanTeam getHumanTeam() {
        return human;
    }
}
