package model;

import java.awt.Point;

import javafx.scene.paint.Color;

public class Shark extends WatorCreature {
	
	public static final int WATER_TAG = 0;
	
	private int myStarveTime;
	private int originalStarveTime;

	public Shark(int tag, int row, int col, WatorObject[][] watorWorld) {
		super(tag, row, col, watorWorld);
		super.myBreedTime = 20;
		super.initialBreedTime = 20;
		myRectangle.setFill(Color.YELLOW);
		myStarveTime = 5;
		originalStarveTime = myStarveTime;
		super.watorObjectColor = Color.YELLOW;
	}
	
	public void setStarveTime(int starveTime) {
		myStarveTime = starveTime;
		originalStarveTime = myStarveTime;
	}
	
	
	public void reduceStarveTime() {
		myStarveTime -=1;
	}

	public boolean isStarved() {
		if(myStarveTime == 0) {
			//died replaced with water cell
			return true;
		}
		else {
			return false;
		}
	}
	
	public void eat(Point eatPoint) {
		myStarveTime = originalStarveTime;
		myWatorWorld[eatPoint.x][eatPoint.y] = new Water(WATER_TAG, eatPoint.x, eatPoint.y, myWatorWorld);
		
	}
	
	public void starve(int x, int y) {
		myWatorWorld[x][y] = new Water(WATER_TAG, x, y, myWatorWorld);
	}
	
}
