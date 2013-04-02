package org.risk.model.army;

/**
 * The class sets the concrete values for army type Conscript.
 * 
 * @author Arij
 * 
 */
public class Conscript extends Army {
	public static int conscriptStrength = 2;
	public static String armyName = "Conscript";
	/**
	 * 
	 * This method sends the values to be set for army type Conscript.
	 * 
	 */
	public Conscript() {
		setArmyName(armyName);
		setArmyStrength(conscriptStrength*numberOfStates());
	}
	/**
	 * This method is used set the values conscript
	 * @param name
	 */
	public Conscript(String name) {
		setArmyName(armyName);
		setArmyStrength(0);
	}
}
