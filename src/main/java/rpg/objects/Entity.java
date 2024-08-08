package rpg.objects;

import rpg.game.Game;
import rpg.level.Level;
import rpg.level.LevelManager;

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

    protected void initHitbox(int xOffset,int yOffset, int newWidth, int newHeight) {
        xDrawOffset = (int) (xOffset* Game.SCALE);
        yDrawOffset = (int) (yOffset* Game.SCALE);
        drawWidth = (int) (newWidth* Game.SCALE);
        drawHeight = (int) (newHeight* Game.SCALE);
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
        g.drawImage(texture, hitbox.x-xDrawOffset, hitbox.y-yDrawOffset, width,height, null);
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
}
