package rpg.objects;

import rpg.math.Vector2D;

public abstract class Base {
    Vector2D position;
    protected int width, height;
    protected String texture;

    public Base(Vector2D pos, int width, int height, String texture){
        this.position = pos;
        this.width = width;
        this.height = height;
        this.texture = texture;
    }

    public void draw(){

    }
}
