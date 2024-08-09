package rpg.objects;

import rpg.game.Game;
import rpg.input.Keyboard;

import java.awt.event.MouseEvent;

public class Player extends Entity {

    public static Player instance;

    public Player(int x, int y) {
        super((int) (x * Game.SCALE), (int) (y * Game.SCALE), 64, 40,
                "player.png", 2, -4);

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

        // Change to IDLE if no keys are pressed
        if (!k.any()) {
            changeMovementState("IDLE");
        }

        // Determine horizontal movement
        int xSpeed = 0;

        if (k.left) {
            direction = Direction.LEFT;
            xSpeed -= speed;
        }
        if (k.right) {
            direction = Direction.RIGHT;
            xSpeed += speed;
        }

        // Jump if space is pressed
        if (k.space) jump();

        // Apply gravity if in the air
        if (inAir) {
            airSpeed += gravity;
        } else if(!this.onGround()) {
            inAir = true;
        }

        // Attempt to move the player
        if (attemptMove(xSpeed, (int) airSpeed)) {
            System.out.println("YSpD "+airSpeed);
            System.out.println("Xspd "+xSpeed);

            if (!inAir) {
                changeMovementState("RUNNING");
            }
        } else if (inAir) {
            System.out.println("Hit wall");
            // Handle collision while in the air
            if (airSpeed > 0) { // Collided while falling
                System.out.println("collided when falling");
                inAir = false;
                airSpeed = 0;
            } else { // Collided while jumping upwards
                airSpeed = fallSpeedAfterCollision; // Adjust this to make the fall smoother
                System.out.println("collided when jumping");

            }
        }
    }

    private void jump() {
        System.out.println("pressed psace");
        if (!inAir) {
            inAir = true;
            airSpeed = jumpSpeed;
            changeMovementState("JUMPING");

        }
    }

    public void mouseInput(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            changeEventState("ATTACKING");
        }
    }


}
