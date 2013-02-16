package org.risk.model.battle;

import org.risk.model.army.Army;
import org.risk.model.army.ArmyDetail;
/**
 * The instance of this Class will be used to modify number of armies during the battle
 * @author Arij
 *
 */
public class BattleArmy {
	private ArmyDetail stateRemainingArmyDetail;
	private ArmyDetail stateCasualtiesDetail;
	
	/**
	 * The method will assign the stateRemaining army
	 * @param stateRemainingArmyDetail	is the instance of Army Detail
	 */
	public void setStateRemainingArmy(ArmyDetail stateRemainingArmyDetail){
		this.stateRemainingArmyDetail = stateRemainingArmyDetail;
	}
	/**
	 * The method will assign the state casulties
	 * @param stateCasualtiesDetail	is the instance of army Detail
	 */
	public void setStateCasualties(ArmyDetail stateCasualtiesDetail){
		this.stateCasualtiesDetail = stateCasualtiesDetail;
	}
	/**
	 * The method returns State remaining armies
	 * @return	returns list of armies stored in Army Detail object
	 */
	public ArmyDetail getStateRemainingArmy(){
		return this.stateRemainingArmyDetail;
	} 
	/**
	 * The method returns State casualties
	 * @return	returns details about state casualties stored in Army Detail object
	 */
	public ArmyDetail getStateCasualties(){
		return this.stateCasualtiesDetail;
	}
}
