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

public class ArmyDetailTest extends TestCase {

	
	@Test
	public void testArmyDetail(){
		
		Aircraft testAirCraft = null;
		NuclearMissile testNuke = null;
		
		assertNull(testAirCraft);
		assertNull(testNuke);
		
		ArmyDetail testArmyDetail = new ArmyDetail();
		
		testAirCraft = testArmyDetail.getAircraft();
		testNuke = testArmyDetail.getNuclearMissile();
		
			
		assertEquals(1000, testNuke.nuclearMissileStrength);
		assertEquals(100, testAirCraft.aircraftStrength);

		
	}
	
//	@Test
//	public void testgetArmyCountStr() {
//		
//		ArmyDetail testArmyDetail = new ArmyDetail();
//		ArmyDetail testArmyDetail2 = new ArmyDetail();
//		String testName = null;
//		int testStrength=0;
//		
//		assertNull(testName);
//		testStrength =testArmyDetail.getArmyStrength(testArmyDetail2); 
//		//testName = testArmyDetail.getArmyCountStr(testArmyDetail2);
//		System.out.println(testName);
//		
//	}

}
