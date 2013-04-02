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

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Observable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * An instance of this class represent a map. It is responsible for maintaining
 * the list of Continents, States, Countries, and edges. This class also applies
 * Singleton Design Pattern
 * 
 * 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Map extends Observable {
	private String mapName;
	@XmlElement 
	@XmlElementWrapper
	private ArrayList<Continent> myContinentList;
	@XmlElement 
	@XmlElementWrapper
	private ArrayList<Country> myCountryList;
	@XmlElement 
	@XmlElementWrapper
	private ArrayList<State> myStateList;
	@XmlElement 
	@XmlElementWrapper
	private ArrayList<Link> myLinkList;
	@XmlElement 
	@XmlElementWrapper
	private static ArrayList<Country> nextCountryTurn;
	@XmlElement 
	@XmlElementWrapper
	private static ArrayList<Country> countryTurnSequence;
	private State state;
	private Link link;
	private Continent continent;
	private Country country;
	@XmlElement
	private static int stage;

	private static Map instance = null;
	

	
	/**
	 * Default Constructor. Default constructor is private because this class is
	 * singleton.
	 */
	private Map() {
		this.mapName = "";
		this.myContinentList = new ArrayList<Continent>();
		this.myCountryList = new ArrayList<Country>();
		this.myStateList = new ArrayList<State>();
		this.myLinkList = new ArrayList<Link>();
		this.nextCountryTurn = new ArrayList<Country>();
		this.countryTurnSequence = new ArrayList<Country>();
		stage = Constants.PRODUCTION_STAGE;
	}

	/**
	 * Constructs and Initializes Map's object based on values receive from
	 * parameters
	 * 
	 * @param name
	 *            - String that specifies Map name
	 */
	private Map(String name) {
		this.mapName = name;
		this.myCountryList = new ArrayList<Country>();
		this.myContinentList = new ArrayList<Continent>();
		this.myStateList = new ArrayList<State>();
		this.myLinkList = new ArrayList<Link>();
		this.nextCountryTurn = new ArrayList<Country>();
		this.countryTurnSequence = new ArrayList<Country>();
		stage = Constants.PRODUCTION_STAGE;
		// this.nextCountryTurn = new ArrayList<Country>();
	}

	/**
	 * This method returns the instance of the Map class.
	 * 
	 * @param name
	 *            : Name of the Map
	 * @return : Instance of Map
	 */
	public static Map getInstance(String name) {
		if (instance == null) {
			instance = new Map(name);
		}
		return instance;
	}

	/**
	 * This method returns the instance of the Map class.
	 * 
	 * @return : Instance of map
	 */
	public static Map getInstance() {
		if (instance == null) {
			instance = new Map();
		}
		return instance;
	}

	/**
	 * Sets the Map
	 * 
	 * @param map
	 *            : Map object
	 */
	public static void setInstance(Map map) {
		instance = map;
	}

	/**
	 * Returns the name of the map
	 * 
	 * @return mapName : Name of map
	 */
	public String getMapName() {
		return mapName;
	}

	/**
	 * Sets the map name
	 * 
	 * @param mapName
	 *            : Name of map
	 */
	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	/**
	 * Returns the array list of Continents
	 * 
	 * @return myContinentList : Arraylist of continent
	 */
	public ArrayList<Continent> getMyContinentList() {
		return myContinentList;
	}

	/**
	 * Sets Continent in an array list
	 * 
	 * @param myContinentList
	 *            : arrayList of Continent
	 */
	public void setMyContinentList(ArrayList<Continent> myContinentList) {
		this.myContinentList = myContinentList;
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Returns list of countries
	 * 
	 * @return : arraylist of Continent
	 */
	public ArrayList<Country> getMyCountryList() {
		return myCountryList;
	}

	/**
	 * Returns State in an array list
	 * 
	 * @return : Array list of State
	 */
	public ArrayList<State> getMyStateList() {
		return myStateList;
	}

	/**
	 * Sets States in an array list
	 * 
	 * @param myStateList
	 *            : state array list
	 */
	public void setMyStateList(ArrayList<State> myStateList) {
		this.myStateList = myStateList;
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Returns Link in an array list
	 * 
	 * @return : Arraylist of links
	 */
	public ArrayList<Link> getMyLinkList() {
		return myLinkList;
	}

	/**
	 * Sets Links in an array list
	 * 
	 * @param myLinkList
	 *            : Arraylist of link
	 */
	public void setMyLinkList(ArrayList<Link> myLinkList) {
		this.myLinkList = myLinkList;
		this.setChanged();
		this.notifyObservers();
	}
	/**
	 * The method is used to set the next country turn
	 * @param nextCountryTurn	represents the arraylist of country object
	 */
	public void setNextCountryTurn(ArrayList<Country> nextCountryTurn) {
		this.nextCountryTurn = nextCountryTurn;
	}
	/**
	 * The method is used to return the next country turn
	 * @return	returns the arraylist of country
	 */
	public ArrayList<Country> getNextCountryTurn() {
		return this.nextCountryTurn;
	}
	/**
	 * This method is used to set country turn sequence
	 * @param countryTurnSequence	is the arraylist of country
	 */
	public void setCountryTurnSequence(ArrayList<Country> countryTurnSequence) {
		this.countryTurnSequence = countryTurnSequence;
	}
	/**
	 * This method returns the country turn sequence
	 * @return		returns the arraylist of country
	 */
	public ArrayList<Country> getCountryTurnSequence() {
		return this.countryTurnSequence;
	}
	/**
	 * The method is used to set the current stage of the game
	 * @param stage		represent the current stage of the game
	 */
	public void setStage(int stage) {
		this.stage = stage;
	}
	/**
	 * The method is used to return the stage of the game
	 * @return		returns the current stage off the game
	 */
	public int getStage() {
		return this.stage;
	}

	/**
	 * This method is used to set the list of Countries (Arraylist)
	 * 
	 * @param myCountryList
	 *            : Arraylist of country
	 */
	public void setMyCountryList(ArrayList<Country> myCountryList) {
		this.myCountryList = myCountryList;
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * This method will authenticate the map, whether its an acceptable map or
	 * not.
	 * 
	 * @return A string which indicates the map problems (null string for no
	 *         problem)
	 */
	public String validateMap() {

		// boolean verifyMap = true;
		String result = "";

		// Check if a state is isolated
		for (State tempState : myStateList) {
			Boolean isolated = true;
			for (Link tempLink : myLinkList) {
				if (tempLink.getSourceState().getStateID() == tempState
						.getStateID()
						|| tempLink.getDestintionState().getStateID() == tempState
								.getStateID()) {
					isolated = false;
					break;
				}
			}
			if (isolated)
				result += "<br>" + tempState.getStateName()
						+ " is an isolated state.";
		}

		// Check if there are more than one country
		if (myCountryList.size() <= 1)
			result += "<br>" + "The map has less than two countries.";

		// Check if each country has exactly one capital
		for (Country tempCountry : myCountryList) {
			int capitalCount = 0;
			for (State tempState : myStateList) {
				if (tempState.getCountryID() == tempCountry.getCountryID()
						&& tempState.getIsCapital())
					capitalCount++;
			}
			if (capitalCount == 0)
				result += "<br>" + tempCountry.getCountryName()
						+ " doesn't have any capital.";
			else if (capitalCount > 1)
				result += "<br>" + tempCountry.getCountryName()
						+ " has more than one capital.";
		}
		return result;
	}

	/**
	 * This method will add new state in the map object.
	 */
	public void addState(State s1) {
		myStateList.add(s1);
	}

	/**
	 * This method is used to add Continent into the list of Continents
	 * 
	 * @param c1
	 *            represents Continents object
	 */
	public void addContinent(Continent c1) {
		myContinentList.add(c1);
	}

	/**
	 * This method is used to add Link into the list of links
	 * 
	 * @param l1
	 *            - represents link object
	 */
	public void addLink(Link l1) {
		myLinkList.add(l1);
	}

	/**
	 * This method is used to add Country into the list of countries
	 * 
	 * @param cntry1
	 */
	public void addCountry(Country cntry1) {
		myCountryList.add(cntry1);
	}

	/**
	 * This method find the percentage of continent possessions for a specific
	 * country
	 * 
	 * @param countryID
	 *            The ID of the country that we want to find its possessions
	 * @return An array of percentage of continent possession
	 */
	public float[] getContinentPossession(int countryID) {
		ArrayList<State> statesOfContinent = null;
		int possessedStates;
		float percentage = 0;
		float[] percentageList = new float[this.myContinentList.size()];

		// Check continents one by one
		for (int i = 0; i < this.myContinentList.size(); i++) {
			possessedStates = 0;
			statesOfContinent = new ArrayList<State>();
			statesOfContinent = getStatesByContinentID(myContinentList.get(i)
					.getContinentID());
			for (State tempState : statesOfContinent) {
				if (tempState.getCountryID() == countryID)
					possessedStates++;
			}
			percentage = (float) possessedStates / statesOfContinent.size();
			percentageList[i] = percentage;
		}
		return percentageList;
	}

	/**
	 * This method finds a country in the map using its ID
	 * 
	 * @param countryID
	 *            ID of the country
	 * @return Country which corresponds to the countryID
	 */
	public Country getCountryByID(int countryID) {
		Country country = null;
		for (Country tempCountry : this.getMyCountryList()) {
			if (tempCountry.getCountryID() == countryID) {
				country = tempCountry;
				break;
			}
		}
		return country;
	}

	/**
	 * This method finds a state in the map using its ID
	 * 
	 * @param stateID
	 *            ID of the state
	 * @return State which corresponds to the ctateID
	 */
	public State getStateByID(int stateID) {
		State state = null;
		for (State tempState : this.getMyStateList()) {
			if (tempState.getStateID() == stateID) {
				state = tempState;
				break;
			}
		}
		return state;
	}

	/**
	 * This method finds the continent to which state belongs
	 * 
	 * @param selectedState
	 *            The state which we want to find its continent
	 * @return Continent of the state
	 */
	public Continent getContinentByState(State selectedState) {
		Continent returnContinent = null;
		for (Continent tempContinent : this.getMyContinentList()) {
			if (tempContinent.getContinentID() == selectedState
					.getContinentID()) {
				returnContinent = tempContinent;
				break;
			}
		}
		return returnContinent;
	}

	/**
	 * This method finds the country to which state belongs
	 * 
	 * @param selectedState
	 *            The state which we want to find its country
	 * @return Country of the state
	 */
	public Country getCountryByState(State selectedState) {
		Country returnCountry = null;
		for (Country tempCountry : this.getMyCountryList()) {
			if (tempCountry.getCountryID() == selectedState.getCountryID()) {
				returnCountry = tempCountry;
				break;
			}
		}
		return returnCountry;
	}

	/**
	 * This method finds the adjacent states of a specific state
	 * 
	 * @param myState
	 *            The state which we want to find its neighbors
	 * @param mode
	 *            0 for all adjacent states, 1 for all own adjacent states, 2
	 *            for all opponent adjacent states
	 * @return List of adjacent states of our state
	 */
	public ArrayList<State> getAdjacentStatesOfState(State myState, byte mode) {
		ArrayList<State> stateList = new ArrayList<State>();

		for (Link tempLink : this.getMyLinkList()) {
			// Checking destination state of the links
			if (tempLink.getSourceState().getStateID() == myState.getStateID()) {
				for (State tempState : this.getMyStateList()) {
					if (tempState.getStateID() == tempLink.getDestintionState()
							.getStateID()) {
						if (mode == 0) {
							// stateList.add(tempLink.getDestintionState());
							stateList.add(tempState);
							break;
						} else if (mode == 1) {
							if (tempState.getCountryID() == tempLink
									.getSourceState().getCountryID()) {
								// stateList.add(tempLink.getDestintionState());
								stateList.add(tempState);
								break;
							}
						} else if (mode == 2) {
							if (tempState.getCountryID() != tempLink
									.getSourceState().getCountryID()) {
								// stateList.add(tempLink.getDestintionState());
								stateList.add(tempState);
								break;
							}
						}
					}
				}
			}
			// Checking source state of the links
			if (tempLink.getDestintionState().getStateID() == myState
					.getStateID()) {
				for (State tempState : this.getMyStateList()) {
					if (tempState.getStateID() == tempLink.getSourceState()
							.getStateID()) {
						if (mode == 0) {
							// stateList.add(tempLink.getSourceState());
							stateList.add(tempState);
							break;
						} else if (mode == 1) {
							if (tempState.getCountryID() == tempLink
									.getDestintionState().getCountryID()) {
								// stateList.add(tempLink.getSourceState());
								stateList.add(tempState);
								break;
							}
						} else if (mode == 2) {
							if (tempState.getCountryID() != tempLink
									.getDestintionState().getCountryID()) {
								// stateList.add(tempLink.getSourceState());
								stateList.add(tempState);
								break;
							}
						}
					}
				}
			}
		}
		return stateList;
	}

	/**
	 * This method finds the opponent states which are adjacent to states of a
	 * country
	 * 
	 * @param countryID
	 *            ID of the country which we want to find its adjacent opponent
	 *            states
	 * @return List of adjacent opponent states of a country
	 */
	public ArrayList<State> getEnemyStatesOfCountry(int countryID,
			ArrayList<State> conqueredStates) {
		ArrayList<State> ownStates = new ArrayList<State>();
		ArrayList<State> enemyStates = new ArrayList<State>();
		ArrayList<State> adjacentStates;
		boolean existsInList;
		boolean conqueredState;

		ownStates = this.getStatesByCountryID(countryID);

		for (State ownState : ownStates) {
			// Get adjacent opponent states of this state
			adjacentStates = new ArrayList<State>();
			adjacentStates = this.getAdjacentStatesOfState(ownState, (byte) 2);

			// Check two conditions. first if each of these states already
			// exists in enemy states list (to avoid duplicate entries). second
			// if each of these states
			// exists in conquered list
			// if result of both is false, then add them to the enemy list
			for (State adjState : adjacentStates) {
				existsInList = false;
				conqueredState = false;
				for (State enemyState : enemyStates) {
					if (adjState.getStateID() == enemyState.getStateID()) {
						existsInList = true;
						break;
					}
				}
				for (State conquered : conqueredStates) {
					if (adjState.getStateID() == conquered.getStateID()) {
						conqueredState = true;
						break;
					}
				}
				if (!existsInList && !conqueredState)
					enemyStates.add(adjState);
			}
		}

		return enemyStates;
	}

	/**
	 * This methods finds all states of a country
	 * 
	 * @param countryID
	 *            The ID of the country which we want to find its states
	 * @return List of states of the country
	 */
	public ArrayList<State> getStatesByCountryID(int countryID) {
		ArrayList<State> stateList = new ArrayList<State>();
		for (State tempState : this.myStateList) {
			if (tempState.getCountryID() == countryID)
				stateList.add(tempState);
		}
		return stateList;
	}

	/**
	 * Returns the states by Continent id
	 * 
	 * @param continentID
	 *            : id of the continent
	 * @return : Arraylist of States
	 */
	public ArrayList<State> getStatesByContinentID(int continentID) {
		ArrayList<State> stateList = new ArrayList<State>();
		for (State tempState : this.myStateList) {
			if (tempState.getContinentID() == continentID)
				stateList.add(tempState);
		}
		return stateList;
	}

	/**
	 * This method finds the adjacent opponent states of a country
	 * 
	 * @param countryID
	 *            The ID of the country which we want to find its neighboring
	 *            states
	 * @return 		List of neighbouring states
	 */
	public ArrayList<State> getAdjacentOpponentStates(int countryID) {
		ArrayList<State> ownStates = new ArrayList<State>();
		ArrayList<State> opponentStates = new ArrayList<State>();
		ownStates = getStatesByCountryID(countryID);

		for (State myState : ownStates) {
			// Checking if the source state is our state
			for (Link tempLink : this.myLinkList) {
				if (tempLink.getSourceState().getStateID() == myState
						.getStateID())
					// Check if adjacent state is an opponent state
					if (tempLink.getDestintionState().getCountryID() != countryID)
						opponentStates.add(tempLink.getDestintionState());
			}
			// Checking if the destination state is our state
			for (Link tempLink : this.myLinkList) {
				if (tempLink.getDestintionState().getStateID() == myState
						.getStateID())
					// Check if adjacent state is an opponent state
					if (tempLink.getSourceState().getCountryID() != countryID)
						opponentStates.add(tempLink.getSourceState());
			}
		}

		// Creating a hashset to remove duplicate states
		HashSet stateHashSet = new HashSet(opponentStates);
		opponentStates.clear();
		opponentStates = new ArrayList(stateHashSet);

		return opponentStates;
	}

	/**
	 * This method finds the adjacent countries of a country
	 * 
	 * @param opponentStates
	 *            The states which we want to find their countries
	 * @return Countries which are adjacent to a specific country
	 */
	public ArrayList<Country> getAdjacentCountries(
			ArrayList<State> opponentStates) {
		ArrayList<Country> opponentCountries = new ArrayList<Country>();
		ArrayList<Integer> opponentCountriesID = new ArrayList<Integer>();

		for (State state : opponentStates) {
			opponentCountriesID.add(state.getCountryID());
		}

		// Creating a hashset to remove duplicate IDs
		HashSet countryHashSet = new HashSet(opponentCountriesID);
		opponentCountriesID.clear();
		opponentCountriesID = new ArrayList(countryHashSet);

		// Finding countries which correspond to the IDs
		for (Integer countryID : opponentCountriesID) {
			for (Country country : this.myCountryList) {
				if (countryID == country.getCountryID()) {
					opponentCountries.add(country);
					break;
				}
			}
		}

		return opponentCountries;
	}

	/**
	 * Get Resource of Country
	 * 
	 * @param countryID
	 *            : Id of country
	 * @return : Resources array list possesed by a country
	 */
	public ArrayList<Resource> getResourcesByCountryID(int countryID) {
		ArrayList<Resource> resourceList = new ArrayList<Resource>();
		ArrayList<State> stateList = new ArrayList<State>();
		stateList = getStatesByCountryID(countryID);
		for (State tempState : stateList) {
			resourceList.add(tempState.getResource());
		}

		// Creating a hashset to remove duplicate resources
		HashSet resourceHashSet = new HashSet(resourceList);
		resourceList.clear();
		resourceList = new ArrayList(resourceHashSet);

		return resourceList;
	}

}