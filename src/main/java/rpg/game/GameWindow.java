package rpg.game;

import javax.swing.*;

public class GameWindow extends JFrame {
    private final boolean RESIZABLE = false;
    private final String TITLE = "2D Adventure";
    
    public GameWindow(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(RESIZABLE);
        this.setTitle(TITLE);

        GamePanel gamePanel = new GamePanel();
        this.add(gamePanel);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
