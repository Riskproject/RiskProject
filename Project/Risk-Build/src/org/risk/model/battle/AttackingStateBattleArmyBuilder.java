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

import org.risk.model.Logger;
import org.risk.model.State;
/**
 * The instance of this Class is used to generate attacking state army
 * @author Arij
 *
 */
public class AttackingStateBattleArmyBuilder extends BattleArmyBuilder {

	State attackingState;
	/**
	 * Assign casualties to state's army 
	 */
	public void buildStateCasualties() {
		attackingState = getState();
		battleArmy.setStateCasualties(battleArmy.getStateCasualties());
		attackingState.setCasualty(battleArmy.getStateCasualties());
		logAttackingStateBattleCasualty();
	}

	/**
	 * This method is used to generate remaining armies
	 */
	public void buildStateRemianingArmy() {
		attackingState = getState();
		battleArmy.setStateRemainingArmy(battleArmy.getStateRemainingArmy());
		attackingState.setArmyDetail(battleArmy.getStateRemainingArmy());
		logAttackingStateBattleRemainingArmies();
	}
	
	/**
	 * This method will log the casualties during battle
	 */
	private void logAttackingStateBattleCasualty() {
		attackingState = getState();
		String casualty = attackingState.getCasualty().getArmyCountStr(
				attackingState.getCasualty());
		if (casualty == "No Armies")
			casualty = "No casualties";
		BattleGround.attackingStateCasualties.add(attackingState.getStateName()
				+ " casualties: " + " \n " + casualty);
		/*Logger.logMessage("-->In attackingStatebattleArmy-->"+attackingState.getStateName() + " casualties: "
				+ " \n " + casualty);*/
		//Logger.logMessage("-->BattleGround.attackingStateCasualties SIZE--->"+BattleGround.attackingStateCasualties.size());
	}
	
	/**
	 * This method will log of creating attacking states of remaining armies
	 */
	private void logAttackingStateBattleRemainingArmies() {
		attackingState = getState();
		BattleGround.attackingStateRemainingArmies.add(attackingState
				.getStateName()
				+ ": \n "
				+ attackingState.getArmyDetail().getArmyCountStr(
						attackingState.getArmyDetail()));
	}
}
