package Fight;

import Basic_classes.Team;
import Basic_classes.Unit;
import StaticClasses.ConsoleInteraction;
import Teams.AITeam;
import Teams.HumanTeam;

import java.util.Random;
import java.util.Scanner;

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

    private void fight(){

        while(humanTeam.isTeamAlive() && aiTeam.isTeamAlive()){
            ConsoleInteraction.drawInterfaceOfBattle(humanTeam, aiTeam);

            humanAttack();

            if(aiTeam.isTeamAlive())
                aIAttack();
        }

        results();
    }

    //Player attack
    private void humanAttack(){

        int attackingUnit,defendingUnit,dealtDamage;

        System.out.println("Выберете кем будете атакавать: ");
        attackingUnit = ConsoleInteraction.checkUnitInput(humanTeam);
        System.out.println("Выберете кого будете атакавать: ");
        defendingUnit = ConsoleInteraction.checkUnitInput(aiTeam);

        dealtDamage = getUnit(aiTeam,
                defendingUnit-1).receiveDamage(getUnit(humanTeam,attackingUnit-1).getDamage()
                + random.nextInt(10)-5);

        ConsoleInteraction.battleLogs(humanTeam,aiTeam,attackingUnit-1,defendingUnit-1,dealtDamage);
    }

    // AI logic of attack
    private void aIAttack(){
        int attackingUnit,defendingUnit,dealtDamage;

        attackingUnit = aIAttackingUnitChoose();
        defendingUnit = aIDefendingUnitChoose();
        dealtDamage = humanTeam.getUnits().get(
                defendingUnit).receiveDamage(aiTeam.getUnits().get(attackingUnit).getDamage() +
                random.nextInt(10)-5);

        ConsoleInteraction.battleLogs(aiTeam,humanTeam,attackingUnit,defendingUnit,dealtDamage);
    }

    // AI logic of choosing an attacking unit
    private int aIAttackingUnitChoose(){

        int attackingUnit = 0,maxDamage;
        maxDamage = 0;
        for(int i = 0; i < aiTeam.getUnits().size(); i++){
            if(getUnit(aiTeam,i).getDamage() > maxDamage && getUnit(aiTeam,i).getIsAlive()){
                maxDamage = getUnit(aiTeam,i).getDamage();
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
            if(getUnit(humanTeam,i).getDamage() > maxDamage && getUnit(humanTeam,i).getIsAlive()){
                maxDamage = getUnit(humanTeam,i).getDamage();
                defendingUnit = i;
            }
        }

        return defendingUnit;
    }

    //Return certain unit
    private Unit getUnit(Team team, int unitNum){
        return team.getUnits().get(unitNum);
    }

    //After battle logic
    private void results(){
        ConsoleInteraction.results(humanTeam);
    }
}
