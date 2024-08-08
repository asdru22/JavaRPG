package rpg.objects;

import rpg.input.Keyboard;

import java.awt.event.MouseEvent;
import java.util.Objects;

public class Player extends Entity {
    private int speed = 4;

    public static Player instance;

    public Player(int x, int y) {
        super(x, y, 64, 40, "player.png");
        instance = this;
    }

    @Override
    public void loadAnimations() {
        animationHandler.add("IDLE", 10, 5, 0);
        animationHandler.add("RUNNING", 10, 6, 1);
        animationHandler.add("JUMPING", 10, 3, 2);
        animationHandler.add("FALLING", 10, 1, 3);
        animationHandler.add("LANDING", 10, 2, 4);
        animationHandler.add("HURT", 10, 4, 5);
        animationHandler.add("ATTACKING", 10, 3, 6,false);
        animationHandler.add("ATTACKING2", 10, 3, 7);
        animationHandler.add("ATTACKING3", 10, 3, 8);
    }

    @Override
    public void update() {
        Keyboard k = Keyboard.instance;
        if (!k.any()) {
            changeMovementState("IDLE");
            return;
        }

        if(Objects.equals(movementState, "IDLE")){
            changeMovementState("RUNNING");
        }

        if (k.up) {
            direction = Direction.UP;
            y -= speed;
        }
        if (k.down) {
            direction = Direction.DOWN;
            y += speed;
        }
        if (k.left) {
            direction = Direction.LEFT;
            x -= speed;
        }
        if (k.right) {
            direction = Direction.RIGHT;
            x += speed;
        }
    }

    public void mouseInput(MouseEvent e){
        if(e.getButton()==MouseEvent.BUTTON1){
            changeEventState("ATTACKING");
        }
    }
}
