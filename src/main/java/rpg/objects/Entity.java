package rpg.objects;

import rpg.game.Game;
import rpg.level.Level;
import rpg.level.LevelManager;
import rpg.utils.Collision;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Objects;

public class Entity extends Drawable {
    protected AnimationHandler animationHandler;
    protected Direction direction = Direction.RIGHT;
    protected String movementState = "IDLE";
    protected String eventState = "NONE";
    protected Rectangle2D.Float hitbox;
    protected int[][] lvlData;
    protected float xDrawOffset, yDrawOffset, drawWidth, drawHeight;

    // Jumping/gravity
    protected double airSpeed = 0, gravity = (0.04 * Game.SCALE), fallSpeedAfterCollision = 1.4 * Game.SCALE;
    protected int speed, jumpSpeed;
    protected boolean inAir = false;


    public Entity(float x, float y, int width, int height, String path, int speed, double jumpSpeed) {
        super(x, y, width, height);
        animationHandler = new AnimationHandler(this, path);
        this.loadAnimations();
        this.animationHandler.initialize();
        this.lvlData = LevelManager.instance.getCurrentLevel().getLvlData();
        this.speed = (int) (speed * Game.SCALE);
        this.jumpSpeed = (int) (jumpSpeed * Game.SCALE);

    }

    protected void initHitbox(int xOffset, int yOffset, int newWidth, int newHeight) {
        xDrawOffset = (int) (xOffset * Game.SCALE);
        yDrawOffset = (int) (yOffset * Game.SCALE);
        drawWidth = (int) (newWidth * Game.SCALE);
        drawHeight = (int) (newHeight * Game.SCALE);
        hitbox = new Rectangle2D.Float(x, y, drawWidth, drawHeight);
    }

    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }

    protected void drawHitbox(Graphics g) {
        g.setColor(Color.RED);
        g.drawRect((int) hitbox.x, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
    }

    public void loadAnimations() {
    }

    @Override
    public void draw(Graphics g) {
        animationHandler.tick();
        drawHitbox(g);
        g.drawImage(texture, (int) (hitbox.x - xDrawOffset), (int) (hitbox.y - yDrawOffset), width, height, null);
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

    public boolean attemptMove(float xSpeed, float ySpeed) {
        float finalX = hitbox.x;
        float finalY = hitbox.y;

        // Attempt to move horizontally
        while (xSpeed != 0) {
            float newX = finalX + xSpeed;

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
            float newY = finalY + ySpeed;

            boolean canMoveVertically = !(Collision.isSolid(finalX + hitbox.width, newY + hitbox.height, lvlData) ||
                    Collision.isSolid(finalX, newY + hitbox.height, lvlData) ||
                    Collision.isSolid(finalX + hitbox.width, newY, lvlData) ||
                    Collision.isSolid(finalX, newY, lvlData));

            if (canMoveVertically) {
                finalY = newY;
                break;
            } else {
                // Reduce speed until it reaches 0
                ySpeed = (ySpeed > 0) ? ySpeed - 0.1f : ySpeed + 0.1f;
            }
        }

        // Check if any movement occurred
        if (finalX != hitbox.x || finalY != hitbox.y) {
            hitbox.x = finalX;
            hitbox.y = finalY;
            return true;
        }

        // No movement occurred
        return false;
    }

    protected boolean onGround() {
        if (!Collision.isSolid(hitbox.x, hitbox.y + hitbox.height+1, lvlData)) {
            if (!Collision.isSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height+1, lvlData)) return false;
        }
        return true;
    }
}