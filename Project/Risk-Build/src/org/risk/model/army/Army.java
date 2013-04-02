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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * The instance of this class represents Army.
 * 
 * 
 *
 */
@XmlType
@XmlAccessorType(XmlAccessType.FIELD) 
public class Army {
	
	private int armyStrength;
	private static String armyName;
	private static int numberOfStates;
	/**
	 * The method returns army name.
	 * 
	 * @return : Name of Army Type 
	 */
	public String armyName(){
		return armyName;
	} 
	/**
	 * The method sets the army name type.
	 * 
	 * @param armyName : Army name type.
	 */
	protected void setArmyName(String armyName){
		Army.armyName = armyName;
	}
	/**
	 * The method returns army strength.
	 * 
	 * @return :  Strength of the army type.
	 */
	public int armyStrength(){
		return armyStrength;
	}
	/**
	 * The method sets the army type strength.
	 * 
	 * @param armyStrength : Strength of the army type.
	 */
	public void setArmyStrength(int armyStrength){
		this.armyStrength = armyStrength;
	}
	/**
	 * The method returns the number of states for army to be produced.
	 * 
	 * @return :  Number of states.
	 */
	protected static int numberOfStates(){
		return Army.numberOfStates;
	}
	/**
	 * The method sets the number of states for army to be produced
	 * 
	 * @param noOfStates : Number of states.
	 */
	public static void setNumberOfStrength(int noOfStates){
		Army.numberOfStates = noOfStates;
	}
}
