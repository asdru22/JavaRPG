package rpg.objects;

import rpg.input.Keyboard;

public class Player extends Entity {
    private int speed = 4;

    public Player(int x, int y) {
        super(x, y, 64, 40);
        this.loadAnimations();
    }

    @Override
    public void loadAnimations() {

        animationHandler.add(State.IDLE,10,5,0);
        animationHandler.add(State.RUNNING,10,6,1);
        animationHandler.add(State.JUMPING,10,3,2);
        animationHandler.add(State.FALLING,10,1,3);
        animationHandler.add(State.LANDING,10,2,4);
        animationHandler.add(State.HURT,10,4,5);
        animationHandler.add(State.ATTACKING,10,3,6);
        animationHandler.add(State.ATTACKING2,10,3,7);
        animationHandler.add(State.ATTACKING3,10,3,8);

        texture = animationHandler.getDefault();
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
