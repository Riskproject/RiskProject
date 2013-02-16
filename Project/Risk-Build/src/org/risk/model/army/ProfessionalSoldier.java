package org.risk.model.army;

/**
 * The class sets the concrete values for army type Professional Soldier.
 * 
 * @author Arij
 *
 */
public class ProfessionalSoldier extends Army{
	public static int professionalSoldierStrength = 5;
	public static String armyName = "Professional Soldier";
	/**
	 * 
	 * This method sends the values to be set for army type Professional Soldier.
	 */
	public ProfessionalSoldier() {
		setArmyName(armyName);
		setArmyStrength(professionalSoldierStrength*numberOfStates());
	}
	/**
	 * This method sends the values to be set for army type Professional Soldier.
	 * @param name	represents the name
	 */
	public ProfessionalSoldier(String name) {
		setArmyName(armyName);
		setArmyStrength(0);
	}
}
