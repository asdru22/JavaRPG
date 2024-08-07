package rpg.objects;

import rpg.game.GamePanel;
import rpg.math.Vector2D;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;


public class Grid {
    private Tile[][] tiles;

    public Grid() {
        this.makeFromFile("1.txt");
        this.getAdjacentTiles();
    }

    public void draw(Graphics2D g2) {
        for (Tile[] row : tiles) {
            for (Tile t : row) {
                t.draw(g2);
            }
        }
    }

    public void makeFromFile(String fileName) {
        tiles = null;
        String resourcePath = "/maps/" + fileName;

        int tileWidth = GamePanel.instance.getTileSize(); // Example tile width

        try (InputStream is = getClass().getResourceAsStream(resourcePath);
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            ArrayList<String> lines = new ArrayList<>();
            String line;

            while ((line = br.readLine()) != null) {
                lines.add(line);
            }

            if (!lines.isEmpty()) {
                int rowCount = lines.size();
                int colCount = lines.get(0).length();
                tiles = new Tile[rowCount][colCount];

                for (int y = 0; y < rowCount; y++) {
                    String tokens = lines.get(y);

                    for (int x = 0; x < tokens.length(); x++) {
                        int tileValue = Integer.parseInt(String.valueOf(tokens.charAt(x)));
                        TileType tileType = TileType.fromInt(tileValue);
                        Tile tile = new Tile(new Vector2D(x * tileWidth, y * tileWidth), tileType);
                        tiles[y][x] = tile;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getAdjacentTiles() {
        int rows = tiles.length;
        int cols = tiles[0].length;

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                Tile currentTile = tiles[y][x];

                // Up
                if (y > 0) {
                    currentTile.addAdjacent(tiles[y - 1][x]);
                }
                // Down
                if (y < rows - 1) {
                    currentTile.addAdjacent(tiles[y + 1][x]);
                }
                // Left
                if (x > 0) {
                    currentTile.addAdjacent(tiles[y][x - 1]);
                }
                // Right
                if (x < cols - 1) {
                    currentTile.addAdjacent(tiles[y][x + 1]);
                }
            }
        }
    }
}
