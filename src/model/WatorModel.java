package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class WatorModel {
	// Possible states of squares that make up a maze
	public static final int WATER_TAG = 0;	// Empty Space 
	public static final int SHARK_TAG = 1; //signifies a shark space
	public static final int FISH_TAG = 2;	//signifies a fish space 

	private static final int max = 10;
	private static final int min = 1;



	private int waterDensity = 3;
	private double sharkDensity = 1;
	private double fishDensity = 10;
	private boolean isShark = true;


	private WatorObject[][] myWatorModel;	// The squares making up the maze
	private Color[][] updatedWatorModel;

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

	public void createWatorWorld(int rows, int cols) {
		assert(rows > 0 && cols > 0);
		myWatorModel = new WatorObject[rows][cols];
		int i,j;
		for (i = 0; i<rows; i++) {
			for (j = 0; j < cols; j++) {
				myWatorModel[i][j] = populateObject(i,j);
			}
		}

	}

	public WatorObject populateObject (int row, int col) {
		//get random double through our range 
		Random random = new Random();
		int r = random.nextInt((max-min+ min)) + min;
		if(r > waterDensity && r >sharkDensity) {
			return new Fish(FISH_TAG,row, col, myWatorModel);
		} else if (r < fishDensity && r > sharkDensity) {
			return new Water(WATER_TAG, row, col, myWatorModel);
		} else { 
			return new Shark(SHARK_TAG, row, col, myWatorModel);
		}
	}

	//setters
	public void setFishDensity(int newDensity) {
		if(newDensity <= 10 && newDensity >= 0) {
			fishDensity = newDensity;
		}
	}

	public void setSharkDensity(int newDensity) {
		if(newDensity <= 10 && newDensity >= 0) {
			sharkDensity = newDensity;
		}
	}

	public void setWaterDensity(int newDensity) {
		if(newDensity <= 10 && newDensity >= 0) {
			waterDensity = newDensity;
		}
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
		isShark = true;
		Random random = new Random();
		for(int i = 0; i < myWatorModel.length; i++) {
			for(int j = 0; j < myWatorModel[i].length; j++) {
				//check is fish 
				if(myWatorModel[i][j].getTag() == FISH_TAG) {
					Fish currentFish = (Fish) myWatorModel[i][j];
					//reduce breed time for the cycle 
					currentFish.reduceBreedTime();
					//breed behavior
					if(currentFish.isBreeding()) {

						//breed behavior re-factor 
						ArrayList<Point> breedAreas = getValidPointArray(currentFish, WATER_TAG);
						if(!breedAreas.isEmpty()) {
							int randomNum = random.nextInt((breedAreas.size() - 1) + 1);
							Point breedPoint = breedAreas.get(randomNum);
							currentFish.breed(FISH_TAG, breedPoint);
							//myWatorModel[breedPoint.x][breedPoint.y] = new Fish(FISH_TAG, breedPoint.x, breedPoint.y, myWatorModel);
						}
					}
					//swim behavior
					//gets array of water spots 
					ArrayList<Point> swimAreas = getValidPointArray(currentFish, WATER_TAG);
					if(!swimAreas.isEmpty()) {
						//swim logic
						int randomNum = random.nextInt((swimAreas.size() - 1) + 1);
						Point swimPoint = swimAreas.get(randomNum);

						currentFish.Swim(swimPoint);
						//refeactor into fish swim area
						/*
						Point previousPoint = currentFish.getLocation();


						myWatorModel[swimPoint.x][swimPoint.y] = currentFish;
						myWatorModel[swimPoint.x][swimPoint.y].setLocation(swimPoint);

						myWatorModel[previousPoint.x][previousPoint.y] = new Water(WATER_TAG, previousPoint.x, previousPoint.y, myWatorModel);
						 */
					}	

				}
				//check shark
				if(myWatorModel[i][j].getTag() == SHARK_TAG) {

					Shark currentShark = (Shark)myWatorModel[i][j];
					currentShark.reduceBreedTime();
					currentShark.reduceStarveTime();

					//starve behavior
					if(currentShark.isStarved()) {
						//replaces shark with water and returns
						currentShark.starve(i, j);
						isShark = false;
					}

					//breed behavior
					if(currentShark.isBreeding() && isShark){
						//shark breed behavior
						ArrayList<Point> breedAreas = getValidPointArray(currentShark, WATER_TAG);
						if(!breedAreas.isEmpty()) {
							int randomNum = random.nextInt((breedAreas.size() -1) + 1);
							Point breedPoint = breedAreas.get(randomNum);
							currentShark.breed(SHARK_TAG, breedPoint);
							//myWatorModel[breedPoint.x][breedPoint.y] = new Shark(SHARK_TAG, breedPoint.x, breedPoint.y, myWatorModel);

						}

					}
					//eat behavior
					if(isShark) {
						ArrayList<Point> eatAreas = getValidPointArray(currentShark, FISH_TAG);
						if(!eatAreas.isEmpty()) {
							int randomNum = random.nextInt((eatAreas.size() -1) + 1);
							Point eatPoint = eatAreas.get(randomNum);
							currentShark.eat(eatPoint);
							//myWatorModel[eatPoint.x][eatPoint.y] = new Water(WATER_TAG, eatPoint.x, eatPoint.y, myWatorModel);
						}
					}

				}

			}
		}


	}

	public Rectangle[][] getWatorModelView(int row, int column){
		Rectangle[][] views =  new Rectangle[row][column];
		for(int i = 0; i < myWatorModel.length; i++) {
			for(int j = 0; j < myWatorModel[i].length; j++) {
				//myWatorModel[i][j].setLocation(i, j);
				views[i][j] = (Rectangle) myWatorModel[i][j].getView();
			}
		}
		return views;
	}

	public Color[][] getUpdatedWatorModel(int row, int columns){
		updatedWatorModel = new Color[row][columns];
		for(int i = 0; i < myWatorModel.length; i++) {
			for(int j = 0; j < myWatorModel[i].length; j++) {
				updatedWatorModel[i][j] = myWatorModel[i][j].getColor();
			}
		}
		return updatedWatorModel;
	}

	public WatorObject[][] getMyWatorModel() {
		return myWatorModel;
	}
}
