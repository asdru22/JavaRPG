package rpg.game;

import rpg.objects.Base;
import rpg.objects.Player;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Base> objects = new ArrayList<>();

    public Game(){
        objects.add(new Player(100,100,4));
    }


    public void mainLoop(){
        update();

        GamePanel.instance.repaint();
    }

    public void update(){
        for(Base b : objects){
            b.update();
        }
    }

    public List<Base> getObjects(){
        return objects;
    }

}
