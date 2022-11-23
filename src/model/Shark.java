package model;

import javafx.scene.paint.Color;

public class Shark extends WatorCreature {
	
	private int myStarveTime;
	private int originalStarveTime = myStarveTime;;

	public Shark(int tag, int row, int col, WatorObject[][] watorWorld) {
		super(tag, row, col, watorWorld);
		super.myBreedTime = 20;
		myRectangle.setFill(Color.YELLOW);
		myStarveTime = 5;
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
	
	public void eat() {
		myStarveTime = originalStarveTime;
	}
}
