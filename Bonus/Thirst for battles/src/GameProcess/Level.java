package GameProcess;

import Teams.AITeam;
import Teams.HumanTeam;

public class Level {

    private boolean isCompleted;

    public Level(){
        isCompleted = false;
    }

    public void start(HumanTeam humanTeam, AITeam aiTeam){
        new Battle(humanTeam, aiTeam);
        isCompleted = humanTeam.isTeamAlive();
        humanTeam.reset();
    }

    public boolean isCompleted(){return isCompleted;}
}
