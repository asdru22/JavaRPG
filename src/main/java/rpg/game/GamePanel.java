package rpg.game;

import rpg.input.Keyboard;
import rpg.input.Mouse;
import rpg.level.LevelManager;
import rpg.objects.Player;

import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel {
    private Mouse mouseInputs = new Mouse();
    private LevelManager levelManager = new LevelManager();
    private Player p = new Player( 128, 128);


    public GamePanel() {
        this.addKeyListener(new Keyboard());
        this.addMouseListener(mouseInputs);
        this.addMouseMotionListener(mouseInputs);
        this.setPanelSize();
    }

    private void setPanelSize() {
        Dimension size = new Dimension(Game.GAME_WIDTH, Game.GAME_HEIGHT);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        levelManager.draw(g);
        p.draw(g);
    }

    public void update() {
        p.update();
    }
}
