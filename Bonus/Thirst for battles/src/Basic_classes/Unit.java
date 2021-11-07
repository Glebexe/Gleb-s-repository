package Basic_classes;
import Equipment.*;

public abstract class Unit {
    protected int hp;
    protected int armor;
    protected int damage;
    protected boolean isAlive;
    protected String name;
    public Sword sword;
    public Shield shield;
    public Spell spell;

    public Unit(int hp, int armor, int damage, String name){
        this.hp = hp;
        this.armor = armor;
        this.damage = damage;
        this.name = name;
        isAlive = true;
    }

    public boolean getIsAlive(){
        return isAlive;
    }

    public String getName(){
        return name;
    }

    public int getDamage() {
        if(sword != null)
            return damage + sword.getDamage();
        return damage;
    }

    public int getHp() {
        return hp;
    }

    public int getArmor(){
        if(shield != null)
            return armor + shield.getDefence();
        return armor;
    }

    public int receiveDamage(int damage) {
        int dealtDamage = 0;
        int additionalArmor;
        if(shield != null)
            additionalArmor = shield.getDefence();
        else
            additionalArmor = 0;
            if (hp > 0) {
                if (hp - (damage - additionalArmor) < 0) {
                    dealtDamage = hp;
                    hp = 0;
                } else {
                    hp -= (damage - additionalArmor);
                    dealtDamage = (damage - additionalArmor);
                }
                if (hp == 0)
                    isAlive = false;
            }

        return dealtDamage;
    }
}
