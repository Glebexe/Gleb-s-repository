package Factories;

import Basic_classes.Equipment;
import Equipment.Shield;
import Equipment.Sword;

public class EquipmentFactory {
    public Equipment createEquipment(EquipmentType type, int bonus) {
        Equipment equipment = null;

        switch (type) {
            case Sword:
                equipment = new Sword("Экскалибур", 5+bonus);
                break;
            case Shield:
                equipment = new Shield("Ярило",7+bonus);
                break;
        }

        return equipment;
    }
}
