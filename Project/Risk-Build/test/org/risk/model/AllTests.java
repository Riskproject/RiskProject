

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
