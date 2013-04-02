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

import org.risk.model.Resource;
import org.risk.model.Technology;

/**
 * The class is responsible for which type of the army to call.
 * 
 * 
 * 
 */
public class ArmyFactory {

	Resource resource = new Resource();
	Technology technology = new Technology();

	/**
	 * The constructor decides on the basis of Resource Level and Technology
	 * Level, which army type to be produced.
	 * 
	 * @param resourceLevel
	 *            : Resource level of Country
	 * @param technologyLevel
	 *            : Technology level of Country
	 * @param numberOfStates
	 *            : States for which army to be produced.
	 * @return : Returns the Army
	 */
	public Army ArmyFactory(int resourceLevel, int technologyLevel,
			int numberOfStates) {

		Army.setNumberOfStrength(numberOfStates);

		// If a country has no Resource it will produce Volunteer as Army
		if (resourceLevel == resource.noResource().resourceLevel()) {
			return new Volunteer();
		}
		// if a country has metal as a resource and Basic technology, it will
		// produce Conscript as Army
		else if (resourceLevel == resource.metalResource().resourceLevel()
				&& technologyLevel == technology.technologyLevelBasic()
						.technologyLevelNo()) {
			return new Conscript();
		}
		// if a country has metal as a resource and Medium technology, it will
		// produce Artillery as Army
		else if (resourceLevel == resource.metalResource().resourceLevel()
				&& technologyLevel == technology.technologyLevelMedium()
						.technologyLevelNo()) {
			return new Artillery();
		}
		// if a country has metal as a resource and High technology, it will
		// produce Heavy Precision Artillery as Army
		else if (resourceLevel == resource.metalResource().resourceLevel()
				&& technologyLevel == technology.technologyLevelHigh()
						.technologyLevelNo()) {
			return new HeavyPrecisionArtillery();
		}
		// if a country has energy as a resource and Basic technology, it will
		// produce Professional Soldier as Army
		else if (resourceLevel == resource.energyResource().resourceLevel()
				&& technologyLevel == technology.technologyLevelBasic()
						.technologyLevelNo()) {
			return new ProfessionalSoldier();
		}
		// if a country has energy as a resource and Medium technology, it will
		// produce Mechanized Infantry as Army
		else if (resourceLevel == resource.energyResource().resourceLevel()
				&& technologyLevel == technology.technologyLevelMedium()
						.technologyLevelNo()) {
			return new MechanizedInfantry();
		}
		// if a country has energy as a resource and High technology, it will
		// produce Tactical Missile as Army
		else if (resourceLevel == resource.energyResource().resourceLevel()
				&& technologyLevel == technology.technologyLevelHigh()
						.technologyLevelNo()) {
			return new TacticalMissile();
		}
		// if a country has knowledge as a resource and Basic technology, it
		// will produce Special Operation Soldier as Army
		else if (resourceLevel == resource.knowledgeResource().resourceLevel()
				&& technologyLevel == technology.technologyLevelBasic()
						.technologyLevelNo()) {
			return new SpecialOperationsSoldier();
		}
		// if a country has knowledge as a resource and Basic technology, it
		// will produce Aircraft as Army
		else if (resourceLevel == resource.knowledgeResource().resourceLevel()
				&& technologyLevel == technology.technologyLevelMedium()
						.technologyLevelNo()) {
			return new Aircraft();
		}
		// if a country has knowledge as a resource and Basic technology, it
		// will produce Nuclear Missile as Army
		else if (resourceLevel == resource.knowledgeResource().resourceLevel()
				&& technologyLevel == technology.technologyLevelHigh()
						.technologyLevelNo()) {
			return new NuclearMissile();
		}
		// if none of the above criteria of resources and technology matches,
		// there would be no army produced
		else {
			return new Army();
		}
	}
}
