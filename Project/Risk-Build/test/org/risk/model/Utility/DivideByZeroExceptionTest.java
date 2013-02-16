package org.risk.model.Utility;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Test;
import org.junit.internal.runners.TestMethod;
/**
 * Test class is use to Test Divide by zero Exception
 * 
 * @author Omer
 *
 */
public class DivideByZeroExceptionTest extends TestCase {
	/**
	 * Test case to test Divide by zero excception
	 */
	@Test
	public void testDivideByZeroException() {
		int testNum= 4;
		int quantity = 2;
		
		double total = testNum / quantity;
		assertEquals(2.0, total);
		
		String testMessage = null;
		try{
			
			quantity = 0;		
			if (quantity == 0 ){
				throw new DivideByZeroException();
			}
			else {
				assertNull(testMessage);
			}
		}
		catch(Exception e){
			testMessage = e.getMessage();					
		}
		
		assertNotNull(testMessage);
		assertEquals("Division by zero not allowed!!", testMessage);
	}

}
