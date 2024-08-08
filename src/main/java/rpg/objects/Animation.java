package rpg.objects;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Animation {
    private Map<State, BufferedImage[]> animations = new HashMap<>();
    private int index = 0, speed, timer = 0;
    private final Entity entity;
    public Animation(Entity e,int speed){
        this.speed = speed;
        this.entity = e;
    }

    public void tick(){
        timer++;
        if (timer >= speed) {
            timer = 0;
            BufferedImage[] frames = animations.get(entity.state);
            index = (index + 1) % (frames.length);
            entity.texture = frames[index];
        }
    }
    public static BufferedImage[] loadAnimation(BufferedImage texture,int frames,int width,int height,int textureRow){
        BufferedImage[] idleAnimations = new BufferedImage[frames];
        for(int i = 0;i< idleAnimations.length;i++){
            idleAnimations[i] = texture.getSubimage(i*width,textureRow*height,width,height);
        }
        return idleAnimations;
    }

    public BufferedImage[] get(State state){
        return animations.get(state);
    }

    public void add(State state, BufferedImage[] frames){
        animations.put(state,frames);
    }
    public void reset(){
        timer = 0;
        entity.texture = get(entity.state)[0];
    }

    @Override
    public String toString(){
        return "State: "+entity.state+" Index: "+index+", Speed: "+speed+", Timer: "+timer;
    }
}
