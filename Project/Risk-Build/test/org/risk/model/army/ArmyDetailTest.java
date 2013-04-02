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
