package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WatorModel {
	// Possible states of squares that make up a maze
	public static final int WATER_TAG = 0;	// Empty Space 
	public static final int SHARK_TAG = 1; //signifies a shark space
	public static final int FISH_TAG = 2;	//signifies a fish space 
	
	private static final double max = 1.0;
	private static final double min = 1.0;
	
	
	
	private double waterDensity = 0.2;
	private double sharkDensity = 0.1;
	private double fishDensity = 0.7;


	private WatorObject[][] myWatorModel;	// The squares making up the maze

	public WatorModel(int rows, int columns){
		//assert(rows > 0 && columns > 0); 
		createWatorWorld(rows, columns);
	}

	public int getNumRows(){
		assert(myWatorModel!=null);
		return myWatorModel.length;
	}

	public int getNumCols(){
		assert(myWatorModel!=null);
		return myWatorModel[0].length;
	}

	//check to see if it is a point in bounds
	public boolean inBounds(Point p){
		assert(myWatorModel!=null);
		return (p!= null && p.x < myWatorModel.length-1 && p.x > 0 && p.y < myWatorModel[0].length-1 && p.y >0);
	}

	/*
	 * Check to see if the point is in bounds (won't cause out-of-bounds or null errors)
	 */
	public boolean validPoint(Point p){
		assert(myWatorModel!=null);
		return (p!=null && p.x < myWatorModel.length && p.x >= 0 && p.y < myWatorModel[0].length && p.y >= 0);
	}

	/*
	 * get - returns a WatorObject state at the given position.
	 */
	public WatorObject get(Point square){
		assert(validPoint(square));
		return myWatorModel[square.x][square.y];
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
	
	/*
	private Collection<Point> getNeighbors(){
		List<Point> maybeNeighbors = new ArrayList<>();
		maybeNeighbors.add(new Point(current.x-1,current.y));
		maybeNeighbors.add(new Point(current.x+1,current.y));
		maybeNeighbors.add(new Point(current.x,current.y+1));
		maybeNeighbors.add(new Point(current.x,current.y-1));
		List<Point> neighbors = new ArrayList<>();
		for(Point p: maybeNeighbors){
			if(myWatorModel.inBounds(p)){
				neighbors.add(p);
			}
		}
		return neighbors;
	}
	
	private Point chooseNeighbor(Collection<Point> neighbors){
		for(Point p: neighbors){
			if(myWatorModel.get(p).getTag()== myWatorModel.WATER_TAG){
				return p;
			}
		}
		return null;
	}
	
	//get 
	public WatorObject[][] getModel() {
		return myWatorModel;
	}
	*/
	
	public void createWatorWorld(int rows, int cols) {
		assert(rows > 0 && cols > 0);
		myWatorModel = new WatorObject[rows][cols];
		int i,j;
		for (i = 0; i<rows; i++)
			for (j = 0; j < cols; j++)
				myWatorModel[i][j] = PopulateObject(i,j);
	}
	
	
	
	
	public WatorObject PopulateObject (int row, int col) {
		//get random double through our range 
		double r = Math.random() * (max -min) - min;
		if(r >= waterDensity + sharkDensity) {
			return new Fish(FISH_TAG,row, col, myWatorModel);
		} else if (sharkDensity <  r && r < waterDensity + sharkDensity) {
			return new Water(WATER_TAG, row, col, myWatorModel);
		}
		return new Shark(SHARK_TAG, row, col, myWatorModel);		
	}
	
	
	//return array of valid points either empty or food in the case of sharks
	private ArrayList<Point> getValidPointArray(WatorObject currentObject, int targetTag) {
		Point currentLocation = currentObject.getLocation();		
		ArrayList<Point> validPoints = new ArrayList<Point>();
		//need to get x-1 and x+1
		//check for x -1 bounds 
		if(currentLocation.x - 1 > 0 ) {
			if(myWatorModel[currentLocation.x - 1][currentLocation.y].getTag() == targetTag) {
			validPoints.add(new Point(currentLocation.x -1, currentLocation.y));
			}
		}
		//check for x+1 bounds
		if(currentLocation.x + 1 != myWatorModel.length) {
			if(myWatorModel[currentLocation.x+ 1][currentLocation.y].getTag() == targetTag) {
			validPoints.add(new Point(currentLocation.x + 1, currentLocation.y ));
			}
		}
		//check for y -1 bounds
		if(currentLocation.y - 1 > 0) {
			if(myWatorModel[currentLocation.x][currentLocation.y - 1].getTag() == targetTag) {
				validPoints.add(new Point(currentLocation.x, currentLocation.y -1));
				}
			
		}
		//check y+1
		if(currentLocation.y + 1 != myWatorModel[0].length) {
			if(myWatorModel[currentLocation.x][currentLocation.y + 1].getTag() == targetTag) {
				validPoints.add(new Point(currentLocation.x, currentLocation.y + 1));
				}
		}
		
		//need to get y-1 and y=1
		
		return validPoints;
	}
	
	
	public void update() {
		//loop through each object
		for(int i = 0; i < myWatorModel.length; i++) {
			for(int j = 0; j < myWatorModel[i].length; j++) {
				//check is fish 
				if(myWatorModel[i][j].getTag() == 1) {
					Fish current = (Fish)myWatorModel[i][j];
					//reduce breed time for the cycle 
					current.reduceBreedTime();
					
					//breed behavior
					if(current.isBreeding()) {
						//breed behavior *refactor 
						ArrayList<Point> breedAreas = getValidPointArray(current, WATER_TAG);
						if(!breedAreas.isEmpty()) {
							int randomNum = (int) Math.random() * (breedAreas.size() - 1);
							Point breedPoint = breedAreas.get(randomNum);
							
							myWatorModel[breedPoint.x][breedPoint.y] = new Fish(FISH_TAG, breedPoint.x, breedPoint.y, myWatorModel);
						}
					}
					
					//swim behavior
					//gets array of water spots 
					ArrayList<Point> swimAreas = getValidPointArray(current, WATER_TAG);
					if(!swimAreas.isEmpty()) {
						//swim logic
						int randomNum = (int) Math.random() * (swimAreas.size() - 1);
						Point swimPoint = swimAreas.get(randomNum);
						Point previousPoint = current.getLocation();
						
						
						myWatorModel[swimPoint.x][swimPoint.y] = current;
						myWatorModel[swimPoint.x][swimPoint.y].setLocation(swimPoint);
						
						myWatorModel[previousPoint.x][previousPoint.y] = new Water(WATER_TAG, previousPoint.x, previousPoint.y, myWatorModel);
					}	
					
					
				
				}
				//check shark
				if(myWatorModel[i][j].getTag() == 2) {
						Shark current = (Shark)myWatorModel[i][j];
						current.reduceBreedTime();
						current.reduceStarveTime();
						
						//starve behavior
						if(current.isStarved()) {
							//replaces shark with water and returns
							myWatorModel[i][j] = new Water(WATER_TAG, i, j, myWatorModel);
							return;
						}
						
						//breed behavior
						if(current.isBreeding()) {
							//shark breed behavior
							ArrayList<Point> breedAreas = getValidPointArray(current, WATER_TAG);
							if(!breedAreas.isEmpty()) {
								int randomNum = (int) Math.random() * (breedAreas.size() - 1);
								Point breedPoint = breedAreas.get(randomNum);
								
								myWatorModel[breedPoint.x][breedPoint.y] = new Shark(SHARK_TAG, breedPoint.x, breedPoint.y, myWatorModel);
								
							}
							
						}
						
						
						
						//eat behavior
						ArrayList<Point> eatAreas = getValidPointArray(current, FISH_TAG);
						if(!eatAreas.isEmpty()) {
							int randomNum = (int) Math.random() * (eatAreas.size() - 1);
							
							Point eatPoint = eatAreas.get(randomNum);
							
							myWatorModel[eatPoint.x][eatPoint.y] = new Water(WATER_TAG, eatPoint.x, eatPoint.y, myWatorModel);
						}
						
					
					}
				
			}
		}
  }
}
