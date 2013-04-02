package org.risk.model.army;

/**
 * The class sets the concrete values for army type Nuclear Missile.
 * 
 * @author Arij
 *
 */
public class NuclearMissile extends Army{
	public static int nuclearMissileStrength = 1000;
	public static String armyName = "Nuclear Missile";
	/**
	 * 
	 * This method sends the values to be set for army type Nuclear Missile.
	 */
	public NuclearMissile(){
		setArmyName(armyName);
		setArmyStrength(nuclearMissileStrength*numberOfStates());
	}
	/**
	 * This method sends the values to be set for army type Nuclear Missile.
	 * @param name	represents the name
	 */
	public NuclearMissile(String name){
		setArmyName(armyName);
		setArmyStrength(0);
	}
}
