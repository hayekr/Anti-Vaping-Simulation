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

	private int tempTestingCounter;

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
		tempTestingCounter = vapeHistoryArray.length - 1;
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
		// Design Review 1 Scenarios - no branching storyline
		scenariosArrayList.add(new Scenario(
				"Your friend comes up to you with an E-Cig and asks if you want to try it. He says he's been having a good time with it recently and hasn't noticed any negative effects. Do you want to try it out?",
				"You shouldn't have done that. He mentioned that he'd only been using it recently and negative side effects can take time to manifest.",
				"Good decision! There are many potential side effects that may only manifest later in a person's use of vaping",
				new Image("file:Proposal1/src/Images/scenario1.jpg"),
				"https://en.wikipedia.org/wiki/File:Electronic_Cigarette_and_USB_Charger_(14939561277)_(retouched).jpg"));
		scenariosArrayList.add(new Scenario(
				"Your friend comes up with E-Cig and asks if you want to try it. He knows that you’ve said no before and this time it doesn’t have any nicotine, it’s just the flavoring. Do you want to try it now?",
				"You shouldn't have done that. While there might not be any nicotine, there are still harmful compounds within the generated aerosol that can be harmful to lungs.",
				"Good decision! While there might not be any nicotine, there are still harmful compounds within the generated aerosol that can be harmful to lungs.",
				new Image("file:Proposal1/src/Images/scenario2.jpg"),
				"https://en.wikipedia.org/wiki/File:E-Cigarette_E-liquid_by_Vaping_Monkey_(9628487175).jpg"));
		scenariosArrayList.add(new Scenario(
				"Your friend comes up with E-Cig and asks if you want to try it. He just got a new cannabis E-cig and he says it makes him feel really good. Do you want to try it now?",
				"You shouldn't have done that. While there might not be any nicotine, there are still harmful compounds within the generated aerosol that can be harmful to lungs.",
				"Good decision! While there might not be any nicotine, there are still harmful compounds within the generated aerosol that can be harmful to lungs.",
				new Image("file:Proposal1/src/Images/scenario3.jpg"),
				"https://commons.wikimedia.org/wiki/File:98880448-marijuana-or-cannabis-leaf-icon-vector-logo-template-isolated-illustration-on-white-background-.jpg"));
		scenariosArrayList.add(new Scenario(
				"You're bored at home and a sibling has an E-Cig. You know that they're okay with you trying it, they've offered it to you before. Do you want to try it?",
				"You shouldn't have done that. Just because someone else is willing to risk their health doesn't mean you should risk yours as well.",
				"Good decision! Just because someone else is willing to risk their health doesn't mean you should risk yours as well.",
				new Image("file:Proposal1/src/Images/scenario4.jpg"),
				"https://en.wikipedia.org/wiki/File:Smoke_Screen_(16582794210).jpg"));
		// Temporary Testing Scenario Generator
		for (int i = 0; i < 15; i++) {
			switch(i % 3) {
				case 0: scenariosArrayList.add(new Scenario("Scenario0", "vapedExp0","didnotVapeExp0", new Image("file:Proposal1/src/Images/testImage1.jpg"), "credit0")); break;
				case 1: scenariosArrayList.add(new Scenario("Scenario1", "vapedExp1","didnotVapeExp1", new Image("file:Proposal1/src/Images/testImage2.jpg"), "credit1")); break;
				case 2: scenariosArrayList.add(new Scenario("Scenario2", "vapedExp2","didnotVapeExp2", new Image("file:Proposal1/src/Images/testImage3.jpg"), "credit2")); break;
				default:
			}
		}
	}

	/**
	 * Used to get a CreditableImage based on an index with rollover (up to 25 cycles negative, infinite cycles positive)
	 * @param index the index of the desired image
	 * @return the CreditableImage requested
	 */
	public CreditableImage getCreditableImageByIndexWithRollover(int index) {
		return scenariosArrayList.get((index + 25 * scenariosArrayList.size()) % scenariosArrayList.size()).getCreditableImage();
	}
}
