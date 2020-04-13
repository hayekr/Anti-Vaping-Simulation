import javafx.scene.image.Image;

/**
 * Used to generate scenarios based on current vape statistics
 */
public class ScenarioHandler {

	private String delimiter;
	private String vapeKeyword;
	private String didNotVapeKeyword;

	private boolean vapedLastTime;
	private int timesVaped;

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
		return "Next scenario";
	}

	/**
	 * Used to get the explanation to be used assuming the user vapes
	 * @return the explanation
	 * NOTE: The vape history must be updated prior to running
	 */
	public String getVapedExplanation() {
		return "Vape explanation";
	}

	/**
	 * Used to get the explanation to be used assuming the user does not vape
	 * @return the explanation
	 * NOTE: Thee vape history must be updated prior to running
	 */
	public String getDidNotVapeExplanation() {
		return "Did not vape explanation";
	}

	/**
	 * Used to get the image representing the scenario
	 * @return the image
	 */
	public Image getImage() {
		Image testImage1 = new Image("file:Proposal1/src/Images/testImage1.jpg");
		Image testImage2 = new Image("file:Proposal1/src/Images/testImage2.jpg");
		Image testImage3 = new Image("file:Proposal1/src/Images/testImage3.jpg");
		switch (timesVaped % 3) {
			case 0: return testImage1;
			case 1: return testImage2;
			case 2: return testImage3;
		}
		return testImage1;
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
}
