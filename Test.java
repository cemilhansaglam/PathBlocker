/*
	1) Why you prefer the search algorithm you choose?
	
	We implemented both DFS(with recursion) and BFS algorithms but we choosed BFS because the board size is small.
	So there are not too much branches. Also BFS gives the optimal path to solution. So, using BFS algorithm in our case is much efficient.
	If the size of the board was much bigger DFS would be more efficient.
	
	2) Can you achieve the optimal result? Why? Why not?
	
	We can achieve the optimal result because we use BFS. After the neighbors of each node are discovered, 
	the neighbors of neighbors are discovered. Thus, nodes are processed in order according to their widths.
	So we can find optimal path with BFS.
	
	3) How you achieved efficiency for keeping the states?
	
	We used Queue for searching neighbors for current state and HashSet for visited nodes.
	
	4) If you prefer to use DFS (tree version) then do you need to avoid cycles?
	
	A tree is a connected graph with no cycles. That is, each node has only one parent and there is no path to return to.
	The tree version of DFS is inherently loop-free, so it can perform deep searches without taking extra precautions.
	
	5) What will be the path-cost for this problem?
	
	Each move is defined by the player sliding in a certain direction and encountering an obstacle.
 	Therefore, the path cost is the total number of slides to reach the target position from the starting position.

*/
public class Test {
    public static void main(String[] args) {
        PngSaver pngSaver = new PngSaver(10);  // Initialize PngSaver with a tile size of 10

        // Loop through 10 levels
        for (int level = 1; level <= 10; level++) {
            // Format the file path and directory for each level
            String levelFilePath = String.format("level%02d.txt", level);
            String levelDirectory = String.format("level%02d", level);

            Game game = new Game(levelFilePath, pngSaver, levelDirectory); // Create a new game for the level

            // Solve the game using BFS (Breadth-First Search)
            game.solveWithBFS();
	    //game.solveWithDFS();

            // Save the solution as PNG images
            game.printSolutionAsPng();
        }
    }
}
