package Factories;

import Basic_classes.Equipment;
import Enums.EquipmentType;
import Equipment.Shield;
import Equipment.Sword;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;

public class EquipmentFactory {

    private static final String equipmentsConfig = "data/equipments_Config.txt";
    private Scanner sc;
    private Random random;

    public Equipment createEquipment(EquipmentType type, int bonus) {
        random = new Random();
        try {
            sc = new Scanner(new InputStreamReader(new FileInputStream(equipmentsConfig)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line = sc.nextLine();
        while (!line.split(":")[0].equals(type.toString())){
            line = sc.nextLine();
        }
        String[] names = line.split(":")[2].split(",");
        String name = names[random.nextInt(names.length)];
        int equipmentBonus = Integer.parseInt(line.split(":")[1].split(",")[0].split(" ")[1]);
        switch (type) {
            case Sword:
                return new Sword(name, equipmentBonus + bonus);
            case Shield:
                return new Shield(name, equipmentBonus + bonus);
        }
        return null;
    }
}
