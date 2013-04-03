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

import java.awt.Color;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.risk.model.army.Aircraft;
import org.risk.model.army.Army;
import org.risk.model.army.ArmyDetail;
import org.risk.model.army.Artillery;
import org.risk.model.army.Conscript;
import org.risk.model.army.HeavyPrecisionArtillery;
import org.risk.model.army.MechanizedInfantry;
import org.risk.model.army.NuclearMissile;
import org.risk.model.army.ProfessionalSoldier;
import org.risk.model.army.SpecialOperationsSoldier;
import org.risk.model.army.TacticalMissile;
import org.risk.model.army.Volunteer;

/**
 * An instance of this class represents a country, which will act as a player in
 * the game. A country is responsible for executing a turn in the game.
 * 
 * @author Omer
 * 
 */
@XmlType
@XmlAccessorType(XmlAccessType.FIELD) 
public class Country {
	@XmlJavaTypeAdapter(ColorAdapter.class)
	private Color countryColor;
	private int countryID;
	private String countryName;
	@XmlElement
	@XmlElementWrapper
	private ArrayList<State> myStatesList;
	@XmlElement
	private Technology technology;
	@XmlElement
	private ArmyDetail armyDetail;
	private int productionNumber;
	private String productionType;
	private int countryTurn = 1;
	
	/**
	 * Default Constructor
	 */
	public Country() {
		this.countryID = 0;
		this.countryName = "";
		this.technology = new Technology();
		this.armyDetail = new ArmyDetail(countryName);
		this.productionNumber=0;
		this.productionType = null;
		
	}

	/**
	 * Constructs and Initialises Country's object based on values receive from
	 * parameters
	 * 
	 * @param color
	 *            - Object that specifies country colour
	 * @param ID
	 *            - String that specifies object's ID
	 * @param name
	 *            - String that specifies its name
	 * @param states
	 *            - Array List of states Objects
	 * @param technology
	 * 			  - is the instance of Technology	           
	 * @param armyDetail
	 *            - Object that specifies armyDetails (List of armies)
	 */
	public Country(String name, int ID, Color color, ArrayList<State> states,
			Technology technology, ArmyDetail armyDetail) {
		this.countryColor = color;
		this.countryID = ID;
		this.countryName = name;
		this.myStatesList = states;
		this.technology = technology;
		this.armyDetail = armyDetail;
	}

	/**
	 * Regular Constructor. This method is used assign provided values
	 * 
	 * @param name
	 *            - String variable: defines country's name
	 */
	public Country(String name) {
		// this.countryColor = color;
		// this.countryID = ID;
		this.countryName = name;
		// this.myStatesList = states;
	}

	/**
	 * Returns Country ID
	 * 
	 * @return countryID : Id of Country
	 */
	public int getCountryID() {
		return countryID;
	}

	/**
	 * Sets Country's ID
	 * 
	 * @param countryID
	 *            : Id of Country
	 */
	public void setCountryID(int countryID) {
		this.countryID = countryID;
	}

	/**
	 * Returns Country Name
	 * 
	 * @return countryName : Name of Country
	 */
	public String getCountryName() {
		return countryName;
	}

	/**
	 * Sets Country's name
	 * 
	 * @param countryName
	 *            : Name of Country
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	/**
	 * Returns List of states in the country
	 * 
	 * @return myStateList : Arraylist of States
	 */
	public ArrayList<State> getMyStatesList() {
		return myStatesList;
	}

	/**
	 * Sets the ArrayList from parameter to Local ArrayList (contains States
	 * Object)
	 * 
	 * @param myStatesList
	 *            : : Arraylist of States
	 */
	public void setMyStatesList(ArrayList<State> myStatesList) {
		this.myStatesList = myStatesList;
	}

	/**
	 * Returns the Technology object.
	 * 
	 * @return : Technology object.
	 */
	public Technology getTechnology() {
		return this.technology;
	}

