package task;

public abstract class BattleUnitBase implements BattleUnit{
    private String name;
    private int maxHealth;
    private int health;
    private int baseStrength;
    private int strength;
    private int maxArmor;
    private int armor;

    public BattleUnitBase(String name, int maxHealth, int baseStrength, int maxArmor){
        this.name = name;
        this.maxHealth = maxHealth;
        health = maxHealth;
        this.baseStrength = baseStrength;
        strength = baseStrength;
        this.maxArmor = maxArmor;
        armor = maxArmor;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public int health() {
        return health;
    }

    @Override
    public int maxHealth() {
        return maxHealth;
    }

    @Override
    public void setMaxHealth(int value) {
        int maxHealthChange;
        if(value < 0) {
            maxHealthChange = maxHealth;
            maxHealth = 0;
        }else {
            maxHealthChange = value;
            maxHealth = value;
        }
        if(health > maxHealth)
            health = maxHealth;
        GameSimulation.logs += maxHealthChange +  " урона максимальному здоровью.\n";
    }

    @Override
    public void heal(int value) {
        int healthChange;
        if(health + value > maxHealth) {
            healthChange = maxHealth - health;
            health = maxHealth;
        }
        else {
            healthChange = value;
            health += value;
        }
        GameSimulation.logs += name() + "исцелился на " + healthChange + " единиц здоровья\n";
    }

    @Override
    public void takeDamage(int value) {
        int damageDealt;
        if(health - value < 0) {
            damageDealt = health;
            health = 0;
        }
        else {
            damageDealt = value;
            health -= value;
        }
        GameSimulation.logs += damageDealt + " урона здоровью";
        if(health == 0)
            GameSimulation.logs += name + " погибает.\n";
    }

    @Override
    public int strength() {
        return strength;
    }

    @Override
    public void setStrength(int value) {
        strength = value;

        if(value < 0)
            GameSimulation.logs += value + " урона силе\n";
        else
            GameSimulation.logs += value + "\n";
    }

    @Override
    public int baseStrength() {
        return baseStrength;
    }

    @Override
    public int armor() {
        return armor;
    }

    @Override
    public void restoreArmor(int value) {
        if(armor + value > maxArmor)
            armor = maxArmor;
        else
            armor += value;
    }

    @Override
    public void damageArmor(int value) {
        int damageDealt;
        if(armor - value < 0) {
            damageDealt = armor;
            armor = 0;
        }
        else {
            damageDealt = value;
            armor -= value;
        }
        GameSimulation.logs += damageDealt + " урона броне";
    }

    @Override
    public int maxArmor() {
        return maxArmor;
    }

    @Override
    public void setMaxArmor(int value) {
        maxArmor = value;
        if(armor > maxArmor)
            armor = maxArmor;
    }

    @Override
    public abstract void specialAbility(BattleUnit[] ownTeam, BattleUnit[] enemyTeam);

    @Override
    public abstract void attack(BattleUnit other);
}
