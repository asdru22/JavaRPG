package rpg.game;

import rpg.objects.Base;
import rpg.objects.Entity;
import rpg.objects.Grid;
import rpg.objects.Player;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Entity> entities = new ArrayList<>();
    private Grid grid;

    public Game(){
        entities.add(new Player(0,0,4));
        grid = new Grid();

    }


    public void mainLoop(){
        update();

        GamePanel.instance.repaint();
    }

    public void update(){
        for(Entity e : entities){
            e.update();
        }
    }

    public List<Entity> getEntities(){
        return entities;
    }
    public Grid getGrid(){
        return grid;
    }

}
