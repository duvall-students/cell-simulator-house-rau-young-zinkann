package model;

import javafx.scene.paint.Color;

public class Water extends WatorObject {
	public Water(int tag, int col, int row, WatorObject[][] watorWorld) {
		super(tag, col, row, watorWorld);
		myRectangle.setFill(Color.YELLOW);
	}

}
