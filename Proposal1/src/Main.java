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

	// UI Values
	//		Do not vape button
	private int doNotVapeButtonScale = 2;
	private String doNotVapeButtonText = "Don't Vape";
	//		Vape button
	private int vapeButtonScale = 2;
	private String vapeButtonText = "Vape";
	//		Continue button
	private int continueButtonScale = 2;
	private String continueButtonText = "Continue";

	// Object Construction
	static ScenarioHandler scenarioHandler = new ScenarioHandler(delimiter, vapeKeyWord, didNotVapeKeyWord);
	static AddictionSimulation addictionSimulation = new AddictionSimulation();

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


		// Define parents
		Parent titleScreen = FXMLLoader.load(getClass().getResource("Screens/TitleScreen.fxml"));
		Parent helpScreen = FXMLLoader.load(getClass().getResource("Screens/HelpScreen.fxml"));
		Parent aboutScreen = FXMLLoader.load(getClass().getResource("Screens/AboutScreen.fxml"));
		Parent gameScreen = FXMLLoader.load(getClass().getResource("Screens/GameScreen.fxml"));
		Parent endScreen = FXMLLoader.load(getClass().getResource("Screens/EndScreen.fxml"));

		// Define Scenes
		Scene titleScreenScene = new Scene(titleScreen, SCREEN_WIDTH, SCREEN_HEIGHT);
		Scene helpScreenScene = new Scene(helpScreen, SCREEN_WIDTH, SCREEN_HEIGHT);
		Scene infoScreenScene = new Scene(aboutScreen, SCREEN_WIDTH, SCREEN_HEIGHT);
		Scene gameScreenScene = new Scene(gameScreen, SCREEN_WIDTH, SCREEN_HEIGHT);
		Scene endScreenScene = new Scene(endScreen, SCREEN_WIDTH, SCREEN_HEIGHT);

		// Define all UI elements
		Button titleScreen_helpScreenButton = (Button) titleScreen.lookup("#helpButton");
		Button titleScreen_aboutScreenButton = (Button) titleScreen.lookup("#infoButton");
		Button titleScreen_startGame = (Button) titleScreen.lookup("#playButton");
		Button helpScreen_backToMainMenuButton = (Button) helpScreen.lookup("#backToMainMenuButton");
		Button aboutScreen_backToMainMenuButton = (Button) aboutScreen.lookup("#backToMainMenuButton");
		Button gameScreen_vapeButton = (Button) gameScreen.lookup("#vapeButton");
		Button gameScreen_doNotVapeButton = (Button) gameScreen.lookup("#doNotVapeButton");
		Button gameScreen_continueButton = (Button) gameScreen.lookup("#continueButton");
		Button endScreen_backToMainMenuButton = (Button) endScreen.lookup("#backToMainMenuButton");
		Text gameScreen_scenarioText = (Text) gameScreen.lookup("#scenarioTextField");
		Text gameScreen_explanationAfterVapingText = (Text) gameScreen.lookup("#explanationAfterVapingText");
		Text gameScreen_explanationAfterNotVapingText = (Text) gameScreen.lookup("#explanationAfterNotVapingText");
		Text endScreen_statisticsText = (Text) endScreen.lookup("#tempText");
		Text endScreen_addictionExplanationText = (Text) endScreen.lookup("#addictionExplanation");


		// Used for reduced code duplication
		// TODO: replace with better method that doesn't use buttons
		//		Back to main menu
		Button backToMainMenu = new Button();
		backToMainMenu.setVisible(false);
		backToMainMenu.setOnAction(actionEvent -> {
			primaryStage.setScene(titleScreenScene);
			primaryStage.setTitle(titleScreenTitle);
		});
		//		Reset game buttons to default values
		Button gameButtonReset = new Button();
		gameButtonReset.setVisible(false);
		gameButtonReset.setOnAction(actionEvent -> {
			// Reset vape button
			gameScreen_vapeButton.setText(vapeButtonText);
			gameScreen_vapeButton.setScaleX(vapeButtonScale);
			gameScreen_vapeButton.setScaleY(vapeButtonScale);
			// Reset do not vape button
			gameScreen_doNotVapeButton.setText(doNotVapeButtonText);
			gameScreen_doNotVapeButton.setScaleX(doNotVapeButtonScale);
			gameScreen_doNotVapeButton.setScaleY(doNotVapeButtonScale);
			// Reset continue button
			gameScreen_continueButton.setText(continueButtonText);
			gameScreen_continueButton.setScaleX(continueButtonScale);
			gameScreen_continueButton.setScaleY(continueButtonScale);
		});


		// Set up Help screen button
		titleScreen_helpScreenButton.setOnAction(actionEvent -> {
			primaryStage.setTitle(helpScreenTitle);
			primaryStage.setScene(helpScreenScene);
		});

		// Help Screen Buttons
		helpScreen_backToMainMenuButton.setOnAction(actionEvent -> {
			backToMainMenu.fire();
		});




		// Set up About screen
		titleScreen_aboutScreenButton.setOnAction(actionEvent -> {
			primaryStage.setScene(infoScreenScene);
			primaryStage.setTitle(infoScreenTitle);
		});
		// About Screen Buttons
		aboutScreen_backToMainMenuButton.setOnAction(actionEvent -> {
			backToMainMenu.fire();
		});



		// This code segment handles a user pressing the play button
		// It needs to set the text and sizes on buttons to enable kiosk style play
		//
		titleScreen_startGame.setOnAction(actionEvent -> {
			primaryStage.setTitle(gameScreenTitle);
			primaryStage.setScene(gameScreenScene);
			/**
			 * Put code for loading in first scenario/pictures here
			 */
			// Reset buttons for new game
			gameButtonReset.fire();
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

		// This stringbuilder is used for ending thee game
		StringBuilder tempRoundTracker = new StringBuilder();
		// This code segment handles a user pressing the vape button
		gameScreen_vapeButton.setOnAction(actionEvent -> {
			// Hide/Reveal elements as needed
			gameScreen_continueButton.setVisible(true);
			gameScreen_doNotVapeButton.setVisible(false);
			gameScreen_vapeButton.setVisible(false);
			gameScreen_explanationAfterVapingText.setVisible(true);

			// Track History
			vapeHistory.append(vapeKeyWordWithDelimiter);
			testImageView.setImage(testImage);
		});

		// This code segment handles a user pressing the do not vape button
		// Addiction simulation code goes in here
		gameScreen_doNotVapeButton.setOnAction(actionEvent -> {
			// Addiction Simulation Code




			if (vapeHistory.toString().split(delimiter)[vapeHistory.toString().split(delimiter).length-1].equals(vapeKeyWord)) {
				testImageView.setImage(testImage2);
			}
			else {
				testImageView.setImage(testImage3);
			}
			// Hide/Reveal elements as needed
			gameScreen_continueButton.setVisible(true);
			gameScreen_doNotVapeButton.setVisible(false);
			gameScreen_vapeButton.setVisible(false);
			gameScreen_explanationAfterNotVapingText.setVisible(true);

			// Track history
			vapeHistory.append(didNotVapeKeyWordWithDelimiter);
		});



		// End Screen Handling
		// This code segment handles the button that sends the user back to the title screen after finishing simulation
		//		and resetting the game end tracker
		endScreen_backToMainMenuButton.setOnAction(actionEvent -> {
			backToMainMenu.fire();
			vapeHistory.delete(0, vapeHistory.length());
		});

		// Continue Button Handling
		// This code segment updates images and sceenarios for next round
		gameScreen_continueButton.setOnAction(actionEvent -> {
			/**
			 * Code here needs to update images to next scenario
			 */
			// Hide/Reveal elements as needed
			gameScreen_continueButton.setVisible(false);
			gameScreen_doNotVapeButton.setVisible(true);
			gameScreen_vapeButton.setVisible(true);
			gameScreen_explanationAfterNotVapingText.setVisible(false);
			gameScreen_explanationAfterVapingText.setVisible(false);

			// Update scenario/explanation for next round
			scenarioHandler.updateVapeHistory(vapeHistory.toString());
			addictionSimulation.setSimulationValues(scenarioHandler.getTimesVaped(), scenarioHandler.isVapedLastTime());
			gameScreen_explanationAfterNotVapingText.setText(scenarioHandler.getDidNotVapeExplanation());
			gameScreen_explanationAfterVapingText.setText(scenarioHandler.getVapedExplanation());
			gameScreen_scenarioText.setText(scenarioHandler.getScenario());

			// Tracker for ending game
			tempRoundTracker.append("x");
			if (tempRoundTracker.toString().length() > 5) {
				primaryStage.setScene(endScreenScene);
				endScreen_statisticsText.setText(generateStatistics(vapeHistory.toString()));
				endScreen_addictionExplanationText.setText(addictionSimulation.getExplanation());
				tempRoundTracker.delete(0,tempRoundTracker.length());
			}
		});





		gameButtonReset.fire();
		backToMainMenu.fire();
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
}
