package controller;

import java.awt.Point;

import model.WatorModel;
import view.WatorView;

public class WatorController {


	private WatorModel model;
	private WatorView simulationDisplay;

	public WatorController(int width, int height, WatorVi simulationDisplay) {	
			this.simulationDisplay=  simulationDisplay;
			this.model = new WatorModel(width,height);
		}

//	private Model model;
//	private SimulationDisplay simulationDisplay;

//	public WatorController(int width, int height, SimulationDisplay simulationDisplay) {	
//			this.simulationDisplay=  simulationDisplay;
//			this.model = new Model(width,height);
//		}
	
	public WatorController() {
		
	}
	
//	public void newSimulation() {
//		model.createSimulation();
//		simulationDisplay.redraw();
//	}
//	
//	public void doOneStep(double elapsedTime){
//		model.step();
//		simulationDisplay.redraw();
//	}
//
//	public int getCellState(Point position) {
//		return model.get(position);
//	}
	
	
}
