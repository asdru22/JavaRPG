package rpg.objects;

import java.awt.*;

public class Entity extends Drawable {
    protected AnimationHandler animationHandler;
    protected Direction direction = Direction.RIGHT;
    protected State state = State.IDLE;
    private State previous = state;

    public Entity(int x, int y, int width, int height,String path) {
        super(x, y, width, height);
        animationHandler = new AnimationHandler(this,path);
        this.loadAnimations();
        this.animationHandler.initialize();
    }

    public void loadAnimations() {
    }

    @Override
    public void draw(Graphics g) {
        if(state!=previous) changedState();
        animationHandler.tick();
        g.drawImage(texture, x, y, width * SCALE, height * SCALE, null);
    }
    public void update(){}

    private void changedState(){
        previous = state;
        animationHandler.reset();
    }

}
