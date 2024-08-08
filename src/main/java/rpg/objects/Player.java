package rpg.objects;

import rpg.input.Keyboard;
import rpg.utils.Textures;

import java.awt.image.BufferedImage;

public class Player extends Entity {
    private int speed = 4;

    public Player(int x, int y) {
        super(x, y, 64, 40);
        this.loadAnimations();
    }

    @Override
    public void loadAnimations() {
        BufferedImage sprites = Textures.getImage("player.png");

        animations.add(State.IDLE, Animation.loadAnimation(sprites, 5, width, height, 0));
        animations.add(State.RUNNING, Animation.loadAnimation(sprites, 6, width, height, 1));
        animations.add(State.JUMPING, Animation.loadAnimation(sprites, 3, width, height, 2));
        animations.add(State.FALLING, Animation.loadAnimation(sprites, 1, width, height, 3));
        animations.add(State.LANDING, Animation.loadAnimation(sprites, 2, width, height, 4));
        animations.add(State.HURT, Animation.loadAnimation(sprites, 4, width, height, 5));
        animations.add(State.ATTACKING, Animation.loadAnimation(sprites, 3, width, height, 6));
        animations.add(State.ATTACKING2, Animation.loadAnimation(sprites, 3, width, height, 7));
        animations.add(State.ATTACKING3, Animation.loadAnimation(sprites, 3, width, height, 8));

        texture = animations.get(State.IDLE)[0];
    }

    @Override
    public void update() {
        Keyboard k = Keyboard.instance;
        if (!k.any()) {
            state = State.IDLE;
            return;
        }

        if(state == State.IDLE){
            state = State.RUNNING;
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
}
