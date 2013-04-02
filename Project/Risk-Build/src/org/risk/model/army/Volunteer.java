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

package org.risk.model.army;

/**
 * The class sets the concrete values for army type Volunteer.
 * 
 * 
 * 
 */
public class Volunteer extends Army {
	public static int volunteerStrength = 1;
	public static String armyName = "Volunteer";
	/**
	 * 
	 * This method sends the values to be set for army type Volunteer.
	 */
	public Volunteer() {
		setArmyName(armyName);
		setArmyStrength(volunteerStrength*numberOfStates());
	}
	/**
	 * This method sends the values to be set for army type Volunteer.
	 * @param name		reresents the name
	 */
	public Volunteer(String name) {
		setArmyName(armyName);
		setArmyStrength(0);
	}
}
