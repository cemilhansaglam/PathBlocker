import java.util.List;
import java.util.Stack;

public class Player {
	private Position position; // Current position of the player
	private boolean isFirstMove; // Flag to check if it's the first move
	private Stack<Position> moveHistory; // Stack to store player's move history
	private Stack<List<Position>> wallsHistory; // Stack to store walls added during moves

	// Constructor to initialize the player at the starting position
	public Player(Position startPosition) {
		this.position = startPosition;
		this.isFirstMove = true;
		this.moveHistory = new Stack<>();
		this.wallsHistory = new Stack<>();
	}

	// Method to move the player in the specified direction
	public void move(Board board, Directions direction) {
		Position oldPosition = position;
		Position newPosition = null;

		// Move based on the given direction
		switch (direction) {
			case UP:
				newPosition = moveUntilObstacle(board, -1, 0);
				break;
			case DOWN:
				newPosition = moveUntilObstacle(board, 1, 0);
				break;
			case LEFT:
				newPosition = moveUntilObstacle(board, 0, -1);
				break;
			case RIGHT:
				newPosition = moveUntilObstacle(board, 0, 1);
				break;
			default:
				return;
		}

		// If the player moved, update the board and history
		if (!oldPosition.equals(newPosition)) {
			moveHistory.push(oldPosition); // Save the old position
			List<Position> wallsAddedThisMove = new Stack<>();
			blockPassedPositions(board, oldPosition, newPosition, direction, wallsAddedThisMove); // Mark passed positions

			if (isFirstMove) {
				board.placeObstacle(oldPosition.getX(), oldPosition.getY()); // Place an obstacle at the start
				wallsAddedThisMove.add(oldPosition);
				isFirstMove = false;
			}

			wallsHistory.push(wallsAddedThisMove); // Save the walls added
			board.updatePlayerPosition(oldPosition, newPosition); // Update the player position on the board
			position = newPosition; // Update the current position
		}
	}

	// Method to undo the last move
	public void undoMove(Board board) {
		if (moveHistory.isEmpty()) return;

		Position previousPosition = moveHistory.pop();
		Position currentPosition = this.position;

		if (!wallsHistory.isEmpty()) {
			List<Position> wallsAdded = wallsHistory.pop();
			for (Position wall : wallsAdded) {
				board.removeObstacle(wall.getX(), wall.getY()); // Remove walls placed in the last move
			}
		}

		board.clearPosition(currentPosition.getX(), currentPosition.getY()); // Clear current position
		board.updatePlayerPosition(previousPosition, previousPosition); // Move player back
		this.position = previousPosition; // Update position
	}

	// Method to move until the player hits an obstacle
	private Position moveUntilObstacle(Board board, int deltaX, int deltaY) {
		int newX = position.getX();
		int newY = position.getY();

		// Continue moving until an obstacle is encountered
		while (board.canMove(new Position(newX + deltaX, newY + deltaY))) {
			newX += deltaX;
			newY += deltaY;

			if (board.isGoalReached(new Position(newX, newY))) {
				break;
			}
		}

		return new Position(newX, newY); // Return the final position
	}

	// Method to move in the specified direction
	public Position moveDirection(Board board, Directions direction) {
		switch (direction) {
			case UP:
				return moveUntilObstacle(board, -1, 0);
			case DOWN:
				return moveUntilObstacle(board, 1, 0);
			case LEFT:
				return moveUntilObstacle(board, 0, -1);
			case RIGHT:
				return moveUntilObstacle(board, 0, 1);
		}
		return position; // Return current position if no movement
	}

	// Method to mark positions passed during the move
	private void blockPassedPositions(Board board, Position oldPosition, Position newPosition, Directions direction,
									  List<Position> wallsAddedThisMove) {
		int x = oldPosition.getX();
		int y = oldPosition.getY();

		// Mark all positions passed as obstacles
		while (x != newPosition.getX() || y != newPosition.getY()) {
			board.placeObstacle(x, y);
			wallsAddedThisMove.add(new Position(x, y));

			if (direction == Directions.UP) x--;
			if (direction == Directions.DOWN) x++;
			if (direction == Directions.LEFT) y--;
			if (direction == Directions.RIGHT) y++;
		}

		board.placeObstacle(x, y); // Place obstacle at the final position
		wallsAddedThisMove.add(new Position(x, y)); // Save the position
	}

	// Method to get the player's move history
	public Stack<Position> getMoveHistory() {
		return this.moveHistory;
	}

	// Method to get the player's current position
	public Position getPosition() {
		return this.position;
	}
}
