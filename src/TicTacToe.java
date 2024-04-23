/**
 * Honors Project Tic Tac Toe
 * Name:        Richard Matejka
 * Student ID:  1221297844
 * Lecture:     MWF 10:10-11:00am
 * Description: The TicTacToe class is the class that organizes all of the other classes and runs the game. It creates
 * 				the application and adds all necessary classes and nodes to it. It is the controller.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TicTacToe extends Application {

	private static int windowWidth = 600;
	private static int windowHeight = 600;
	private static String windowTitle = "Tic Tac Toe";

	//creates Tabs in the GUI environment where the user can choose to play
	//against themselves or an AI algorithm which is unbeatable
	private TabPane tabPane;
	private Tab twoPlayerTab;
	private Tab aiPlayerTab;
	//private Tab settingsTab;

	private BoardPane pvpBoardPane;
	private AIBoardPane aiBoardPane;
	//private SettingsPane settingsPane;

	@Override
	public void start(Stage primaryStage) {
		StackPane root = new StackPane();
		Scene scene = new Scene(root, windowWidth, windowHeight);

		pvpBoardPane = new BoardPane();
		aiBoardPane = new AIBoardPane();
		//settingsPane = new SettingsPane();

		tabPane = new TabPane();

		twoPlayerTab = new Tab();
		twoPlayerTab.setText("Player vs. Player");
		twoPlayerTab.setContent(pvpBoardPane);
		twoPlayerTab.setClosable(false);

		aiPlayerTab = new Tab();
		aiPlayerTab.setText("Player vs. AI");
		aiPlayerTab.setContent(aiBoardPane);
		aiPlayerTab.setClosable(false);

//		settingsTab = new Tab();
//		settingsTab.setText("Settings");
//		settingsTab.setContent(settingsPane);
//		settingsTab.setClosable(false);

		tabPane.getSelectionModel().select(0);
		tabPane.getTabs().addAll(twoPlayerTab, aiPlayerTab); //missing settingsTab

		root.getChildren().add(tabPane);

		primaryStage.setTitle(windowTitle);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
}
