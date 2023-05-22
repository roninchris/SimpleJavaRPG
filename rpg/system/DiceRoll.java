package gbf.rpg.system;

import java.util.Random;

public class DiceRoll {
    private static final Random RND = new Random();

    private int dices;
    private int faces;
    private int bonus;

    public DiceRoll(int dices, int faces, int bonus) {
        if (dices < 0) dices = 0;
        if (faces < 1) {
            throw new IllegalArgumentException("Invalid faces: " + faces);
        }
        this.dices = dices;
        this.faces = faces;
        this.bonus = bonus;
    }

    public DiceRoll(int dices, int faces) {
        this(dices, faces, 0);
    }

    public DiceRoll(int faces) {
        this(1, faces);
    }

    public int getDices() {
        return dices;
    }

    public int getFaces() {
        return faces;
    }

    public int getBonus() {
        return bonus;
    }

    public int roll() {
        int total = 0;
        for (int i = 0; i < dices; i++) {
            total += RND.nextInt(faces) + 1;
        }
        total += bonus;
        return Math.max(total, 0);
    }

    @Override
    public String toString() {
        String saida = String.format("%dD%d", dices, faces);
        if (bonus < 0) saida += bonus;
        else if (bonus > 0) saida += "+" + bonus;
        return saida;
    }

    public static String roll(String ... strings) {
        return strings[RND.nextInt(strings.length)];
    }
}
