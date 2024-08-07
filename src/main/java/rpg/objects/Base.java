package rpg.objects;

import rpg.math.Vector2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public abstract class Base {
    Vector2D position;
    protected int width, height;
    private BufferedImage texture;

    public Base(Vector2D pos, int width, int height, String texture) {
        this.position = pos;
        this.width = width;
        this.height = height;
        System.out.println(texture);
        this.setTexture(texture);
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(texture, position.x, position.y, width, height, null);
    }

    public abstract void update();

    public void setTexture(String texturePath) {
        try {
            texture = ImageIO.read(Objects.requireNonNull(this.getClass().getResource("/textures/"+texturePath)));
        } catch (IOException e) {
            System.err.println("Image " + texturePath + " doesn't exist");
            throw new RuntimeException(e);
        }
    }

}
