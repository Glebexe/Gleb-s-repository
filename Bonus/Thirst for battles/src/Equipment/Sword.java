package Equipment;

import Basic_classes.Equipment;
import Factories.EquipmentType;

public class Sword extends Equipment {
    private int damage;
    public Sword(String name, int damage){
        super(name, EquipmentType.Sword);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
