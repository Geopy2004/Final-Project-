package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoadingScreen extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Load the background image
        Image backgroundImage = new Image("file:/C:/Users/MSi/Downloads/ping.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);

        backgroundImageView.setFitWidth(480);
        backgroundImageView.setFitHeight(800);

        ProgressBar progressBar = new ProgressBar();

        Label loadingLabel = new Label("Loading");
        loadingLabel.setStyle("-fx-text-fill: white; -fx-font-size: 20;");

        VBox loadingVBox = new VBox(10, progressBar, loadingLabel);
        loadingVBox.setAlignment(Pos.CENTER);

        StackPane loadingPane = new StackPane();
        loadingPane.setAlignment(Pos.CENTER);
        loadingPane.getChildren().addAll(backgroundImageView, loadingVBox);

        Scene loadingScene = new Scene(loadingPane, 480, 800);

        primaryStage.setScene(loadingScene);
        primaryStage.show();

        Task<Void> renderTask = new Task<>() {
            final int totalSteps = 100;
            final int maxDots = 5;
            int currentDots = 1;

            @Override
            protected Void call() throws Exception {
                for (int i = 0; i <= totalSteps; i++) {
                    updateProgress(i, totalSteps);

                    // Simulate some work being done
                    Thread.sleep(30);

                    // Update loading dots
                    loadingLabel.setText("Loading" + ".".repeat(currentDots));
                    currentDots = (currentDots % maxDots) + 1;
                }

                // Create the main scene
                Scene mainScene = new Scene(new StackPane(), 480, 800);

                // Launch TicTacToeFX
                Platform.runLater(() -> {
                    Stage ticTacToeStage = new Stage();
                    new TicTacToeFX().start(ticTacToeStage);
                });

                return null;
            }
        };

        progressBar.progressProperty().bind(renderTask.progressProperty());

        renderTask.setOnSucceeded(event -> primaryStage.close());

        new Thread(renderTask).start();
    }
}
