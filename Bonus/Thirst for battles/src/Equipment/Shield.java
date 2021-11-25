package Equipment;

import Basic_classes.Equipment;
import Factories.EquipmentType;

public class Shield extends Equipment {

    private int defence;

    public Shield(String name, int defence) {
        super(name, EquipmentType.Shield);
        this.defence = defence;
    }

    public int getDefence() {
        return defence;
    }
}
