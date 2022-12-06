package controller;

import java.awt.Point;

import javafx.scene.shape.Rectangle;
import model.WatorModel;
import view.WatorView;

/* Will Zinkann */

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

	public Rectangle[][] getWatorModelView(int rows, int columns) {
		Rectangle[][] views = new Rectangle[rows][columns];
		for (int i = 0; i < model.getMyWatorModel().length; i++) {
			for (int j = 0; j < model.getMyWatorModel()[i].length; j++) {
				views[i][j] = (Rectangle) model.getMyWatorModel()[i][j].getView();
			}
		}
		return views;
	}


}
