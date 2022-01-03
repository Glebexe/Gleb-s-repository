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
        readFile();

        int levelNumber = Integer.parseInt(sc.nextLine().split(" ")[0]);

        for(int i = 0; i < levelNumber; i++) {
            levels.add(new Level());
        }
        sc.nextLine();
    }

    private void readFile(){
        try {
            sc = new Scanner(new InputStreamReader(new FileInputStream(levelsConfig)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void LoadLevel(int level, HumanTeam humanTeam) {
        readFile();
        sc.nextLine();
        sc.nextLine();

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
            String[] statsLine = line.split("\\(");

            UnitType unitType = getUnitType(statsLine);

            int[] unitStats = {0,0,0};
            if(statsLine.length > 1){

                if(!statsLine[1].split("equipment:")[0].replace(")","").equals(""))
                    unitStats = getUnitStats(statsLine[1].split("equipment:")[0].replace(")",""));

                if(line.split("equipment:").length > 1)
                    equipment = getEquipment(line.split("equipment:")[1]);
            }

            units.add(unitFactory.createUnit(unitType,unitStats[0], unitStats[1],unitStats[2]));
            for(Equipment equip: equipment){
                equip.attach(units.get(units.size()-1));
            }
        }

        return units;
    }

    private List<Equipment> getEquipment(String line) {
        List<Equipment> equipment = new ArrayList<>();
        Scanner equipmentInfo = new Scanner(line.replaceAll("\\)",""));
        while (equipmentInfo.hasNext()) {
            switch (equipmentInfo.next()) {
                case "Sword(bonus" -> equipment.add(
                        equipmentFactory.createEquipment(EquipmentType.Sword, Integer.parseInt(equipmentInfo.next()
                                .replace(")", "").replace(",", ""))));
                case "Sword" -> equipment.add(
                        equipmentFactory.createEquipment(EquipmentType.Sword, 0));
                case "Shield(bonus" -> equipment.add(
                        equipmentFactory.createEquipment(EquipmentType.Shield, Integer.parseInt(equipmentInfo.next()
                                .replace(")", "").replace(",", ""))));
                case "Shield" -> equipment.add(
                        equipmentFactory.createEquipment(EquipmentType.Shield, 0));

            }
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

    private UnitType getUnitType(String[] line) {
        switch (line[0]) {
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
    private void postBattleAward(HumanTeam humanTeam, int levelNumber) {
        Level level = levels.get(levelNumber-1);
        if(level.isCompleted() && !level.isAwardReceived()) {
            switch (new Random().nextInt(2)) {
                case 0 -> humanTeam.getEquipment().add(equipmentFactory.createEquipment(EquipmentType.Sword, (levelNumber-1)*2));
                case 1 -> humanTeam.getEquipment().add(equipmentFactory.createEquipment(EquipmentType.Shield, (levelNumber-1)*2));
            }
            humanTeam.addUpgradingPoints(1);
            level.setAwardIsReceived();
        }
    }

    public List<Level> getLevels() {
        return levels;
    }
}
