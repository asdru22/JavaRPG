package rpg.objects;

import rpg.game.Game;
import rpg.game.GamePanel;
import rpg.math.Vector2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public abstract class Base {
    public Vector2D position;
    protected int width, height;
    protected BufferedImage texture;

    public Base(Vector2D pos, String texture) {
        this.position = pos;
        this.setTexture(texture);
    }

    public void draw(Graphics2D g2)
    {
        int cameraX = Player.instance.position.x + position.x;
        int cameraY = Player.instance.position.y + position.y;

        int screenWidth = GamePanel.instance.getScreenWidth();
        int screenHeight = GamePanel.instance.getScreenHeight();
        int tileSize = GamePanel.instance.getTileSize();

        if(cameraX<-tileSize) return;
        if(cameraY<-tileSize) return;
        if(cameraX>screenWidth+tileSize) return;
        if(cameraY>screenHeight+tileSize) return;

        g2.drawImage(texture, cameraX, cameraY, width*2, height*2, null);
    }

    public abstract void update();

    public void setTexture(String texturePath) {
        try {
            texture = ImageIO.read(Objects.requireNonNull(this.getClass().getResource("/textures/"+texturePath)));
            width = (int) (texture.getWidth()*1.5);
            height = (int) (texture.getWidth()*1.5);

        } catch (IOException e) {
            System.err.println("Image " + texturePath + " doesn't exist");
            throw new RuntimeException(e);
        }
    }

}
