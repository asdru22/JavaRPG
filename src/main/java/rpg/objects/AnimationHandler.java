package rpg.objects;

import rpg.utils.Textures;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class AnimationHandler {
    private Map<State, Animation> animations = new HashMap<>();
    private int index = 0, timer = 0;
    private final Entity entity;
    private final BufferedImage textureSheet;
    public AnimationHandler(Entity e,String path){
        this.entity = e;
        textureSheet = Textures.getImage(path);
    }

    public void tick(){
        timer++;
        if (timer >= animations.get(entity.state).speed) {
            timer = 0;
            BufferedImage[] frames = animations.get(entity.state).frames;
            index = (index + 1) % (frames.length);
            entity.texture = frames[index];
        }
    }
    public void add(State s, int speed, int frames, int textureRow){
        BufferedImage[] sprites = new BufferedImage[frames];
        for(int i = 0; i< sprites.length; i++){
            sprites[i] = textureSheet.getSubimage(i*entity.width,textureRow*entity.height,entity.width,entity.height);
        }
        animations.put(s,new Animation(sprites,speed));
    }

    public Animation get(State state){
        return animations.get(state);
    }

    public void reset(){
        timer = 0;
        entity.texture = get(entity.state).frames[0];
    }

    public BufferedImage getDefault(){
        return animations.get(State.IDLE).frames[0];
    }
}
