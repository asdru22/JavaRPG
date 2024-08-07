package rpg.objects;

public enum TileType {
    PATH("path.png", false),
    WATER("water.png", true),
    GRASS("grass.png", false),
    ROCK("rock.png", true);

    private final String texture;
    private final boolean hasCollision;

    TileType(String texture, boolean hasCollision) {
        this.texture = texture;
        this.hasCollision = hasCollision;
    }
    public boolean hasCollision() {
        return hasCollision;
    }
    public String getTexture() {
        return texture;
    }

    public static TileType fromInt(int value) {
        return switch (value) {
            case 0 -> PATH;
            case 1 -> WATER;
            case 2 -> ROCK;
            case 3 -> GRASS;
            default -> throw new IllegalArgumentException("Invalid tile value: " + value);
        };
    }

}
