# Project Title

Sliding Puzzle Solver

---

## Description

This project implements a **sliding puzzle solver** that can solve board puzzles using two algorithms:

- **Breadth-First Search (BFS)**
- **Depth-First Search (DFS)**

The solver finds the optimal path (or an efficient solution) to move a player from a starting position (`P`) to a goal position (`G`) on a grid-based board. Obstacles (`#`) and empty spaces (`.`) define the layout of the board.

The solution can also be visualized as a series of PNG images generated for each step of the solution.

---

## Key Components

### 1. **Board**
- Represents the game grid, starting position, and goal position.
- Provides functionality to check moves, update player positions, and mark obstacles.

### 2. **Player**
- Represents the player navigating the board.
- Maintains move history and can undo moves.

### 3. **Solvers**
- **BreadthFirstSearch**:
  - Explores all possible moves level by level.
  - Ensures the optimal path is found.
- **DepthFirstSearch**:
  - Explores as far as possible along each branch before backtracking.
  - May not find the optimal path but is useful for larger boards.

### 4. **Game**
- Manages the overall game process, including loading levels, solving the puzzle, and saving solutions as PNG images.

### 5. **PngSaver**
- Utility class to generate PNG images of each board state during the solution process.

### 6. **LevelLoader**
- Parses level files to create the game board.

---

## How It Works

1. **Input**: A text file (`levelXX.txt`) defining the board layout. Example:
   ```
################
#..............#
#..............#
#..............#
#..............#
#..............#
#..............#
#.......G......#
#..............#
#..............#
#..............#
#..............#
#..............#
#..............#
#P.............#
################
   ```

2. **Solution**:
   - The player (`P`) navigates to the goal (`G`) while avoiding obstacles (`#`).
   - Moves are represented by sliding to the nearest obstacle or boundary in one direction.

3. **Output**:
   - Each step of the solution is saved as a PNG image in a directory for the level.





## License
This project is licensed under the MIT License.

