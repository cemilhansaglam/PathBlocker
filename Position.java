public class Position {
    private int x, y; // Coordinates of the position

    // Constructor to initialize the position with x and y values
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Method to get the x-coordinate
    public int getX() {
        return x;
    }

    // Method to get the y-coordinate
    public int getY() {
        return y;
    }

    // Override equals method to compare two Position objects
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // If both objects are the same
        if (obj == null || getClass() != obj.getClass()) return false; // Check if obj is null or a different class
        Position position = (Position) obj; // Cast obj to Position
        return x == position.x && y == position.y; // Compare x and y values
    }

    // Override hashCode method to generate a unique hash based on x and y
    @Override
    public int hashCode() {
        return 17 * x + 13 * y; // Return a unique hash value based on x and y
    }
}
