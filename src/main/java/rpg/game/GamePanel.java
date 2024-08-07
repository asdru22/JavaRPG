package rpg.game;

import rpg.input.KeyHandler;
import rpg.objects.Base;
import rpg.objects.Entity;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    private final int ORIGINAL_TILE_SIZE = 16;
    private final int SCALE = 3;
    private final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;
    private final int COLUMNS = 16;
    private final int ROWS = 12;
    private final int WIDTH = TILE_SIZE * COLUMNS;
    private final int HEIGHT = TILE_SIZE * ROWS;
    private final int FPS = 60;

    private Thread gameThread;
    private KeyHandler keyHandler = new KeyHandler();
    private Game game;
    public static GamePanel instance;

    public GamePanel() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setFocusable(true);
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);

        gameThread = new Thread(this);
        instance = this;

        game = new Game();
        gameThread.start();

    }

    @Override
    public void run() {
        Thread gameThread = GamePanel.instance.getThread();

        double drawInterval = 10e8 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                game.mainLoop();
                delta--;
                drawCount++;
            }

            if (timer >= 10e8) {
                System.out.printf("FPS: %d%n", drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // draw tiles
        game.getGrid().draw(g2);

        // draw entities
        for (Entity e : game.getEntities()) {
            e.draw(g2);

        }
        g2.dispose();
    }


    public Thread getThread() {
        return gameThread;
    }

    public int getTileSize() {
        return TILE_SIZE;
    }
}
