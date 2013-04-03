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
 * The class sets the concrete values for army type Nuclear Missile.
 * 
 * @author Arij
 *
 */
public class NuclearMissile extends Army{
	public static int nuclearMissileStrength = 1000;
	public static String armyName = "Nuclear Missile";
	/**
	 * 
	 * This method sends the values to be set for army type Nuclear Missile.
	 */
	public NuclearMissile(){
		setArmyName(armyName);
		setArmyStrength(nuclearMissileStrength*numberOfStates());
	}
	/**
	 * This method sends the values to be set for army type Nuclear Missile.
	 * @param name	represents the name
	 */
	public NuclearMissile(String name){
		setArmyName(armyName);
		setArmyStrength(0);
	}
}
