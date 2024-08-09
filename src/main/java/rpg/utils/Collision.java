package rpg.utils;

import rpg.game.Game;

public class Collision {

    public static boolean isSolid(float x, float y, int[][] lvlData) {
        if (x < 0 || x >= Game.GAME_WIDTH) return true;
        if (y < 0 || y >= Game.GAME_HEIGHT) return true;

        int xIndex = (int) (x / Game.TILE_SIZE);
        int yIndex = (int) (y / Game.TILE_SIZE);
        int value = lvlData[yIndex][xIndex];

        return value != 11;

    }
}
