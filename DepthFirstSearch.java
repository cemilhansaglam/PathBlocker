import java.util.List;

public class DepthFirstSearch extends Solver {

	Player player; // The player object that will move on the board

	// Constructor to initialize the DFS solver with the player
	public DepthFirstSearch(Player player) {
		this.player = player; // Set the player
	}

	// Method to solve the board using DFS
	@Override
	public List<Board> solve(Board board) {
		solution.add(board.copyAsNewBoard()); // Add the initial board state to the solution
		dfs(board, player.getPosition()); // Start the DFS search from the player's position
		if (hasWon) {
			return solution; // Return the solution if the goal is reached
		}
		return null; // Return null if no solution is found
	}

	// Private method to perform the DFS search
	private boolean dfs(Board board, Position currentPosition) {

		// Check if the current position is the goal
		if (board.isGoalReached(currentPosition)) {
			hasWon = true; // Mark that the goal has been reached
			return true; // Return true to indicate success
		}

		// Add the current position to the player's move history
		player.getMoveHistory().push(currentPosition);

		// Try moving in all directions (UP, DOWN, LEFT, RIGHT)
		Directions[] directions = Directions.values();

		// Explore each direction
		for (Directions direction : directions) {
			// Get the new position by moving in the current direction
			Position newPosition = player.moveDirection(board, direction);

			// Check if the new position has not been visited and is valid for moving
			if (!player.getMoveHistory().contains(newPosition) && board.canMove(newPosition)) {
				// Move the player to the new position on the board
				player.move(board, direction);
				// Create a copy of the current board after the move
				Board copyBoard = board.copyAsNewBoard();
				solution.add(copyBoard); // Add the new board state to the solution

				// Recursively perform DFS from the new position
				if (dfs(board, newPosition)) {
					return true; // If the goal is reached, return true
				}

				// If the goal is not reached, undo the move and remove the last board state
				player.undoMove(board);
				solution.remove(solution.size() - 1);
			}
		}

		// Backtrack by removing the current position from the move history
		player.getMoveHistory().pop();
		return false; // Return false if no solution is found from this path
	}
}
