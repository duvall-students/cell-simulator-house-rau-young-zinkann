package view;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.WatorModel;

import java.awt.Point;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
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
	private final int MILLISECOND_DELAY = 15;	// speed of animation
	private final int EXTRA_VERTICAL = 100; 	// GUI area allowance when making the scene width
	private final int EXTRA_HORIZONTAL = 150; 	// GUI area allowance when making the scene width
	private final int BLOCK_SIZE = 12;     		// size of each cell in pixels
	private final int INITIAL_NUM_ROWS = 30; 
	private final int INITIAL_NUM_COLUMNS = 80;

	private int numRows;
	private int numColumns;
	private Scene myScene;						// the container for the GUI
	private boolean paused = false;		
	private Button pauseButton;
	private TextField inputField;

	private Rectangle[][] cells;				// the Rectangle objects that will get updated and drawn

	private WatorModel cellModel;

	public void start(Stage stage) {
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
	public void step(double elapsedTime){
		//		if(!paused) {
		//			doOneStep(elapsedTime);
		//		}
	}

	private Scene setupScene() {
		// Make containers
		Group cellDrawing = setupCells();
		HBox controls = setupControlButtons();
		HBox textField = setupTextField();

		VBox root = new VBox();
		root.setAlignment(Pos.TOP_CENTER);
		root.setSpacing(10);
		root.setPadding(new Insets(10, 10, 10, 10));
		root.getChildren().addAll(controls, textField, cellDrawing);

		Scene scene = new Scene(root, Color.LIGHTBLUE);

		return scene;
	}

	private HBox setupControlButtons() {
		// Make the controls parts
		HBox controls = new HBox();
		controls.setAlignment(Pos.BASELINE_CENTER);
		controls.setSpacing(10);

		Button setNumRowsButton = new Button("Set Num Rows");
		setNumRowsButton.setOnAction(value -> {
			numRows = Integer.parseInt(inputField.getText());
		});
		controls.getChildren().add(setNumRowsButton);

		Button setNumColumnsButton = new Button("Set Num Columns");
		setNumColumnsButton.setOnAction(value -> {
			numColumns = Integer.parseInt(inputField.getText());
		});
		controls.getChildren().add(setNumColumnsButton);

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

	private Group setupCells() {
		Group drawing = new Group();
		cells = new Rectangle[INITIAL_NUM_ROWS + 1][INITIAL_NUM_COLUMNS + 1];

		for(int i = 0; i < INITIAL_NUM_ROWS + 1; i++){
			for(int j = 0; j < INITIAL_NUM_COLUMNS + 1; j++){
				Rectangle rect = new Rectangle(j*BLOCK_SIZE, i*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);

				if (i == 0 || j == 0 || i == INITIAL_NUM_ROWS || j == INITIAL_NUM_COLUMNS) {
					rect.setFill(Color.WHITE);
				} else {
					rect.setFill(Color.YELLOW);
				}

				cells[i][j] = rect;
				drawing.getChildren().add(rect);
			}	
		}
		return drawing;
	}

	public int getCellState(Point position) {
		return cellModel.get(position);
	}

	public static void main(String[] args) {
		launch(args);
	}

}