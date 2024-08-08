package rpg.level;

import rpg.game.Game;
import rpg.utils.Textures;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelManager {
    private Game game;
    private Level levelOne;
    private BufferedImage[] tiles;
    public static LevelManager instance;
    public LevelManager() {
        this.game = Game.instance;
        this.tiles = Textures.getTiles("tiles_outside.png");
        levelOne = new Level("1.png");
        instance = this;
    }

    public void draw(Graphics g) {
        int index;
        for(int j = 0; j<Game.VERTICAL_TILES;j++){
            for(int i =0;i<Game.HORIZONTAL_TILES;i++){
                index = levelOne.getDataAt(j,i);
                g.drawImage(tiles[index],i*Game.TILE_SIZE,j*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE,null);
            }
        }
    }
    public Level getCurrentLevel(){
        return levelOne;
    }
}
