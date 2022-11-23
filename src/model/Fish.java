package model;

import javafx.scene.paint.Color;

public class Fish extends WatorCreature{

	public Fish(int tag, int row, int column, WatorObject[][] watorWorld) {
		super(tag, row, column, watorWorld);
		super.myBreedTime = 1;
		myRectangle.setFill(Color.GREEN);
	}
	
	
	

}
