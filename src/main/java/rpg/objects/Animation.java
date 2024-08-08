package rpg.objects;

import java.awt.image.BufferedImage;

public class Animation {
    public boolean looping;
    public BufferedImage[] frames;
    public int speed;

    public Animation(BufferedImage[] frames, int speed,boolean looping) {
        this.frames = frames;
        this.speed = speed;
        this.looping = looping;
    }
    public Animation() {
    }

}
