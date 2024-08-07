package rpg.objects;

import rpg.game.GamePanel;
import rpg.input.KeyHandler;
import rpg.math.Vector2D;

import java.awt.*;

public class Player extends Entity {
    private final Vector2D CAMERA_POS;

    public static Player instance;

    public Player(int x, int y, int speed) {
        super(new Vector2D(x, y), "player.png", speed);
        GamePanel gp = GamePanel.instance;
        CAMERA_POS = new Vector2D(gp.getScreenWidth() / 2 - gp.getTileSize() / 2,
                gp.getScreenHeight() / 2 - gp.getTileSize() / 2);

        instance = this;
    }

    @Override
    public void update() {
        if (KeyHandler.isUp) {
            position.y += speed;
        }
        if (KeyHandler.isDown) {
            position.y -= speed;
        }
        if (KeyHandler.isLeft) {
            position.x += speed;
        }
        if (KeyHandler.isRight) {
            position.x -= speed;
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(texture, CAMERA_POS.x, CAMERA_POS.y, width * 2, height * 2, null);
    }


}
