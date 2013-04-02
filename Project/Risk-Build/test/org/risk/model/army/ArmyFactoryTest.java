package org.risk.model.army;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Test;
/**
 * Test class to Test Army Factory
 * @author Omer
 *
 */
public class ArmyFactoryTest extends TestCase{
	/**
	 * Test case to test Armt Facctory
	 */
	@Test
	public void testArmyFactory() {
		int countryId = 1;
		int resourceLevel=2;
		int technologyLevel=1;
		int numberOfStates=4;
		Army testArmy = new Army();
		//ProfessionalSoldier testProfessionalSoldier = new ProfessionalSoldier();
		ArmyFactory testArmyFactory = new ArmyFactory();
		
		testArmy = testArmyFactory.ArmyFactory(resourceLevel, technologyLevel, numberOfStates);
		
		assertEquals("professional soldier", testArmy.armyName().toLowerCase());
		assertEquals(5*numberOfStates, testArmy.armyStrength());

	}

}
