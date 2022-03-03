package GameControllers;

import GameProcess.CreateTeams;
import Teams.HumanTeam;

public class GameController {

    static HumanTeam humanTeam;
    static Hub hub;

    public static void main(String[] args){
        CreateTeams createTeams = new CreateTeams();

        humanTeam = createTeams.getHumanTeam();

        hub = new Hub(humanTeam);
        hub.homeLocation();
    }
}
