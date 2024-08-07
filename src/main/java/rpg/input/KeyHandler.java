package rpg.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public static boolean isUp = false, isDown = false, isLeft = false, isRight = false;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code==KeyEvent.VK_W) isUp = true;
        if(code==KeyEvent.VK_S) isDown = true;
        if(code==KeyEvent.VK_A) isLeft = true;
        if(code==KeyEvent.VK_D) isRight = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code==KeyEvent.VK_W) isUp = false;
        if(code==KeyEvent.VK_S) isDown = false;
        if(code==KeyEvent.VK_A) isLeft = false;
        if(code==KeyEvent.VK_D) isRight = false;
    }
}
