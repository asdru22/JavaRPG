package rpg.level;

import rpg.utils.Textures;

public class Level {
    private int[][] lvlData;
    public Level(String name) {
        lvlData = Textures.getLevelData(name);
    }

    public int getDataAt(int x,int y){
        return lvlData[x][y];
    }
    public int[][] getLvlData(){
        return lvlData;
    }
}
