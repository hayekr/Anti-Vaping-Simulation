import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

	// Variables used for statistics generation
	private final static String delimiter = ",";
	private final static String vapeKeyWord = "vape";
	private final static String didNotVapeKeyWord = "no";

	@Override
	public void start(Stage primaryStage) throws Exception {

		StringBuilder vapeHistory = new StringBuilder();
		final String vapeKeyWordWithDelimiter = vapeKeyWord + delimiter;
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
		Parent aboutScreen = FXMLLoader.load(getClass().getResource("Screens/AboutScreen.fxml"));
		Scene infoScreenScene = new Scene(aboutScreen, SCREEN_WIDTH, SCREEN_HEIGHT);
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
		Button aboutScreenButton = (Button) titleScreen.lookup("#infoButton");
		aboutScreenButton.setOnAction(actionEvent -> {
			primaryStage.setScene(infoScreenScene);
			primaryStage.setTitle(infoScreenTitle);
		});
		// About Screen Buttons
		Button backToMainMenuButtonAboutScreen = (Button) aboutScreen.lookup("#backToMainMenuButton");
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
			explanationAfterNotVapingText.setText(getNextPart());
			explanationAfterVapingText.setText(getNextPart());
			scenarioText.setText(getNextPart());

			// Tracker for ending game
			tempRoundTracker.append("x");
			if (tempRoundTracker.toString().length() > 5) {
				primaryStage.setScene(endScreenScene);
				endScreenText.setText(generateStatistics(vapeHistory.toString()));
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

	/**
	 * Used to generate a user-friendly explanation of what their vaping, or lack thereof has done
	 * @param vapeHistory the String containing the history of vaping and not vaping
	 * @return
	 */
	private static String generateStatistics(String vapeHistory) {
		int timesVaped = 0;
		int timesNotVaped = 0;

		String[] vapeHistoryArray = vapeHistory.split(delimiter);
		for (int i = 0; i < vapeHistoryArray.length; i++) {
			if (vapeHistoryArray[i].equals(vapeKeyWord)) {
				timesVaped++;
			} else {
				timesNotVaped++;
			}
		}

		StringBuilder userMessage = new StringBuilder();
		if (timesVaped == 0) {
			userMessage.append("You haven't vaped at all. This cost you $0, meaning you have no financial obligation to anything but yourself!\n");
			return userMessage.toString();
		}
		else if (timesVaped == 1) {
			userMessage.append("You just vaped once during this simulation.\n");
		}
		else {
			userMessage.append("You have vaped " + timesVaped + " times during this simulation.\n");
		}
		userMessage.append(String.format("This could cost you up to $%.2f per ", (double) timesVaped / 4 * 16)); // Assuming 4 vapes cost $16
		userMessage.append((timesNotVaped + timesVaped) + " days, which is equal to ");
		userMessage.append(String.format("$%.2f per year, and that's without tax!", (365.0 / (timesNotVaped + timesVaped)) * ((double) timesVaped / 4 * 16)));
		return userMessage.toString();
	}


	private static int counter = -1;
	private static String getNextPart() {
		ArrayList<String> arrlist = new ArrayList<String>();
		for (int i = 0; i < 30; i++) {
			arrlist.add("test" + i);
		}
		counter++;
		return arrlist.get(counter);
	}
}
