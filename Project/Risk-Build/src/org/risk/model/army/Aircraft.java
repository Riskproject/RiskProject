package org.risk.model.army;

/**
 * The class sets the concrete values for army type Aircraft.
 * 
 * @author Arij
 *
 */
public class Aircraft extends Army{
	public static int aircraftStrength = 100;
	public static String armyName = "Aircraft";
	/**
	 * 
	 * This method sends the values to be set for army type Aircraft.
	 * 
	 * */
	public Aircraft() {
		setArmyName(armyName);
		setArmyStrength(aircraftStrength*numberOfStates());
	}
	/**
	 * The method sets the state name
	 * @param stateName		name of the states
	 */
	public Aircraft(String stateName){
		setArmyName(armyName);
		setArmyStrength(0);
	}
	
}
