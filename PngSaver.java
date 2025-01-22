import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class PngSaver {
    private int tileSize; // Size of each tile in pixels

    // Constructor to initialize PngSaver with a given tile size
    public PngSaver(int tileSize) {
        this.tileSize = tileSize;
    }

    // Method to save the current board state as a PNG image
    public void saveBoardAsPng(Board board, int stepNumber, String directoryName) {
        int width = board.getGrid()[0].length * tileSize; // Width of the image based on the grid size
        int height = board.getGrid().length * tileSize; // Height of the image based on the grid size

        // Create a blank image with the specified width and height
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics(); // Get the graphics context to draw

        char[][] grid = board.getGrid(); // Get the grid from the board
        // Loop through each cell in the grid
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                // Set color based on the cell content
                if (grid[i][j] == 'P') {
                    g.setColor(Color.YELLOW);  // Color the player yellow
                } else if (grid[i][j] == '#') {
                    g.setColor(new Color(53, 13, 98));  // Dark purple for walls
                } else if (grid[i][j] == 'G') {
                    // Draw a black and white checkered pattern for the goal
                    for (int y = 0; y < tileSize; y++) {
                        for (int x = 0; x < tileSize; x++) {
                            boolean isEvenSquare = ((x / (tileSize / 4)) + (y / (tileSize / 4))) % 2 == 0;
                            g.setColor(isEvenSquare ? Color.BLACK : Color.WHITE);
                            g.fillRect(j * tileSize + x, i * tileSize + y, 1, 1);
                        }
                    }
                } else {
                    g.setColor(new Color(180, 180, 250));  // Light purple for empty spaces
                }

                // Draw the colored tile (skip this for the checkered goal)
                if (grid[i][j] != 'G') {
                    g.fillRect(j * tileSize, i * tileSize, tileSize, tileSize);
                }
            }
        }

        g.dispose(); // Release the graphics context

        try {
            File dir = new File(directoryName); // Create the directory if it doesn't exist
            if (!dir.exists()) {
                dir.mkdir();
            }

            // Format the file name to include step number (e.g., 0001.png)
            String fileName = String.format("%04d.png", stepNumber);
            File outputFile = new File(directoryName + "/" + fileName);
            ImageIO.write(image, "png", outputFile); // Save the image as a PNG file
            System.out.println("Step saved as PNG: " + outputFile.getPath()); // Output the saved file path
        } catch (Exception e) {
            e.printStackTrace(); // Print any error that occurs during file saving
        }
    }
}
