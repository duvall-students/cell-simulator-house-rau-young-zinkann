package model;

import java.awt.Point;

import javafx.scene.paint.Color;

public class Fish extends WatorCreature{
	
	public static final int WATER_TAG = 0;

	public Fish(int tag, int row, int column, WatorObject[][] watorWorld) {
		super(tag, row, column, watorWorld);
		super.myBreedTime = 1;
		super.initialBreedTime = 1;
		myRectangle.setFill(Color.GREEN);
	}
	
	public void Swim(Point swimPoint) {
		Point previousPoint = this.getLocation();
		
		
		myWatorWorld[swimPoint.x][swimPoint.y] = this;
		myWatorWorld[swimPoint.x][swimPoint.y].setLocation(swimPoint);
		
		myWatorWorld[previousPoint.x][previousPoint.y] = new Water(WATER_TAG, previousPoint.x, previousPoint.y, myWatorWorld);
	}
	

}
