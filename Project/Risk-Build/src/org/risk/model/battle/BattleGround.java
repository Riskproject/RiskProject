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

import java.util.ArrayList;
import java.util.Random;

import org.risk.model.Logger;
import org.risk.model.State;
import org.risk.model.army.MechanizedInfantry;
import org.risk.model.army.Army;
/**
 * The instance of this class creates the battle and in builder pattern it acts as a director
 * @author Arij
 *
 */
public class BattleGround implements Runnable {

	State targetState;
	State attackingState;
	private BattleArmyBuilder battleArmyBuilder;
	private AttackingStateBattleArmyBuilder attackingStateBattleArmyBuilder;
	private TargetStateBattleArmyBuilder targetStateBattleArmyBuilder;
	public static ArrayList<String> targetStateCasualties;
	public static ArrayList<String> attackingStateCasualties;
	public static ArrayList<String> attackingStateRemainingArmies;
	public static String targetStateRemainingArmies;
	public static ArrayList<String> battleWinners;

	private String battleWinner;
	int strengthToBeRemovedFromBattleWinner = 0;
	/**
	 * Constructor
	 * @param targetState	represents the target state in the battle
	 * @param attackingState	represents the attacking staate in the battle
	 */
	public BattleGround(State targetState, State attackingState) {
		battleWinner = "";
		if (targetStateCasualties == null)
			targetStateCasualties = new ArrayList<String>();
		if (attackingStateCasualties == null)
			attackingStateCasualties = new ArrayList<String>();
		if (attackingStateRemainingArmies == null)
			attackingStateRemainingArmies = new ArrayList<String>();
		if (battleWinners == null)
			battleWinners = new ArrayList<String>();
		targetStateRemainingArmies = "";
		setTargetState(targetState);
		setAttackingState(attackingState);
	}
	/**
	 * The method sets the target state
	 * @param targetState	is the target state in the battle
	 */
	protected void  setTargetState(State targetState) {
		this.targetState = targetState;
	}
	/**
	 * The method sets the attacking state
	 * @param attackingState	is the attacking state in the battle
	 */
	protected void setAttackingState(State attackingState) {
		this.attackingState = attackingState;
	}
	/**
	 * The method sets the value of army builder
	 * @param battleArmyBuilder		is instance of BattleArmyBuilder
	 */
	public void setBattleArmyBuilder(BattleArmyBuilder battleArmyBuilder) {
		this.battleArmyBuilder = battleArmyBuilder;
	}
	/**
	 * This method initiates the battle
	 * @param state		is the instance of state and is the weakest state after the analysis
	 * @param strengthToBeRemoved	int value represent strength to be removed
	 */
	public void startBattles(State state, int strengthToBeRemoved) {

		battleArmyBuilder.setStrengthToBeRemoved(strengthToBeRemoved);
		battleArmyBuilder.setState(state);
		battleArmyBuilder.createNewBattleArmy();
		battleArmyBuilder.buildStateCasualties();
		battleArmyBuilder.buildStateRemianingArmy();
	}
	/** 
	 * The method excutes the threads
	 */
	public void run() {
		try {
			attackingStateBattleArmyBuilder = new AttackingStateBattleArmyBuilder();
			targetStateBattleArmyBuilder = new TargetStateBattleArmyBuilder();
			if (targetState.getArmyDetail().getArmyStrength(
					targetState.getArmyDetail()) > 0) {
				setFavouredState();
				strengthToBeRemovedFromBattleWinner = (targetState
						.getArmyDetail().getArmyStrength(
								targetState.getArmyDetail()) + attackingState
						.getArmyDetail().getArmyStrength(
								attackingState.getArmyDetail())) / 25;
			} else {
				battleWinner = attackingState.getStateName();
			}
			BattleGround.battleWinners.add(battleWinner + " wins.");
			setBattleArmyBuilder(attackingStateBattleArmyBuilder);
			if (battleWinner.equals(attackingState.getStateName())) {
				startBattles(attackingState,
						strengthToBeRemovedFromBattleWinner);
			} else {
				startBattles(attackingState, attackingState.getArmyDetail()
						.getArmyStrength(attackingState.getArmyDetail()));
			}

			synchronized (targetState) {
				setBattleArmyBuilder(targetStateBattleArmyBuilder);
				if (battleWinner.equals(targetState.getStateName())) {
					startBattles(targetState,
							strengthToBeRemovedFromBattleWinner);
				} else {
					startBattles(targetState, targetState.getArmyDetail()
							.getArmyStrength(targetState.getArmyDetail()));
				}
			}
		} catch (Exception ex) {
			throw ex;
		}
	}
	/**
	 * The method is used to assign the favoured state
	 */
	private void setFavouredState() {
		Random random = new Random();
		Army.setNumberOfStrength(1);
		MechanizedInfantry extraArmy = new MechanizedInfantry();
		int favouredStateIndex = random.nextInt(2);
		// Logger.logMessage("favouredStateIndex : "+favouredStateIndex);
		battleWinner(favouredStateIndex, extraArmy.armyStrength());
	}
	/**
	 * The method is used to identify the battle winner
	 * @param favouredStateIndex	int value represent the index of favoured state
	 * @param randomFactorArmyStrength		int value represent the random factory army strength
	 */
	private void battleWinner(int favouredStateIndex,
			int randomFactorArmyStrength) {
		//Logger.logMessage("***favouredStateIndex" + " : " + favouredStateIndex);
		int targetBattlearmyAndRandomFactor = 0;
		int attackBattlearmyAndRandomFactor = 0;
		if (favouredStateIndex == 0) {
			targetBattlearmyAndRandomFactor = targetState.getArmyDetail()
					.getArmyStrength(targetState.getArmyDetail())
					+ randomFactorArmyStrength;
			attackBattlearmyAndRandomFactor = attackingState.getArmyDetail()
					.getArmyStrength(attackingState.getArmyDetail());
		} else if (favouredStateIndex == 1) {
			targetBattlearmyAndRandomFactor = targetState.getArmyDetail()
					.getArmyStrength(targetState.getArmyDetail());
			attackBattlearmyAndRandomFactor = attackingState.getArmyDetail()
					.getArmyStrength(attackingState.getArmyDetail())
					+ randomFactorArmyStrength;
		}
		/*Logger.logMessage("***" + targetState.getStateName() + " : "
				+ targetBattlearmyAndRandomFactor);
		Logger.logMessage("***" + attackingState.getStateName() + " : "
				+ attackBattlearmyAndRandomFactor);*/
		if (targetBattlearmyAndRandomFactor > attackBattlearmyAndRandomFactor) {
			//Logger.logMessage(targetState.getStateName() + " wins");
			battleWinner = targetState.getStateName();

		} else {
			//Logger.logMessage(attackingState.getStateName() + " wins");
			battleWinner = attackingState.getStateName();
		}
	}

}