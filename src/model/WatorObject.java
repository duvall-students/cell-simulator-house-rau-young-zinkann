package model;

import java.awt.Point;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

public class WatorObject {
	
	private static final int BLOCK_SIZE = 12;
	
	private int myTag;
	protected Point location;
	protected WatorObject[][] myWatorWorld;
	
	protected Rectangle myRectangle;
	
	
	public WatorObject(int tag, int row, int column, WatorObject[][] watorWorld) {
		myTag = tag;
		location = new Point(row, column);
		myWatorWorld = watorWorld;
		myRectangle = new Rectangle(BLOCK_SIZE * column, BLOCK_SIZE * row, BLOCK_SIZE ,BLOCK_SIZE);
		//myRectangle.setWidth(12);
		//myRectangle.setHeight(12);
		
	}
	
	public int getTag() {
		return myTag;
	}
	
	public Point getLocation() {
		return location;
	}
	
	public void setLocation(Point newLocation) {
		location = newLocation;
		myRectangle.setX(location.x * BLOCK_SIZE);
		myRectangle.setY(location.y * BLOCK_SIZE);
	}
	
	public Node getView() {
		return myRectangle;
	}
	
	public void setLocation(int x, int y) {
		myRectangle.setX(x);
		myRectangle.setY(y);
	}
	
}
