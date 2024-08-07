package rpg.game;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    private final int ORIGINAL_TILE_SIZE = 16;
    private final int SCALE = 3;
    private final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;
    private final int COLUMNS = 16;
    private final int ROWS = 12;
    private final int WIDTH = TILE_SIZE*COLUMNS;
    private final int HEIGHT = TILE_SIZE*ROWS;
    private Thread gameThread;

    public static GamePanel instance;

    public GamePanel(){
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);

        gameThread = new Thread(this);
        gameThread.start();
        instance = this;
    }

    @Override
    public void run() {
        Game g = new Game();
    }

    public void paintComponent(Graphics g){
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.white);
        g2.fillRect(100,100, instance.TILE_SIZE, instance.TILE_SIZE);
    }

    public Thread getThread(){
        return gameThread;
    }
}
