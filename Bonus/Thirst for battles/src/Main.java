import Fight.Battle;
import Fight.PreBattle;

public class Main {
    public static void main(String[] args){
        PreBattle preBattle = new PreBattle();
        Battle battle = new Battle(preBattle.getHumanTeam(), preBattle.getAiTeam());
    }
}
