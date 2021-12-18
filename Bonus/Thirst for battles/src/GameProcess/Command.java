package GameProcess;

import Enums.Commands;
import Enums.UnitStats;

public class Command {
    private int[] values;
    private Commands command;
    private UnitStats unitStats;

    public Command(Commands command) {
        this.command = command;
    }

    public Command(Commands command, int[] values) {
        this.command = command;
        this.values = values;
    }

    public Command(Commands command, UnitStats unitStats, int[] values) {
        this.command = command;
        this.unitStats = unitStats;
        this.values = values;
    }

    public Commands getCommand() {
        return command;
    }

    public UnitStats getUnitStats() {
        return unitStats;
    }

    public int[] getValues() {
        return values;
    }
}
