package Equipment;

import Basic_classes.Equipment;

public class Sword extends Equipment {
    private int damage;
    public Sword(String name, int damage){
        super(name);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
