package org.risk.model.army;

/**
 * The class sets the concrete values for army type Mechanized Infantry.
 * 
 * @author Arij
 *
 */
public class MechanizedInfantry extends Army{
	public static int mechanizedInfantryStrength = 50;
	public static String armyName = "MechanizedInfantry";
	/**
	 * 
	 * This method sends the values to be set for army type Mechanized Infantry.
	 */
	public MechanizedInfantry() {
		setArmyName(armyName);
		setArmyStrength(mechanizedInfantryStrength*numberOfStates());
	}
	/**
	 * This method sends the values to be set for army type Mechanized Infantry.
	 * @param name		sets the name
	 */
	public MechanizedInfantry(String name) {
		setArmyName(armyName);
		setArmyStrength(0);
	}
}
