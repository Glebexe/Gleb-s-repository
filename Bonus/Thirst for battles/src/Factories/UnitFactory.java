package Factories;

import Basic_classes.Unit;
import Enums.UnitType;
import Units.Archer;
import Units.Warrior;
import Units.Wizard;

public class UnitFactory {

    public Unit createUnit(UnitType type, int hpBonus, int armorBonus, int damageBonus) {
        Unit unit = null;

        switch (type) {
            case Warrior:
                unit = new Warrior(250+hpBonus,20+armorBonus,20+damageBonus,"Воин");
                break;
            case Archer:
                unit = new Archer(150+hpBonus,10+armorBonus,40+damageBonus,"Лучник");
                break;
            case Wizard:
                unit = new Wizard(100+hpBonus,0+armorBonus,70+damageBonus,"Волшебник");
                break;
        }

        return unit;
    }
}
