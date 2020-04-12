/**
 * Used to generate scenarios based on current vape statistics
 */
public class ScenarioHandler {

	String delimiter;
	String vapeKeyword;
	String didNotVapeKeyword;

	boolean vapedLastTime;
	int timesVaped;

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
	 * @param vapeHistory the current vape history
	 * @return the scenario
	 */
	public String getScenario(String vapeHistory) {
		updateVapeHistory(vapeHistory);
		return getScenario();
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
	 * Used to get the explanation to be eusede assuming the user vapes
	 * @param vapeHistory the current vape history
	 * @return the explanation
	 */
	public String getVapedExplantion(String vapeHistory) {
		updateVapeHistory(vapeHistory);
		return getVapedExplanation();
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
	 * @param vapeHistory the current vape history
	 * @return the explanation
	 */
	public String getDidNotVapeExplanation(String vapeHistory) {
		updateVapeHistory(vapeHistory);
		return getDidNotVapeExplanation();
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
	 * Used to update statistics about a user's vaping to enable variable storylines
	 * @param vapeHistory the current vape history
	 */
	public void updateVapeHistory(String vapeHistory) {
		String[] vapeHistoryArray = vapeHistory.split(delimiter);
		System.out.println(vapeHistory);
		System.out.println(delimiter);
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
