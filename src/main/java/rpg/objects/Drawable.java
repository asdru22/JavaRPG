package rpg.objects;

import rpg.game.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Drawable {
    protected float x, y;
    protected int width, height;
    public BufferedImage texture;

    public Drawable(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics g) {
        g.drawImage(texture, (int) x, (int) y, (int) (width * Game.SCALE), (int) (height * Game.SCALE), null);
    }
}
