import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * Used to generate scenarios based on current vape statistics
 */
public class ScenarioHandler {

	private String delimiter;
	private String vapeKeyword;
	private String didNotVapeKeyword;

	private boolean vapedLastTime;
	private int timesVaped;

	private ArrayList<Scenario> scenariosArrayList = new ArrayList<Scenario>();

	private int tempTestingCounter = 0;

	/**
	 * Constructor for Scenario Handler
	 * @param delimiter
	 * @param vapeKeyword
	 * @param didNotVapeKeyWord
	 */
	ScenarioHandler(String delimiter, String vapeKeyword, String didNotVapeKeyWord) {
		this.delimiter = delimiter;
		this.vapeKeyword = vapeKeyword;
		this.didNotVapeKeyword = didNotVapeKeyWord;
		generateScenarioList();
	}

	/**
	 * Default Constructor
	 */
	ScenarioHandler() {
		this("","","");
	}

	/**
	 * Used to get the current scenario based on vape history
	 * @return the current scenario
	 * NOTE: The vape history must be updated prior to running
	 */
	public String getScenario() {
		return scenariosArrayList.get(tempTestingCounter).getScenario();
	}

	/**
	 * Used to get the explanation to be used assuming the user vapes
	 * @return the explanation
	 * NOTE: The vape history must be updated prior to running
	 */
	public String getVapedExplanation() {
		return scenariosArrayList.get(tempTestingCounter).getVapedExplanation();
	}

	/**
	 * Used to get the explanation to be used assuming the user does not vape
	 * @return the explanation
	 * NOTE: Thee vape history must be updated prior to running
	 */
	public String getDidNotVapeExplanation() {
		return scenariosArrayList.get(tempTestingCounter).getDidNotVapeExplanation();
	}

	/**
	 * Used to get the image representing the scenario
	 * @return the image
	 */
	public Image getImage() {
		return scenariosArrayList.get(tempTestingCounter).getScenarioImage();
	}

	/**
	 * Used to update statistics about a user's vaping to enable variable storylines
	 * @param vapeHistory the current vape history
	 */
	public void updateVapeHistory(String vapeHistory) {
		String[] vapeHistoryArray = vapeHistory.split(delimiter);
		int vapeCount = 0;
		for (String s : vapeHistoryArray) {
			if (s.equals(vapeKeyword)) vapeCount++;
		}
		this.timesVaped = vapeCount;
		vapedLastTime = (vapeHistoryArray[vapeHistoryArray.length - 1].equals(vapeKeyword));
		tempTestingCounter++;
	}

	/**
	 * Used to get the number of times a user has vaped
	 * @return the number of times a user has vaped
	 */
	public int getTimesVaped() {
		return timesVaped;
	}

	/**
	 * Used to check if the user vaped in the previous scenario
	 * @return true if the user vaped in the previous scenario, false otherwise
	 */
	public boolean isVapedLastTime() {
		return vapedLastTime;
	}

	/**
	 * Used to generate the scenario list
	 */
	private void generateScenarioList() {
		// Temporary Testing Scenario Generator
		for (int i = 0; i < 150; i++) {
			switch(i % 3) {
				case 0: scenariosArrayList.add(new Scenario("Scenario0", "vapedExp0","dnVapedExp0", new Image("file:Proposal1/src/Images/testImage1.jpg"))); break;
				case 1: scenariosArrayList.add(new Scenario("Scenario1", "vapedExp1","dnVapedExp1", new Image("file:Proposal1/src/Images/testImage2.jpg"))); break;
				case 2: scenariosArrayList.add(new Scenario("Scenario2", "vapedExp2","dnVapedExp2", new Image("file:Proposal1/src/Images/testImage3.jpg"))); break;
				default:
			}
		}
	}
}
