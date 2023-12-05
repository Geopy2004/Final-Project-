package application;

import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class TicTacToeFX extends Application implements EventHandler<ActionEvent> {

    Random random = new Random();
    Stage stage;
    Label textfield = new Label();
    Button[] buttons = new Button[9];
    boolean player1_turn;
    Button pauseButton, resetButton;
    boolean isPaused = false;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.setTitle("Tic-Tac-Toe");

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(createTitlePanel());
        borderPane.setCenter(createButtonPanel());
        borderPane.setBottom(createButtonControlPanel());

        Scene scene = new Scene(borderPane, 480, 800);

        stage.setScene(scene);

        firstTurn();
        stage.show();
    }

    private BorderPane createTitlePanel() {
        BorderPane titlePane = new BorderPane();
        titlePane.setStyle("-fx-background-color: #191919;");
        textfield.setStyle("-fx-background-color: #191919; -fx-text-fill: #19ff00; -fx-font-size: 50;");
        textfield.setAlignment(Pos.CENTER);
        textfield.setText("Tic-Tac-Toe");
        titlePane.setCenter(textfield);
        return titlePane;
    }

    private GridPane createButtonPanel() {
        GridPane buttonPanel = new GridPane();
        buttonPanel.setStyle("-fx-background-color: #transparent;");
        buttonPanel.setAlignment(Pos.CENTER);
        buttonPanel.setHgap(5);
        buttonPanel.setVgap(5);

        for (int i = 0; i < 9; i++) {
            buttons[i] = new Button();
            buttons[i].setMinSize(160, 160);
            buttons[i].setStyle("-fx-font-size: 40; -fx-background-color: transparent;"); // Set to transparent
            buttonPanel.add(buttons[i], i % 3, i / 3);
            buttons[i].setOnAction(this);
        }

        buttonPanel.setGridLinesVisible(true);

        return buttonPanel;
    }




    private BorderPane createButtonControlPanel() {
        BorderPane buttonControlPane = new BorderPane();
        
        pauseButton = new Button("Pause");
        resetButton = new Button("Reset");

        pauseButton.setOnAction(this);
        resetButton.setOnAction(this);

        HBox buttonBox = new HBox(10, pauseButton, resetButton);
        buttonBox.setAlignment(Pos.CENTER); 

        buttonControlPane.setCenter(buttonBox);
        buttonControlPane.setStyle("-fx-background-color: #191919;");
        
        return buttonControlPane;
    }


    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == pauseButton) {
            togglePause();
        } else if (event.getSource() == resetButton) {
            reset();
        } else {
            for (int i = 0; i < 9; i++) {
                if (event.getSource() == buttons[i]) {
                    if (player1_turn) {
                        handlePlayerMove(i, "X", "#ff0000");
                    } else {
                        handlePlayerMove(i, "O", "#0000ff");
                    }
                }
            }
        }
    }

	
private void handlePlayerMove(int index, String symbol, String color) {
	    if (buttons[index].getText().equals("")) {
	        buttons[index].setStyle("-fx-text-fill: " + color + "; -fx-font-size: 40;");
	        buttons[index].setText(symbol);
	        player1_turn = !player1_turn;
	        textfield.setText(player1_turn ? "X turn" : "O turn");
	        if (symbol.equals("X")) {
	            checkForWin("Player X wins!");
	        } else {
	            checkForWin("Player O wins!");
	        }
	    }
	}
private void checkForWin(String string) {
    if (
            (buttons[0].getText().equals("X") && buttons[1].getText().equals("X") && buttons[2].getText().equals("X")) ||
            (buttons[3].getText().equals("X") && buttons[4].getText().equals("X") && buttons[5].getText().equals("X")) ||
            (buttons[6].getText().equals("X") && buttons[7].getText().equals("X") && buttons[8].getText().equals("X")) ||
            (buttons[0].getText().equals("X") && buttons[3].getText().equals("X") && buttons[6].getText().equals("X")) ||
            (buttons[1].getText().equals("X") && buttons[4].getText().equals("X") && buttons[7].getText().equals("X")) ||
            (buttons[2].getText().equals("X") && buttons[5].getText().equals("X") && buttons[8].getText().equals("X")) ||
            (buttons[0].getText().equals("X") && buttons[4].getText().equals("X") && buttons[8].getText().equals("X")) ||
            (buttons[2].getText().equals("X") && buttons[4].getText().equals("X") && buttons[6].getText().equals("X"))
    ) {
        showAlert("Player X wins!");
    } else if (
            (buttons[0].getText().equals("O") && buttons[1].getText().equals("O") && buttons[2].getText().equals("O")) ||
            (buttons[3].getText().equals("O") && buttons[4].getText().equals("O") && buttons[5].getText().equals("O")) ||
            (buttons[6].getText().equals("O") && buttons[7].getText().equals("O") && buttons[8].getText().equals("O")) ||
            (buttons[0].getText().equals("O") && buttons[3].getText().equals("O") && buttons[6].getText().equals("O")) ||
            (buttons[1].getText().equals("O") && buttons[4].getText().equals("O") && buttons[7].getText().equals("O")) ||
            (buttons[2].getText().equals("O") && buttons[5].getText().equals("O") && buttons[8].getText().equals("O")) ||
            (buttons[0].getText().equals("O") && buttons[4].getText().equals("O") && buttons[8].getText().equals("O")) ||
            (buttons[2].getText().equals("O") && buttons[4].getText().equals("O") && buttons[6].getText().equals("O"))
    ) {
        showAlert("Player O wins!");
    } else {
        boolean isDraw = true;
        for (int i1 = 0; i1 < 9; i1++) {
            if (buttons[i1].getText().equals("")) {
                isDraw = false;
                break;
            }
        }

        if (isDraw) {
            showAlert("It's a draw!");
        }
    }
}

	private void showAlert(String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Game Over");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();

		reset();
}



	private void togglePause() {
        isPaused = !isPaused;

        if (isPaused) {
            textfield.setText("Game is Paused");
        } else {
            textfield.setText(player1_turn ? "X turn" : "O turn");
        }
    }

	private void reset() {
	    for (int i = 0; i < 9; i++) {
	        buttons[i].setText("");
	        buttons[i].setStyle("-fx-font-size: 40; -fx-background-color: #ffffff;"); // Set to white
	        buttons[i].getStyleClass().clear();
	        buttons[i].setAlignment(Pos.CENTER);
	        buttons[i].getStyleClass().add("game-button");
	    }
	    player1_turn = (random.nextInt(2) == 0);
	    textfield.setText(player1_turn ? "X turn" : "O turn");
	    isPaused = false;
	}



    private void firstTurn() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (random.nextInt(2) == 0) {
            player1_turn = true;
            textfield.setText("X turn");
        } else {
            player1_turn = false;
            textfield.setText("O turn");
        }
    }
}

    
    
    