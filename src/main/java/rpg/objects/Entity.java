package rpg.objects;

import rpg.game.Game;
import rpg.level.Level;
import rpg.level.LevelManager;
import rpg.utils.Collision;

import java.awt.*;
import java.util.Objects;

public class Entity extends Drawable {
    protected AnimationHandler animationHandler;
    protected Direction direction = Direction.RIGHT;
    protected String movementState = "IDLE";
    protected String eventState = "NONE";
    protected Rectangle hitbox;
    protected int[][] lvlData;
    protected int xDrawOffset, yDrawOffset, drawWidth, drawHeight;

    public Entity(int x, int y, int width, int height, String path) {
        super(x, y, width, height);
        animationHandler = new AnimationHandler(this, path);
        this.loadAnimations();
        this.animationHandler.initialize();
        this.lvlData = LevelManager.instance.getCurrentLevel().getLvlData();
    }

    protected void initHitbox(int xOffset, int yOffset, int newWidth, int newHeight) {
        xDrawOffset = (int) (xOffset * Game.SCALE);
        yDrawOffset = (int) (yOffset * Game.SCALE);
        drawWidth = (int) (newWidth * Game.SCALE);
        drawHeight = (int) (newHeight * Game.SCALE);
        hitbox = new Rectangle(x, y, drawWidth, drawHeight);
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    protected void drawHitbox(Graphics g) {
        g.setColor(Color.RED);
        g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }

    public void loadAnimations() {
    }

    @Override
    public void draw(Graphics g) {
        animationHandler.tick();
        drawHitbox(g);
        g.drawImage(texture, hitbox.x - xDrawOffset, hitbox.y - yDrawOffset, width, height, null);
    }

    public void update() {
    }

    public void changeEventState(String newEventState) {
        if (Objects.equals(eventState, newEventState)) return;
        eventState = newEventState;
        animationHandler.changeAnimation();
    }

    public void changeMovementState(String newMovementState) {
        if (Objects.equals(movementState, newMovementState)) return;
        movementState = newMovementState;
        animationHandler.changeAnimation();
    }

    public boolean attemptMove(int xSpeed, int ySpeed) {
        int finalX = hitbox.x;
        int finalY = hitbox.y;

        // Attempt to move horizontally
        while (xSpeed != 0) {
            int newX = finalX + xSpeed;

            boolean canMoveHorizontally = !(Collision.isSolid(newX + hitbox.width, finalY + hitbox.height, lvlData) ||
                    Collision.isSolid(newX, finalY + hitbox.height, lvlData) ||
                    Collision.isSolid(newX + hitbox.width, finalY, lvlData) ||
                    Collision.isSolid(newX, finalY, lvlData));

            if (canMoveHorizontally) {
                finalX = newX;
                break;
            } else {
                // Reduce speed until it reaches 0
                xSpeed = (xSpeed > 0) ? xSpeed - 1 : xSpeed + 1;
            }
        }

        // Attempt to move vertically
        while (ySpeed != 0) {
            int newY = finalY + ySpeed;

            boolean canMoveVertically = !(Collision.isSolid(finalX + hitbox.width, newY + hitbox.height, lvlData) ||
                    Collision.isSolid(finalX, newY + hitbox.height, lvlData) ||
                    Collision.isSolid(finalX + hitbox.width, newY, lvlData) ||
                    Collision.isSolid(finalX, newY, lvlData));

            if (canMoveVertically) {
                finalY = newY;
                break;
            } else {
                // Reduce speed until it reaches 0
                ySpeed = (ySpeed > 0) ? ySpeed - 1 : ySpeed + 1;
            }
        }

        // Check if any movement occurred
        if (finalX != hitbox.x || finalY != hitbox.y) {
            hitbox.x = finalX;
            hitbox.y = finalY;
            changeMovementState("RUNNING");
            return true;
        }

        // No movement occurred
        return false;
    }
}
