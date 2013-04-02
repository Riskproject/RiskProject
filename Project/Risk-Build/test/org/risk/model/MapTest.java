package org.risk.model;

import static org.junit.Assert.*;
import java.util.ArrayList;
import junit.framework.TestCase;
import org.junit.Test;
import org.risk.model.*;
import org.risk.view.ControlPanel;

/**
 * The test class for Map class which test the MapValidation addState and addContinent method
 *	@author Zhang
 */
public class MapTest extends TestCase {

	/**
	 * Test method for map validation method.
	 */
	@Test
	public void testMapValidation() {

		String expStr = "<br>unknown is an isolated state.<br>unknown is an isolated state.<br>The map has less than two countries.";
			
		Continent con1 = new Continent();
		Continent con2 = new Continent();
		ArrayList<Continent> continents = new ArrayList<Continent>();
		continents.add(con1);
		continents.add(con2);
		con1.setContinentID(1);
		con1.setContinentID(2);

		State s1 = new State();
		State s2 = new State();
		ArrayList<State> states = new ArrayList<State>();
		states.add(s1);
		states.add(s2);
		s1.setStateID(1);
		s2.setStateID(2);
		s1.setContinentID(1);
		s2.setContinentID(2);

		
		Map testMap = Map.getInstance();
		testMap.setMyContinentList(continents);
		testMap.setMyStateList(states);

		assertTrue(testMap.validateMap().equals(expStr));
	}

	/**
	 * Test method for {@link org.risk.model.Map#addState(org.risk.model.State)}.
	 */
	@Test
	public void testAddState() {
		Map testMap = Map.getInstance();
		State testState1 = new State();
		State testState2 = new State();
		testState1.setStateID(1);
		testState2.setStateID(2);
		testMap.addState(testState1);
		testMap.addState(testState2);
		
		//assertEquals(testState1.getStateID(), testMap.getMyStateList().get(0).getStateID());
		assertEquals(1, testMap.getMyStateList().get(0).getStateID());
	}

	/**
	 * Test method for {@link org.risk.model.Map#addContinent(org.risk.model.Continent)}.
	 */
	@Test
	public void testAddContinent() {
		Map testMap = Map.getInstance();
		Continent testContinent1 = new Continent();
		testContinent1.setContinentID(1);
		testContinent1.setContinentID(2);
		Continent testContinent2 = new Continent();
		testMap.addContinent(testContinent1);
		testMap.addContinent(testContinent2);
		
		assertSame(testContinent2.getContinentID(), testMap.getMyContinentList().get(1).getContinentID());
		
	}
	/**
	 * Test case for setInstance Method. This case will test the implemented Singleton design pattern
	 */
	@ Test
	public void testGetInstance(){
		
		Map testMap;
		Map testMap2;
		testMap = Map.getInstance();

		assertNotNull(testMap);
		testMap2 = Map.getInstance();
		
		assertEquals(testMap, testMap2);
		
		assertEquals("", testMap.getMapName());
		
		assertEquals(testMap.getMapName(), testMap2.getMapName());
	}
	/**
	 * Testcase for testing observer Design pattern by adding observer to subject
	 */
	@ Test
	public void testAddObserver(){
		
		int totalObservers = -1;
		Map testMap = null;
		MapVisualization testMapVisualization = new MapVisualization();
		assertNull(testMap);
		
		testMap = Map.getInstance();
		assertNotNull(testMap);
		assertNotNull(testMapVisualization);
		
		totalObservers = testMap.countObservers();
		assertEquals(0, totalObservers);
		
		testMap.addObserver(testMapVisualization);
		totalObservers = testMap.countObservers();
		assertEquals(1, totalObservers);			
	}
	/**
	 * Testcase for testing observer Design pattern by removing observer to subject
	 */
	@ Test
	public void testRemoveObserver(){
		
		int countObservers = -1;
		Map testMyMap = null;
		MapVisualization testMyMapVisualization = new MapVisualization();
		MapVisualization testMapVisualization2 = new MapVisualization();
		assertNull(testMyMap);
		
		testMyMap = Map.getInstance();
		assertNotNull(testMyMap);
		assertNotNull(testMyMapVisualization);
		testMyMap.deleteObservers();
		
		countObservers = testMyMap.countObservers();
		
		assertEquals(0, countObservers);
		
		testMyMap.addObserver(testMyMapVisualization);
		testMyMap.addObserver(testMapVisualization2);
		countObservers = testMyMap.countObservers();
		
		assertEquals(2, countObservers);	
		
		testMyMap.deleteObserver(testMapVisualization2);
		countObservers = testMyMap.countObservers();
		
		assertEquals(1, countObservers);	
		
		testMyMap.addObserver(testMapVisualization2);
		testMyMap.deleteObservers();
		countObservers = testMyMap.countObservers();
		
		assertEquals(0, countObservers);
	}
	
//	@Test
//	public void testObserver(){
//		//GameEngine testGameEngine = new GameEngine();
//		MapVisualization testMapVisualization = null;
//		MapVisualization testMapVisualization2 = null;
//		Map testMap = null;
//		// Verifying Map is null 
//		assertNull(testMap);
//		
//		ArrayList<Continent> testContinentList = new ArrayList<Continent>();
//		for(int i= 0; i<5;i++){
//			Continent testContinent = new Continent("Continent" + (i+1));
//			
//		}
//		
//		testMap = Map.getInstance();
//		
//		// Verifying Map is not null
//		assertNotNull(testMap);
//		assertNull(testMapVisualization);
//		assertEquals(testMapVisualization, testMapVisualization2);
//		assertFalse(testMap.hasChanged());
//		
//		
//		testMapVisualization = new MapVisualization();
//		testMapVisualization.extractMap(testMap);
//		testMapVisualization2 = testMapVisualization; 
//		
//		int count = testMapVisualization.continents.size();
//		//assertEquals(0, count);
//		
//		assertEquals(testMapVisualization, testMapVisualization2);
//		
//		testMap.addObserver(testMapVisualization);
//		
//		
//		testMap.setMyContinentList(testContinentList);
//		testMapVisualization.extractMap(testMap);
//		System.out.println(testMapVisualization.continents.size());
//		//assertSame(testMapVisualization, testMapVisualization2);
//		
//	}
}

