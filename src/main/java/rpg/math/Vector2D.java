package rpg.math;


public class Vector2D {
    public int x;
    public int y;

    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void add(Vector2D other) {
        this.x += other.x;
        this.y += other.y;
    }

    public void substract(Vector2D other) {
        this.x -= other.x;
        this.y -= other.y;
    }

    public void multiply(int scalar) {
        this.x *= scalar;
        this.y *= scalar;
    }

    public void divide(int scalar) {
        if (scalar == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        this.x /= scalar;
        this.y /= scalar;
    }
}
