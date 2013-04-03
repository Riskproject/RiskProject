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
