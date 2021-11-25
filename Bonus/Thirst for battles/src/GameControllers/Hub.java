package GameControllers;

import GameProcess.Level;
import StaticClasses.HubInterface;
import Teams.HumanTeam;

import java.util.Scanner;

public class Hub {

    private HumanTeam humanTeam;
    private Scanner sc;
    private LevelManager levelManager;

    public Hub(HumanTeam humanTeam){
        this.humanTeam = humanTeam;
        sc = new Scanner(System.in);
        levelManager = new LevelManager();
    }

    public void homeLocation(){
        String input = "";
        while(!input.equals("Завершить игру")) {

            HubInterface.drawHomeLocation(humanTeam,levelManager.getLevels());
            input = HubInterface.commandProcessing(humanTeam);

            if (input.split(" ").length == 3) {
                System.out.println("Уровень " + input.split(" ")[2]);
                levelManager.LoadLevel(Integer.parseInt(input.split(" ")[2]), humanTeam);
            }
            else if(input.split(" ").length == 5){
                humanTeam.getUnits().get(Integer.parseInt(input.split(" ")[4])-1).setEquipment(
                                humanTeam.getEquipment().get(Integer.parseInt(input.split(" ")[2])-1),humanTeam);
                humanTeam.getEquipment().remove(Integer.parseInt(input.split(" ")[2])-1);

            }
        }
    }
}
