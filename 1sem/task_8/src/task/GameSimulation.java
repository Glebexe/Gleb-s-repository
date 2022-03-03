package task;

public class GameSimulation {

    public static final String RED = "\u001B[31m";
    public static final String BLUE = "\u001B[34m";

    private BattleUnit[] redTeam;
    private BattleUnit[] blueTeam;
    public static String logs;

    public GameSimulation(BattleUnit[] redTeam, BattleUnit[] blueTeam){
        this.redTeam = redTeam;
        this.blueTeam = blueTeam;
        logs = "";
    }

    public String runSimulation(int maxStages, int specialAbilityTimer){
        int stages = 0;
        int preAttackingRedUnit = -1;
        int preAttackingBlueUnit = -1;
        while(stages < maxStages && isTeamAlive(redTeam) && isTeamAlive(blueTeam)){
            preAttackingRedUnit = teamAttack(stages % specialAbilityTimer == 0, preAttackingRedUnit, redTeam, blueTeam);
            if (stages < maxStages && isTeamAlive(blueTeam))
                preAttackingBlueUnit = teamAttack(stages % specialAbilityTimer == 0,preAttackingRedUnit, blueTeam, redTeam);
            if(preAttackingRedUnit == -1)
                stages ++;
        }

        if(countAliveUnits(redTeam) > countAliveUnits(blueTeam)){
            logs += RED + "RedTeam";
        }
        else if(countAliveUnits(redTeam) < countAliveUnits(blueTeam)){
            logs += BLUE + "BlueTeam";
        }

        return logs;
    }

    private boolean isTeamAlive(BattleUnit[] team){
        for(BattleUnit i: team){
            if(i.health() > 0)
                return true;
        }
        return false;
    }

    private BattleUnit chooseWhoAttack(int unitNum, BattleUnit[] team){
        for(int i = 0; i < team.length; i++){
            if(unitNum + i < team.length && team[unitNum + i].health() > 0)
                return team[unitNum + i];
            else if(unitNum - i >= 0 && team[unitNum - i].health() > 0)
                return team[unitNum - i];
        }
        return null;
    }

    private int teamAttack(boolean timeForSpecialAttack, int preAttackingUnit, BattleUnit[] attackingTeam, BattleUnit[] defendingTeam){
        if(preAttackingUnit >= attackingTeam.length - 1)
            preAttackingUnit = -1;
        for(int i = preAttackingUnit + 1; i < attackingTeam.length; i++){
            if(attackingTeam[i].health() > 0){
                if(timeForSpecialAttack)
                    attackingTeam[i].specialAbility(attackingTeam, defendingTeam);
                else
                    attackingTeam[i].attack(chooseWhoAttack(i,defendingTeam));
                return i;
            }
        }
        return 0;
    }

    private int countAliveUnits(BattleUnit[] team){
        int number = 0;
        for(BattleUnit unit : team){
            if(unit.health() > 0){
                number++;
            }
        }
        return number;
    }
}
