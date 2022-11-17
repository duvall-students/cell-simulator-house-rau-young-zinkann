package model;

public class Shark extends WatorCreature {
	
	private int myStarveTime;

	public Shark(int tag, int row, int col, WatorObject[][] watorWorld) {
		super(tag, row, col, watorWorld);
		super.myBreedTime = 20;
		myStarveTime = 5;
	}
	
	public void setStarveTime(int starveTime) {
		myStarveTime = starveTime;
	}
	
	
	public void reduceTime() {
		myStarveTime -=1;
	}

	public void starve() {
		if(myStarveTime == 0) {
			//died replaced with water cell
			super.myWatorModel.getModel()[super.location.x][super.location.y] = new Water(super.location.x,super.location.y)
			
		}
	}
}
