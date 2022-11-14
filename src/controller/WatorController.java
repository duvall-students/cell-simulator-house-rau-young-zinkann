package controller;

import java.awt.Point;

import model.WatorModel;
import view.WatorView;

/*
 *  wzinkann
 */

public class WatorController {

	private WatorModel model;
	private WatorView simulationDisplay;

	public WatorController(int width, int height, WatorView simulationDisplay) {	
			this.simulationDisplay=  simulationDisplay;
			this.model = new WatorModel(width,height);
		}
	
	public void newSimulation() {
		model.createSimulation();
		simulationDisplay.redraw();
	}
	
	public void doOneStep(double elapsedTime){
		model.step();
		simulationDisplay.redraw();
	}

	public int getCellState(Point position) {
		return model.get(position);
	}
	
	
}
