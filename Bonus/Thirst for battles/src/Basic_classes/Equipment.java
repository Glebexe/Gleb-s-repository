package Basic_classes;

import Factories.EquipmentType;

public class Equipment {

    protected String name;
    private EquipmentType equipmentType;

    public Equipment(String name, EquipmentType equipmentType){
        this.name = name;
        this.equipmentType = equipmentType;
    }

    public String getName() {
        return name;
    }
    public EquipmentType getEquipmentType() {return equipmentType;}
}
