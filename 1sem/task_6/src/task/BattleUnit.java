package task;

public class BattleUnit {
    private int strength;
    private int armor;
    private int hp;
    private int x;
    private int y;
    public BattleUnit(int strength, int armor, int hp, int x, int y){
        this.strength = strength;
        this.armor = armor;
        this.hp = hp;
        this.x = x;
        this.y = y;
    }

    public int getStrength() {
        return strength;
    }

    public int getArmor() {
        return armor;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHealth() {
        return hp;
    }

    public void attacked(BattleUnit enemy){
        if(armor > 0 && enemy.getStrength() > armor)
            hp -= (enemy.getStrength() - armor);
        else if(armor < 0)
            hp -= enemy.getStrength();
    }

    public void moveUp(){
        y--;
    }

    public void moveDown(){
        y++;
    }

    public void moveLeft(){
        x--;
    }

    public void moveRight(){
        x++;
    }

    public boolean isAlive(){
        return hp > 0;
    }
}
