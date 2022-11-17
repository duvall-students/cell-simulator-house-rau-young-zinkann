package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



public class WatorCreature extends WatorObject{

	protected double myDensity;
	protected int myBreedTime;

	
	
	public WatorCreature(int tag, int row, int column, WatorObject[][] watorWorld) {
		super(tag,row, column, watorWorld);
	}
	
	public void SetBreedTime(int breedTime) {
		myBreedTime = breedTime;
	}
	
	public boolean isBreeding() {
		if(myBreedTime <= 0) {
			return true;
		}
		else {
			return false;
		}
	}

	
	
	


	
}
