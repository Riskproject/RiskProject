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
 * The class sets the concrete values for army type Aircraft.
 * 
 * @author Arij
 *
 */
public class Aircraft extends Army{
	public static int aircraftStrength = 100;
	public static String armyName = "Aircraft";
	/**
	 * 
	 * This method sends the values to be set for army type Aircraft.
	 * 
	 * */
	public Aircraft() {
		setArmyName(armyName);
		setArmyStrength(aircraftStrength*numberOfStates());
	}
	/**
	 * The method sets the state name
	 * @param stateName		name of the states
	 */
	public Aircraft(String stateName){
		setArmyName(armyName);
		setArmyStrength(0);
	}
	
}
