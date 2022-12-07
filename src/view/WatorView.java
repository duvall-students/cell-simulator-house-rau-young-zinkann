package view;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.WatorModel;
import controller.WatorController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * 
 * @author Christopher J Young
 *	This class is the "main" class that holds all view elements
 */

public class WatorView extends Application {

	/*
	 * GUI settings
	 */
	private final int MILLISECOND_DELAY = 1000;	// speed of animation
	private final int BLOCK_SIZE = 12;     		// size of each cell in pixels
	private final int INITIAL_NUM_ROWS = 30; 
	private final int INITIAL_NUM_COLUMNS = 80;

	private Scene myScene;						// the container for the GUI
	private Button pausedButton;
	private TextField inputField;

	private Rectangle[][] cells;				// the Rectangle objects that will get updated and drawn

	private WatorModel model;
	private WatorController controller;

	public void start(Stage stage) {
		// Initialize the model
		model = new WatorModel(INITIAL_NUM_ROWS, INITIAL_NUM_COLUMNS);

		// Initialize the controller
		controller = new WatorController(INITIAL_NUM_ROWS, INITIAL_NUM_COLUMNS, this);

		// Initializing the gui
		myScene = setupScene();
		stage.setScene(myScene);
		stage.setTitle("Water World");
		stage.show();

		// Makes the animation happen.  Will call "step" method repeatedly.
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(MILLISECOND_DELAY));
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}

	/*
	 * Does a step in the search only if not paused.
	 */
	public void step(double elapsedTime) {
		if(!controller.isPaused()) {
			controller.doOneStep(elapsedTime);
		}
	}

	private Scene setupScene() {
		// Make containers
		Group cellDrawing = setupInitialCells();
		HBox topButtons = setupTopButtons();
		HBox textField = setupTextField();
		HBox bottomButtons = setupBottomButtons();

		VBox root = new VBox();
		root.setAlignment(Pos.TOP_CENTER);
		root.setSpacing(10);
		root.setPadding(new Insets(10, 10, 10, 10));
		root.getChildren().addAll(topButtons, textField, cellDrawing, bottomButtons);

		Scene scene = new Scene(root, Color.LIGHTBLUE);

		return scene;
	}

	private HBox setupTopButtons() {
		// Make the controls parts
		HBox controls = new HBox();
		controls.setAlignment(Pos.BASELINE_CENTER);
		controls.setSpacing(10);

		Button setNumRowsButton = new Button("Set Num Rows");
		setNumRowsButton.setOnAction(value -> {
			// controller
			controller.setRows(Integer.parseInt(inputField.getText()));
			newSimulation();
		});
		controls.getChildren().add(setNumRowsButton);

		Button setNumColumnsButton = new Button("Set Num Columns");
		setNumColumnsButton.setOnAction(value -> {
			// controller
			controller.setColumns(Integer.parseInt(inputField.getText()));
			newSimulation();
		});
		controls.getChildren().add(setNumColumnsButton);
		
		// breed time fish
		Button setFishBreedTime = new Button("Set Fish Breed Time");
		setFishBreedTime.setOnAction(value -> {
			// controller
		});
		controls.getChildren().add(setFishBreedTime);
		
		// breed time shark
		Button setSharkBreedTime = new Button("Set Shark Breed Time");
		setSharkBreedTime.setOnAction(value -> {
			// controller
		});
		controls.getChildren().add(setSharkBreedTime);

		return controls;
	}

	private HBox setupTextField() {
		HBox textFieldBox = new HBox();
		textFieldBox.setAlignment(Pos.BASELINE_CENTER);
		Label textFieldLabel = new Label("Change values");
		textFieldBox.getChildren().add(textFieldLabel);
		inputField = new TextField();
		textFieldBox.getChildren().add(inputField);
		textFieldBox.setSpacing(10);
		return textFieldBox;
	}

	private HBox setupBottomButtons() {
		HBox controls = new HBox();
		controls.setAlignment(Pos.BASELINE_CENTER);
		controls.setSpacing(10);

		// start new simulation button
		Button newSimulationButton = new Button("New Simulation");
		newSimulationButton.setOnAction(value -> {
			// controller
			newSimulation();
		});
		controls.getChildren().add(newSimulationButton);

		// take a single step
		Button takeStepButton = new Button("Step");
		takeStepButton.setOnAction(value -> {
			// controller
			controller.doOneStep(MILLISECOND_DELAY);
		});
		controls.getChildren().add(takeStepButton);

		// pause
		pausedButton = new Button("Pause");
		pausedButton.setOnAction(value -> {
			// controller
			controller.setPaused(!controller.isPaused());
		});
		controls.getChildren().add(pausedButton);

		return controls;
	}

	private Group setupInitialCells() {
		Group drawing = new Group();
		cells = new Rectangle[INITIAL_NUM_ROWS + 2][INITIAL_NUM_COLUMNS + 2];
		// get cells from model
		Rectangle[][] modelCells = model.getWatorModelView(INITIAL_NUM_ROWS, INITIAL_NUM_COLUMNS);

		// initial group population
		for (int i = 0; i <= INITIAL_NUM_ROWS; i++) {
			for (int j = 0; j <= INITIAL_NUM_COLUMNS; j++) {
				// if edge case, then make black
				if (i == 0 || j == 0 || i == INITIAL_NUM_ROWS || j == INITIAL_NUM_COLUMNS) {
					Rectangle rect = new Rectangle(j*BLOCK_SIZE, i*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
					rect.setFill(Color.BLACK);
					cells[i][j] = rect;
					drawing.getChildren().add(rect);
				} 
				// else, get the wator cells from the model
				else {
					Rectangle rect = modelCells[i][j];
					cells[i][j] = modelCells[i][j];
					drawing.getChildren().add(rect);
				}

			}
		}
		return drawing;
	}

	public void redraw() {
		Color[][] updatedColors = controller.getUpdatedModelColor();

		for (int i = 0; i <= controller.getModelRows(); i++) {
			for (int j = 0; j <= controller.getModelCols(); j++) {
				// ignore black boundaries
				if (!(i == 0 || j == 0 || i == controller.getModelRows() || j == controller.getModelCols())) {
					Rectangle rect = cells[i][j];
					rect.setFill(updatedColors[i][j]);
				} else {
					Rectangle rect = cells[i][j];
					rect.setFill(Color.BLACK);
				}
			}
		}
	}

	public void newSimulation() {
//		controller.getModel().createWatorWorld(controller.getModelRows(), controller.getModelCols());
		controller.getModel().createWatorWorld(controller.getRows(), controller.getColumns());
		redraw();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
