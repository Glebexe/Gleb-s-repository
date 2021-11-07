package Basic_classes;

public class Spell {
    protected String name;
    protected int cooldown;
    public Spell(String name, int cooldown){
        this.name = name;
        this.cooldown = cooldown;
    }

    public String getName() {
        return name;
    }
}
