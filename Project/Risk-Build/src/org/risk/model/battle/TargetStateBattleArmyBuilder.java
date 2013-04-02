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
import org.risk.model.army.ArmyDetail;
/**
 * The instance of ths class represent target state army builder 
 *
 *
 */
public class TargetStateBattleArmyBuilder extends BattleArmyBuilder {
	public static State totalArmyDetailOfTargetState;
	private State targetState;
	/**
	 * The method is used to build state casulaties
	 */
	public void buildStateCasualties() {
		targetState = getState();
		battleArmy.setStateCasualties(battleArmy.getStateCasualties());
		targetState.setCasualty(battleArmy.getStateCasualties());
		logTargetStateBattleCasualty();
	}
	/**
	 * The method is used to build state remaining army
	 */
	public void buildStateRemianingArmy() {
		if (totalArmyDetailOfTargetState == null)
			totalArmyDetailOfTargetState = new State();

		targetState = getState();
		battleArmy.setStateRemainingArmy(battleArmy.getStateRemainingArmy());
		targetState.setArmyDetail(battleArmy.getStateRemainingArmy());
		totalArmyDetailOfTargetState.addArmyDetail(battleArmy
				.getStateRemainingArmy());

/*		Logger.logMessage("*************In buildStateRemianingArmy "
				+ totalArmyDetailOfTargetState.getArmyDetail().getArmyCountStr(
						totalArmyDetailOfTargetState.getArmyDetail()));*/
	}
	/**
	 * The method is use to log target state battle casualties
	 */
	private void logTargetStateBattleCasualty() {
		targetState = getState();
		String casualty = targetState.getCasualty().getArmyCountStr(
				targetState.getCasualty());
		if (casualty == "No Armies")
			casualty = "No casualties";
		BattleGround.targetStateCasualties.add(targetState.getStateName()
				+ " casualties: " + " \n " + casualty);
		/*Logger.logMessage("-->In targetstatebattleArmy-->"
				+ targetState.getStateName() + " casualties: " + " \n "
				+ casualty);
		Logger.logMessage("-->BattleGround.targetStateCasualties SIZE--->"
				+ BattleGround.targetStateCasualties.size());*/
	}
}
