package org.risk.model.army;

/**
 * The class sets the concrete values for army type Artillery.
 * 
 * @author Arij
 * 
 */
public class Artillery extends Army {
	public static int artilleryStrength = 20;
	public static String armyName = "Artillery";
	/**
	 * 
	 * This method sends the values to be set for army type Artillery.
	 */
	public Artillery() {
		setArmyName(armyName);
		setArmyStrength(artilleryStrength*numberOfStates());
	}
	/**
	 * The method is used to set the properties of Artillery
	 * @param name		represents the name 
	 */
	public Artillery(String name) {
		setArmyName(armyName);
		setArmyStrength(0);
	}

}
