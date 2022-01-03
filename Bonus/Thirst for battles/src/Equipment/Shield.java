package Equipment;

import Basic_classes.Equipment;
import Basic_classes.Team;
import Basic_classes.Unit;
import Enums.EquipmentType;

public class Shield extends Equipment {

    public Shield(String name, int defence) {
        super(name, defence, EquipmentType.Shield);
    }

    @Override
    public void attach(Unit unit) {
        unit.setShield(this);
    }

    @Override
    public void attach(Unit unit, Team team) {
        if(unit.getShield() != null)
            team.getEquipment().add(unit.getShield());
        unit.setShield(this);
    }
}
