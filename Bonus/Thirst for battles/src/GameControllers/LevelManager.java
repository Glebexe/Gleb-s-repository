package GameControllers;

import Basic_classes.Unit;
import Equipment.Shield;
import Equipment.Sword;
import Factories.EquipmentFactory;
import Factories.EquipmentType;
import Factories.UnitFactory;
import Factories.UnitType;
import GameProcess.Level;
import Teams.AITeam;
import Teams.HumanTeam;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LevelManager {

    private UnitFactory unitFactory;
    private EquipmentFactory equipmentFactory;

    private List<Level> levels = new ArrayList<>();

    public LevelManager(){
        unitFactory = new UnitFactory();
        equipmentFactory = new EquipmentFactory();

        levels.add(new Level());
    }

    public void LoadLevel(int level, HumanTeam humanTeam) {
        List<Unit> units = new ArrayList<>();
        switch(level) {
            case 1:
                units.add(unitFactory.createUnit(UnitType.Warrior,0,0,0));
                units.add(unitFactory.createUnit(UnitType.Warrior,0,0,0));
                units.add(unitFactory.createUnit(UnitType.Warrior,0,0,0));

                levels.get(level-1).start(humanTeam, new AITeam("Компьютер новичок", units));
        }

        postBattleAward(humanTeam,level);
    }

    private void postBattleAward(HumanTeam humanTeam, int level) {
        if(levels.get(level-1).isCompleted()) {
            switch (new Random().nextInt(2)) {
                case 0 -> humanTeam.getEquipment().add(equipmentFactory.createEquipment(EquipmentType.Sword, (level-1)*2));
                case 1 -> humanTeam.getEquipment().add(equipmentFactory.createEquipment(EquipmentType.Shield, (level-1)*2));
            }
        }
    }

    public List<Level> getLevels() {
        return levels;
    }
}
