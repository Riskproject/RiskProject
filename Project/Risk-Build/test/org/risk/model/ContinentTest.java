package org.risk.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import junit.framework.TestCase;
import org.junit.Test;
import org.risk.model.*;

/**
 * The test class for Continent class which test the addState method
 * @author Zhang
 */
public class ContinentTest extends TestCase {
	
	/**
	 * Test method for {@link org.risk.model.Continent#addState(org.risk.model.State)}.
	 */
	@Test
	public void testAddState() {
		Continent testContinent = new Continent();
		State testState1 = new State();
		State testState2 = new State();
		
		testContinent.addState(testState1);
		testContinent.addState(testState2);
		
		assertSame(testState2, testContinent.getMyStateList().get(1));
	}
	/**
	 * Test case for Continent Constructor
	 */
	@Test
	public void testContinent(){
		
		Continent c1 = new Continent();
		
		assertEquals("", c1.getContinentName());
		assertEquals(0, c1.getContinentID());
		assertNotNull(c1.getMyStateList());
	}
	
	/**
	 * Test case for setting name
	 */
	@Test
	public void testSetName(){
		Continent c1 = new Continent();
		c1.setContinentName("Asia");
		
		assertEquals("Asia", c1.getContinentName());
		
	}
	
	/**
	 * Test case for SetMyStateList method
	 */
	@Test
	public void testSetMyStateList(){
		Continent c1 = new Continent();
		ArrayList<State> testStateList = new ArrayList<State>();
		for(int i=0;i<4;i++){
			State s1 = new State("State"+(i+1));
			testStateList.add(s1);
		}
		assertTrue(c1.getMyStateList().isEmpty());
		c1.setMyStateList(testStateList);
		
		assertFalse(c1.getMyStateList().isEmpty());
	}
}
