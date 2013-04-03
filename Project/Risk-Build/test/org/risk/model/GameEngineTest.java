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
import java.util.Collections;

import junit.framework.TestCase;
import org.junit.Test;
import org.risk.model.*;
import org.risk.model.army.Army;
import org.risk.model.army.ArmyFactory;

/**
 * The test class for GameEngine class which test its various methods
 * @author Omer
 *
 */
public class GameEngineTest extends TestCase {

	/**
	 * Test case to populate map
	 */
	@Test
	public void testPopulateMap() {
		
		Map map;
		ArrayList<Continent> myContinentList = new ArrayList<Continent>();
		ArrayList<Country> myCountryList = new ArrayList<Country>();
		ArrayList<State> myStateList = new ArrayList<State>();
		ArrayList<Link> myLinkList = new ArrayList<Link>();
		

		map = Map.getInstance();
		myContinentList = map.getMyContinentList();
		myCountryList = map.getMyCountryList();
		myStateList = map.getMyStateList();
		myLinkList = map.getMyLinkList();
		assertNotNull(map);
		assertNotNull(myContinentList);
		assertNotNull(myCountryList);
		assertNotNull(myStateList);
		assertNotNull(myLinkList);	
	}
	
	/**
	 * Test case for AssignTechnologyLevelToCountry method
	 */
	@Test
	public void testAssignTechnologyLevelToCountry(){
		
		Technology tech = new Technology();
		tech.technologyLevelBasic();
		ArrayList<Country> testCountryList = new ArrayList<Country>();
		Country testCountry1 = new Country("Player1");
		Country testCountry2 = new Country("Player2");
		Country testCountry3 = new Country("Player3");
		
		testCountryList.add(testCountry1);
		testCountryList.add(testCountry2);
		testCountryList.add(testCountry3);
		
		for (Country country : testCountryList) {
			country.setTechnology(tech);
		}
		assertEquals(1, testCountry1.getTechnology().technologyLevelNo());
		//assertEquals(1, testCountryList.get(0).getTechnology().technologyLevelNo());
	}
	
	/**
	 * Test case for countryTurnSequence method
	 */
	@Test
	public void testcountryTurnSequence(){
		
		GameEngine testGameEngine = new GameEngine();
		ArrayList<Country> testCountryList = new ArrayList<Country>();
		ArrayList<Country> testCountryTurnList = new ArrayList<Country>();
		for(int i=0;i<4;i++){
			Country testCountry = new Country("Player"+(i+1));
			testCountryList.add(testCountry);
		}
		
		testCountryTurnList = testCountryList;
		
		//Verify both arraylist are equal
		assertEquals(testCountryTurnList, testCountryList);
		//Verify the first value as per input
		assertEquals("Player1", testCountryList.get(0).getCountryName());
		
		testCountryTurnList =  testGameEngine.countryTurnSequence(testCountryList);
		
		
		
		Collections.shuffle(testCountryList);
		assertNotSame("Player1", testCountryTurnList.get(0).getCountryName());		
	}
	
	/**
	 * Test case for Production phase
	 */
	@Test 
	public void testProductionPhase(){
		int countryId =1;
		int resourceLevel=2;
		int technologyLevel=1;
		int numberOfStates=4;
		Army testArmy = new Army();
		ArmyFactory testArmyFactory = new ArmyFactory();
		
		testArmy = testArmyFactory.ArmyFactory(resourceLevel, technologyLevel, numberOfStates);
		
		assertEquals("professional soldier", testArmy.armyName().toLowerCase());
		assertEquals(5*numberOfStates, testArmy.armyStrength());

	}
	
//	@Test
//	public void testBattlePhase(){
//		boolean test=false;
//		String message=null;
//		
//		ArrayList<Integer> tempArrayList = new ArrayList<Integer>();
//		Resource r1 = new Resource();
//	    r1.metalResource();
//		State targetState = new State("s1",01,01,01,false,tempArrayList,r1);
//		State attackingState = new State("s2",02,02,02,false,tempArrayList,r1);
//		ArrayList<State> testArrayList = new ArrayList<State>();
//		testArrayList.add(attackingState);
//		//GameEngine ge = new GameEngine();
//		GameEngine.attackingStates.add(testArrayList.get(0));
//		GameEngine.weakestState = targetState;
//		//GameEngine.
//		
//		try{
//		Country c1 = new Country();
//		c1.setCountryID(1);
//		int countryId = c1.getCountryID();
//		System.out.println(countryId);
//		GameEngine testGameEngine = new GameEngine();
//		test = testGameEngine.battlePhase(countryId);
//		}catch(Exception e){
//			message = e.getMessage();
//		}
//		System.out.println(test);
//		assertTrue(test);
//	}
}