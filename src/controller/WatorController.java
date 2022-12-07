package controller;

import java.awt.Point;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.WatorModel;
import view.WatorView;

/* Will Zinkann */

public class WatorController {


	private WatorModel model;
	private WatorView view;
	private boolean paused = false;
	
	private int rows;
	private int columns;

	public WatorController(int rows, int columns, WatorView view) {	
		this.view =  view;
		this.model = new WatorModel(rows, columns);
		this.rows = rows;
		this.columns = columns;
	}

	public void doOneStep(double elapsedTime){
		model.update();
		view.redraw();
	}

	/*
	 * Does a step in the search only if not paused.
	 */
	public void step(double elapsedTime) {
		if(!isPaused()) {
			doOneStep(elapsedTime);
		}
	}
	
	public void setFishDensity(int newDensity) {
		model.setFishDensity(newDensity);
		System.out.println("Fish Density: Set");
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

	public Color[][] getUpdatedModelColor() {
		return model.getUpdatedWatorModel(model.getNumRows(), model.getNumCols());
	}

	public int getModelRows() {
		return model.getNumRows();
	}

	public int getModelCols() {
		return model.getNumCols();
	}

	public WatorModel getModel() {
		return model;
	}
	
	public void setColumns(int columns) {
		this.columns = columns;
	}
	
	public void setRows(int rows) {
		this.rows = rows;
	}
	
	public int getColumns() {
		return columns;
	}
	
	public int getRows() {
		return rows;
	}

}
