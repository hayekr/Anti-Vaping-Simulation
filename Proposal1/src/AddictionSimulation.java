import java.awt.desktop.PreferencesEvent;

public class AddictionSimulation {

	private int timesVaped;
	private boolean vapedLastScenario;

	/**
	 * Constructor for AddictionSimulation
	 * @param timesVaped
	 * @param vapedLastScenario
	 */
	AddictionSimulation(int timesVaped, boolean vapedLastScenario) {
		this.timesVaped = timesVaped;
		this.vapedLastScenario = vapedLastScenario;
	}

	/**
	 * Default Constructor
	 */
	AddictionSimulation() {
		this(0,false);
	}

	/**
	 * Used to set the number of times a user vaped
	 * @param timesVaped the number of times a user vaped
	 * @return true if the value was set, false otherwise
	 */
	public boolean setTimesVaped(int timesVaped) {
		if (timesVaped < 0) return false;
		this.timesVaped = timesVaped;
		return true;
	}

	/**
	 * Used to set if the user vaped in the last scenario
	 * @param vapedLastScenario true if the user vaped in the last scenario, false otherwise
	 */
	public void setVapedLastScenario(boolean vapedLastScenario) {
		this.vapedLastScenario = vapedLastScenario;
	}

	/**
	 * Used to set the number of times a user vape and whether or not the user vaped in the last scenario
	 * @param timesVaped the number of times a user vaped
	 * @param vapedLastScenario true if the user vaped in the last scenario, false otherwise
	 * @return true if the values were set, false otherwise
	 */
	public boolean setSimulationValues(int timesVaped, boolean vapedLastScenario) {
		if (timesVaped < 0) return false;
		this.timesVaped = timesVaped;
		this.vapedLastScenario = vapedLastScenario;
		return true;
	}

	/**
	 * Used to see if the user needs to double click in order to not vape
	 * @return true if the user must double click, false otherwise
	 */
	public boolean requireDoubleClick() {
		return (timesVaped >= 1) && (timesVaped < 4);
	}

	/**
	 * Used to see if a popup should be displayed encouraging the user to vape
	 * @return true if the popup should be used, false otherwise
	 */
	public boolean popUp() {
		return timesVaped >= 4 && timesVaped < 6;
	}

	/**
	 * Used to see if the user should need to double click and see a popup
	 * @return true if the user must double click and a popup should be shown, false otherwise
	 */
	public boolean requireDoubleClickAndPopUp() {
		return timesVaped >= 6;
	}

	/**
	 * Used to see if the doNotVapeButton should be shrunk
	 * @return true if it should shrink, false otherwise
	 */
	public boolean shrinkDoNotVapeButton() {
		return timesVaped >= 5;
	}

	/**
	 * Used to seee if the vape button should be expanded
	 * @return true if it should expand, false otherwise
	 */
	public boolean expandVapeButton() {
		return timesVaped >= 7;
	}

	public boolean hideDoNotVapeButton() {
		return timesVaped >= 9;
	}

	/**
	 * Used to generate information regarding the addiction simulation for the user
	 * @return the explanation
	 */
	public String getExplanation() {
		StringBuilder sb = new StringBuilder();
		sb.append("You vaped a total of " + timesVaped + " times.\n");
		int timesVaped = this.timesVaped;
		if (this.timesVaped > 9) timesVaped = 9;
		switch (timesVaped) {
			case 9: sb.append("You experienced every simulated aspect of addiction.\n" +
					"After vaping 9 times, the do not vape button simply started disappearing.\n");
			case 8:
			case 7: sb.append("After vaping 7 times, the vape button became larger.\n");
			case 6: sb.append("After vaping 6 times, you had to both double click the do not vape button and close a popup.\n");
			case 5: sb.append("After vaping 5 times, the do not vape button became smaller.\n"); break;
			case 4: sb.append("After vaping 4 times, you had to close a popup before being able to choose not to vape.\n");
			case 3:
			case 2:
			case 1: sb.append("After vaping just once, you had to double click the do not vape button.\n");
			default:
		}
		sb.append("Obviously, this is not how addiction works in the real world. However, it is somewhat similar.\n" +
				"After becoming addicted, there is not always an easy way to reverse the addiction.\n" +
				"You may have vaped many times in a row and not even noticed some of the earlier effects.\n" +
				"This is exactly how addiction works in real life. You may not realize you're addicted until you try\n" +
				"to stop and are unable to. We hope that this simulation helped give a bit of insight into how exactly\n" +
				"addiction and vaping can negatively effect your life. Thank you for taking your time to work with us.");
		return sb.toString();
	}
}
