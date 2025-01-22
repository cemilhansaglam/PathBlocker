# PATH BLOCKER

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

### 7. **Directions**
- Enumerates the four possible directions a player can move:
  - **UP**
  - **DOWN**
  - **LEFT**
  - **RIGHT**

---

## How It Works

1. **Input**: A text file (`levelXX.txt`) defining the board layout. Example:
   ```
   #####
   #P..#
   #.#.#
   #..G#
   #####
   ```

2. **Solution**:
   - The player (`P`) navigates to the goal (`G`) while avoiding obstacles (`#`).
   - Moves are represented by sliding to the nearest obstacle or boundary in one direction.

3. **Output**:
   - Each step of the solution is saved as a PNG image in a directory for the level.

---

## Running the Project

### Prerequisites
- **Java Development Kit (JDK)** 8 or above.

### Steps
1. Compile all Java files:
   ```
   javac *.java
   ```
2. Run the `Test` class to solve puzzles for predefined levels:
   ```
   java Test
   ```

### File Structure
- `Board.java`: Manages the game board.
- `Player.java`: Handles player movement.
- `BreadthFirstSearch.java`: BFS implementation.
- `DepthFirstSearch.java`: DFS implementation.
- `Game.java`: Game logic and coordination.
- `PngSaver.java`: PNG generation utility.
- `LevelLoader.java`: Loads levels from text files.
- `Directions.java`: Enumerates possible movement directions.
- `Test.java`: Main class to test the solver.

---

## Design Decisions

1. **Algorithm Choice**:
   - BFS is preferred for small boards as it guarantees the optimal path.
   - DFS is provided as an alternative for larger, more complex boards.

2. **Efficiency**:
   - BFS uses a `Queue` for neighbors and a `HashSet` to track visited states.
   - DFS uses a recursive approach and tracks visited nodes to avoid cycles.

3. **Visualization**:
   - PNG images provide a step-by-step view of the solution, aiding in understanding and debugging.

---

## Example Usage

For a level defined as:
```txt
#####
#P..#
#.#.#
#..G#
#####
```

The output will include:
- A series of PNG images (`0001.png`, `0002.png`, ...) showing the player’s progression to the goal.

---

## Limitations

- **Board Size**: Large boards may require significant computation time for BFS.
- **DFS Limitations**: May not find the optimal solution due to its nature of exploring deeply before considering alternatives.

---

## Future Improvements

1. Add support for additional heuristic-based algorithms (e.g., A*).
2. Enhance visualization with animations or real-time rendering.
3. Optimize memory usage for larger boards.



