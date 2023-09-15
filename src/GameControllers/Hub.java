package GameControllers;

import GameProcess.Command;
import StaticClasses.HubInterface;
import Teams.HumanTeam;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Scanner;

import static Enums.Commands.*;

public class Hub {

    private static final String upgradingBonusesConfig = "data/upgradingBonuses_Config.txt";

    private HumanTeam humanTeam;
    private LevelManager levelManager;
    private static int hpUpgradingBonus;
    private static int armorUpgradingBonus;
    private static int damageUpgradingBonus;

    public Hub(HumanTeam humanTeam){
        this.humanTeam = humanTeam;
        levelManager = new LevelManager();
        setUpgradingBonuses();
    }

    private void setUpgradingBonuses() {
        Scanner sc = null;
        try {
            sc = new Scanner(new InputStreamReader(new FileInputStream(upgradingBonusesConfig)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        hpUpgradingBonus = Integer.parseInt(sc.nextLine().split(":")[1]);
        armorUpgradingBonus = Integer.parseInt(sc.nextLine().split(":")[1]);
        damageUpgradingBonus = Integer.parseInt(sc.nextLine().split(":")[1]);
    }

    public void homeLocation(){
        Command command = new Command(InvalidCommand,null);
        while(command.getCommand() != FinishGame) {

            HubInterface.drawHomeLocation(humanTeam,levelManager.getLevels());
            command = HubInterface.commandProcessing(humanTeam);

            switch (command.getCommand()){
                case LoadLevel:
                    System.out.println("Уровень " + command.getValues()[0]);
                    levelManager.LoadLevel(command.getValues()[0], humanTeam);
                    break;
                case PutEquipment:
                    humanTeam.getEquipment().get(command.getValues()[0]-1).attach(
                            humanTeam.getUnits().get(command.getValues()[1]-1), humanTeam);
                    humanTeam.getEquipment().remove(command.getValues()[0]-1);
                    break;
                case UseUpgradingPoints:
                    switch(command.getUnitStats()){
                        case Attack -> humanTeam.getUnits().get(command.getValues()[0]-1).increaseDamage(damageUpgradingBonus);
                        case Armor -> humanTeam.getUnits().get(command.getValues()[0]-1).increaseArmor(armorUpgradingBonus);
                        case HP -> humanTeam.getUnits().get(command.getValues()[0]-1).increaseHp(hpUpgradingBonus);
                    }
                    humanTeam.addUpgradingPoints(-1);
            }
        }
    }

    public static int getArmorUpgradingBonus() {
        return armorUpgradingBonus;
    }

    public static int getDamageUpgradingBonus() {
        return damageUpgradingBonus;
    }

    public static int getHpUpgradingBonus() {
        return hpUpgradingBonus;
    }
}
