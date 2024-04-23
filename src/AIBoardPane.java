/**
 * Honors Project Tic Tac Toe
 * Name:        Richard Matejka
 * Student ID:  1221297844
 * Lecture:     MWF 10:10-11:00am
 * Description: The AIBoardPane class creates the environment where the user plays Tic Tac Toe
 * 				against an AI algorithm. The set up is similar to the BoardPane class but it incorporates
 * 				methods which are better suited to help an AI make a move
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

public class AIBoardPane extends GridPane {
	
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
	
	private int value;
	
	private int bestValue = 0;
	
	public AIBoardPane() {	
		value = 0;
		
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
		topLeft = new Button("");
		topMiddle = new Button("");
		topRight = new Button("");
		midLeft = new Button("");
		midMiddle = new Button("");
		midRight = new Button("");
		bottomLeft = new Button("");
		bottomMid = new Button("");
		bottomRight = new Button("");
		
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
	
	//method that works through all possible combinations on the board and checks if there is a winner
	public boolean checkWinner(String symbol) {
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
	
	//a method that checks if the board is filled or not. If there are no open spots left, the game must have ended in a draw. (the return
	//statements are slightly counter-intuitive because they are used for two different functions in the program
	public boolean checkDraw() {
		for (Button b: buttonList) {
			if(b.getText().equals("")) {
				return true;
			}
		}
		return false;
	}
	
	//a method that assigns values to different winning or draw situations. These values are used by the AI algorithm to determine which move is best
	public int determineValue() {
		if (topLeft.getText().equals("X") && topMiddle.getText().equals("X") && topRight.getText().equals("X")) {
			return -10;
		}
		else if (midLeft.getText().equals("X") && midMiddle.getText().equals("X") && midRight.getText().equals("X")) {
			return -10;
		}
		else if (bottomLeft.getText().equals("X") && bottomMid.getText().equals("X") && bottomRight.getText().equals("X")) {
			return -10;
		}
		else if (topLeft.getText().equals("X") && midLeft.getText().equals("X") && bottomLeft.getText().equals("X")) {
			return -10;
		}
		else if (topMiddle.getText().equals("X") && midMiddle.getText().equals("X") && bottomMid.getText().equals("X")) {
			return -10;
		}
		else if (topRight.getText().equals("X") && midRight.getText().equals("X") && bottomRight.getText().equals("X")) {
			return -10;
		}
		else if (topLeft.getText().equals("X") && midMiddle.getText().equals("X") && bottomRight.getText().equals("X")) {
			return -10;
		}
		else if (topRight.getText().equals("X") && midMiddle.getText().equals("X") && bottomLeft.getText().equals("X")) {
			return -10;
		}
		else if (topLeft.getText().equals("O") && topMiddle.getText().equals("O") && topRight.getText().equals("O")) {
			return 10;
		}
		else if (midLeft.getText().equals("O") && midMiddle.getText().equals("O") && midRight.getText().equals("O")) {
			return 10;
		}
		else if (bottomLeft.getText().equals("O") && bottomMid.getText().equals("O") && bottomRight.getText().equals("O")) {
			return 10;
		}
		else if (topLeft.getText().equals("O") && midLeft.getText().equals("O") && bottomLeft.getText().equals("O")) {
			return 10;
		}
		else if (topMiddle.getText().equals("O") && midMiddle.getText().equals("O") && bottomMid.getText().equals("O")) {
			return 10;
		}
		else if (topRight.getText().equals("O") && midRight.getText().equals("O") && bottomRight.getText().equals("O")) {
			return 10;
		}
		else if (topLeft.getText().equals("O") && midMiddle.getText().equals("O") && bottomRight.getText().equals("O")) {
			return 10;
		}
		else if (topRight.getText().equals("O") && midMiddle.getText().equals("O") && bottomLeft.getText().equals("O")) {
			return 10;
		}
		return 0;
	}
	
	/*
	 * The algorithm running the AI. Known as MinMax algorithm
	 * 
	 * Assigns either the user or the computer to be the "maximizing" player and the other to be the "minimizing" player.
	 * The maximizing player will be looking to have the highest assigned score. The minimizing player attempts to have the 
	 * lowest (most negative score). The method is recursive and plays out future moves in order to determine which moves will
	 * be most beneficial for both players (either by a positive score or a negative score)
	 */
	public int minmaxAI(int depth, boolean maximizing) {
		value = determineValue(); //determines the state of the board and assigns a value based on the state
		
		//base cases for once the recursive algorithm is finished running
		if (value == 10) {
			return value - depth;
		}
		if (value == -10) {
			return value + depth;
		}
		if(!checkDraw()) {
			return 0;
		}
		
		//checks if the maximizing player is being run
		if (maximizing) {
			bestValue = Integer.MIN_VALUE;
			
			//checks if there are any empty spots left on the board
			for (int i = 0; i < 9; i++) {
				if (buttonList.get(i).getText().equals("")) {
					buttonList.get(i).setText("O"); //makes a tentative move for the computer
					bestValue = Math.max(bestValue, minmaxAI(depth+1, false)); //runs AI algorithm once again with new move taken into account
					buttonList.get(i).setText(""); //removes previous experimental move
				}
			}
			return bestValue; //returns a maximum possible value that will later be compared to determine the proper move
		}
		
		//checks if the minimizing player is running
		else {
			bestValue = Integer.MAX_VALUE;
			for (int i = 0; i < 9; i++) {
				if (buttonList.get(i).getText().equals("")) {
					buttonList.get(i).setText("X"); //makes a tentative move for the user
					bestValue = Math.min(bestValue, minmaxAI(depth+1, true)); //runs AI algorithm once again with the new move taken into account
					buttonList.get(i).setText(""); //removes previous experimental move
				}
			}
			return bestValue; //returns a minimum possible value that will later be compared to determine the proper move
		}
	}
	
	public void makeAIMove() {
		int bestValue = Integer.MIN_VALUE;
		int moveValue = Integer.MIN_VALUE;
		int moveIndex = 0;
		
		//checks if there are available spots on the board
		for (int i = 0; i < 9; i++) {
			if (buttonList.get(i).getText().equals("")) {
				buttonList.get(i).setText("O"); //attempts to make a move for the computer in each of the available spots on the board
				moveValue = minmaxAI(0, false); //runs the AI algorithm with the computer as the minimizing player
				buttonList.get(i).setText(""); //deletes the previous move
				
				//compares the results of each algorithm run to determine the index of the best move and the value of the best move
				if (moveValue > bestValue) {
					moveIndex = i;
					bestValue = moveValue;
				}
			}
		}
		
		//System.out.println(bestValue);
		
		//finally places the computers move on the board
		buttonList.get(moveIndex).setFont(Font.font(60));
		buttonList.get(moveIndex).setText("O");
		buttonList.get(moveIndex).setDisable(true);
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
			}
			else {
				Button button = (Button) event.getSource();
				button.setFont(Font.font(60));
		    	button.setDisable(true);
		    	button.setText("X");
		    	
		    	//checks if the user has won
		    	if(checkWinner("X")) {
			    	updates.setText("X is the winner!");
			    	for (Button b: buttonList) {
			    		b.setDisable(true);
			    	}
			    }
		    	//checks if there is a draw
		    	else if (!checkDraw()) {
					updates.setText("The game ended in a draw!");
				}
		    	//calls the AI to make its move
		    	else {
		    		makeAIMove();
			    	if(checkWinner("O")) {
				    	updates.setText("O is the winner!");
				    	for (Button b: buttonList) {
				    		b.setDisable(true);
				    	}
				    }
			    	else if (!checkDraw()) {
						updates.setText("The game ended in a draw!");
					}
		    	}
		    	
			}
		}
		
	}
	
}
