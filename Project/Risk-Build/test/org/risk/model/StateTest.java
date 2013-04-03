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

package org.risk.model;


import static org.junit.Assert.*;

import java.util.ArrayList;

import junit.framework.TestCase;
import org.junit.Test;
import org.risk.model.*;
import org.risk.model.army.Army;

/**
 * The test class for State class which test the setIsCapital method
 * @author Zhang
 */
public class StateTest extends TestCase {

	
	/**
	 * Test method for {@link org.risk.model.State#setIsCapital(java.lang.Boolean)}.
	 */
	@Test
	public void testSetIsCapital() {
		State testState = new State();
		testState.setIsCapital(true);
		assertTrue(testState.getIsCapital());
	}
	
	/**
	 * Test case for constructor method 
	 */
	@Test
	public void testState(){
		State testState = new State();
		ArrayList<Link> testLinkList = new ArrayList<Link>();
		Resource testResource = new Resource();
		Army testArmy = new Army();
		
		assertEquals("unknown", testState.getStateName());
		assertEquals(0, testState.getStateID());
		assertEquals(0, testState.getCountryID());
		assertEquals(0, testState.getContinentID());
		assertFalse(testState.getIsCapital());
		assertNotNull(testLinkList);
		assertNotNull(testResource);
		assertNotNull(testArmy);
		
	}
	/**
	 * Testcase for getStateIndexById method.
	 * @throws Exception	is used for handling unfavourable conditions
	 */
	@Test
	public void testgetStateIndexByStateId()throws Exception {
		
		int stateId = 1;
		int returnedIndex=-1;
		ArrayList<State> testStatesList = new ArrayList<State>();
		assertNotNull(testStatesList);
		for(int i=0; i<10;i++){
			State testState = new State("State"+(i+1));
			testState.setStateID(i+1);
			testStatesList.add(testState);
		}
		testStatesList.get(2).setStateID(1);
		assertNotNull(testStatesList);
		
		State tempState = new State();
		
		returnedIndex = tempState.getStateIndexByStateId(stateId, testStatesList); 
		assertEquals(0, returnedIndex);

		
	}
	/**
	 * Testcase for getResourceLevelByStateId method.
	 * @throws Exception	is used for handling unfavourable conditions
	 */
	@Test
	public void testgetResourceLevelByStateId()throws Exception{
		
		int testStateId=2;
		State testState1 = new State("State1");
		State testState2 = new State("State2");
		State testState3 = new State("State3");
		
		Resource testResource1 = new Resource();
		Resource testResource2 = new Resource();
		
		testResource1.metalResource();
		testResource2.knowledgeResource();
				
		testState1.setStateID(1);
		testState2.setStateID(2);
		testState3.setStateID(3);
		
		testState1.setResource(testResource1);
		testState2.setResource(testResource2);
		testState3.setResource(testResource1);
		
		ArrayList<State> testStateList = new ArrayList<State>();
		assertTrue(testStateList.isEmpty());
		
		testStateList.add(testState1);
		testStateList.add(testState2);
		testStateList.add(testState3);

		State tempState = new State();
		
		testStateId = tempState.getResourceLevelByStateId(testStateId, testStateList);
		
		assertEquals(3, testStateId);
		
	}

}
