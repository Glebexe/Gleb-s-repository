package test;

import task.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;

public class FunctionalityTester {
    private String descriptorsPath;
    private StringBuilder protocol;
    private int testNum;
    private boolean allOk;

    public FunctionalityTester(String descriptorsPath) {
        this.descriptorsPath = descriptorsPath;
        protocol = new StringBuilder();
    }

    public String getProtocol() {
        return protocol.toString();
    }

    public boolean testClass(String className) {
        protocol = new StringBuilder();
        testNum = 1;
        allOk = true;
        return switch (className) {
            case "task.BattleUnit", "task.BattleUnitBase" -> true;

            //Раскомментируйте строку ниже, если сделали подзадачу 3
            case "task.Infantryman" -> testInfantryman();

            //Раскомментируйте строку ниже, если сделали подзадачу 4
            case "task.ArmorDestroyer" -> testArmorDestroyer();

            //Раскомментируйте строку ниже, если сделали подзадачу 5
            case "task.Alchemist" -> testAlchemist();

            default -> false;
        };
    }

    void writeProto(boolean testRes) {
        allOk = allOk && testRes;
        protocol.append("\tТест ").append(testNum++).append(": ").append(testRes ? "ОК\n" : "Ошибка\n");
    }


    //Раскомментируйте следующий метод, если сделали подзадачу 3

    private boolean testInfantryman() {
        Infantryman infantryman = new Infantryman("inf 1", 100, 20, 50);
        writeProto(infantryman.name().equals("inf 1"));

        writeProto(infantryman.armor() == 50);
        writeProto(infantryman.maxArmor() == 50);
        writeProto(infantryman.health() == 100);
        writeProto(infantryman.maxHealth() == 100);
        writeProto(infantryman.strength() == 20);
        writeProto(infantryman.baseStrength() == 20);

        infantryman.setMaxHealth(150);
        writeProto(infantryman.health() == 100);
        writeProto(infantryman.maxHealth() == 150);

        infantryman.setMaxHealth(80);
        writeProto(infantryman.health() == 80);
        writeProto(infantryman.maxHealth() == 80);

        infantryman.setMaxHealth(100);
        infantryman.heal(10);
        writeProto(infantryman.health() == 90);
        writeProto(infantryman.maxHealth() == 100);
        infantryman.heal(20);
        writeProto(infantryman.health() == 100);
        writeProto(infantryman.maxHealth() == 100);

        infantryman.takeDamage(20);
        writeProto(infantryman.health() == 80);
        writeProto(infantryman.maxHealth() == 100);
        infantryman.takeDamage(90);
        writeProto(infantryman.health() == 0);
        writeProto(infantryman.maxHealth() == 100);

        infantryman.setStrength(10);
        writeProto(infantryman.baseStrength() == 20);
        writeProto(infantryman.strength() == 10);
        infantryman.setStrength(30);
        writeProto(infantryman.baseStrength() == 20);
        writeProto(infantryman.strength() == 30);

        infantryman.setMaxArmor(40);
        writeProto(infantryman.maxArmor() == 40);
        writeProto(infantryman.armor() == 40);
        infantryman.setMaxArmor(50);
        writeProto(infantryman.maxArmor() == 50);
        writeProto(infantryman.armor() == 40);
        infantryman.damageArmor(30);
        writeProto(infantryman.maxArmor() == 50);
        writeProto(infantryman.armor() == 10);
        infantryman.damageArmor(20);
        writeProto(infantryman.maxArmor() == 50);
        writeProto(infantryman.armor() == 0);
        infantryman.restoreArmor(30);
        writeProto(infantryman.maxArmor() == 50);
        writeProto(infantryman.armor() == 30);
        infantryman.restoreArmor(30);
        writeProto(infantryman.maxArmor() == 50);
        writeProto(infantryman.armor() == 50);

        infantryman = new Infantryman("inf 1", 100, 20, 50);
        Infantryman enemy = new Infantryman("enemy", 100, 10, 50);
        infantryman.attack(enemy);
        writeProto(enemy.health() == 90);
        writeProto(enemy.armor() == 45);

        infantryman = new Infantryman("inf 1", 100, 11, 50);
        enemy = new Infantryman("enemy", 100, 10, 50);
        infantryman.attack(enemy);
        writeProto(enemy.health() == 95);
        writeProto(enemy.armor() == 48);

        infantryman = new Infantryman("inf 1", 100, 1, 50);
        enemy = new Infantryman("enemy", 100, 10, 50);
        infantryman.attack(enemy);
        writeProto(enemy.health() == 99);
        writeProto(enemy.armor() == 49);

        return allOk;
    }


    //Раскомментируйте следующий метод, если сделали подзадачу 4

