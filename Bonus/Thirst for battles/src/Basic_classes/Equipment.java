package Basic_classes;

import Enums.EquipmentType;

public abstract class Equipment {

    protected String name;
    private EquipmentType equipmentType;
    private int bonus;

    public Equipment(String name, int bonus, EquipmentType equipmentType){
        this.name = name;
        this.bonus = bonus;
        this.equipmentType = equipmentType;
    }

    public String getName() {
        return name;
    }
    public int getBonus(){
        return bonus;
    }
    public EquipmentType getEquipmentType() {return equipmentType;}
}
