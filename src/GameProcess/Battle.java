package GameProcess;

import Basic_classes.Team;
import Basic_classes.Unit;
import StaticClasses.BattleInterface;
import Teams.AITeam;
import Teams.HumanTeam;

import java.util.Random;

public class Battle {

    private HumanTeam humanTeam;
    private AITeam aiTeam;
    private Random random;

    public Battle(HumanTeam humanTeam, AITeam aiTeam){
        this.humanTeam = humanTeam;
        this.aiTeam = aiTeam;
        random = new Random();

        fight();
    }

    //Fighting process
    private void fight(){

        while(humanTeam.isTeamAlive() && aiTeam.isTeamAlive()){
            BattleInterface.drawInterfaceOfBattle(humanTeam, aiTeam);

            humanAttack();

            if(aiTeam.isTeamAlive())
                aIAttack();
        }

        results();
    }

    //Player attack
    private void humanAttack(){

        int attackingUnit,defendingUnit,dealtDamage;

        System.out.println("Выберите кем будете атаковать: ");
        attackingUnit = BattleInterface.checkUnitInput(humanTeam);
        System.out.println("Выберите кого будете атаковать: ");
        defendingUnit = BattleInterface.checkUnitInput(aiTeam);

        dealtDamage = getUnit(aiTeam,
                defendingUnit-1).receiveDamage(getUnit(humanTeam,attackingUnit-1).getFullDamage()
                + random.nextInt(10)-5);

        BattleInterface.battleLogs(humanTeam,aiTeam,attackingUnit-1,defendingUnit-1,dealtDamage);
    }

    // AI logic of attack
    private void aIAttack(){
        int attackingUnit,defendingUnit,dealtDamage;

        attackingUnit = aIAttackingUnitChoose();
        defendingUnit = aIDefendingUnitChoose();
        dealtDamage = humanTeam.getUnits().get(
                defendingUnit).receiveDamage(aiTeam.getUnits().get(attackingUnit).getFullDamage() +
                random.nextInt(10)-5);

        BattleInterface.battleLogs(aiTeam,humanTeam,attackingUnit,defendingUnit,dealtDamage);
    }

    // AI logic of choosing an attacking unit
    private int aIAttackingUnitChoose(){

        int attackingUnit = 0,maxDamage;
        maxDamage = 0;
        for(int i = 0; i < aiTeam.getUnits().size(); i++){
            if(getUnit(aiTeam,i).getFullDamage() > maxDamage && getUnit(aiTeam,i).getIsAlive()){
                maxDamage = getUnit(aiTeam,i).getFullDamage();
                attackingUnit = i;
            }
        }

        return attackingUnit;
    }

    // AI logic of choosing a defending unit
    private int aIDefendingUnitChoose(){

        int defendingUnit = 0,maxDamage;
        maxDamage = 0;
        for(int i = 0; i < humanTeam.getUnits().size(); i++){
            if(getUnit(humanTeam,i).getFullDamage() > maxDamage && getUnit(humanTeam,i).getIsAlive()){
                maxDamage = getUnit(humanTeam,i).getFullDamage();
                defendingUnit = i;
            }
        }

        return defendingUnit;
    }

    //Return certain unit
    private Unit getUnit(Team team, int unitNum){
        return team.getUnits().get(unitNum);
    }

    private void results(){
        BattleInterface.results(humanTeam);
    }
}
