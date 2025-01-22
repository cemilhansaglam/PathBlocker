import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class LevelLoader {

    // Private constructor to prevent instantiation (utility class)
    // There is no need to create an instance.
    private LevelLoader() {
    }

    // Method to load a level from a file and return a Board object
    public static Board loadLevel(String filePath) {
        try {
            // Read all lines from the level file
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            int rows = lines.size(); // Get the number of rows
            int cols = lines.get(0).length(); // Get the number of columns

            // Create a grid based on the number of rows and columns
            char[][] grid = new char[rows][cols];
            Position startPosition = null; // Variable to store the player's starting position
            Position goalPosition = null;  // Variable to store the goal position

            // Loop through each row and column of the grid
            for (int i = 0; i < rows; i++) {
                grid[i] = lines.get(i).toCharArray(); // Convert the line to a char array
                for (int j = 0; j < cols; j++) {
                    // If 'P' is found, it's the player's starting position
                    if (grid[i][j] == 'P') {
                        startPosition = new Position(i, j);
                    }
                    // If 'G' is found, it's the goal position
                    else if (grid[i][j] == 'G') {
                        goalPosition = new Position(i, j);
                    }
                }
            }

            // Return a new Board object with the loaded grid, goal, and start positions
            return new Board(grid, goalPosition, startPosition);
        } catch (IOException e) {
            // If there's an error reading the file, throw a runtime exception with a message
            throw new RuntimeException("Failed to load the level file: " + filePath, e);
        }
    }
}
