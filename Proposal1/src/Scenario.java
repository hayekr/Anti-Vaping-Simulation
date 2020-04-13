import javafx.scene.image.Image;

public class Scenario {
	private String scenario;
	private String vapedExplanation;
	private String didNotVapeExplanation;
	private Image scenarioImage;

	/**
	 * Constructor for scenario
	 * @param scenario the description of the scenario
	 * @param vapedExplanation the explanation of a user's choice if he or she vaped
	 * @param didNotVapeExplanation the explanation of a user's choice if he or she did not vape
	 * @param scenarioImage the image associated with the scenario
	 */
	Scenario(String scenario, String vapedExplanation, String didNotVapeExplanation, Image scenarioImage) {
		this.scenario = scenario;
		this.vapedExplanation = vapedExplanation;
		this.didNotVapeExplanation = didNotVapeExplanation;
		this.scenarioImage = scenarioImage;
	}

	/**
	 * Used to get the scenario
	 * @return the scenario
	 */
	public String getScenario() {
		return scenario;
	}

	/**
	 * Used to get the explanation of a user's choice if he or she vaped
	 * @return the explanation of a user's choice if he or she vaped
	 */
	public String getVapedExplanation() {
		return vapedExplanation;
	}

	/**
	 * Used to get the explanation of a user's choice if he or she did not vape
	 * @return the explanation of a user's choice if he or she did not vape
	 */
	public String getDidNotVapeExplanation() {
		return didNotVapeExplanation;
	}

	/**
	 * Used to get the image associated with the scenario
	 * @return the image associated with the scenario
	 */
	public Image getScenarioImage() {
		return scenarioImage;
	}
}
