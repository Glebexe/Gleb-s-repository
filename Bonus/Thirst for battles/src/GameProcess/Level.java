package GameProcess;

import Teams.AITeam;
import Teams.HumanTeam;

public class Level {

    private boolean isCompleted;
    private boolean isAwardReceived;

    public Level(){
        isCompleted = false;
        isAwardReceived = false;
    }

    public void start(HumanTeam humanTeam, AITeam aiTeam){
        new Battle(humanTeam, aiTeam);
        isCompleted = humanTeam.isTeamAlive();
        humanTeam.reset();
    }

    public boolean isCompleted(){return isCompleted;}
    public boolean isAwardReceived(){return isAwardReceived;}
    public void setAwardIsReceived(){
        isAwardReceived = true;
    }
}
