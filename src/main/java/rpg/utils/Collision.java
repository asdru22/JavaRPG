package rpg.utils;

import rpg.game.Game;

public class Collision {

    public static boolean isSolid(int x, int y, int[][] lvlData) {
        if (x < 0 || x >= Game.GAME_WIDTH) return true;
        if (y < 0 || y >= Game.GAME_HEIGHT) return true;

        int xIndex = x / Game.TILE_SIZE;
        int yIndex = y / Game.TILE_SIZE;
        int value = lvlData[yIndex][xIndex];

        return value != 11;

    }
}
