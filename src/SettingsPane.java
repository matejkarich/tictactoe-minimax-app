/**
 * Honors Project Tic Tac Toe
 * Name:        Richard Matejka
 * Student ID:  1221297844
 * Lecture:     MWF 10:10-11:00am
 * Description: The Settings Pane class was intended to be another displayed Pane that
 * 				allows the user to interact with visual settings and change the background
 * 				and fill of various nodes in the scene. However, it is not functional for nodes
 * 				outside of its own class, and it was therefore never implemented in the final product.
 */

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class SettingsPane extends BorderPane {
	
	private VBox group;
	private ToggleGroup options;
	
	private TextField displaySettings;
	
	private RadioButton lightMode;
	private RadioButton darkMode;
	private RadioButton rgbMode;
	
	//constructor for the class
	public SettingsPane() {
		group = new VBox();
		options = new ToggleGroup();
		
		displaySettings = new TextField("Display Settings:");
		displaySettings.setEditable(false);
		
		//instantiates all three buttons
		lightMode = new RadioButton("Light Theme");
		darkMode = new RadioButton("Dark Theme");
		rgbMode = new RadioButton("RGB Mode");
		
		//automatically selects the lightMode button as the default
		lightMode.setSelected(true);
		
		//adds all three buttons to the same group so that only one is selected at a time
		lightMode.setToggleGroup(options);
		darkMode.setToggleGroup(options);
		rgbMode.setToggleGroup(options);
		
		lightMode.setOnAction(new RadioHandler());
		darkMode.setOnAction(new RadioHandler());
		rgbMode.setOnAction(new RadioHandler());
		
		group.getChildren().addAll(displaySettings, lightMode, darkMode, rgbMode);
		
		this.setPadding(new Insets(20));
		this.setCenter(group);
	}
	
	//changes the background of this class whenever the appropriate button is selected
	private class RadioHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			if(lightMode.isSelected()) {
				setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
			}
			else if (darkMode.isSelected()) {
				setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
			}
			else if (rgbMode.isSelected()) {
				setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
			}
		}
		
	}
}
