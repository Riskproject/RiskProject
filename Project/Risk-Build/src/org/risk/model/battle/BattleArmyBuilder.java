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

import org.risk.model.State;
import org.risk.model.army.ArmyDetail;
/**
 * This is the abstract class for Builder pattern
 * @author Arij
 *
 */
public abstract class BattleArmyBuilder {
	protected BattleArmy battleArmy;
	protected org.risk.model.State state;
	
	int strengthToBeRemoved;
	/**
	 * The method is used to set the strength which will be removed
	 * @param strengthToBeRemoved	int value represent strength to be removed
	 */
	protected void setStrengthToBeRemoved(int strengthToBeRemoved ){
		this.strengthToBeRemoved  = strengthToBeRemoved ;
	}
	/**
	 * The method returns the strength to be removed
	 * @return	Returns int value which represent strength to be removed
	 */
	protected int getStrengthToBeRemoved (){
		return this.strengthToBeRemoved ;
	}
	/**
	 * The method is used to set the state
	 * @param state		is the instance of State
	 */
	protected void setState(State state){
		this.state = state;
	}
	/**
	 * The method is used to return the State object
	 * @return		Returns the instance of State
	 */
	protected State getState(){
		return this.state;
	}
	/**
	 * The method is used to return the Battle army
	 * @return	Returns the instance of Battle Army
	 */
	public BattleArmy getBattleArmy(){
		return this.battleArmy;
	}
	/**
	 * The method is used to Create Battle army
	 */
	public void createNewBattleArmy(){
		battleArmy = new BattleArmy();
		battleArmy = state.reduceArmyDetail(state, strengthToBeRemoved);
	}
	/**
	 * The abstract method build state casualties 
	 */
	public abstract void buildStateCasualties();
	/**
	 * The abstract method for buildStateRemianingArmy
	 */
	public abstract void buildStateRemianingArmy();
}
