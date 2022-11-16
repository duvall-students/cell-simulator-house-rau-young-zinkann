package model;

import java.awt.Point;

public class WatorModel {
	// Possible states of squares that make up a maze
			public static final int WATER = 1;	
			public static final int FISH = 2;		
			public static final int SHARK = 3;
			public static final double WATER_DENSITY = .2;
			public static final double FISH_DENSITY = .7;
			public static final double SHARK_DENSITY = .1;



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

			//Ben: creates a maze
			public void createMaze(int rows, int cols) {
				assert(rows > 0 && cols > 0);
				maze = new int[rows][cols];
				int i,j;
				for (i = 0; i<rows; i++)
					for (j = 0; j < cols; j++)
						maze[i][j] = TileRandomizer(i,j);
			}
			
			
			//Ben: Randomizes the Tile Value Based on Densities
			public int TileRandomizer (int row, int col) {
				int r = (int) Math.random();
				if(r >= WATER_DENSITY + SHARK_DENSITY) {
					return FISH;
				} else if (SHARK_DENSITY <  r && r < WATER_DENSITY + SHARK_DENSITY) {
					return WATER;
				}
				
				return SHARK;
				
			}

		



}
