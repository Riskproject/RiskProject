package org.risk.model.Utility;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Test;
/**
 * Test Class to Test Negative value Exception
 * @author Omer
 *
 */
public class NegativeValueExceptionTest extends TestCase {
	/**
	 * Test case to test Negative value exception
	 */
	@Test
	public void testNegativeValueException() {
		String errorMessage = null;
		int testArmyStrength = 5;
		try {
			for (int i=5; i>=-1; i--){
				testArmyStrength--;
				if (testArmyStrength < 0) {
					throw new NegativeValueException();
				}
				else{
					assertNull(errorMessage);
				}
			}
		} catch (Exception e) {
			errorMessage = e.getMessage();
		}
		assertNotNull(errorMessage);
		assertEquals("Negative Value not allowed!!", errorMessage);

	}

}
