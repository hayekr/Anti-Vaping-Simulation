import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		StringBuilder vapeHistory = new StringBuilder();
		final String delimiter = ",";
		final String vapeKeyWord = "vape";
		final String vapeKeyWordWithDelimiter = vapeKeyWord + delimiter;
		final String didNotVapeKeyWord = "no";
		final String didNotVapeKeyWordWithDelimiter = didNotVapeKeyWord + delimiter;

		final int SCREEN_WIDTH = 830;
		final int SCREEN_HEIGHT = 380;

		final String titleScreenTitle = "E-Cig Simulation";
		final String helpScreenTitle = "Help";
		final String gameScreenTitle = "E-Cig Simulation";
		final String infoScreenTitle = "Info";

		Parent titleScreen = FXMLLoader.load(getClass().getResource("Screens/TitleScreen.fxml"));
		Scene titleScreenScene = new Scene(titleScreen, SCREEN_WIDTH, SCREEN_HEIGHT);
		Parent helpScreen = FXMLLoader.load(getClass().getResource("Screens/HelpScreen.fxml"));
		Scene helpScreenScene = new Scene(helpScreen, SCREEN_WIDTH, SCREEN_HEIGHT);
		Parent infoScreen = FXMLLoader.load(getClass().getResource("Screens/AboutScreen.fxml"));
		Scene infoScreenScene = new Scene(infoScreen, SCREEN_WIDTH, SCREEN_HEIGHT);
		Parent gameScreen = FXMLLoader.load(getClass().getResource("Screens/GameScreen.fxml"));
		Scene gameScreenScene = new Scene(gameScreen, SCREEN_WIDTH, SCREEN_HEIGHT);
		Parent endScreen = FXMLLoader.load(getClass().getResource("Screens/EndScreen.fxml"));
		Scene endScreenScene = new Scene(endScreen, SCREEN_WIDTH, SCREEN_HEIGHT);


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
		});

		// End Screen Buttons
		Text endScreenText = (Text) endScreen.lookup("#tempText");
		Button backToMainMenuButtonEndScreen = (Button) endScreen.lookup("#backToMainMenuButton");
		backToMainMenuButtonEndScreen.setOnAction(actionEvent -> {
			primaryStage.setTitle(titleScreenTitle);
			primaryStage.setScene(titleScreenScene);
			vapeHistory.delete(0, vapeHistory.length());
		});



		// Setup for actual gameplay
		Button startGame = (Button) titleScreen.lookup("#playButton");
		startGame.setOnAction(actionEvent -> {
			primaryStage.setTitle(gameScreenTitle);
			primaryStage.setScene(gameScreenScene);
			/**
			 * Put code for loading in first situation/pictures here
			 */
		});

		/**
		 * Start Image Testing
 		 */
		Image testImage = new Image("file:Proposal1/src/Images/testImage.jpg");
		Image testImage2 = new Image("file:Proposal1/src/Images/testImage2.jpg");
		Image testImage3 = new Image("file:Proposal1/src/Images/testImage3.jpg");
		ImageView testImageView = (ImageView) gameScreen.lookup("#imageTest");
		/**
		 * End Image Testing
		 */


		StringBuilder tempRoundTracker = new StringBuilder();
		// Gameplay Buttons
		Button vapeButton = (Button) gameScreen.lookup("#vapeButton");
		Button doNotVapeButton = (Button) gameScreen.lookup("#doNotVapeButton");
		Button continueButton = (Button) gameScreen.lookup("#continueButton");
		Text scenarioText = (Text) gameScreen.lookup("#scenarioTextField");
		Text explanationAfterVapingText = (Text) gameScreen.lookup("#explanationAfterVapingText");
		Text explanationAfterNotVapingText = (Text) gameScreen.lookup("#explanationAfterNotVapingText");

		vapeButton.setOnAction(actionEvent -> {
			/**
			 * Code for handling a user vaping here
			 * It will also send the user into the acknowledgement segment
			 */

			// Hide/Reveal elements as needed
			continueButton.setVisible(true);
			doNotVapeButton.setVisible(false);
			vapeButton.setVisible(false);
			explanationAfterVapingText.setVisible(true);

			// Track History
			vapeHistory.append(vapeKeyWordWithDelimiter);
			testImageView.setImage(testImage);
			System.out.println("User vaped.");
		});

		doNotVapeButton.setOnAction(actionEvent -> {
			/**
			 * Code for handling a user not vaping here
			 * Code here needs to have some form of addiction simulation
			 * It will also send the user into the acknowledgement segment
			 */
			System.out.println("User did not vape.");
			if (vapeHistory.toString().split(delimiter)[vapeHistory.toString().split(delimiter).length-1].equals(vapeKeyWord)) {
				testImageView.setImage(testImage2);
			}
			else {
				testImageView.setImage(testImage3);
			}
			// Hide/Reveal elements as needed
			continueButton.setVisible(true);
			doNotVapeButton.setVisible(false);
			vapeButton.setVisible(false);
			explanationAfterNotVapingText.setVisible(true);

			// Track history
			vapeHistory.append(didNotVapeKeyWordWithDelimiter);
		});


		StringBuilder tempStringValue = new StringBuilder();
		continueButton.setOnAction(actionEvent -> {
			/**
			 * Code here needs to update scenario/images to next scenario
			 */
			System.out.println("User acknowledges explanation.");

			// Hide/Reveal elements as needed
			continueButton.setVisible(false);
			doNotVapeButton.setVisible(true);
			vapeButton.setVisible(true);
			explanationAfterNotVapingText.setVisible(false);
			explanationAfterVapingText.setVisible(false);

			// Update scenario/explanation for next round
			tempStringValue.append("X");
			explanationAfterNotVapingText.setText("Did not vape " + tempStringValue.toString());
			explanationAfterVapingText.setText("Vaped " + tempStringValue.toString());
			scenarioText.setText("scenario " + tempStringValue.toString());

			// Tracker for ending game
			tempRoundTracker.append("x");
			if (tempRoundTracker.toString().length() > 5) {
				primaryStage.setScene(endScreenScene);
				endScreenText.setText(vapeHistory.toString());
				tempRoundTracker.delete(0,tempRoundTracker.length());
			}
		});






		// Initialize UI
		primaryStage.setTitle(titleScreenTitle);
		primaryStage.setScene(titleScreenScene);
		primaryStage.show();

	}




	public static void main(String[] args) {
		launch(args);
	}
}
