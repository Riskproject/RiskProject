/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
