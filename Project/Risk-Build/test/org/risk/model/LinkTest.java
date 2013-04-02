/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.risk.model;

import static org.junit.Assert.*;
import junit.framework.TestCase;
import org.junit.Test;
import org.risk.model.*;

/**
 * The test class for Link class which test the getSourceState and getDestintionState method
 *	
 */

public class LinkTest extends TestCase {
	
	/**
	 * Test method for {@link org.risk.model.Link#getSourceState()}.
	 */
	
	@Test
	public void testGetSourceState() {
		Link testLink = new Link();
		State testState = new State();
		testLink.setSourceState(testState);
		assertSame(testState, testLink.getSourceState());
	}
	
	/**
	 * Test method for {@link org.risk.model.Link#getDestintionState()}.
	 */
	
	@Test
	public void testGetDestintionState() {
		Link testLink = new Link();
		State testState = new State();
		testLink.setDestintionState(testState);
		assertSame(testState, testLink.getDestintionState());
	}
	/**
	 * Test case for Link constructor
	 */
	@Test
	public void testLink() {
		Link testMyLink = new Link();
		State s1 = new State("s1");
		State s2 = new State("s2");
		
		testMyLink.setSourceState(s1);
		testMyLink.setDestintionState(s2);
		
		assertSame(s1, testMyLink.getSourceState());
		assertSame(s2, testMyLink.getDestintionState());
	}

}
