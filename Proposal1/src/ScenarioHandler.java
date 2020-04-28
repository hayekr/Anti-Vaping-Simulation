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
		if (vapeHistoryArray[0].equals("")) {
			tempTestingCounter = 0;
		}
		else {
			tempTestingCounter = vapeHistoryArray.length;
		}
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
				new Image("file:Proposal1/src/Images/1920px-Electronic_cigarette_vape_pen.JPG"),
				"https://commons.wikimedia.org/wiki/Category:Njoy_vaping_device#/media/File:Electronic_cigarette_vape_pen.JPG"));
		scenariosArrayList.add(new Scenario(
				"You are relaxing at home browsing social media when you see an ad for vaping that claims vaping is safe and makes you want to try it. Do you do it?",
				"You shouldn’t have done that. Social media tends to portray vaping as a healthy alternative to smoking, but in reality it is just as dangerous.",
				"Good decision! Vaping ads claim that vaping is healthier than smoking and that it is not dangerous, but it is actually just as bad.",
				new Image("file:Proposal1/src/Images/800px-Smoke_Screen_(16582794210).jpg"),
				"https://en.wikipedia.org/wiki/File:Smoke_Screen_(16582794210).jpg"));
		scenariosArrayList.add(new Scenario(
				"You are at a baseball game and a buddy of yours is vaping. He’s offered to share before, do you want to try it?",
				"Your buddy may be fine vaping, but that doesn’t mean that you should be fine as well.",
				"Good decision! Just because your buddy is fine with the possible health risks doesn’t mean you should be too.",
				new Image("file:Proposal1/src/Images/800px-MLB_game.JPG"),
				"https://commons.wikimedia.org/wiki/File:MLB_game.JPG"));
		scenariosArrayList.add(new Scenario(
				"You are at a party and everyone seems to be vaping. You want to fit in, but do you want to vape?",
				"It may seem like everyone is vaping, but according to the CDC, around 1 in 5 students vape.",
				"Good decision! It may seem like everyone is vaping, but according to the CDC around 1 in 5 students vape.",
				new Image("file:Proposal1/src/Images/1920px-Juul_in_hand.jpg"),
				"https://commons.wikimedia.org/wiki/Category:Juul_vaping_device#/media/File:Juul_in_hand.jpg"));
		scenariosArrayList.add(new Scenario(
				"You saw an ad saying that vaping was safer than smoking cigarettes. You like the aesthetic of smoking, but want to be safe. Do you want to vape?",
				"While vaping can be safer than smoking cigarettes, safer does not mean safe. There are still health risks to vaping.",
				"Good decision! While vaping can be safer than smoking cigarettes, safer does not mean safe. There are still health risks to vaping.",
				new Image("file:Proposal1/src/Images/320px-Juul_vaping_device,_with_one_pod_(cropped).jpg"),
				"https://commons.wikimedia.org/wiki/Category:Juul_vaping_device#/media/File:Juul_vaping_device,_with_one_pod_(cropped).jpg"));
		scenariosArrayList.add(new Scenario(
				"Your parents have always told you that they disapprove of smoking due to the numerous health effects and issues regarding the odor, but you’ve seen discrete e-cigarettes. Do you want to use one?",
				"While you may disagree with what your parents say, they tend to have good reason for what they say, especially in the case of vaping.",
				"Good decision! Many things your parents say have logically sound reasoning behind them, especially in this case.",
				new Image("file:Proposal1/src/Images/1920px-Teen_vaping_is_now_getting_popular_due_to_discreet_vaping_devices.jpg"),
				"https://commons.wikimedia.org/wiki/Category:Juul_vaping_device#/media/File:Teen_vaping_is_now_getting_popular_due_to_discreet_vaping_devices.jpg"));
	}

	/**
	 * Used to get a CreditableImage based on an index with rollover (up to 25 cycles negative, infinite cycles positive)
	 * @param index the index of the desired image
	 * @return the CreditableImage requested
	 */
	public CreditableImage getCreditableImageByIndexWithRollover(int index) {
		return scenariosArrayList.get((index + 25 * scenariosArrayList.size()) % scenariosArrayList.size()).getCreditableImage();
	}

	/**
	 * Reset the scenario handler to the first position
	 * Allows for kiosk style play
	 */
	public void resetIndex() {
		tempTestingCounter = 0;
	}

	/**
	 * Used to get the number of available scenarios
	 * @return the number of available scenarios
	 */
	public int getNumberOfScenarios() {
		return scenariosArrayList.size();
	}
}
