package controller;

import java.awt.Point;

import model.WatorModel;
import view.WatorView;
/* 
Will Zinkann
*/
public class WatorController {


	private WatorModel model;
	private WatorView view;
	private boolean paused = false;

	public WatorController(int rows, int columns, WatorView view) {	
		this.view =  view;
		this.model = new WatorModel(rows, columns);
	}

	public void doOneStep(double elapsedTime){
		model.update();
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
