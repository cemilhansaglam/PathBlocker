import java.util.List;
import java.util.ArrayList;

// Abstract class that provides the base for solving the game using different algorithms
public abstract class Solver {
	protected boolean hasWon = false; // Flag to check if the game is won
	protected List<Board> solution = new ArrayList<>(); // List to store the solution path (boards)

	// Abstract method to be implemented by subclasses (DFS, BFS) to solve the board
	public abstract List<Board> solve(Board board);
}
