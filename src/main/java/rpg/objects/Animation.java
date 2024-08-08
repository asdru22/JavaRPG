package rpg.objects;

import java.awt.image.BufferedImage;

public class Animation {
    public BufferedImage[] frames;
    public int speed;

    public Animation(BufferedImage[] frames, int speed) {
        this.frames = frames;
        this.speed = speed;
    }
}
