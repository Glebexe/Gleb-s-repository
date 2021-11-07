package Factories;

import Basic_classes.Unit;
import Equipment.Shield;
import Equipment.Sword;
import Units.Archer;
import Units.Warrior;
import Units.Wizard;

public class UnitFactory {
    public Unit createUnit(UnitType type) {
        Unit unit = null;

        switch (type) {
            case Warrior:
                unit = new Warrior(250,20,20,"Воин");
                unit.sword = new Sword("Экскалибур", 5);
                break;
            case Archer:
                unit = new Archer(150,10,40,"Лучник");
                unit.shield = new Shield("Ярило",7);
                break;
            case Wizard:
                unit = new Wizard(100,0,70,"Волшебник");
                break;
        }

        return unit;
    }
}
