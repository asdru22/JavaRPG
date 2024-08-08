package rpg.objects;

import java.awt.*;
import java.util.Objects;

public class Entity extends Drawable {
    protected AnimationHandler animationHandler;
    protected Direction direction = Direction.RIGHT;
    protected String movementState = "IDLE";
    protected String eventState = "NONE";

    public Entity(int x, int y, int width, int height, String path) {
        super(x, y, width, height);
        animationHandler = new AnimationHandler(this, path);
        this.loadAnimations();
        this.animationHandler.initialize();
    }

    public void loadAnimations() {
    }

    @Override
    public void draw(Graphics g) {
        animationHandler.tick();

        g.drawImage(texture, x, y, width * SCALE, height * SCALE, null);
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
