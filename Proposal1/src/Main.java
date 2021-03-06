import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

	// Variables used for statistics generation
	private final static String delimiter = ",";
	private final static String vapeKeyWord = "vape";
	private final static String didNotVapeKeyWord = "no";

	// UI Values
	//		Do not vape button
	private final double doNotVapeButtonDefaultScale = 2.5;
	private final String doNotVapeButtonDefaultText = "Don't Vape";
	private final String doNotVapeButtonDoublePressText = "Are you sure?";
	//		Vape button
	private final double vapeButtonDefaultScale = 2.5;
	private final String vapeButtonDefaultText = "Vape";
	//		Continue button
	private final double continueButtonDefaultScale = 2.5;
	private final String continueButtonDefaultText = "Continue";

	// Object Construction
	private static ScenarioHandler scenarioHandler = new ScenarioHandler(delimiter, vapeKeyWord, didNotVapeKeyWord);
	private static AddictionSimulation addictionSimulation = new AddictionSimulation();

	// Addiction Simulation
	private int doublePressed = 1;
	private final String popUpText = "Are you sure you don't want to vape?";
	private final String popUpAffirmative = "Yes";
	private final double vapeScaleSpeed = 1.025;
	private final double doNotVapeScaleSpeed = 0.975;

	// Image Credit
	private int imageCreditIndex = 0;

	@Override
	public void start(Stage primaryStage) throws Exception {

		StringBuilder vapeHistory = new StringBuilder();
		final String vapeKeyWordWithDelimiter = vapeKeyWord + delimiter;
		final String didNotVapeKeyWordWithDelimiter = didNotVapeKeyWord + delimiter;

		final int SCREEN_WIDTH = 830;
		final int SCREEN_HEIGHT = 420;

		final String titleScreenTitle = "E-Cig Simulation";
		final String helpScreenTitle = "Help";
		final String gameScreenTitle = "E-Cig Simulation";
		final String infoScreenTitle = "Info";
		final String imageCreditScreenTitle = "Image Credits";


		// Define parents
		Parent titleScreen = FXMLLoader.load(getClass().getResource("Screens/TitleScreen.fxml"));
		Parent helpScreen = FXMLLoader.load(getClass().getResource("Screens/HelpScreen.fxml"));
		Parent aboutScreen = FXMLLoader.load(getClass().getResource("Screens/AboutScreen.fxml"));
		Parent gameScreen = FXMLLoader.load(getClass().getResource("Screens/GameScreen.fxml"));
		Parent endScreen = FXMLLoader.load(getClass().getResource("Screens/EndScreen.fxml"));
		Parent imageCreditScreen = FXMLLoader.load(getClass().getResource("Screens/ImageCreditScreen.fxml"));

		// Define Scenes
		Scene titleScreenScene = new Scene(titleScreen, SCREEN_WIDTH, SCREEN_HEIGHT);
		Scene helpScreenScene = new Scene(helpScreen, SCREEN_WIDTH, SCREEN_HEIGHT);
		Scene infoScreenScene = new Scene(aboutScreen, SCREEN_WIDTH, SCREEN_HEIGHT);
		Scene gameScreenScene = new Scene(gameScreen, SCREEN_WIDTH, SCREEN_HEIGHT);
		Scene endScreenScene = new Scene(endScreen, SCREEN_WIDTH, SCREEN_HEIGHT);
		Scene imageCreditScene = new Scene(imageCreditScreen, SCREEN_WIDTH, SCREEN_HEIGHT);

		// Define all UI elements
		Button titleScreen_helpScreenButton = (Button) titleScreen.lookup("#helpButton");
		Button titleScreen_aboutScreenButton = (Button) titleScreen.lookup("#infoButton");
		Button titleScreen_startGame = (Button) titleScreen.lookup("#playButton");
		Button helpScreen_backToMainMenuButton = (Button) helpScreen.lookup("#backToMainMenuButton");
		Button aboutScreen_backToMainMenuButton = (Button) aboutScreen.lookup("#backToMainMenuButton");
		Button aboutScreen_goToImageCreditsButton = (Button) aboutScreen.lookup("#goToImageCredits");
		Button gameScreen_vapeButton = (Button) gameScreen.lookup("#vapeButton");
		Button gameScreen_doNotVapeButton = (Button) gameScreen.lookup("#doNotVapeButton");
		Button gameScreen_continueButton = (Button) gameScreen.lookup("#continueButton");
		Button endScreen_backToMainMenuButton = (Button) endScreen.lookup("#backToMainMenuButton");
		Button imageCreditScreen_nextButton = (Button) imageCreditScreen.lookup("#nextButton");
		Button imageCreditScreen_previousButton = (Button) imageCreditScreen.lookup("#previousButton");
		Button imageCreditScreen_backToMainMenuButton = (Button) imageCreditScreen.lookup("#backToMainMenuButton");
		Text gameScreen_scenarioText = (Text) gameScreen.lookup("#scenarioTextField");
		Text gameScreen_explanationAfterVapingText = (Text) gameScreen.lookup("#explanationAfterVapingText");
		Text gameScreen_explanationAfterNotVapingText = (Text) gameScreen.lookup("#explanationAfterNotVapingText");
		Text endScreen_statisticsText = (Text) endScreen.lookup("#tempText");
		Text endScreen_addictionExplanationText = (Text) endScreen.lookup("#addictionExplanation");
		Text imageCreditScreen_imageCredit = (Text) imageCreditScreen.lookup("#imageCredit");
		ImageView gameScreen_imageDisplay = (ImageView) gameScreen.lookup("#imageTest");
		ImageView imageCreditScreen_image = (ImageView) imageCreditScene.lookup("#image");



		// Used for reduced code duplication
		// TODO: replace with better method that doesn't use buttons
		//		Back to main menu
		Button backToMainMenu = new Button();
		backToMainMenu.setOnAction(actionEvent -> {
			primaryStage.setScene(titleScreenScene);
			primaryStage.setTitle(titleScreenTitle);
		});
		//		Reset game buttons to default values
		Button gameButtonReset = new Button();
		gameButtonReset.setOnAction(actionEvent -> {
			// Reset vape button
			gameScreen_vapeButton.setText(vapeButtonDefaultText);
			gameScreen_vapeButton.setScaleX(vapeButtonDefaultScale);
			gameScreen_vapeButton.setScaleY(vapeButtonDefaultScale);
			// Reset do not vape button
			gameScreen_doNotVapeButton.setText(doNotVapeButtonDefaultText);
			gameScreen_doNotVapeButton.setScaleX(doNotVapeButtonDefaultScale);
			gameScreen_doNotVapeButton.setScaleY(doNotVapeButtonDefaultScale);
			// Reset continue button
			gameScreen_continueButton.setText(continueButtonDefaultText);
			gameScreen_continueButton.setScaleX(continueButtonDefaultScale);
			gameScreen_continueButton.setScaleY(continueButtonDefaultScale);
		});
		// 		Update scenario
		Button scenarioLoader = new Button();
		scenarioLoader.setOnAction(actionEvent -> {
			scenarioHandler.updateVapeHistory(vapeHistory.toString());
			addictionSimulation.setSimulationValues(scenarioHandler.getTimesVaped(), scenarioHandler.isVapedLastTime());
			gameScreen_explanationAfterNotVapingText.setText(scenarioHandler.getDidNotVapeExplanation());
			gameScreen_explanationAfterVapingText.setText(scenarioHandler.getVapedExplanation());
			gameScreen_scenarioText.setText(scenarioHandler.getScenario());
			gameScreen_imageDisplay.setImage(scenarioHandler.getImage());

		});
		Button imageCreditLoader = new Button();
		imageCreditLoader.setOnAction(actionEvent -> {
			imageCreditScreen_image.setImage(scenarioHandler.getCreditableImageByIndexWithRollover(imageCreditIndex).getImage());
			imageCreditScreen_imageCredit.setText(scenarioHandler.getCreditableImageByIndexWithRollover(imageCreditIndex).getCredit());
		});


		// About Screen Button Handling
		aboutScreen_backToMainMenuButton.setOnAction(actionEvent -> {
			backToMainMenu.fire();
		});
		aboutScreen_goToImageCreditsButton.setOnAction(actionEvent -> {
			imageCreditIndex = 0;
			imageCreditLoader.fire();
			primaryStage.setScene(imageCreditScene);
			primaryStage.setTitle(imageCreditScreenTitle);
		});

		// Image Credit Screen Handling
		imageCreditScreen_backToMainMenuButton.setOnAction(actionEvent -> {
			backToMainMenu.fire();
		});
		imageCreditScreen_nextButton.setOnAction(actionEvent -> {
			imageCreditIndex++;
			imageCreditLoader.fire();
		});
		imageCreditScreen_previousButton.setOnAction(actionEvent -> {
			imageCreditIndex--;
			imageCreditLoader.fire();
		});

		// Help Screen Button Handling
		helpScreen_backToMainMenuButton.setOnAction(actionEvent -> {
			backToMainMenu.fire();
		});

		// End Screen Handling
		// This code segment handles the button that sends the user back to the title screen after finishing simulation
		//		and resetting the game end tracker
		endScreen_backToMainMenuButton.setOnAction(actionEvent -> {
			backToMainMenu.fire();
			vapeHistory.delete(0, vapeHistory.length());
			scenarioHandler.resetIndex();
		});

		// Title screen button handling
		titleScreen_helpScreenButton.setOnAction(actionEvent -> {
			primaryStage.setTitle(helpScreenTitle);
			primaryStage.setScene(helpScreenScene);
		});
		titleScreen_aboutScreenButton.setOnAction(actionEvent -> {
			primaryStage.setScene(infoScreenScene);
			primaryStage.setTitle(infoScreenTitle);
		});

		// This code segment handles a user pressing the play button
		titleScreen_startGame.setOnAction(actionEvent -> {
			primaryStage.setTitle(gameScreenTitle);
			primaryStage.setScene(gameScreenScene);
			// Load in first scenario
			scenarioLoader.fire();
			// Reset buttons for new game
			gameButtonReset.fire();
		});

		// This code segment handles a user pressing the vape button
		gameScreen_vapeButton.setOnAction(actionEvent -> {
			// Hide/Reveal elements as needed
			gameScreen_continueButton.setVisible(true);
			gameScreen_doNotVapeButton.setVisible(false);
			gameScreen_vapeButton.setVisible(false);
			gameScreen_explanationAfterVapingText.setVisible(true);

			// Track History
			vapeHistory.append(vapeKeyWordWithDelimiter);
		});


		// Additional addiction simulation code
		ButtonType popUpAffirmativeButton = new ButtonType(popUpAffirmative);
		Alert popUp = new Alert(Alert.AlertType.CONFIRMATION, popUpText, popUpAffirmativeButton);
		popUp.setTitle(popUpText);

		// This code segment handles a user pressing the do not vape button
		// Addiction simulation code goes in here
		gameScreen_doNotVapeButton.setOnAction(actionEvent -> {
			// Addiction Simulation Code
			//		Double press
			if (addictionSimulation.requireDoubleClick() && (doublePressed == 0)) {
				gameScreen_doNotVapeButton.setText(doNotVapeButtonDoublePressText);
			}
			// 		Popup
			if (addictionSimulation.requirePopUp() && (doublePressed == 0)) {
				popUp.showAndWait();
				doublePressed++;
			}
			//		Popup and double click
			if (addictionSimulation.requireDoubleClickAndPopUp() && (doublePressed == 0)) {
				popUp.showAndWait();
				gameScreen_doNotVapeButton.setText(doNotVapeButtonDoublePressText);
			}


			// Ensure that, if needed, addiction simulation runs prior to advancing scenario
			if (doublePressed == 1) {
				// Hide/Reveal elements as needed
				gameScreen_continueButton.setVisible(true);
				gameScreen_doNotVapeButton.setVisible(false);
				gameScreen_vapeButton.setVisible(false);
				gameScreen_explanationAfterNotVapingText.setVisible(true);

				// Track history
				vapeHistory.append(didNotVapeKeyWordWithDelimiter);
			}
			doublePressed++;
		});


		StringBuilder tempRoundTracker = new StringBuilder();

		// Continue Button Handling
		// This code segment updates images and scenarios for next round
		gameScreen_continueButton.setOnAction(actionEvent -> {
			// Hide/Reveal elements as needed
			gameScreen_continueButton.setVisible(false);
			gameScreen_doNotVapeButton.setVisible(true);
			gameScreen_vapeButton.setVisible(true);
			gameScreen_explanationAfterNotVapingText.setVisible(false);
			gameScreen_explanationAfterVapingText.setVisible(false);

			// Addiction Simulation
			if (addictionSimulation.expandVapeButton()) {
				gameScreen_vapeButton.setScaleX(gameScreen_vapeButton.getScaleX() * vapeScaleSpeed);
				gameScreen_vapeButton.setScaleY(gameScreen_vapeButton.getScaleY() * vapeScaleSpeed);
			}
			if (addictionSimulation.shrinkDoNotVapeButton()) {
				gameScreen_doNotVapeButton.setScaleX(gameScreen_doNotVapeButton.getScaleX() * doNotVapeScaleSpeed);
				gameScreen_doNotVapeButton.setScaleY(gameScreen_doNotVapeButton.getScaleY() * doNotVapeScaleSpeed);
			}

			// Update scenario
			scenarioLoader.fire();

			// Addiction simulation
			if (addictionSimulation.requireDoubleClick() || addictionSimulation.requireDoubleClickAndPopUp() || addictionSimulation.requirePopUp()) {
				doublePressed = 0;
				gameScreen_doNotVapeButton.setText(doNotVapeButtonDefaultText);
			}
			else {
				doublePressed = 1;
			}

			// Tracker for ending game
			tempRoundTracker.append("x");
			// Not sure why you need to subtract 2, but it works
			if (tempRoundTracker.toString().length() > scenarioHandler.getNumberOfScenarios() - 2) {
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
