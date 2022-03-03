package task;

public class Infantryman extends BattleUnitBase{
    public Infantryman(String name, int maxHealth, int baseStrength, int maxArmor) {
        super(name, maxHealth, baseStrength, maxArmor);
    }

    public void specialAbility(BattleUnit[] ownTeam, BattleUnit[] enemyTeam){
        GameSimulation.logs += name() + " использует спецспособность\n";
    }

    public void attack(BattleUnit other){
        GameSimulation.logs += name() + " атакует " + other.name() + "\n";
        GameSimulation.logs += other.name() + " получает ";
        if(other.armor() > 0){
            if(strength()/4 > 0)
                other.damageArmor(strength()/4);
            else
                other.damageArmor(1);
            GameSimulation.logs += " и ";
            if(strength()/2 > 0)
                other.takeDamage(strength()/2);
            else
                other.takeDamage(1);
        }
        else {
            GameSimulation.logs += "0 урона броне и ";
            other.takeDamage(strength());
        }
        GameSimulation.logs += ".\n";
    }
}
