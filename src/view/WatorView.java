/**
 * @author christopherjyoung
 * 
 * This class will hold all GUI elements and JavaFX application
 */

package view;

import java.util.Scanner;

import controller.WatorController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.WatorModel;

public class WatorView extends Application {
	
	// GUI Settings
	private final int BLOCK_SIZE = 12;
	private final int EXTRA_HORIZONTAL = 150;
	private final int EXTRA_VERTICAL = 100; 
	private final int MILLISECOND_DELAY = 15;
	
	// Fields
	private int numRows;
	private int numColumns;
	private Scene myScene;
	private boolean paused = false;
	private Button pauseButton;
	private GridPane grid;
	Scanner s = new Scanner(System.in);
	
	// Model and controller access
	private WatorModel model;
	private WatorController controller;
	private Rectangle[][] cells;
	
	
	// Start of the JavaFX application
	public void start(Stage stage) {
		// Get user input for number of rows and cols
		numRows = getUserInputRows();
		numColumns = getUserInputColumns();
		
		myScene = setupScene();
		stage.setScene(myScene);
		stage.setTitle("Cell Simulator");
		stage.show();
		
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(MILLISECOND_DELAY));
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}
	
	private int getUserInputColumns() {
		System.out.println("How many cell rows? ");
		return s.nextInt();
	}

	private int getUserInputRows() {
		System.out.println("How many cell columns? ");
		return s.nextInt();
	}

	// Create the scene
	private Scene setupScene() {
		// Make containers
		Group cellDrawing = setupCells();
		HBox controls = setupControlButtons();
		
		// Make VBox
		VBox root = new VBox();
		root.getChildren().addAll(cellDrawing);
		
		// Create scene
		Scene scene = new Scene(root, numColumns*BLOCK_SIZE+ EXTRA_HORIZONTAL, 
				numRows*BLOCK_SIZE + EXTRA_VERTICAL, Color.BLACK);
		
		return scene;
	}
	
	// Setup GUI control buttons
	private HBox setupControlButtons() {
		return null; //fixme
	}
	
	// This method will iterate through the 2D array of cells and add them to the GridPane
	private Group setupCells() {
		Group drawing = new Group();
		cells = new Rectangle[numRows][numColumns];
		
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				Rectangle rect = new Rectangle(j*BLOCK_SIZE, i*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
				rect.setFill(Color.WHITE);
				drawing.getChildren().add(rect);
			}
		}
		return drawing;
	}
	
	/*
	 * Does a step in the search only if not paused.
	 */
	public void step(double elapsedTime){
//		if(!paused) {
//			doOneStep(elapsedTime);
//		}
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	public void redraw() {
		// TODO Auto-generated method stub
		
	}
	
	

}
