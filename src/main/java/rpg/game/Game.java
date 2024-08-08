package rpg.game;

public class Game implements Runnable {
    private final GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int TARGET_FPS = 60;
    private int FPS;

    public Game() {
        this.gamePanel = new GamePanel();
        this.gameWindow = new GameWindow(gamePanel);
        this.gamePanel.requestFocus();
        this.gameThread = new Thread(this);
        this.gameThread.start();

    }

    @Override
    public void run() {

        double timePerFrame = 10e8 / TARGET_FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int frames = 0;
        while (true) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / timePerFrame;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if (delta >= 1) {
                this.tick();
                delta--;
                frames++;
            }

            if (timer >= 10e8) {
                FPS = frames;
                gameWindow.setTitle(String.format("2D Game (%d FPS)",FPS));
                frames = 0;
                timer = 0;
            }
        }
    }

    private void tick() {
        gamePanel.repaint();
        gamePanel.update();
    }
}