	/**
	 * Sets the Technology object
	 * 
	 * @param technology
	 *            : Technology object.
	 */
	public void setTechnology(Technology technology) {
		this.technology = technology;
	}
	/**
	 * The method will return the Country's Aircraft total count
	 * @return		Returns int value of total number of aircrafts
	 */
	private int getCountryAircraftCount(){
		return (this.armyDetail.getAircraft().armyStrength()/Aircraft.aircraftStrength);
	}
	/**
	 * The method will return the Country's Artillery total count
	 * @return		Returns int value of total number of Artilleries
	 */
	private int getCountryArtilleryCount(){
		return (this.armyDetail.getArtillery().armyStrength()/Artillery.artilleryStrength);
	}
	/**
	 * The method will return the Country's Conscript total count
	 * @return		Returns int value of total number of Conscripts
	 */
	private int getCountryConscriptCount(){
		return (this.armyDetail.getConscript().armyStrength()/Conscript.conscriptStrength);
	}
	/**
	 * The method will return the Country's Heavy Precision Artillery total count
	 * @return		Returns int value of total number of Heavy Precision Artillery
	 */
	private int getCountryHeavyPrecisionArtilleryCount(){
		return (this.armyDetail.getHeavyPrecisionArtillery().armyStrength()/HeavyPrecisionArtillery.heavyPrecisionArtilleryStrength);
	}
	/**
	 * The method will return the Country's Mechanized Infantry total count
	 * @return		Returns int value of total number of Mechanized Infantry
	 */
	private int getCountryMechanizedInfantryCount(){
		return (this.armyDetail.getMechanizedInfantry().armyStrength()/MechanizedInfantry.mechanizedInfantryStrength);
	}
	/**
	 * The method will return the Country's Nuclear Missile total count
	 * @return  	Returns int value of total number of Nuclear Missile
	 */
	private int getCountryNuclearMissileCount(){
		return (this.armyDetail.getNuclearMissile().armyStrength()/NuclearMissile.nuclearMissileStrength);
	}
	/**
	 * The method will return the Country's Professional Soldier Count total count
	 * @return	Returns int value of total number of Professional Soldier
	 */
	private int getCountryProfessionalSoldierCount(){
		return (this.armyDetail.getProfessionalSoldier().armyStrength()/ProfessionalSoldier.professionalSoldierStrength);
	}
	/**
	 * The method will return the Country's Special Operations Soldier total count
	 * @return		Returns int value of total number of Special Operations Soldier   
	 */
	private int getCountrySpecialOperationsSoldierCount(){
		return (this.armyDetail.getSpecialOperationsSoldier().armyStrength()/SpecialOperationsSoldier.specialOperationsSoldierStrength);
	}
	/**
	 * The method will return the Country's Tactical Missile Count total count
	 * @return   Returns int value of total number of Tactical Missile
	 */
	private int getCountryTacticalMissileCount(){
		return (this.armyDetail.getTacticalMissile().armyStrength()/TacticalMissile.tacticalMissileStrength);
	}
	/**
	 * The method will return the Country's Volunteer total count
	 * @return	Returns the int value which represents Volunteer count
	 */
	private int getCountryVolunteerCount(){
		return (this.armyDetail.getVolunteer().armyStrength()/Volunteer.volunteerStrength);
	}
	/**
	 * The method will return the Country's Army count
	 * @return	Returns the Hashtable of army count 
	 */
	public Hashtable<String, Integer> getCountryArmyCount(){
		Hashtable<String, Integer> armyCount = new Hashtable<String, Integer>();
		if(this.getCountryAircraftCount() > 0){
			armyCount.put(Aircraft.armyName, getCountryAircraftCount());
		}
		if(this.getCountryArtilleryCount() > 0){
			armyCount.put(Artillery.armyName,getCountryArtilleryCount());
		}
		if(this.getCountryConscriptCount() > 0){
			armyCount.put(Conscript.armyName, getCountryConscriptCount());
		}
		if(this.getCountryHeavyPrecisionArtilleryCount() > 0){
			armyCount.put(HeavyPrecisionArtillery.armyName, getCountryHeavyPrecisionArtilleryCount());
		}
		if(this.getCountryMechanizedInfantryCount() > 0){
			armyCount.put(MechanizedInfantry.armyName, getCountryMechanizedInfantryCount());
		}
		if(this.getCountryNuclearMissileCount() > 0){
			armyCount.put(NuclearMissile.armyName, getCountryNuclearMissileCount());
		}
		if(this.getCountryProfessionalSoldierCount() > 0){
			armyCount.put(ProfessionalSoldier.armyName, getCountryProfessionalSoldierCount());
		}
		if(this.getCountrySpecialOperationsSoldierCount() > 0){
			armyCount.put(SpecialOperationsSoldier.armyName, getCountrySpecialOperationsSoldierCount());
		}
		if(this.getCountryTacticalMissileCount() > 0){
			armyCount.put(TacticalMissile.armyName, getCountryTacticalMissileCount());
		}
		if(this.getCountryVolunteerCount() > 0){
			armyCount.put(Volunteer.armyName, getCountryVolunteerCount());
		}
		return armyCount;
	}
	/**
	 * The method will return the Country's Army Strength
	 * @return	Returns int value which represents country army strength
	 */
	public int getCountryArmyStrength(){
		Hashtable<String, Integer> armyCount = getCountryArmyCount();
		int armyStrength = 0;
		if (!armyCount.isEmpty()) {
			if (armyCount.containsKey(Aircraft.armyName)) {
				armyStrength += armyCount.get(Aircraft.armyName)
						* Aircraft.aircraftStrength;
			}
			if (armyCount.containsKey(Artillery.armyName)) {
				armyStrength += armyCount.get(Artillery.armyName)
						* Artillery.artilleryStrength;
			}
			if (armyCount.containsKey(Conscript.armyName)) {
				armyStrength += armyCount.get(Conscript.armyName)
						* Conscript.conscriptStrength;
			}
			if (armyCount.containsKey(HeavyPrecisionArtillery.armyName)) {
				armyStrength += armyCount.get(HeavyPrecisionArtillery.armyName)
						* HeavyPrecisionArtillery.heavyPrecisionArtilleryStrength;
			}
			if (armyCount.containsKey(MechanizedInfantry.armyName)) {
				armyStrength += armyCount.get(MechanizedInfantry.armyName)
						* MechanizedInfantry.mechanizedInfantryStrength;
			}
			if (armyCount.containsKey(NuclearMissile.armyName)) {
				armyStrength += armyCount.get(NuclearMissile.armyName)
						* NuclearMissile.nuclearMissileStrength;
			}
			if (armyCount.containsKey(ProfessionalSoldier.armyName)) {
				armyStrength += armyCount.get(ProfessionalSoldier.armyName)
						* ProfessionalSoldier.professionalSoldierStrength;
			}
			if (armyCount.containsKey(SpecialOperationsSoldier.armyName)) {
				armyStrength += armyCount.get(SpecialOperationsSoldier.armyName)
						* SpecialOperationsSoldier.specialOperationsSoldierStrength;
			}
			if (armyCount.containsKey(TacticalMissile.armyName)) {
				armyStrength += armyCount.get(TacticalMissile.armyName)
						* TacticalMissile.tacticalMissileStrength;
			}
			if (armyCount.containsKey(Volunteer.armyName)) {
				armyStrength += armyCount.get(Volunteer.armyName)
						* Volunteer.volunteerStrength;
			}
		}
		return armyStrength;
		
	}

