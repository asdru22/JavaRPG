package rpg.objects;

import rpg.math.Vector2D;

public abstract class Entity extends Base {
    protected int speed;

    public Entity(Vector2D pos, int width, int height, String texture, int speed) {

        super(pos, width, height, texture);
        this.speed = speed;
    }
}
