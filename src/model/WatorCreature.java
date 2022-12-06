package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



public class WatorCreature extends WatorObject{

	protected double myDensity;
	protected int myBreedTime;
	protected int initialBreedTime;
	public static final int FISH_TAG = 2;
	public static final int SHARK_TAG = 1;

	
	
	public WatorCreature(int tag, int row, int column, WatorObject[][] watorWorld) {
		super(tag,row, column, watorWorld);
	}
	
	public void SetBreedTime(int breedTime) {
		myBreedTime = breedTime;
		initialBreedTime = breedTime;
	}
	public void reduceBreedTime() {
		myBreedTime -= 1;
	}
	
	private void resetBreedTime() {
		myBreedTime = initialBreedTime;
	}
	
	
	public boolean isBreeding() {
		if(myBreedTime <= 0) {
			return true;
		}
		else {
			return false;
		}
	}
		
	public void breed(int tag, Point breedPoint) {
		if(tag == FISH_TAG) {
			myWatorWorld[breedPoint.x][breedPoint.y] = new Fish(FISH_TAG, breedPoint.x, breedPoint.y, myWatorWorld);
			this.resetBreedTime();
		}
		else {
			myWatorWorld[breedPoint.x][breedPoint.y] = new Shark(SHARK_TAG, breedPoint.x, breedPoint.y, myWatorWorld);
			this.resetBreedTime();
		}
		
	}
			
		
}

	
	
	


