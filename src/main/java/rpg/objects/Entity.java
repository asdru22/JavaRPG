package rpg.objects;

import java.awt.*;

public class Entity extends Drawable {
    protected Animation animations;
    protected Direction direction = Direction.RIGHT;
    protected State state = State.IDLE;
    private State previous = state;

    public Entity(int x, int y, int width, int height) {
        super(x, y, width, height);
        animations = new Animation(this,10);
    }

    public void loadAnimations() {
    }

    @Override
    public void draw(Graphics g) {
        if(state!=previous) changedState();
        animations.tick();
        g.drawImage(texture, x, y, width * SCALE, height * SCALE, null);
    }
    public void update(){}

    private void changedState(){
        previous = state;
        animations.reset();
    }

}
