package RPG;

import RPG.Math.Vector2D;

public class Main {
    public static void main(String[] args) {
        Vector2D v = new Vector2D(1,1);
        v.add(v);
        System.out.println(v);
        v.multiply(2);
        System.out.println(v);
        v.divide(0);

    }
}