package rpg.game;

import java.awt.*;

public class Game {
    private final int FPS = 60;
    public Game(){
        this.mainLoop();
    }
    private void mainLoop(){
        Thread gameThread = GamePanel.instance.getThread();

        double drawInterval = 10e9/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread!=null){
            currentTime = System.nanoTime();
            delta += (currentTime-lastTime)/drawInterval;
            lastTime = currentTime;

            if(delta>=1){
                update();

                GamePanel.instance.repaint();
            }

        }
    }

    private void update(){

    }
    private void paint(Graphics g){
        GamePanel.instance.paint(g);
    }
}