	/**
	 * Returns the Army object.
	 * 
	 * @return : Army object.
	 */
	public ArmyDetail getArmyDetail() {
		return this.armyDetail;
	}

	/**
	 * Sets the army object.
	 * 
	 * @param army
	 *            : Army object.
	 */
	public void addArmyDetail(Army army) {
		if (army.getClass().equals(Aircraft.class)) {
			Aircraft aircraft = new Aircraft(countryName);
			aircraft.setArmyStrength(this.getArmyDetail().getAircraft()
					.armyStrength()
					+ army.armyStrength());
			this.armyDetail.setAircraft(aircraft);
		} else if (army.getClass().equals(Artillery.class)) {
			Artillery artillery = new Artillery(countryName);
			artillery.setArmyStrength(this.getArmyDetail().getArtillery()
					.armyStrength()
					+ army.armyStrength());
			this.armyDetail.setArtillery(artillery);
		} else if (army.getClass().equals(Conscript.class)) {
			Conscript conscript = new Conscript(countryName);
			conscript.setArmyStrength(this.getArmyDetail().getArtillery()
					.armyStrength()
					+ army.armyStrength());
			this.armyDetail.setConscript(conscript);
		} else if (army.getClass().equals(HeavyPrecisionArtillery.class)) {
			HeavyPrecisionArtillery heavyPrecisionArtillery = new HeavyPrecisionArtillery(
					countryName);
			heavyPrecisionArtillery.setArmyStrength(this.getArmyDetail()
					.getHeavyPrecisionArtillery().armyStrength()
					+ army.armyStrength());
			this.armyDetail.setHeavyPrecisionArtillery(heavyPrecisionArtillery);
		} else if (army.getClass().equals(MechanizedInfantry.class)) {
			MechanizedInfantry mechanizedInfantry = new MechanizedInfantry(
					countryName);
			mechanizedInfantry.setArmyStrength(this.getArmyDetail()
					.getMechanizedInfantry().armyStrength()
					+ army.armyStrength());
			this.armyDetail.setMechanizedInfantry(mechanizedInfantry);
		} else if (army.getClass().equals(NuclearMissile.class)) {
			NuclearMissile nuclearMissile = new NuclearMissile(countryName);
			nuclearMissile.setArmyStrength(this.getArmyDetail()
					.getNuclearMissile().armyStrength()
					+ army.armyStrength());
			this.armyDetail.setNuclearMissile(nuclearMissile);
		} else if (army.getClass().equals(ProfessionalSoldier.class)) {
			ProfessionalSoldier professionalSoldier = new ProfessionalSoldier(
					countryName);
			professionalSoldier.setArmyStrength(this.getArmyDetail()
					.getProfessionalSoldier().armyStrength()
					+ army.armyStrength());
			this.armyDetail.setProfessionalSoldier(professionalSoldier);
		} else if (army.getClass().equals(SpecialOperationsSoldier.class)) {
			SpecialOperationsSoldier specialOperationsSoldier = new SpecialOperationsSoldier(
					countryName);
			specialOperationsSoldier.setArmyStrength(this.getArmyDetail()
					.getSpecialOperationsSoldier().armyStrength()
					+ army.armyStrength());
			this.armyDetail
					.setSpecialOperationsSoldier(specialOperationsSoldier);
		} else if (army.getClass().equals(TacticalMissile.class)) {
			TacticalMissile tacticalMissile = new TacticalMissile(countryName);
			tacticalMissile.setArmyStrength(this.getArmyDetail()
					.getTacticalMissile().armyStrength()
					+ army.armyStrength());
			this.armyDetail.setTacticalMissile(tacticalMissile);
		} else if (army.getClass().equals(Volunteer.class)) {
			Volunteer volunteer = new Volunteer(countryName);
			volunteer.setArmyStrength(this.getArmyDetail().getVolunteer()
					.armyStrength()
					+ army.armyStrength());
			this.armyDetail.setVolunteer(volunteer);
		}
	}

