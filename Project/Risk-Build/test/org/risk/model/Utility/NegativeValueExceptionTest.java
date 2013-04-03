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
