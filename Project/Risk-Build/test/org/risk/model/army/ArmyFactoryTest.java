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
