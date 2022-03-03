package task;

public class Alchemist extends BattleUnitBase{
    public Alchemist(String name, int maxHealth, int baseStrength, int maxArmor) {
        super(name, maxHealth, baseStrength, maxArmor);
    }

    public void specialAbility(BattleUnit[] ownTeam, BattleUnit[] enemyTeam){
        GameSimulation.logs += name() + " использует спецспособность\n";

        int minHealth = 1000000000;
        int maxHealth = 0;
        BattleUnit unitWithMinHealth = null;
        BattleUnit unitWithMaxHealth = null;

        for(int i = 0; i < ownTeam.length; i++){
            if(ownTeam[i].health() < minHealth && ownTeam[i].health() > 0){
                minHealth = ownTeam[i].health();
                unitWithMinHealth = ownTeam[i];
            }
            else if(ownTeam[i].health() > maxHealth && ownTeam[i].health() > 0){
                unitWithMaxHealth = ownTeam[i];
                maxHealth = ownTeam[i].health();
            }
        }

        unitWithMinHealth.heal(10);
        unitWithMaxHealth.setStrength(unitWithMaxHealth.strength()+1);
    }

    public void attack(BattleUnit other){
        GameSimulation.logs += other.name() + " получает ";
        other.setStrength(other.strength()-2);
        GameSimulation.logs += other.name() + " получает ";
        other.setMaxHealth(other.maxHealth()-4);
    }
}