	/**
	 * Gets the turn of country
	 * 
	 * @return : Turn of country
	 */
	public int getCountryTurn() {
		return this.countryTurn;
	}

	/**
	 * Sets the turn of country
	 * 
	 * @param countryTurn
	 *            : Turn of country
	 */
	public void setCountryTurn(int countryTurn) {
		this.countryTurn = countryTurn;
	}

	/**
	 * This method is used to return technology level after sorting by countryId
	 * 
	 * @param countryId
	 *            : Id of Country
	 * @param countries
	 *            : Arraylist of Countries
	 * @return : Technology level of country
	 * @throws Exception	throws custom exception
	 */
	public int getTechnologyLevelByCountryId(int countryId,
			ArrayList<Country> countries) throws Exception {
		int technologyLevel = -1;
		for (int i = 0; i < countries.size(); i++) {
			if (countryId == countries.get(i).getCountryID()) {
				technologyLevel = countries.get(i).getTechnology()
						.technologyLevelNo();
				break;
			}
		}
		if (technologyLevel == -1) {
			throw new Exception("Technology of country is not set");
		}
		return technologyLevel;
	}

	/**
	 * This method returns country index after receiving Country ID and list of states
	 * @param countryId
	 *            : ID of country
	 * @param countries
	 *            : Arraylist of countries
	 * @return : Index of country
	 * @throws Exception	throws a custom Exception if ID does not exist
	 */
	public int getCountryIndexByCountryID(int countryId,
			ArrayList<Country> countries) throws Exception {
		int indexToBeReturned = -1;
		for (int i = 0; i < countries.size(); i++) {
			if (countryId == countries.get(i).getCountryID()) {
				indexToBeReturned = i;
				break;
			}
		}
		if (indexToBeReturned == -1) {
			throw new Exception("Country ID does not exist in country list!");
		}
		return indexToBeReturned;
	}

