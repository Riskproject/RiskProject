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

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

/**
 * The class hold the instance of Technology.
 * 
 * @author Arij
 * 
 */
@XmlType
@XmlAccessorType(XmlAccessType.FIELD) 
public class Technology {
	private String technologyLevel;
	private int technologyLevelNo;
	private ArrayList<Technology> technologies;

	/**
	 * The method sets the technology level name.
	 * 
	 * @param technologyLevel : Name of technology level.
	 */
	private void setTechnologyLevel(String technologyLevel) {
		this.technologyLevel = technologyLevel;
	}

	/**
	 * The method returns the name of technology level.
	 * 
	 * @return : Name of technology level.
	 */
	public String technologyLevel() {
		return this.technologyLevel;
	}

	/**
	 * The method sets the technology level.
	 * 
	 * @param technologyLevelNo : Technology level
	 */
	private void setTechnologyLevelNo(int technologyLevelNo) {
		this.technologyLevelNo = technologyLevelNo;
	}

	/**
	 * The method returns the technology level.
	 * 
	 * @return : Technology level
	 */
	public int technologyLevelNo() {
		return this.technologyLevelNo;
	}

	/**
	 * Sets the concrete values for basic technology level.
	 * 
	 * @return : Technology
	 */
	public Technology technologyLevelBasic() {
		setTechnologyLevel("Basic");
		setTechnologyLevelNo(1);
		return getTechnology();
	}

	/**
	 * Sets the concrete values for medium technology level.
	 * 
	 * @return : Technology
	 */
	public Technology technologyLevelMedium() {
		setTechnologyLevel("Medium");
		setTechnologyLevelNo(2);
		return getTechnology();
	}

	/**
	 * Sets the concrete values for high technology level.
	 * 
	 * @return : Technology
	 */
	public Technology technologyLevelHigh() {
		setTechnologyLevel("High");
		setTechnologyLevelNo(3);
		return getTechnology();
	}
	/**
	 * This method returns the Object of Technology
	 * 
	 * @return Technology Object
	 */
	private Technology getTechnology(){
		Technology technology = new Technology();
		technology.setTechnologyLevel(this.technologyLevel());
		technology.setTechnologyLevelNo(this.technologyLevelNo());
		return technology;
	}
	/**
	 * This method returns the list of Technologies Array List
	 * 
	 * @return Arraylist of technologies
	 */
	public ArrayList<Technology> getTechnologyList(){
		technologies = new ArrayList<Technology>();
		technologies.add(technologyLevelBasic());
		technologies.add(technologyLevelMedium());
		technologies.add(technologyLevelHigh());
		return technologies;
	}
	/**
	 * The method returns Technology after getting technology level as a parameter
	 * @param technologyLevel	Describes technology level	
	 * @return	The instance of Technology
	 */
	public Technology getTechnologyByTechnologyLevel(int technologyLevel){
		ArrayList<Technology> technologies = getTechnologyList();
		Technology technology = new Technology();
		for(int i = 0; i < technologies.size(); i++){
			if(technologyLevel == technologies.get(i).technologyLevelNo()){
				technology = technologies.get(i);
				break;
			}
		}
		return technology;
	}
}
