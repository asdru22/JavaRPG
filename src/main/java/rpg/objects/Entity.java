package rpg.objects;

import rpg.math.Vector2D;

public abstract class Entity extends Base {
    protected int speed;

    public Entity(Vector2D pos, String texture, int speed) {

        super(pos, texture);
        this.speed = speed;
    }
}
