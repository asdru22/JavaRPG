package rpg.game;

import rpg.input.Keyboard;
import rpg.input.Mouse;
import rpg.objects.Player;

import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel {
    private final int WIDTH = 1280,HEIGHT = 800,SCALE = 2;
    private Mouse mouseInputs = new Mouse();
    Player p = new Player(0,0);
    public GamePanel(){
        this.addKeyListener(new Keyboard());
        this.addMouseListener(mouseInputs);
        this.addMouseMotionListener(mouseInputs);
        this.setPanelSize();
    }

    private void setPanelSize(){
        Dimension size = new Dimension(WIDTH/SCALE,HEIGHT/SCALE);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        p.draw(g);
    }

    public void update(){
        p.update();
    }
}
