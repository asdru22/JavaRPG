package rpg.objects;

import rpg.game.Game;
import rpg.input.Keyboard;
import rpg.utils.Collision;

import java.awt.event.MouseEvent;

public class Player extends Entity {
    private int speed = 2;

    public static Player instance;

    public Player(int x, int y) {
        super((int) (x * Game.SCALE), (int) (y * Game.SCALE), 64, 40, "player.png");

        instance = this;
        this.initHitbox(21, 4, 20, 28);
    }

    @Override
    public void loadAnimations() {
        animationHandler.add("IDLE", 10, 5, 0);
        animationHandler.add("RUNNING", 10, 6, 1);
        animationHandler.add("JUMPING", 10, 3, 2);
        animationHandler.add("FALLING", 10, 1, 3);
        animationHandler.add("LANDING", 10, 2, 4);
        animationHandler.add("HURT", 10, 4, 5);
        animationHandler.add("ATTACKING", 10, 3, 6, false);
        animationHandler.add("ATTACKING2", 10, 3, 7);
        animationHandler.add("ATTACKING3", 10, 3, 8);
    }

    @Override
    public void update() {
        updatePosition();
    }

    private void updatePosition() {
        Keyboard k = Keyboard.instance;
        if (!k.any()) {
            changeMovementState("IDLE");
            return;
        }

        int xSpeed = 0, ySpeed = 0;

        if (k.up) {
            direction = Direction.UP;
            ySpeed = -speed;
        }
        if (k.down) {
            direction = Direction.DOWN;
            ySpeed = speed;
        }
        if (k.left) {
            direction = Direction.LEFT;
            xSpeed = -speed;
        }
        if (k.right) {
            direction = Direction.RIGHT;
            xSpeed = speed;
        }

        if (attemptMove(xSpeed,ySpeed)) {
            changeMovementState("RUNNING");
        }
    }

    public void mouseInput(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            changeEventState("ATTACKING");
        }
    }


}
