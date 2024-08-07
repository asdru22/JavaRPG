package rpg.objects;

import rpg.math.Vector2D;

import java.util.ArrayList;

public class Tile extends Base {
    TileType tileType;
    ArrayList<Tile> adjacentTiles = new ArrayList<>();
    public Tile(Vector2D pos, int width, int height,TileType tileType){
        super(pos, width, height, "tile/"+tileType.getTexture());
        this.tileType = tileType;
    }

    @Override
    public void update() {

    }
    public boolean hasCollision(){
        return tileType.hasCollision();
    }

    public void addAdjacent(Tile tile) {
        adjacentTiles.add(tile);
    }

    public ArrayList<Tile> getAdjacent() {
        return adjacentTiles;
    }
}
