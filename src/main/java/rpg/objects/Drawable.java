package rpg.objects;

import rpg.game.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Drawable {
    protected int x, y, width, height;
    public BufferedImage texture;

    public Drawable(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics g) {
        g.drawImage(texture, x, y, (int) (width * Game.SCALE), (int) (height * Game.SCALE), null);
    }
}
