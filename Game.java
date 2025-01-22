import java.util.List;

public class Game {
    private Board board; // The game board representing the current level
    private Player player; // The player who will solve the board
    private String levelDirectory; // Directory where PNG files will be saved
    private List<Board> solution; // List to store the solution boards
    private PngSaver pngSaver; // Utility to save the board as PNG files

    // Constructor to initialize the game with a level file, PNG saver, and directory
    public Game(String levelFilePath, PngSaver pngSaver, String levelDirectory) {
        this.board = LevelLoader.loadLevel(levelFilePath); // Load the board from the file
        this.player = new Player(board.getStartPosition()); // Initialize the player at the start position
        this.levelDirectory = levelDirectory; // Set the directory for PNG files
        this.pngSaver = pngSaver; // Set the PNG saver to save images
    }

    // Method to solve the board using Depth-First Search (DFS)
    public void solveWithDFS() {
        DepthFirstSearch depthFirstSearch = new DepthFirstSearch(this.player); // Create a DFS solver
        solution = depthFirstSearch.solve(this.board); // Solve the board and store the solution
    }

    // Method to solve the board using Breadth-First Search (BFS)
    public void solveWithBFS() {
        BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch(this.player); // Create a BFS solver
        solution = breadthFirstSearch.bfs(board); // Solve the board and store the solution
    }

    // Method to get the player object
    public Player getPlayer() {
        return this.player; // Return the player
    }

    // Method to get the solution as a list of board states
    public List<Board> getSolution() {
        return this.solution; // Return the solution
    }

    // Method to print the solution as PNG images
    public void printSolutionAsPng() {
        int stepCount = 1; // Step counter for image filenames
        for (Board board : getSolution()) {
            pngSaver.saveBoardAsPng(board, stepCount++, levelDirectory); // Save each step as a PNG file
        }
    }
}
