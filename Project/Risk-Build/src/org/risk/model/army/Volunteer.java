package org.risk.model.army;

/**
 * The class sets the concrete values for army type Volunteer.
 * 
 * @author Arij
 * 
 */
public class Volunteer extends Army {
	public static int volunteerStrength = 1;
	public static String armyName = "Volunteer";
	/**
	 * 
	 * This method sends the values to be set for army type Volunteer.
	 */
	public Volunteer() {
		setArmyName(armyName);
		setArmyStrength(volunteerStrength*numberOfStates());
	}
	/**
	 * This method sends the values to be set for army type Volunteer.
	 * @param name		reresents the name
	 */
	public Volunteer(String name) {
		setArmyName(armyName);
		setArmyStrength(0);
	}
}
