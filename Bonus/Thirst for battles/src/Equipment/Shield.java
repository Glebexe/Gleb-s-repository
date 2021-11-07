package Equipment;

import Basic_classes.Equipment;

public class Shield extends Equipment {
    private int defence;
    public Shield(String name, int defence) {
        super(name);
        this.defence = defence;
    }

    public int getDefence() {
        return defence;
    }
}
