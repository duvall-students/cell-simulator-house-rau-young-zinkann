package controller;

import java.awt.Point;
import java.util.ArrayList;

import model.WatorModel;
import view.WatorView;

/* Will Zinkann */

public class WatorController {


	private WatorModel model;
	private WatorView view;
	private boolean paused = false;

	public WatorController(int rows, int columns, WatorView view, WatorModel model) {	
		this.view =  view;
		this.model = model;
	}

	public void doOneStep(double elapsedTime){
		model.update();
	}
	
	
	//Ben - updates boardsize in both model and view
	public void updateBoardSize(ArrayList<Integer> size) {
		model.createWatorWorld(size.get(0), size.get(1));
		view.redraw(model.getNumCols(), model.getNumRows());
	}

	public void step(double elapsedTime) {
		doOneStep(elapsedTime);
	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}


}
