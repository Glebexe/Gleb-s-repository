package Equipment;

import Basic_classes.Equipment;
import Basic_classes.Team;
import Basic_classes.Unit;
import Enums.EquipmentType;

public class Sword extends Equipment {

    public Sword(String name, int damage){
        super(name, damage, EquipmentType.Sword);
    }

    @Override
    public void attach(Unit unit) {
        unit.setSword(this);
    }

    @Override
    public void attach(Unit unit, Team team) {
        if(unit.getSword() != null)
            team.getEquipment().add(unit.getSword());
        unit.setSword(this);
    }
}