	/**
	 * Removes capitals from the arraylist of states
	 * 
	 * @param stateArr
	 *            : arraylist of States
	 * @return : Arraylist of States with no capital
	 */
	public ArrayList<State> removeCapitals(ArrayList<State> stateArr) {
		for (State state : stateArr) {
			state.setIsCapital(false);
		}
		return stateArr;
	}

	/**
	 * Gets the Country controlling All capitals if any
	 * 
	 * @param stateList
	 *            : arraylist of states
	 * @return : Id of country controlling all capitals if any
	 */
	public int getCountryControllingAllCapitals(ArrayList<State> stateList) {
		int countryId = -1;
		int i;
		int j;
		ArrayList<State> capitals = new ArrayList<State>();
		State state = new State();
		capitals = state.getCapitalStates(stateList);
		for (i = 0; i < capitals.size(); i++) {
			for (j = i + 1; j < capitals.size(); j++) {
				if (capitals.get(i).getCountryID() != capitals.get(j)
						.getCountryID()) {
					return -1;
				} else
					countryId = stateList.get(i).getCountryID();
			}
		}
		return countryId;
	}

	/**
	 * Gets the Country controlling All states if any
	 * 
	 * @param stateList
	 *            : Arraylist of States
	 * @return : Id of country controlling all states if any
	 */
	public int getIdOfCountryControllingAllStates(ArrayList<State> stateList) {
		int countryId = -1;
		int i;
		int j;
		for (i = 0; i < stateList.size(); i++) {
			for (j = i + 1; j < stateList.size(); j++) {
				if (stateList.get(i).getCountryID() != stateList.get(j)
						.getCountryID()) {
					return -1;
				} else
					countryId = stateList.get(i).getCountryID();
			}
		}
		return countryId;
	}

