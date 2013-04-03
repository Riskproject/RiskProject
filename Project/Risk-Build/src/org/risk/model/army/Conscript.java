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

/**
 * The class sets the concrete values for army type Conscript.
 * 
 * @author Arij
 * 
 */
public class Conscript extends Army {
	public static int conscriptStrength = 2;
	public static String armyName = "Conscript";
	/**
	 * 
	 * This method sends the values to be set for army type Conscript.
	 * 
	 */
	public Conscript() {
		setArmyName(armyName);
		setArmyStrength(conscriptStrength*numberOfStates());
	}
	/**
	 * This method is used set the values conscript
	 * @param name
	 */
	public Conscript(String name) {
		setArmyName(armyName);
		setArmyStrength(0);
	}
}
