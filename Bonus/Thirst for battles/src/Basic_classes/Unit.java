package Basic_classes;
import Enums.UnitType;

public class Unit {
    private int hp;
    private int initialHp;
    private int armor;
    private int initialArmor;
    private int damage;
    private boolean isAlive;
    private String name;
    private Equipment sword;
    private Equipment shield;
    private UnitType unitType;

    public Unit(int hp, int armor, int damage, String name, UnitType unitType){
        this.hp = hp;
        initialHp = hp;
        this.armor = armor;
        initialArmor = armor;
        this.damage = damage;
        this.name = name;
        this.unitType = unitType;
        isAlive = true;
    }

    public boolean getIsAlive(){
        return isAlive;
    }
    public String getName(){
        return name;
    }
    public int getFullDamage() {
        if(sword != null)
            return damage + sword.getBonus();
        return damage;
    }
    public int getHp() {
        return hp;
    }
    public int getFullArmor(){
        if(shield != null)
            return armor + shield.getBonus();
        return armor;
    }
    public int getBasicArmor() {
        return armor;
    }
    public int getBasicDamage() {
        return damage;
    }
    public Equipment getShield() {
        return shield;
    }
    public Equipment getSword() {
        return sword;
    }

    public int receiveDamage(int damage) {
        int dealtDamage = 0;
        if (hp > 0) {
            if (hp - (damage - getFullArmor()) < 0) {
                dealtDamage = hp;
                hp = 0;
            } else {
                if(damage - getFullArmor() > 0) {
                    hp -= (damage - getFullArmor());
                    dealtDamage = (damage - getFullArmor());
                }
                else{
                    dealtDamage = 0;
                }
            }
            if (hp == 0)
                isAlive = false;
        }

        return dealtDamage;
    }
    public void setShield(Equipment equipment){
        shield = equipment;
    }
    public void setSword(Equipment equipment){
        sword = equipment;
    }

    public void increaseArmor(int value) {
        armor += value;
        initialArmor = armor;
    }
    public void increaseHp(int value) {
        hp += value;
        initialHp = hp;
    }
    public void increaseDamage(int value) {
        damage += value;
    }

    public void reset(){
        hp = initialHp;
        armor = initialArmor;
        isAlive = true;
    }
}
