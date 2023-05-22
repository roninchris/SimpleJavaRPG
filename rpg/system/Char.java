package gbf.rpg.system;

import gbf.rpg.items.Weapon;

public class Char {
    private String name;
    private int skill;
    private int defense;
    private int life;

    private Weapon weapon = Weapon.FISTS;

    public Char(String name, int skill, int defense, int life) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be blank!");
        }

        if (skill <= 0) {
            throw new IllegalArgumentException("Invalid skill: " + skill);
        }

        if (defense <= 0) {
            throw new IllegalArgumentException("Invalid defense: " + skill);
        }

        if (life <= 0) {
            life = 0;
        }

        this.name = name;

        this.skill = skill;
        this.defense = defense;
        this.life = life;
    }

    public Char(int skill, int defense, int life) {
        this("John Doe", skill, defense, life);
    }

    public static Char createGoblin() {
        String name =
            DiceRoll.roll("Spitz", "Fuling", "Fenrir", "Dandar") + " " +
            DiceRoll.roll("The Weak", "Baggins", "The Ugly", "Son of Arathorn");
        int skill = new DiceRoll(1, 20, 9).roll();
        int defense = new DiceRoll(1, 8, 3).roll();
        int life = new DiceRoll(2, 10, 30).roll();

        return new Char(name, skill, defense, life);
    }

    public String getName() {
        return name;
    }

    public int getSkill() {
        return skill;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        if (defense <= 0) {
            throw new IllegalArgumentException("Invalid defense: " + skill);
        }

        this.defense = defense;
    }

    public int getLife() {
        return life;
    }

    public void attack(Char enemy) {
        if (enemy == null) {
            throw new IllegalArgumentException("You must provide an enemy!");
        }
        if (getLife() <= 0) {
            throw new IllegalStateException("Cannot attack while dead.");
        }

        String weaponName = weapon.getName();

        System.out.printf("%s attacks %s with %s: ", name, enemy.name, weaponName);

        int roll = new DiceRoll(3, 6).roll();

        int goal = skill - enemy.defense;
        if (roll <= goal) {
            System.out.println("HIT!");
            enemy.takeDamage(weapon.roll());
        } else {
            System.out.println("MISS!");
        }
    }
    public void takeDamage(int damage) {
        if (damage < 0) damage = 0;

        life = life - damage;
        if (life < 0) {
            life = 0;
        }

        System.out.printf("%s took %d damage. Life: %d%n",
                name, damage, life);
    }

    public boolean isAlive() {
        return life > 0;
    }

    /**
     * @return a arma do personagem. Caso ele esteja desarmado, retorna os punhos.
     * @see Weapon#FISTS
     */
    public Weapon getWeapon() {
        return weapon;
    }

    /**
     * Substitui a arma do personagem.
     * @param weapon A nova arma. Nulo se estiver desarmado.
     *               Neste caso, a arma será substituida pelos punhos.
     * @see Weapon#FISTS
     */
    public void setWeapon(Weapon weapon) {
        if (weapon == null) weapon = Weapon.FISTS;
        this.weapon = weapon;
    }
}
