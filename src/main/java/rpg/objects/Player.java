package rpg.objects;

import rpg.game.GamePanel;
import rpg.input.KeyHandler;
import rpg.math.Vector2D;

public class Player extends Entity{
    public Player(int x, int y,int speed){
        super(new Vector2D(x,y), "player.png",speed);
    }

    @Override
    public void update() {
        if(KeyHandler.isUp){
            position.y -= speed;
        }
        if(KeyHandler.isDown){
            position.y += speed;
        }
        if(KeyHandler.isLeft){
            position.x -= speed;
        }
        if(KeyHandler.isRight){
            position.x += speed;
        }
    }

}
