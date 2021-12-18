package Equipment;

import Basic_classes.Equipment;
import Enums.EquipmentType;

public class Shield extends Equipment {

    public Shield(String name, int defence) {
        super(name, defence, EquipmentType.Shield);
    }
}
