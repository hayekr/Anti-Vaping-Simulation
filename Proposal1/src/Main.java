import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.sql.SQLOutput;
import java.util.concurrent.TimeUnit;

public class Main extends Application {


	@Override
	public void start(Stage primaryStage) throws Exception{

		final int SCREEN_WIDTH = 830;
		final int SCREEN_HEIGHT = 380;

		final String titleScreenTitle = "E-Cig Simulation";
		final String helpScreenTitle = "Help";
		final String gameScreenTitle = "E-Cig Simulation";
		final String infoScreenTitle = "Info";

		Parent titleScreen = FXMLLoader.load(getClass().getResource("TitleScreen.fxml"));
		Scene titleScreenScene = new Scene(titleScreen, SCREEN_WIDTH, SCREEN_HEIGHT);
		Parent helpScreen = FXMLLoader.load(getClass().getResource("HelpScreen.fxml"));
		Scene helpScreenScene = new Scene(helpScreen, SCREEN_WIDTH, SCREEN_HEIGHT);
		Parent infoScreen = FXMLLoader.load(getClass().getResource("AboutScreen.fxml"));
		Scene infoScreenScene = new Scene(infoScreen, SCREEN_WIDTH, SCREEN_HEIGHT);
		Parent gameScreen = FXMLLoader.load(getClass().getResource("GameScreen.fxml"));
		Scene gameScreenScene = new Scene(gameScreen, SCREEN_WIDTH, SCREEN_HEIGHT);


		// Set up Help screen
		Button helpScreenButton = (Button) titleScreen.lookup("#helpButton");
		helpScreenButton.setOnAction(actionEvent -> {
			primaryStage.setTitle(helpScreenTitle);
			primaryStage.setScene(helpScreenScene);
		});
		// Help Screen Buttons
		Button backToMainMenuButtonHelpScreen = (Button) helpScreen.lookup("#backToMainMenuButton");
		backToMainMenuButtonHelpScreen.setOnAction(actionEvent -> {
			primaryStage.setTitle(titleScreenTitle);
			primaryStage.setScene(titleScreenScene);
			primaryStage.show();
		});




		// Set up About screen
		Button aboutScreen = (Button) titleScreen.lookup("#infoButton");
		aboutScreen.setOnAction(actionEvent -> {
			primaryStage.setScene(infoScreenScene);
			primaryStage.setTitle(infoScreenTitle);
		});
		// About Screen Buttons
		Button backToMainMenuButtonAboutScreen = (Button) infoScreen.lookup("#backToMainMenuButton");
		backToMainMenuButtonAboutScreen.setOnAction(actionEvent -> {
			primaryStage.setTitle(titleScreenTitle);
			primaryStage.setScene(titleScreenScene);
			primaryStage.show();
		});




		// Setup for actual gameplay
		Button startGame = (Button) titleScreen.lookup("#playButton");
		startGame.setOnAction(actionEvent -> {
			primaryStage.setTitle(gameScreenTitle);
			primaryStage.setScene(gameScreenScene);
		});


		primaryStage.setTitle(titleScreenTitle);
		primaryStage.setScene(titleScreenScene);
		primaryStage.show();

	}




	public static void main(String[] args) {
		launch(args);
	}
}
