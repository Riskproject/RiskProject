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



package org.risk.model;

import junit.framework.Test;
import junit.framework.TestSuite;
import org.risk.model.army.*;
import org.risk.model.battle.*;
import org.risk.model.Utility.*;

/**
 * The Class act as test suite for all the test classes
 * @author Zhang
 */

public class AllTests {
	public static Test suite() {
		TestSuite suite = new TestSuite("Test cases");
		suite.addTestSuite(ContinentTest.class);
		suite.addTestSuite(CountryTest.class);
		suite.addTestSuite(GameEngineTest.class);
		suite.addTestSuite(LinkTest.class);
		suite.addTestSuite(MapTest.class);
		suite.addTestSuite(StateTest.class);
		suite.addTestSuite(ArmyFactoryTest.class);
		suite.addTestSuite(NegativeValueExceptionTest.class);
		suite.addTestSuite(DivideByZeroExceptionTest.class);
		suite.addTestSuite(MapSaverTest.class);
		suite.addTestSuite(MapLoaderTest.class);
		suite.addTestSuite(ArmyDetailTest.class);
		suite.addTestSuite(BattleGroundTest.class);
		suite.addTestSuite(ResourceTest.class);
				
		return suite;
		
	}

}
