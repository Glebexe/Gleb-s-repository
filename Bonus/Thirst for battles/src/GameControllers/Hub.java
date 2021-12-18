package GameControllers;

import GameProcess.Command;
import StaticClasses.HubInterface;
import Teams.HumanTeam;

import java.util.Scanner;

import static Enums.Commands.*;

public class Hub {

    private HumanTeam humanTeam;
    private LevelManager levelManager;
    private int upgradingBonus;

    public Hub(HumanTeam humanTeam){
        this.humanTeam = humanTeam;
        levelManager = new LevelManager();
        upgradingBonus = 5;
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
                    humanTeam.getUnits().get(command.getValues()[1]-1).setEquipment(
                            humanTeam.getEquipment().get(command.getValues()[0]-1),humanTeam);
                    humanTeam.getEquipment().remove(command.getValues()[0]-1);
                    break;
                case UseUpgradingPoints:
                    switch(command.getUnitStats()){
                        case Attack -> humanTeam.getUnits().get(command.getValues()[0]-1).increaseDamage(upgradingBonus);
                        case Armor -> humanTeam.getUnits().get(command.getValues()[0]-1).increaseArmor(upgradingBonus);
                        case HP -> humanTeam.getUnits().get(command.getValues()[0]-1).increaseHp(upgradingBonus);
                    }
                    humanTeam.addUpgradingPoints(-1);
            }
        }
    }
}
