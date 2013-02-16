package org.risk.model.army;

/**
 * The class sets the concrete values for army type Heavy Precision Army.
 * 
 * @author Arij
 *
 */
public class HeavyPrecisionArtillery extends Army{
	public static int heavyPrecisionArtilleryStrength = 200;
	public static String armyName = "Heavy Precision Artillery";
	/**
	 * 
	 * This method sends the values to be set for army type Heavy Precision Artillery.
	 */
	public HeavyPrecisionArtillery(){
		setArmyName(armyName);
		setArmyStrength(heavyPrecisionArtilleryStrength*numberOfStates());
	}
	/**
	 * This method sends the values to be set for army type Heavy Precision Artillery.
	 * @param name		the name of army
	 */
	public HeavyPrecisionArtillery(String name){
		setArmyName(armyName);
		setArmyStrength(0);
	}
}
