/**
 * Honors Project Tic Tac Toe
 * Name:        Richard Matejka
 * Student ID:  1221297844
 * Lecture:     MWF 10:10-11:00am
 * Description: The BoardPane class creates the game of Tic Tac Toe and makes it playable. It organizes nodes such
 * 				as buttons into a game grid and makes sure each node has a function. This game is between two players
 * 				playing on the same device. One click will place an X and the following click will place an O mark.
 */

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class BoardPane extends GridPane {
	
	private ArrayList<Button> buttonList;
	
	private ButtonHandler buttonHandler;
	
	private Button topLeft;
	private Button topMiddle;
	private Button topRight;
	private Button midLeft;
	private Button midMiddle;
	private Button midRight;
	private Button bottomLeft;
	private Button bottomMid;
	private Button bottomRight;
	
	private Label updates;
	private Button replay;
	
	private GridPane gridPane;
	
	private boolean playerTurn;
	private String symbol;
	
	public BoardPane() {
		playerTurn = true;
		symbol = "X";
		
		buttonList = new ArrayList<Button>();
		buttonHandler = new ButtonHandler();
		
		//a label that updates the user on the status of the game
		updates = new Label("");
		updates.setFont(Font.font(20));
		updates.setTextFill(Color.RED);
		
		//button that allows user to wipe the board and play again
		replay = new Button("Play Again");
		replay.setOnAction(buttonHandler);
		
		//initializes all buttons in the Tic Tac Toe grid
		topLeft = new Button();
		topMiddle = new Button();
		topRight = new Button();
		midLeft = new Button();
		midMiddle = new Button();
		midRight = new Button();
		bottomLeft = new Button();
		bottomMid = new Button();
		bottomRight = new Button();
		
		//adds all buttons to an array list
		buttonList.add(topLeft);
		buttonList.add(topMiddle);
		buttonList.add(topRight);
		buttonList.add(midLeft);
		buttonList.add(midMiddle);
		buttonList.add(midRight);
		buttonList.add(bottomLeft);
		buttonList.add(bottomMid);
		buttonList.add(bottomRight);
		
		for (Button b: buttonList) {
			b.setOnAction(buttonHandler);
			b.setPrefSize(130, 130);
		}
		
		gridPane = new GridPane();
		
		gridPane.add(topLeft, 0, 0);
		gridPane.add(topMiddle, 1, 0);
		gridPane.add(topRight, 2, 0);
		
		gridPane.add(midLeft, 0, 1);
		gridPane.add(midMiddle, 1, 1);
		gridPane.add(midRight, 2, 1);
		
		gridPane.add(bottomLeft, 0, 2);
		gridPane.add(bottomMid, 1, 2);
		gridPane.add(bottomRight, 2, 2);
		
		this.add(updates, 0, 0);
		this.add(gridPane, 0, 1);
		this.add(replay, 0, 2);
		this.setAlignment(Pos.CENTER);
		this.setVgap(20);
	}
	
	//method that alternates between setting the X and O symbols for the user so that two people can interact with one mouse
	public void setTurn(Button button) {
		button.setFont(Font.font(60));
		if (playerTurn) {
			button.setText(symbol);
			if(checkWinner()) {
	    		updates.setText(symbol + " is the winner!");
	    		for (Button b: buttonList) {
	    			b.setDisable(true);
	    		}
	    	}
			else if (checkDraw()) {
				updates.setText("The game ended in a draw!");
			}
			symbol = "O";
			playerTurn = false;
		}
		else {
			button.setText(symbol);
			if(checkWinner()) {
	    		updates.setText(symbol + " is the winner!");
	    		for (Button b: buttonList) {
	    			b.setDisable(true);
	    		}
	    	}
			else if (checkDraw()) {
				updates.setText("The game ended in a draw!");
			}
			symbol = "X";
			playerTurn = true;
		}
	}
	
	//checks all possible winning combinations to determine if there is a winner
	public boolean checkWinner() {
		if (topLeft.getText().equals(symbol) && topMiddle.getText().equals(symbol) && topRight.getText().equals(symbol)) {
			return true;
		}
		else if (midLeft.getText().equals(symbol) && midMiddle.getText().equals(symbol) && midRight.getText().equals(symbol)) {
			return true;
		}
		else if (bottomLeft.getText().equals(symbol) && bottomMid.getText().equals(symbol) && bottomRight.getText().equals(symbol)) {
			return true;
		}
		else if (topLeft.getText().equals(symbol) && midLeft.getText().equals(symbol) && bottomLeft.getText().equals(symbol)) {
			return true;
		}
		else if (topMiddle.getText().equals(symbol) && midMiddle.getText().equals(symbol) && bottomMid.getText().equals(symbol)) {
			return true;
		}
		else if (topRight.getText().equals(symbol) && midRight.getText().equals(symbol) && bottomRight.getText().equals(symbol)) {
			return true;
		}
		else if (topLeft.getText().equals(symbol) && midMiddle.getText().equals(symbol) && bottomRight.getText().equals(symbol)) {
			return true;
		}
		else if (topRight.getText().equals(symbol) && midMiddle.getText().equals(symbol) && bottomLeft.getText().equals(symbol)) {
			return true;
		}
		return false;
	}
	
	//checks how many disabled buttons there are to tell if there are any spaces avaiable or if the game ended in a draw
	public boolean checkDraw() {
		int count = 0;
		for (Button b: buttonList) {
			if (b.isDisabled()) {
				count++;
			}
		}
		if (count == 9) {
			return true;
		}
		return false;
	}
	
	private class ButtonHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			
			//resets the board
			if (event.getSource() == replay) {
				for (Button b: buttonList) {
					b.setText("");
					b.setDisable(false);
				}
				updates.setText("");
				playerTurn = true;
				symbol = "X";
			}
			else {
				//places the appropriate mark on the button and disables it
				Button button = (Button) event.getSource();
		    	button.setDisable(true);
		    	setTurn(button);
			}
		}
		
	}
	
}
