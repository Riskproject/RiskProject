/**
 * 
 */
package org.risk.model;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;
import junit.framework.TestCase;
import org.junit.Test;
import org.risk.model.*;
import org.risk.model.army.Army;
import org.risk.model.army.ArmyDetail;

/**
 * The test class for Country class which test the getMyStateList method
 * @author Zhang
 */
public class CountryTest extends TestCase {
	

	/**
	 * Test method for {@link org.risk.model.Country#getMyStatesList()}.
	 */
	@Test
	public void testGetMyStatesList() {
		Country testCountry = new Country();
		State testState1 = new State();
		State testState2 = new State();
		ArrayList<State> testStates = new ArrayList<State>();
		testStates.add(testState1);
		testStates.add(testState2);
		testCountry.setMyStatesList(testStates);
		
		assertSame(testState1, testCountry.getMyStatesList().get(0));
	}
	/**
	 * Test case for Country Constructor
	 */
	@Test
	public void testCountry(){
		Technology testTechnology = new Technology();
		ArrayList<State> testStateList = new ArrayList<State>();
		ArmyDetail testArmyDetail = new ArmyDetail("test");
		Country c1 = new Country("Player 1", 1, Color.blue,testStateList,testTechnology,testArmyDetail);
		
		assertEquals("Player 1", c1.getCountryName());
		assertEquals(1, c1.getCountryID());
		//assertNotNull(c1.getArmy());
		assertNotNull(c1.getTechnology());
		assertNotNull(c1.getMyStatesList());
	
	}

	/**
	 * Test case for SetProductionNumber method
	 */
	@Test
	public void testSetProductionNumber(){
		Country c1 =new Country();
		assertEquals(0, c1.getProductionNumber());
		c1.setProductionNumber(2);
		
		assertEquals(2, c1.getProductionNumber());
		
	}
	
	/**
	 * Test case for getting and setting ProductionNumber method
	 */
	@Test
	public void testProductionType(){
		
		Country c1 =new Country();
		assertEquals(null, c1.getProductionType());
		c1.setProductionType("Nuclear Missile");
		
		assertEquals("Nuclear Missile", c1.getProductionType());

		
	}
	
	/**
	 * Test case for getTechnologyLevelByCountryId method
	 * @throws Exception	is used for handling unfavourable conditions
	 */
	@Test
	public void testgetTechnologyLevelByCountryId()throws Exception {

		 
		int countryId=2;
		int technologyLevel = 0;
		Country testCountry1 = new Country("Plyr1");
		Country testCountry2 = new Country("Plyr2");
		Country testCountry3 = new Country("Plyr3");
				
		Technology testTech1 = new Technology();
		Technology testTech2 = new Technology();
		
		ArrayList<Country> testCountriesList = new ArrayList<Country>();
		
		assertTrue(testCountriesList.isEmpty());
		
		testTech1.technologyLevelBasic();
		testTech2.technologyLevelMedium();
		
		testCountry1.setCountryID(1);
		testCountry2.setCountryID(2);
		testCountry3.setCountryID(3);
		
		testCountry1.setTechnology(testTech1);
		testCountry2.setTechnology(testTech2);
		testCountry3.setTechnology(testTech1);
		
		
		testCountriesList.add(testCountry1);
		testCountriesList.add(testCountry2);
		testCountriesList.add(testCountry3);
		
			
		assertFalse(testCountriesList.isEmpty());
		
		technologyLevel =  testCountry1.getTechnologyLevelByCountryId(countryId, testCountriesList);
		
		assertEquals(2, technologyLevel);
	}
	
	/**
	 * Test case for RemoveCapitals method
	 */
	@Test
	public void testRemoveCapitals(){
		
		Country testCountry = new Country();
		State testState1 = new State("State1");
		State testState2 = new State("State2");
		State testState3 = new State("State3");
		
		testState1.setIsCapital(false);
		testState1.setIsCapital(true);
		testState1.setIsCapital(true);
		
		ArrayList<State> testStateList = new ArrayList<State>();
		assertTrue(testStateList.isEmpty());
		
		testStateList.add(testState1);
		testStateList.add(testState2);
		testStateList.add(testState3);
		
		assertFalse(testStateList.isEmpty());
		
		ArrayList<State> testStateTempList = new ArrayList<State>();
		
		testStateTempList = testCountry.removeCapitals(testStateList);
		
		for(int count=0; count < testStateTempList.size(); count++){
			
			boolean isCapital = testStateTempList.get(count).getIsCapital();
			assertEquals(false, isCapital);
			
		}
	}
	/**
	 * Test case for getIdOfCountryControllingAllStates method
	 */
	@Test 
	public void testgetIdOfCountryControllingAllStates(){
		
		Country testCountry = new Country();
		testCountry.setCountryID(1);
		int testCountryId=0;
		State testState1 = new State("State1");
		State testState2 = new State("State2");
		State testState3 = new State("State3");
		
		testState1.setCountryID(1);
		testState2.setCountryID(1);
		testState3.setCountryID(1);
		
		ArrayList<State> testStateList = new ArrayList<State>();
		assertTrue(testStateList.isEmpty());
		
		testStateList.add(testState1);
		testStateList.add(testState2);
		testStateList.add(testState3);
		
		assertFalse(testStateList.isEmpty());
		
		testCountryId = testCountry.getIdOfCountryControllingAllStates(testStateList);
		
		assertEquals(1, testCountryId);
	}
	
	/**
	 * Test case for getCountryControllingAllCapitals method
	 */
	@Test
	public void testgetCountryControllingAllCapitals(){
		
		Country testCountry = new Country();
		testCountry.setCountryID(1);
		int testCountryId=0;
		State testState1 = new State("State1");
		State testState2 = new State("State2");
		State testState3 = new State("State3");
		
		testState1.setCountryID(1);
		testState2.setCountryID(1);
		testState3.setCountryID(1);
		
		testState1.setIsCapital(true);
		testState2.setIsCapital(true);
		testState3.setIsCapital(true);
		
		ArrayList<State> testStateList = new ArrayList<State>();
		assertTrue(testStateList.isEmpty());
		
		testStateList.add(testState1);
		testStateList.add(testState2);
		testStateList.add(testState3);
		
		assertFalse(testStateList.isEmpty());
		
		
		testCountryId = testCountry.getCountryControllingAllCapitals(testStateList);
		assertEquals(1, testCountryId);
	}
	/**
	 * Test case to getCoutries army Strength
	 */
	@Test 
	public void testGetCountryArmyStrength(){
		
		Country c1 = null;
		
		assertNull(c1);
		
		c1 = new Country();
		int testStrength = c1.getCountryArmyStrength();
		
		assertEquals(0, testStrength);	
	}
	@Test
	public void testGetTechnologyLevel(){
		
		Technology testTech = new Technology();
		testTech.technologyLevelMedium();
		
		Country testCountry = new Country();
		testCountry.setTechnology(testTech);
		assertEquals("medium", testCountry.getTechnology().technologyLevel().toLowerCase());
	}
}