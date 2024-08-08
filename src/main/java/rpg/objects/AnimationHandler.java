package rpg.objects;

import rpg.utils.Textures;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AnimationHandler {
    private Map<String, Animation> animations = new HashMap<>();
    private int frame = 0, timer = 0;
    private final Entity entity;
    private BufferedImage textureSheet;
    public Animation currentAnimation;

    public AnimationHandler(Entity e, String path) {
        this.entity = e;
        textureSheet = Textures.getImage(path);
    }

    public void tick() {
        timer++;
        if (timer >= currentAnimation.speed) {
            timer = 0;
            BufferedImage[] frames = currentAnimation.frames;
            frame += 1;
            if (frame == frames.length) {
                frame = 0;
                if (!currentAnimation.looping) {
                    entity.changeEventState("NONE");
                    return;
                }

            }
            entity.texture = frames[frame];
        }
    }

    public void add(String s, int speed, int frames, int textureRow, boolean looping) {
        BufferedImage[] sprites = new BufferedImage[frames];
        for (int i = 0; i < sprites.length; i++) {
            sprites[i] = textureSheet.getSubimage(i * entity.width, textureRow * entity.height, entity.width, entity.height);
        }
        animations.put(s, new Animation(sprites, speed, looping));
    }

    public void add(String s, int speed, int frames, int textureRow) {
        add(s, speed, frames, textureRow, true);
    }


    public Animation get(String state) {
        return animations.get(state);
    }

    public void changeAnimation() {
        frame = 0;
        timer = 0;

        if (Objects.equals(entity.eventState, "NONE")) {
            // no event state, display movement state
            currentAnimation = get(entity.movementState);
        } else {
            // display event state
            currentAnimation = get(entity.eventState);
        }
        entity.texture = currentAnimation.frames[0];
    }

    public void initialize() {
        animations.put("NONE", new Animation());
        textureSheet = null;
        entity.texture = animations.get("IDLE").frames[0];
        this.currentAnimation = animations.get(entity.movementState);
    }
}
