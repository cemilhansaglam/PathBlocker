public class Board {
    private char[][] grid; // This 2D array represents the board grid
    private Position goalPosition; // This is the position of the goal ('G')
    private Position startPosition; // This is the position where the player starts ('P')

    // Constructor to initialize the board with a grid, goal position, and start position
    public Board(char[][] grid, Position goalPosition, Position startPosition) {
        this.grid = grid; // The board grid is set
        this.goalPosition = goalPosition; // The goal position is set
        this.startPosition = startPosition; // The player's start position is set
    }

    // Method to return the current grid (board layout)
    public char[][] getGrid() {
        return grid;
    }

    // Method to get the position of the goal ('G')
    public Position getGoalPosition() {
        return goalPosition;
    }

    // Method to get the starting position of the player ('P')
    public Position getStartPosition() {
        return startPosition;
    }

    // Method to check if the player has reached the goal position
    public boolean isGoalReached(Position playerPosition) {
        return playerPosition.equals(goalPosition); // Returns true if player's position matches goal
    }

    // Method to create a copy of the grid (2D array) to avoid modifying the original grid
    private char[][] copyGrid(char[][] source) {
        char[][] copy = new char[source.length][source[0].length]; // Create an empty copy
        for (int i = 0; i < source.length; i++) {
            System.arraycopy(source[i], 0, copy[i], 0, source[i].length); // Copy each row
        }
        return copy; // Return the copied grid
    }

    // Method to clear a position on the grid, marking it as empty ('.')
    public void clearPosition(int x, int y) {
        grid[x][y] = '.'; // Set the position to empty
    }

    // Method to update the player's position on the grid
    public void updatePlayerPosition(Position oldPosition, Position newPosition) {
        grid[oldPosition.getX()][oldPosition.getY()] = '#';  // Mark the old position as visited ('#')
        grid[newPosition.getX()][newPosition.getY()] = 'P';  // Mark the new position with the player ('P')
    }

    // Method to remove an obstacle (mark a position as empty) on the grid
    public void removeObstacle(int x, int y) {
        grid[x][y] = '.';  // Remove obstacle by setting position to empty ('.')
    }

    // Method to check if the player can move to a new position on the grid
    public boolean canMove(Position newPosition) {
        // Check if the new position is within the bounds of the grid
        if (newPosition.getX() < 0 || newPosition.getX() >= grid.length ||
                newPosition.getY() < 0 || newPosition.getY() >= grid[0].length) {
            return false; // Return false if out of bounds
        }
        // Return true if the new position is not an obstacle ('#')
        return grid[newPosition.getX()][newPosition.getY()] != '#';
    }

    // Method to print the current state of the grid (board) to the console
    public void print() {
        for (char[] row : grid) {
            System.out.println(row); // Print each row of the grid
        }
        System.out.println(); // Add an empty line after printing the grid
    }

    // Method to place an obstacle at a specific position on the grid
    public void placeObstacle(int x, int y) {
        grid[x][y] = '#'; // Mark the position as an obstacle ('#')
    }

    // Method to create a new copy of the current board, including grid and positions
    public Board copyAsNewBoard() {
        char[][] newGrid = copyGrid(this.grid); // Create a copy of the grid
        // Create a new Board with the copied grid, goal, and start positions
        Board newBoard = new Board(newGrid, new Position(goalPosition.getX(), goalPosition.getY()),
                new Position(startPosition.getX(), startPosition.getY()));
        return newBoard; // Return the new board
    }
}
