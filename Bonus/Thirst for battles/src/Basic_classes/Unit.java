package Basic_classes;
import Equipment.*;

public abstract class Unit {
    protected int hp;
    protected int initialHp;
    protected int armor;
    protected int initialArmor;
    protected int damage;
    protected boolean isAlive;
    protected String name;
    protected Sword sword;
    protected Shield shield;
    protected Spell spell;

    public Unit(int hp, int armor, int damage, String name){
        this.hp = hp;
        initialHp = hp;
        this.armor = armor;
        initialArmor = armor;
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

    public Shield getShield() {
        return shield;
    }

    public Sword getSword() {
        return sword;
    }

    public Spell getSpell() {
        return spell;
    }

    public void setEquipment(Equipment equipment, Team team) {
        switch (equipment.getEquipmentType()){
            case Sword:
                if(this.sword != null)
                    team.getEquipment().add(this.sword);
                this.sword = (Sword)equipment;
                break;
            case Shield:
                if(this.shield != null)
                    team.getEquipment().add(this.shield);
                this.shield = (Shield) equipment;
                break;
        }
    }

    public void reset(){
        hp = initialHp;
        armor = initialArmor;
        isAlive = true;
    }
}
