package org.risk.model.army;

/**
 * The class sets the concrete values for army type Tactical Missile.
 * 
 * @author Arij
 *
 */
public class TacticalMissile extends Army{
	public static int tacticalMissileStrength = 500;
	public static String armyName = "Tactical Missile";
	/**
	 * 
	 * This method sends the values to be set for army type Tactical Missile.
	 */
	public TacticalMissile() {
		setArmyName(armyName);
		setArmyStrength(tacticalMissileStrength*numberOfStates());
	}
	/**
	 * This method sends the values to be set for army type Tactical Missile.
	 * @param name	represents the name
	 */
	public TacticalMissile(String name) {
		setArmyName(armyName);
		setArmyStrength(0);
	}
}
