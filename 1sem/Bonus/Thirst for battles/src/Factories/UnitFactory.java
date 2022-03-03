package Factories;

import Basic_classes.Unit;
import Enums.UnitType;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Scanner;

import static Enums.UnitType.*;

public class UnitFactory {

    private static final String unitsConfig = "data/units_Config.txt";
    private Scanner sc;

    public Unit createUnit(UnitType type, int hpBonus, int armorBonus, int damageBonus) {
        try {
            sc = new Scanner(new InputStreamReader(new FileInputStream(unitsConfig)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line = sc.nextLine();
        while (!line.split(":")[0].equals(type.toString())){
            line = sc.nextLine();
        }
        String[] unitInfo = line.split(":")[1].split(",");
        return new Unit(Integer.parseInt(unitInfo[1].split(" ")[1]) + hpBonus,
                Integer.parseInt(unitInfo[2].split(" ")[1]) + armorBonus,
                Integer.parseInt(unitInfo[3].split(" ")[1]) + damageBonus,
                unitInfo[0].split(" ")[1], type);
    }
}
