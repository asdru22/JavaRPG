package RPG.Math;

public class Vector2D {
    public int x;
    public int y;

    // Constructor
    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Add operation
    public void add(Vector2D other) {
        this.x += other.x;
        this.y += other.y;
    }

    // Subtract operation
    public void substract(Vector2D other) {
        this.x -= other.x;
        this.y -= other.y;
    }

    // Multiply operation
    public void multiply(int scalar) {
        this.x *= scalar;
        this.y *= scalar;
    }

    // Divide operation
    public void divide(int scalar) {
        if (scalar == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        this.x /= scalar;
        this.y /= scalar;
    }

    // Overriding toString method for better readability
    @Override
    public String toString() {
        return String.format("(%d, %d)",x,y);
    }
}
