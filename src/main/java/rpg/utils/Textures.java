package rpg.utils;

import rpg.game.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Textures {
    public static BufferedImage getImage(String path) {
        InputStream is = Textures.class.getResourceAsStream("/textures/" + path);
        try {
            return ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static BufferedImage[] getTiles(String level){
        BufferedImage sheet = getImage(level);
        BufferedImage[] tiles = new BufferedImage[48];
        int index;
        for(int j = 0; j<4;j++){
            for(int i =0;i<12;i++){
                index = j*12+i;
                tiles[index] = sheet.getSubimage(i*32,j*32,32,32);
            }
        }
        return tiles;
    }

    public static int[][] getLevelData(String path){
        int[][] lvlData = new int[Game.VERTICAL_TILES][Game.HORIZONTAL_TILES];
        BufferedImage img = getImage("level_data/"+path);
        Color color;
        int value;
        for(int j = 0; j<img.getWidth();j++){
            for(int i =0;i<img.getHeight();i++){
                color = new Color(img.getRGB(j,i));
                value = color.getRed();
                if(value >=48) value = 0;
                lvlData[i][j] = value;
            }
        }
        return lvlData;
    }
}
