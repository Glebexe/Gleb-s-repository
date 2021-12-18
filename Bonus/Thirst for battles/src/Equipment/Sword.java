package Equipment;

import Basic_classes.Equipment;
import Enums.EquipmentType;

public class Sword extends Equipment {

    public Sword(String name, int damage){
        super(name, damage, EquipmentType.Sword);
    }
}
