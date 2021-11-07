package Basic_classes;

import java.util.List;

public abstract class Team {
    protected List<Unit> units;
    protected String name;

    public Team(String name, List<Unit> units){
        this.name = name;
        this.units = units;
    }

    public List<Unit> getUnits() {
        return units;
    }

    public String getName() {
        return name;
    }

    public boolean isTeamAlive(){
        boolean check = false;
        for(Unit i: units){
            if (i.isAlive){
                check = true;
                break;
            }
        }
        return check;
    }
}
