package org.risk.model.army;

/**
 * The class sets the concrete values for army type Special Operations Soldier.
 * 
 * @author Arij
 *
 */
public class SpecialOperationsSoldier extends Army{
	public static int specialOperationsSoldierStrength = 10;
	public static String armyName = "Special Operations Soldier";
	/**
	 * 
	 * This method sends the values to be set for army type Special Operations Soldier.
	 */
	public SpecialOperationsSoldier(){
		setArmyName(armyName);
		setArmyStrength(specialOperationsSoldierStrength*numberOfStates());
	}
	/**
	 * This method sends the values to be set for army type Special Operations Soldier.
	 * @param name		represents the name
	 */
	public SpecialOperationsSoldier(String name){
		setArmyName(armyName);
		setArmyStrength(0);
	}
}