	/**
	 * Gets the resource of country that it can utilize in producing army.
	 * 
	 * @param myStates
	 *            : Arraylist of States
	 * @return : Resource of country that it can utilize in producing army
	 */
	public Resource getResourceOfCountry(ArrayList<State> myStates) {
		boolean noResource = false;
		boolean metal = false, energy = false, knowledge = false;
		Resource resource = new Resource();
		for (State state : myStates) {
			if (state.getResource().resourceLevel() == resource.noResource()
					.resourceLevel()) {
				noResource = true;
			} else if (state.getResource().resourceLevel() == resource
					.metalResource().resourceLevel()) {
				metal = true;
			} else if (state.getResource().resourceLevel() == resource
					.energyResource().resourceLevel()) {
				energy = true;
			} else if (state.getResource().resourceLevel() == resource
					.knowledgeResource().resourceLevel()) {
				knowledge = true;
			}
		}
		if ((metal) && (energy) && (knowledge)) {
			// set level 3
			resource = resource.knowledgeResource();
		} else if ((energy) && (knowledge)) {
			// set level 0
			resource = resource.noResource();
		} else if ((metal) && (knowledge)) {
			// set level 1
			resource = resource.metalResource();
		} else if ((metal) && (energy)) {
			// set level 2
			resource = resource.energyResource();
		} else if ((metal)) {
			// set level 1
			resource = resource.metalResource();
		} else if ((energy)) {
			// set level 0
			resource = resource.noResource();
			// send factory level 0 resource
		} else if ((knowledge)) {
			// set level 0
			resource = resource.noResource();
		} else if (!(metal) && !(energy) && !(knowledge)) {
			// set level 0
			resource = resource.noResource();
		}
		return resource;
	}
	/**
	 * The method will return the list of Resources owned by country
	 * @param countryStates		Represent array list of States owned by country
	 * @return	Returns the Hashtable of resources
	 */
	private Hashtable<Integer,Resource> getAllResourcesOfCountry(ArrayList<State> countryStates){
		Hashtable<Integer,Resource> resources = new Hashtable<Integer,Resource>();
		Resource noResource = new Resource();
		noResource = noResource.noResource();
		int index = 0;
		for(State state : countryStates){
			if(!resources.containsValue(state.getResource()) && !state.getResource().equals(noResource)){
				resources.put(index, state.getResource());
				index++;
			}
		}
		return resources;
	}
	/**
	 * Returns the Resources of a country from List of states
	 * @param countryStates		Represent array list of States owned by country
	 * @return		Returns String of resources
	 */
	public String getAllResourcesOfCountryStr(ArrayList<State> countryStates){
		Hashtable<Integer,Resource> resources = getAllResourcesOfCountry(countryStates);
		String resourcesStr = "";
		String resourceName = "";
		if(!resources.isEmpty()){
			Enumeration keys = resources.keys();
			while(keys.hasMoreElements()){
				resourceName = resources.get(keys.nextElement()).resourceName();
				if(resourceName != "None")
					resourcesStr += resourceName+ ",";
			}
		}
		else{
			resourcesStr = "No Resource"; 
		}
		return resourcesStr;
	}

	/**
	 * Gets the capital of country
	 * 
	 * @param countryId
	 *            : ID of country
	 * @param states
	 *            : arraylist of states
	 * @return : State which is the capital of a country
	 */
	public State getCountryCapital(int countryId, ArrayList<State> states) {
		Country country = new Country();
		State capital = new State();
		for (State state : states) {
			if (countryId == state.getCountryID()
					&& state.getIsCapital() == true) {
				capital = state;
				break;
			}
		}
		return capital;
	}
	/**
	 * This method returns the Production number
	 * @return		Returns int value which represent production number
	 */
	public int getProductionNumber() {
		return productionNumber;
	}
	/**
	 * This method will assign the Production number
	 * @param productionNumber		int value represents number of production
	 */
	public void setProductionNumber(int productionNumber) {
		this.productionNumber = productionNumber;
	}
	/**
	 * This method will return the type of production
	 * @return		Returns the Production type 
	 */
	public String getProductionType() {
		return productionType;
	}
	/**
	 * This method is used to assign the production type
	 * @param productionType	Describes the type produced
	 */
	public void setProductionType(String productionType) {
		this.productionType = productionType;
	}
	
	/**
	 * XML adapter for Color object
	 * 
	 */
	
	public class ColorAdapter extends XmlAdapter<String, Color> {
		public Color unmarshal(String val) throws Exception {
			return Color.getColor(val);
		}

		public String marshal(Color val) throws Exception {
			return val.toString();
		}
	}
	public boolean isCountryHaveCapital(int countryId, ArrayList<State> states){
		boolean rtnValue = true;
		Country country = new Country();
		State capital = country.getCountryCapital(countryId, states);
		if(capital.getStateID() == 0){
			rtnValue = false;
		}
		return rtnValue;
	}
	
}
