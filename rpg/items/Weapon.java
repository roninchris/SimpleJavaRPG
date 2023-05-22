package gbf.rpg.items;

import gbf.rpg.system.DiceRoll;

public class Weapon {
    public static final Weapon FISTS =
            new Weapon("Fists", new DiceRoll(0, 1, 1));
    private String name;
    private DiceRoll damage;

    public Weapon(String name, DiceRoll damage) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("You must provide a name!");
        }
        if (damage == null) {
            throw new IllegalArgumentException("Damage cannot be null!");
        }

        this.name = name;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public DiceRoll getDamage() {
        return damage;
    }

    public int roll() {
        return damage.roll();
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", name, damage);
    }
}
