package model;

import java.awt.Point;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

public class WatorObject {
	
	private int myTag;
	protected Point location;
	protected WatorObject[][] myWatorWorld;
	
	protected Rectangle myRectangle;
	
	
	public WatorObject(int tag, int row, int column, WatorObject[][] watorWorld) {
		myTag = tag;
		location = new Point(row, column);
		myWatorWorld = watorWorld;
		myRectangle = new Rectangle();
		myRectangle.setWidth(12);
		myRectangle.setHeight(12);
		
	}
	
	public int getTag() {
		return myTag;
	}
	
	public Point getLocation() {
		return location;
	}
	
	public void setLocation(Point newLocation) {
		location = newLocation;
	}
	
	public Node getView() {
		return myRectangle;
	}
}