    private boolean testArmorDestroyer() {
        ArmorDestroyer ad = new ArmorDestroyer("ad 1", 100, 20, 50);
        Infantryman enemy = new Infantryman("enemy", 100, 10, 50);
        ad.attack(enemy);
        writeProto(enemy.health() == 95);
        writeProto(enemy.armor() == 30);

        ad = new ArmorDestroyer("ad 1", 100, 11, 50);
        enemy = new Infantryman("enemy", 100, 10, 50);
        ad.attack(enemy);
        writeProto(enemy.health() == 98);
        writeProto(enemy.armor() == 39);

        ad = new ArmorDestroyer("ad 1", 100, 1, 50);
        enemy = new Infantryman("enemy", 100, 10, 50);
        ad.attack(enemy);
        writeProto(enemy.health() == 99);
        writeProto(enemy.armor() == 49);

        BattleUnit[] enemies = new BattleUnit[] {
            new Infantryman("enemy 1", 100, 10, 50),
            new Infantryman("enemy 2", 100, 10, 45),
            new Infantryman("enemy 3", 100, 10, 55)
        };

        ad = new ArmorDestroyer("ad 1", 100, 20, 50);
        BattleUnit[] ownTeam = new BattleUnit[] {
            ad
        };
        ad.specialAbility(ownTeam, enemies);
        writeProto(enemies[2].armor() == 15);
        writeProto(enemies[2].health() == 100);
        writeProto(enemies[0].armor() == 50);
        writeProto(enemies[0].health() == 100);
        writeProto(enemies[1].armor() == 45);
        writeProto(enemies[1].health() == 100);

        enemies = new BattleUnit[] {
            new Infantryman("enemy 1", 100, 10, 50),
            new Infantryman("enemy 2", 100, 10, 45),
            new Infantryman("enemy 3", 0, 10, 55)
        };

        ad = new ArmorDestroyer("ad 1", 100, 20, 50);
        ownTeam = new BattleUnit[] {
            ad
        };
        ad.specialAbility(ownTeam, enemies);
        writeProto(enemies[2].armor() == 55);
        writeProto(enemies[2].health() == 0);
        writeProto(enemies[0].armor() == 10);
        writeProto(enemies[0].health() == 100);
        writeProto(enemies[1].armor() == 45);
        writeProto(enemies[1].health() == 100);

        enemies = new BattleUnit[] {
            new Infantryman("enemy 1", 100, 10, 0),
            new Infantryman("enemy 2", 100, 10, 0)
        };

        ad = new ArmorDestroyer("ad 1", 100, 20, 50);
        ownTeam = new BattleUnit[] {
            ad
        };
        ad.specialAbility(ownTeam, enemies);
        writeProto(enemies[0].armor() == 0);
        writeProto(enemies[1].armor() == 0);
        writeProto(
            (enemies[0].health() == 95 ||
            enemies[1].health() == 95) &&
            !(enemies[0].health() == 95 &&
            enemies[1].health() == 95)
        );


        return allOk;
    }


    //Раскомментируйте следующий метод, если сделали подзадачу 5

    private boolean testAlchemist() {
        Alchemist alchemist = new Alchemist("al 1", 100, 10, 20);
        Infantryman enemy = new Infantryman("inf 1", 100, 10, 20);

        alchemist.attack(enemy);
        writeProto(enemy.strength() == 8);
        writeProto(enemy.maxHealth() == 96);
        writeProto(enemy.armor() == 20);

        enemy = new Infantryman("inf 1", 100, 1, 20);
        alchemist.attack(enemy);
        writeProto(enemy.strength() == -1);
        writeProto(enemy.maxHealth() == 96);
        writeProto(enemy.armor() == 20);

        enemy.attack(alchemist);
        writeProto(alchemist.health() == 99);

        alchemist = new Alchemist("al 1", 80, 10, 20);
        BattleUnit[] enemies = new BattleUnit[] {
            enemy
        };
        BattleUnit[] own = new BattleUnit[] {
            alchemist,
            new Infantryman("i1", 80, 10, 20),
            new Infantryman("i1", 100, 10, 20),
            new Infantryman("i1", 70, 10, 20)
        };
        own[3].takeDamage(20);
        alchemist.specialAbility(own, enemies);
        writeProto(own[0].health() == 80);
        writeProto(own[1].health() == 80);
        writeProto(own[2].health() == 100);
        writeProto(own[3].health() == 60);

        writeProto(own[0].strength() == 10);
        writeProto(own[1].strength() == 10);
        writeProto(own[2].strength() == 11);
        writeProto(own[3].strength() == 10);

        own = new BattleUnit[] {
            alchemist,
            new Infantryman("i1", 90, 10, 20),
            new Infantryman("i1", 0, 10, 20),
            new Infantryman("i1", 0, 10, 20)
        };
        own[0].takeDamage(20);
        alchemist.specialAbility(own, enemies);
        writeProto(own[0].health() == 70);
        writeProto(own[1].health() == 90);
        writeProto(own[2].health() == 0);
        writeProto(own[3].health() == 0);

        writeProto(own[0].strength() == 10);
        writeProto(own[1].strength() == 11);
        writeProto(own[2].strength() == 10);
        writeProto(own[3].strength() == 10);

        return allOk;
    }

}
