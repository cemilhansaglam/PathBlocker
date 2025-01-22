import java.util.*;

public class BreadthFirstSearch extends Solver {
    private Player player; // The player object used for solving the board

    // Constructor to initialize the BFS solver with the player
    public BreadthFirstSearch(Player player) {
        this.player = player; // Set the player
    }

    // Method to solve the board using BFS
    @Override
    public List<Board> solve(Board board) {
        solution = bfs(board); // Call the bfs method to find the solution
        if (hasWon) {
            return solution; // If the goal is reached, return the solution path
        }
        return null; // Return null if no solution is found
    }

    // Method to perform BFS and return the path to the solution
    public List<Board> bfs(Board board) {
        Queue<Position> queue = new LinkedList<>(); // Queue to store positions
        Queue<Board> boardQueue = new LinkedList<>(); // Queue to store board states
        Queue<List<Board>> pathQueue = new LinkedList<>(); // Queue to store paths

        Set<String> visited = new HashSet<>(); // Set to track visited states

        // Add the player's starting position to the queue
        queue.add(player.getPosition());
        boardQueue.add(board);

        // Initialize the path with the starting board
        List<Board> initialPath = new ArrayList<>();
        initialPath.add(board);
        pathQueue.add(initialPath);

        // Mark the starting position as visited
        visited.add(generateStateHash(player.getPosition(), board));

        // Continue while there are positions to explore
        while (!queue.isEmpty()) {
            Position current = queue.poll(); // Get the next position
            Board currBoard = boardQueue.poll(); // Get the corresponding board state
            List<Board> currentPath = pathQueue.poll(); // Get the current path

            // Check if the goal has been reached
            if (currBoard.isGoalReached(current)) {
                hasWon = true; // Set the win flag
                return currentPath; // Return the path to the goal
            }

            // Try moving in all directions (UP, DOWN, LEFT, RIGHT)
            Directions[] directions = Directions.values();
            for (Directions direction : directions) {
                Player newPlayer = new Player(current); // Create a new player at the current position
                Position newPosition = newPlayer.moveDirection(currBoard, direction); // Get the new position

                // Check if the new position is valid and can be moved to
                if (newPosition != null && currBoard.canMove(newPosition)) {
                    Board newBoard = currBoard.copyAsNewBoard(); // Copy the current board
                    newPlayer.move(newBoard, direction); // Move the player on the new board

                    // Create a unique state identifier for the new position and board
                    String newStateHash = generateStateHash(newPosition, newBoard);

                    // If the new state has not been visited, add it to the queues
                    if (!visited.contains(newStateHash)) {
                        queue.add(newPosition); // Add the new position to the queue
                        boardQueue.add(newBoard); // Add the new board state to the queue

                        // Create a new path list with the current board state
                        List<Board> newPath = new ArrayList<>(currentPath);
                        newPath.add(newBoard); // Add the new board to the path
                        pathQueue.add(newPath); // Add the new path to the path queue

                        visited.add(newStateHash); // Mark the new state as visited

                        // Check if the goal has been reached after the move
                        if (newBoard.isGoalReached(newPosition)) {
                            return newPath; // Return the path to the goal
                        }
                    }
                }
            }
        }

        return new ArrayList<>(); // Return an empty list if no solution is found
    }

    // Method to generate a unique hash for the current board state and position
    private String generateStateHash(Position position, Board board) {
        return position.toString() + Arrays.deepToString(board.getGrid()); // Combine position and grid as a string
    }
}
