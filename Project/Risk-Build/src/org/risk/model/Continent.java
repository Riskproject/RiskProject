package org.risk.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

/**
 * The Instance of this class represents continent.
 * 
 * @author Omer
 * 
 */
@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class Continent {

	private String continentName;
	private int continentID;
	@XmlElement
	@XmlElementWrapper
	private ArrayList<State> myStateList;

	/**
	 * Default Constructor. It will initialize continent object with default
	 * values.
	 */
	public Continent() {
		this.continentName = "";
		this.continentID = 0;
		myStateList = new ArrayList<State>();

	}

	/**
	 * Regular Constructor
	 * 
	 * @param name
	 *            : Name of Continent
	 */
	public Continent(String name) {
		this.continentName = name;
		this.continentID = 0;
		myStateList = new ArrayList<State>();
		// this.myCountryList = myCountryArrayList;
	}

	/**
	 * This method is used to return Continent name
	 * 
	 * @return Gets the name of the continent
	 */
	public String getContinentName() {
		return continentName;
	}

	/**
	 * This method is used to set continent name
	 * 
	 * @param continentName
	 *            Sets the name of the continent
	 */
	public void setContinentName(String continentName) {
		this.continentName = continentName;
	}

	/**
	 * This method will return List of state
	 * 
	 * @return : Arraylist of states
	 */
	public ArrayList<State> getMyStateList() {
		return myStateList;
	}

	/**
	 * This method is used to assign list of states to array list
	 * 
	 * @param myStateList
	 *            : Sets the arraylist of States
	 */
	public void setMyStateList(ArrayList<State> myStateList) {
		this.myStateList = myStateList;
	}

	/**
	 * This method is used to add state object to the list of states
	 * 
	 * @param s1
	 *            Instance of state
	 */
	public void addState(State s1) {
		myStateList.add(s1);
	}

	/**
	 * This method returns Continent ID which state is assigned to
	 * 
	 * @return : Continent Id
	 */
	public int getContinentID() {
		return continentID;
	}

	/**
	 * This method will help in setting continentID in state
	 * 
	 * @param continentID
	 *            : Continent Id
	 */
	public void setContinentID(int continentID) {
		this.continentID = continentID;
	}

	/**
	 * Gets the continent list owned by a country
	 * 
	 * @param continentList
	 *            : Array list of continent
	 * @param stateList
	 *            : Array list of states
	 * @param countryId
	 *            : Id of country
	 * @return : Returns the arraylist of continents
	 */
	public ArrayList<Continent> getContinentsOwnedByCountry(
			ArrayList<Continent> continentList, ArrayList<State> stateList,
			int countryId) {
		ArrayList<Continent> continentOwnedByCountry = new ArrayList<Continent>();
		ArrayList<State> continentStates = new ArrayList<State>();
		State state = new State();
		for (Continent continent : continentList) {
			boolean allContinentStatesAreCountryStates = true;
			continentStates = state.getStateByContinentId(
					continent.getContinentID(), stateList);
			for (State continentState : continentStates) {
				if (continentState.getCountryID() != countryId) {
					allContinentStatesAreCountryStates = false;
					break;
				}
			}
			if (allContinentStatesAreCountryStates) {
				continentOwnedByCountry.add(continent);
			}
		}
		return continentOwnedByCountry;
	}

}
