package task;

public class ArmorDestroyer extends BattleUnitBase{

    public ArmorDestroyer(String name, int maxHealth, int baseStrength, int maxArmor) {
        super(name, maxHealth, baseStrength, maxArmor);
    }

    public void specialAbility(BattleUnit[] ownTeam, BattleUnit[] enemyTeam){
        GameSimulation.logs += name() + " использует спецспособность\n";

        int maxArmor = -1;
        BattleUnit unit = null;
        for(int i = 0; i < enemyTeam.length; i++){
            if(enemyTeam[i].maxArmor() > maxArmor && enemyTeam[i].health() > 0){
                maxArmor = enemyTeam[i].maxArmor();
                unit = enemyTeam[i];
            }
        }
        if(unit.armor() > 0)
            unit.damageArmor(strength()*2);
        else
            unit.takeDamage(strength()/4);
    }

    public void attack(BattleUnit other){
        GameSimulation.logs += name() + " атакует " + other.name() + "\n";
        GameSimulation.logs += other.name() + " получает ";
        if(other.armor() > 0){
            other.damageArmor(strength());
            GameSimulation.logs += " и ";
            if(strength()/4 > 0)
                other.takeDamage(strength()/4);
            else
                other.takeDamage(1);
        }
        else {
            GameSimulation.logs += "0 урона броне и ";
            if(strength()/2 > 0)
                other.takeDamage(strength()/2);
            else
                other.takeDamage(1);
        }
        GameSimulation.logs += ".\n";
    }
}
