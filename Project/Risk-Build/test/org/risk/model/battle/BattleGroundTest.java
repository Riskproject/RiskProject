/*
* Copyright (C) 2013 author Arij,Omer
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package org.risk.model.battle;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.risk.model.Link;
import org.risk.model.Resource;
import org.risk.model.State;
import org.risk.model.army.ArmyDetail;

import junit.framework.TestCase;

/**
 * Test class for testing Battle Ground
 * @author Omer
 *
 */
public class BattleGroundTest extends TestCase {
//
	@Test
	public void testStartBattles(){
		
		ArrayList<Integer> tempArrayList = new ArrayList<Integer>();
		Resource r1 = new Resource();
	    r1.metalResource();
		State targetState = new State("s1",01,01,01,false,tempArrayList,r1);
		State attackingState = new State("s2",02,02,02,false,tempArrayList,r1);
		//targetState
		ArmyDetail testArmyDetail = new ArmyDetail();
		
		attackingState.setArmyDetail(testArmyDetail);
		targetState.setArmyDetail(testArmyDetail);
		
		BattlePhase testBattlePhase = new BattlePhase();
		
		testBattlePhase.setAttackingState(attackingState);
		testBattlePhase.setTargetState(targetState);
		boolean test=true;
		try{
			test = testBattlePhase.BattleGround(targetState, attackingState);
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		
		assertFalse(test);
		//assertTrue(test);
		//assert
		
//		BattleGroundTest b = new BattleGroundTest(targetState, attackingState);
//			
//			targetState.getArmyDetail().getArmyStrength(targetState.getArmyDetail());//target int
//		//	attackingState.getArmyDetail().getArmyStrength(attackingState.getArmyDetail())//attacking strength int
//			NuclearMissile n = new NuclearMissile();
//			//targetState.setArmyDetail(armyDetail)
		}


	/**
	 * The Test case to test Constructor
	 */
	@Test
	public void testBattleGround() {
		State testTargetState = new State();
		State testAttackinState = new State();
		
		BattleGround testBattleGround = new BattleGround(testTargetState, testAttackinState);
		
		assertNotNull(testBattleGround.attackingState);
		assertNotNull(testBattleGround.targetState);
		assertNotNull(testBattleGround.targetStateCasualties);
		assertEquals(0, testBattleGround.targetStateCasualties.size());
		assertNotNull(testBattleGround.attackingStateCasualties);
		assertEquals(0, testBattleGround.attackingStateCasualties.size());
		assertNotNull(testBattleGround.attackingStateRemainingArmies);
		assertEquals(0, testBattleGround.attackingStateRemainingArmies.size());
		
	}

}
