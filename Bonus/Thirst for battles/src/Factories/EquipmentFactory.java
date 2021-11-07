package Factories;

import Basic_classes.Equipment;
import Equipment.Shield;
import Equipment.Sword;

public class EquipmentFactory {
    public Equipment createEquipment(EquipmentType type) {
        Equipment equipment = null;

        switch (type) {
            case Sword:
                equipment = new Sword("Экскалибур", 5);
                break;
            case Shield:
                equipment = new Shield("Ярило",7);
                break;
        }

        return equipment;
    }
}
