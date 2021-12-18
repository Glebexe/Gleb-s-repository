package GameControllers;

import Basic_classes.Equipment;
import Basic_classes.Unit;
import Factories.EquipmentFactory;
import Enums.EquipmentType;
import Factories.UnitFactory;
import Enums.UnitType;
import GameProcess.Level;
import Teams.AITeam;
import Teams.HumanTeam;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.*;

import static Enums.UnitType.*;

public class LevelManager {

    private static final String levelsConfig = "data/levels_Config.txt";

    private UnitFactory unitFactory;
    private EquipmentFactory equipmentFactory;
    private Scanner sc;

    private List<Level> levels = new ArrayList<>();

    public LevelManager(){
        unitFactory = new UnitFactory();
        equipmentFactory = new EquipmentFactory();

        levels.add(new Level());
        levels.add(new Level());
        levels.add(new Level());
        levels.add(new Level());
        levels.add(new Level());
        levels.add(new Level());
        levels.add(new Level());
    }

    public void LoadLevel(int level, HumanTeam humanTeam) {
        try {
            sc = new Scanner(new InputStreamReader(new FileInputStream(levelsConfig)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        levels.get(level-1).start(humanTeam, new AITeam(findAiTeamName(sc,level), formAiTeamUnits()));

        postBattleAward(humanTeam,level);
    }

    private String findAiTeamName(Scanner sc, int level){
        String line = sc.nextLine();
        while (Integer.parseInt(line.split(" ")[1]) != level) {
            sc.nextLine();
            sc.nextLine();
            sc.nextLine();
            sc.nextLine();
            sc.nextLine();
            line = sc.nextLine();
        }
        return sc.nextLine();
    }

    private List<Unit> formAiTeamUnits(){
        List<Unit> units = new ArrayList<>();
        String line;
        for(int i = 0; i < 3; i++) {
            line = sc.nextLine();
            List<Equipment> equipment = new ArrayList<>();

            UnitType unitType = getUnitType(line);

            int[] unitStats = {0,0,0};
            if(line.split("\\(").length > 1){

                unitStats = getUnitStats(line.split("\\(")[1].split("equipment:")[0].replace(")",""));

                equipment = getEquipment(line.split("equipment:")[1]);
            }

            units.add(unitFactory.createUnit(unitType,unitStats[0], unitStats[1],unitStats[2]));
            for(Equipment equip: equipment){
                units.get(units.size()-1).setEquipment(equip);
            }
        }

        return units;
    }

    private List<Equipment> getEquipment(String line) {
        List<Equipment> equipment = new ArrayList<>();
        Scanner equipmentInfo = new Scanner(line);
            switch(equipmentInfo.next()) {
                case "Sword(bonus" -> equipment.add(
                        equipmentFactory.createEquipment(EquipmentType.Sword,Integer.parseInt(equipmentInfo.next()
                                .replace(")","").replace(",",""))));
                case "Sword" -> equipment.add(
                        equipmentFactory.createEquipment(EquipmentType.Sword,0));
                case "Shield(bonus" -> equipment.add(
                        equipmentFactory.createEquipment(EquipmentType.Shield,Integer.parseInt(equipmentInfo.next()
                                .replace(")","").replace(",",""))));
                case "Shield" -> equipment.add(
                        equipmentFactory.createEquipment(EquipmentType.Shield,0));

            }

        return equipment;
    }

    private int[] getUnitStats(String line) {
        int[] unitStats = {0,0,0};
        Scanner stats = new Scanner(line);
        String currWord = stats.next();
        boolean hasNext = true;
        while (hasNext) {
            switch (currWord) {
                case "hpBonus" -> unitStats[0] = Integer.parseInt(stats.next().replace(",",""));
                case "armorBonus" -> unitStats[1] = Integer.parseInt(stats.next().replace(",",""));
                case "damageBonus" -> unitStats[2] = Integer.parseInt(stats.next().replace(",",""));
            }
            if(stats.hasNext())
                currWord = stats.next();
            else
                hasNext = false;
        }
        return unitStats;
    }

    private UnitType getUnitType(String line) {
        switch (line.split("\\(")[0]) {
            case "Warrior":
                return Warrior;
            case "Archer":
                return Archer;
            case "Wizard":
                return Wizard;
        }
        return Warrior;
    }

    //Set after battle award
    private void postBattleAward(HumanTeam humanTeam, int level) {
        if(levels.get(level-1).isCompleted()) {
            switch (new Random().nextInt(2)) {
                case 0 -> humanTeam.getEquipment().add(equipmentFactory.createEquipment(EquipmentType.Sword, (level-1)*2));
                case 1 -> humanTeam.getEquipment().add(equipmentFactory.createEquipment(EquipmentType.Shield, (level-1)*2));
            }
            humanTeam.addUpgradingPoints(1);
        }
    }

    public List<Level> getLevels() {
        return levels;
    }
}
