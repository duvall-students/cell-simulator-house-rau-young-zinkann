package model;

import java.awt.Point;

public class WatorObject {
	
	private int myTag;
	protected Point location;
	protected WatorObject[][] myWatorWorld;
	
	
	public WatorObject(int tag, int row, int column, WatorObject[][] watorWorld) {
		myTag = tag;
		location = new Point(row, column);
		myWatorWorld = watorWorld;
		
	}
	
	public int getTag() {
		return myTag;
	}
}
