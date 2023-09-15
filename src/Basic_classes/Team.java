package Basic_classes;

import java.util.ArrayList;
import java.util.List;

public abstract class Team {
    protected List<Unit> units;
    protected String name;

    private List<Equipment> equipment;

    public Team(String name, List<Unit> units){
        this.name = name;
        this.units = units;
        equipment = new ArrayList<>();
    }

    public List<Unit> getUnits() {
        return units;
    }
    public String getName() {
        return name;
    }
    public List<Equipment> getEquipment(){
        return equipment;
    }

    public boolean isTeamAlive(){
        boolean check = false;
        for(Unit i: units){
            if (i.getIsAlive()){
                check = true;
                break;
            }
        }
        return check;
    }

    public void reset(){
        for(Unit i: units){
            i.reset();
        }
    }
}
