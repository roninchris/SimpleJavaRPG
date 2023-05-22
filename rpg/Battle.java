package gbf.rpg;

import gbf.rpg.items.Weapon;
import gbf.rpg.system.Char;
import gbf.rpg.system.DiceRoll;

import java.util.HashMap;
import java.util.Map;

public class Battle {
    public static void main(String[] args) {
        Char hero = new Char(DiceRoll.roll("Lancelot", "Vane", "Percival", "Siegfried"),
                14, 5, 73);
        Map<String, Weapon> armas = new HashMap<>();
        armas.put("Lancelot", new Weapon("Heiliges Schwert", new DiceRoll(1, 4, 1)));
        armas.put("Vane", new Weapon("Raging Halberd", new DiceRoll(1, 4, 1)));
        armas.put("Siegfried", new Weapon("Ascalon", new DiceRoll(1, 4, 1)));
        armas.put("Percival", new Weapon("Lohengrin", new DiceRoll(1, 4, 1)));

        hero.setWeapon(armas.get(hero.getName()));


        Char monster = Char.createGoblin();

        System.out.println(hero.getName() + " versus " + monster.getName());


        while (hero.isAlive() && monster.isAlive()) {
            hero.attack(monster);
            if (monster.isAlive()) {
                monster.attack(hero);
            }
            System.out.println();
        }

        Char winner = hero.isAlive() ? hero : monster;
        System.out.printf("%s wins! Life: %d%n",
                winner.getName(), winner.getLife());
    }
}
