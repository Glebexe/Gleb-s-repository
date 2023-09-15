package Teams;

import Basic_classes.Team;
import Basic_classes.Unit;
import java.util.List;

public class HumanTeam extends Team {

    private int upgradingPoints;

    public HumanTeam(String name, List<Unit> units){
        super(name, units);
        upgradingPoints = 0;
    }

    public int getUpgradingPoints() {
        return upgradingPoints;
    }

    public void addUpgradingPoints(int num) {
        this.upgradingPoints += num;
    }
}
