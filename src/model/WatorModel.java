package model;

import java.awt.Point;

public class WatorModel {
	// Possible states of squares that make up a maze
			public static final int WATER = 2;		// Basic hall space
			public static final int FISH = 2;		


			private int[][] maze;	// The squares making up the maze

			public WatorModel(int rows, int columns){
				assert(rows > 0 && columns > 0);
				createMaze(rows, columns);
			}

			public int getNumRows(){
				assert(maze!=null);
				return maze.length;
			}

			public int getNumCols(){
				assert(maze!=null);
				return maze[0].length;
			}

			/*
			 * Check to see if the square is inside the outer walls of the maze
			 */
			public boolean inBounds(Point p){
				assert(maze!=null);
				return (p!= null && p.x < maze.length-1 && p.x > 0 && p.y < maze[0].length-1 && p.y >0);
			}

			/*
			 * Check to see if the point is in bounds (won't cause out-of-bounds or null errors)
			 */
			public boolean validPoint(Point p){
				assert(maze!=null);
				return (p!=null && p.x < maze.length && p.x >= 0 && p.y < maze[0].length && p.y >= 0);
			}

			/*
			 * get - returns a square state at the given position.
			 */
			public int get(Point square){
				assert(validPoint(square));
				return maze[square.x][square.y];
			}



			
			/* 
			 * 
			 * 
			 * 
			 * Remaining code is from "Introduction to Programming Using Java" by Eck.
			 *
			 *
			 *
			 */
			/*
			 * Create a new random maze of the given dimensions and store the result.
			 * Maze has no cycles.
			 */

			public void createMaze(int rows, int cols) {
				assert(rows > 0 && cols > 0);
				maze = new int[rows][cols];
				// Create a random maze.  The strategy is to start with
				// a grid of disconnected "rooms" separated by walls,
				// then look at each of the separating walls, in a random
				// order.  If tearing down a wall would not create a loop
				// in the maze, then tear it down.  Otherwise, leave it in place.
				int i,j;
				int emptyCt = 0; // number of rooms
				int wallCt = 0;  // number of walls
				int[] wallrow = new int[(rows*cols)/2];  // position of walls between rooms
				int[] wallcol = new int[(rows*cols)/2];
				for (i = 0; i<rows; i++)  // start with everything being a wall
					for (j = 0; j < cols; j++)
						maze[i][j] = WATER;
			}


		



}
