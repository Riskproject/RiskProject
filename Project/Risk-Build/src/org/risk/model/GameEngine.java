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

import java.lang.reflect.Array;
import java.security.acl.Owner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import java.util.Timer;
import java.util.TimerTask;

import java.util.Set;

import javax.sound.midi.Receiver;
import javax.naming.LinkLoopException;
import javax.swing.JOptionPane;

import org.risk.model.army.Army;
import org.risk.model.army.ArmyDetail;
import org.risk.model.army.ArmyFactory;
import org.risk.model.battle.BattleArmy;
import org.risk.model.battle.BattleGround;
import org.risk.model.battle.MultipleBattleFronts;
import org.risk.model.battle.TargetStateBattleArmyBuilder;
import org.risk.view.Game;

import controller.CGame;

/**
 * The Instance of this Class holds Map Object. I will also allow to add or
 * remove functionalities on Map Object. It's responsible for the entire
 * simulation flow.
 * 
 * @author Omer
 * 
 */
public class GameEngine {
	private Map map;
	private State state;
	private Link link;
	private Continent continent;
	private Country country;
	private ArrayList<Continent> myContinentList;
	private ArrayList<Country> myCountryList;
	private ArrayList<State> myStateList;
	private ArrayList<Link> myLinkList;
	private static ArrayList<Country> countryTurnSequence;
	private ArmyFactory armyFactory;
	private Timer timer;
	private static ArrayList<Country> nextCountryTurn;
	private static int stage = Constants.PRODUCTION_STAGE;
	static ArrayList<State> conqueredStates = null;
	static private MapVisualization myMapVisualizer;
	static private Game game;
	static State weakestState;
	static ArrayList<State> attackingStates;

	// private boolean phaseFinalized = false;

	/**
	 * Default Constructor
	 */
	public GameEngine() {

		state = new State();
		link = new Link();
		continent = new Continent();
		country = new Country();
		if (countryTurnSequence == null)
			countryTurnSequence = new ArrayList<Country>();
		myStateList = new ArrayList<State>();
		if (conqueredStates == null)
			conqueredStates = new ArrayList<State>();
		if (weakestState == null)
			weakestState = new State();
		if (attackingStates == null)
			attackingStates = new ArrayList<State>();
		populateMap();
	}

	/**
	 * The method sets the values of map.
	 */
	private void populateMap() {
		map = Map.getInstance();
		myContinentList = map.getMyContinentList();
		myCountryList = map.getMyCountryList();
		myStateList = map.getMyStateList();
		myLinkList = map.getMyLinkList();
		nextCountryTurn = map.getNextCountryTurn();
		countryTurnSequence = map.getCountryTurnSequence();
		stage = map.getStage();
	}

	/**
	 * This is used to update the view of the map.
	 */

	private void updateMap() {
		game.updateView(myMapVisualizer);
		game.frame.revalidate();
		game.frame.repaint();
	}

	/**
	 * This is used to return the map instance.
	 * 
	 * @return: Returns the map of the game
	 */
	private Map getMap() {
		return map;
	}

	/**
	 * This method get the map object and load it in the memory.
	 * 
	 * @param map
	 */
	private void setMap(Map map) {
		this.map = map;
	}

	/**
	 * This method is used to generate the map on the screen using Class library
	 * named JUNG
	 */
	public void generateMap() {
		myMapVisualizer = new MapVisualization();
		Map.setInstance(map);
		myMapVisualizer.extractMap(map);
		map.addObserver(myMapVisualizer);
		game = new Game(myMapVisualizer, map);
	}

	/**
	 * This method analyzes the attack between different states
	 * 
	 * @param countryID
	 *            ID of own country
	 * @param firstAnalysis
	 *            true to show the first analysis of the turn. false to show
	 *            reanalysis
	 * @throws CloneNotSupportedException
	 *             Exception about supporting clone
	 */
	public void analyzeAttack(int countryID, boolean firstAnalysis)
			throws CloneNotSupportedException {
		Map myMap = null;
		myMap = Map.getInstance("Map01");
		ArrayList<Resource> desiredResources = new ArrayList<Resource>();
		ArrayList<State> desiredStates = new ArrayList<State>();
		ArrayList<State> enemyStates = new ArrayList<State>();

		int WeakestStateIndex = 0;
		float[] stateStrengths = null;
		boolean canAttack = false;
		boolean enemyUnavailable = false;

		// conqueredStates = new ArrayList<State>();

		Logger.logMessage("\n");
		if (firstAnalysis)
			Logger.logMessage("*** STRATEGIC INTELLIGENCE GATHERING STAGE ***");
		else
			Logger.logMessage("*** REANALYSIS AFTER BATTLE ***");

		// Find all required resources for the country
		desiredResources = getDesiredResources(myMap, countryID);

		// Find the states to be attacked and conquered based on
		// current continent possession
		desiredStates = getDesiredStates(myMap, countryID);

		// Get all the enemy states of our country
		enemyStates = myMap.getEnemyStatesOfCountry(countryID, conqueredStates);
		stateStrengths = new float[enemyStates.size()];

		for (int i = 0; i < enemyStates.size(); i++) {
			stateStrengths[i] = enemyStates.get(i).getArmyDetail()
					.getArmyStrength(enemyStates.get(i).getArmyDetail());

			if (enemyStates.get(i).getResource().resourceLevel() > 0
					&& desiredResources.size() > 0) {
				if (enemyStates.get(i).getResource().resourceLevel() == desiredResources
						.get(0).resourceLevel()) {
					if (stateStrengths[i] > 0)
						stateStrengths[i] *= 0.8;
					else
						stateStrengths[i] = -5;
				}
			}

			for (State desired : desiredStates) {
				if (enemyStates.get(i).getStateID() == desired.getStateID()) {
					if (stateStrengths[i] > 0)
						stateStrengths[i] *= 0.8;
					else if (stateStrengths[i] == 0)
						stateStrengths[i] = -5;
					else
						stateStrengths[i] *= 1.2;
				}
			}
		}

		while (!canAttack && !enemyUnavailable) {
			WeakestStateIndex = getWeakestStateIndex(stateStrengths);
			if (WeakestStateIndex != -1) {
				Logger.logMessage("Weakest and wealthiest adjacent enemy state: "
						+ enemyStates.get(WeakestStateIndex).getStateName());
				Logger.logMessage("\n");

				// Find states of my country which are adjacent to this state
				ArrayList<State> adjStates = new ArrayList<State>();
				adjStates = myMap.getAdjacentStatesOfState(
						enemyStates.get(WeakestStateIndex), (byte) 2);
				int attackingStrength = 0;
				attackingStates = new ArrayList<State>();

				for (State adjState : adjStates) {
					if (adjState.getCountryID() == countryID) {
						if (adjState.getArmyDetail().getArmyStrength(
								adjState.getArmyDetail()) > 0) {
							attackingStates.add(adjState);
							attackingStrength += adjState.getArmyDetail()
									.getArmyStrength(adjState.getArmyDetail());
						}
					}
				}
				if (attackingStrength >= enemyStates
						.get(WeakestStateIndex)
						.getArmyDetail()
						.getArmyStrength(
								enemyStates.get(WeakestStateIndex)
										.getArmyDetail())) {
					weakestState = enemyStates.get(WeakestStateIndex);
					Logger.logMessage("Based on three criteria, odds are favorable to attack "
							+ weakestState.getStateName());
					Logger.logMessage("\n");
					canAttack = true;
				} else {
					Logger.logMessage("Based on three criteria, odds are unfavorable to attack "
							+ enemyStates.get(WeakestStateIndex).getStateName()
							+ ". Finding next weakest and wealthiest state ...");
					Logger.logMessage("\n");
					enemyStates.remove(WeakestStateIndex);
					stateStrengths = removeFromArray(stateStrengths,
							WeakestStateIndex);
					attackingStates.clear();
					weakestState = null;
					if (enemyStates.size() == 0) {
						Logger.logMessage("No more attacks can be done in this turn.");
						Logger.logMessage("\n");
						enemyUnavailable = true;
					}
				}
			} else {
				Logger.logMessage("No more attacks can be done in this turn.");
				Logger.logMessage("\n");
				attackingStates.clear();
				weakestState = null;
				enemyUnavailable = true;
			}
		}
	}

	/**
	 * This method gets the index of lowest strength in an array
	 * 
	 * @param stateStrengths
	 *            An array of strengths
	 * @return Index of weakest state
	 */
	private int getWeakestStateIndex(float[] stateStrengths) {
		int weakestStateIndex = -1;
		float weakestStateStrength = -1000;

		if (stateStrengths.length > 0) {
			weakestStateStrength = stateStrengths[0];
			weakestStateIndex = 0;
		}
		if (stateStrengths.length > 1) {
			for (int i = 1; i < stateStrengths.length; i++) {
				if (stateStrengths[i] < weakestStateStrength) {
					weakestStateStrength = stateStrengths[i];
					weakestStateIndex = i;
				}
			}
		}
		return weakestStateIndex;
	}

	/**
	 * This method removes an element from array
	 * 
	 * @param stateStrengths
	 *            Array to be modified
	 * @param index
	 *            Index of element to be removed
	 * @return New array with removed element
	 */
	private float[] removeFromArray(float[] myArray, int index) {
		float[] newArray = new float[myArray.length - 1];

		for (int i = 0; i < myArray.length; i++) {
			if (i < index)
				newArray[i] = myArray[i];
			else if (i > index)
				newArray[i - 1] = myArray[i];
		}

		return newArray;
	}

	/**
	 * This methods analyzes the movement of armies between states
	 * 
	 * @param countryID
	 *            ID of own country
	 */
	public void analyzeMovement(int countryID) {
		Map myMap = null;
		myMap = Map.getInstance("Map01");
		ArrayList<Resource> opponentDesiredResources = null;
		ArrayList<State> opponentDesiredStates = new ArrayList<State>();
		ArrayList<State> ownStates = new ArrayList<State>();
		ArrayList<State> opponentStates = null;
		ArrayList<State> defendingStates = new ArrayList<State>();
		ArrayList<State> supportingStates = new ArrayList<State>();
		ArrayList<State> weakStates = new ArrayList<State>();
		ArrayList<State> resourceOwnerStates = new ArrayList<State>();
		ArrayList<State> borderStates = new ArrayList<State>();
		ArrayList<Integer> stateThreats = new ArrayList<Integer>();
		int strengthThreat = 36;
		int resourceThreat = 24;
		int borderThreat = 20;

		ownStates = myMap.getStatesByCountryID(countryID);

		for (int index = 0; index < ownStates.size(); index++) {
			State ownState = ownStates.get(index);
			int stateThreat = 0;

			// Get adjacent opponent states of this state
			opponentStates = new ArrayList<State>();
			opponentStates = myMap.getAdjacentStatesOfState(ownState, (byte) 2);

			// Removing conquered (defenseless) states from the list of
			// opponents
			for (State conState : conqueredStates) {
				for (State oppState : opponentStates) {
					if (oppState.getStateID() == conState.getStateID()) {
						opponentStates.remove(oppState);
						break;
					}
				}
			}

			if (opponentStates.size() > 0) {
				// Getting the CountryID of adjacent opponent states
				ArrayList<Integer> countryIdList = new ArrayList<Integer>();
				for (State oppState : opponentStates) {
					boolean countryExists = false;
					for (Integer oppCountryID : countryIdList) {
						if (oppState.getCountryID() == oppCountryID) {
							countryExists = true;
							break;
						}
					}
					if (!countryExists)
						countryIdList.add(oppState.getCountryID());
				}

				// Check attack threat based on state strength
				for (Integer oppCountryID : countryIdList) {
					int opponentStrength = 0;
					for (State oppState : opponentStates) {
						if (oppState.getCountryID() == oppCountryID)
							opponentStrength += oppState.getArmyDetail()
									.getArmyStrength(oppState.getArmyDetail());
					}
					if ((ownState.getArmyDetail().getArmyStrength(
							ownState.getArmyDetail()) <= opponentStrength)
							|| (ownState.getArmyDetail().getArmyStrength(
									ownState.getArmyDetail()) == 0 && opponentStrength == 0)) {
						weakStates.add(ownState);
						stateThreat += strengthThreat;
						break;
					}
				}

				// Check attack threat based on state resource
				for (Integer oppCountryID : countryIdList) {
					opponentDesiredResources = getDesiredResources(myMap,
							oppCountryID);
					if (opponentDesiredResources.size() > 0)
						if (ownState.getResource().resourceLevel() == opponentDesiredResources
								.get(0).resourceLevel()) {
							resourceOwnerStates.add(ownState);
							stateThreat += resourceThreat;
							break;
						}
				}

				// Check attack threat based on continent possession
				for (Integer oppCountryID : countryIdList) {
					opponentDesiredStates = getDesiredStates(myMap,
							oppCountryID);
					for (State oppDesiredState : opponentDesiredStates) {
						if (oppDesiredState.getStateID() == ownState
								.getStateID()) {
							borderStates.add(ownState);
							stateThreat += borderThreat;
							break;
						}
					}
				}
			} else {
				if (ownState.getArmyDetail().getArmyStrength(
						ownState.getArmyDetail()) > 0)
					supportingStates.add(ownState);
			}

			stateThreats.add(stateThreat);
		}

		LogMovementAnalysisResult(weakStates, resourceOwnerStates, borderStates);

		// Finding the defending states. Condition: if the threat is greater
		// than 35. It means if the strength is less than enemies and/or two
		// other conditions (resource and continent possession) are met.
		String defending = "";
		for (int i = 0; i < ownStates.size(); i++) {
			if (stateThreats.get(i) > 35
					|| ownStates.get(i).getArmyDetail()
							.getArmyStrength(ownStates.get(i).getArmyDetail()) == 0) {
				defendingStates.add(ownStates.get(i));
				defending += ownStates.get(i).getStateName() + ", ";
			}
		}
		Logger.logMessage("Conclusion:");
		if (!defending.equals(""))
			Logger.logMessage("Try to Reinforce: " + defending);
		else
			Logger.logMessage("No defensive action is required");
		moveArmies(defendingStates, supportingStates, countryID);
	}

	/**
	 * This method finds the state that have not attacked or will not defend,
	 * and moves 50% of their armies to states that need more armies
	 * 
	 * @param defendingStates
	 *            List of states which have attacked or will defend
	 * @param supportingStates
	 *            List of states which have not attacked or will not defend
	 * @param myCountryID
	 *            ID of own country
	 */
	public void moveArmies(ArrayList<State> defendingStates,
			ArrayList<State> supportingStates, int myCountryID) {
		ArrayList<State> adjacentStates = null;
		ArrayList<State> receiverStates = null;
		BattleArmy battleArmy = null;
		ArmyDetail movingArmyDetail = null;
		ArmyDetail remainingArmyDetail = null;
		ArrayList<Integer> conqueredStatesID = null;
		Map myMap = null;
		myMap = Map.getInstance("Map01");

		// Move some armies to newly attacked states which are defenseless to
		// conquer them
		// if a supporting states is adjacent to defenseless state and it has
		// enough armies, move 1/3 of its armies to defenseless state and
		// conquer it
		conqueredStatesID = new ArrayList<Integer>();
		boolean firstConditionMet = true;
		for (int i = 0; i < conqueredStates.size(); i++) {
			State conqueredState = conqueredStates.get(i);
			ArrayList<State> allAdjToConquered = new ArrayList<State>();
			ArrayList<State> ownAdjToConquered = new ArrayList<State>();

			// Getting own states which are adjacent to defenseless state
			allAdjToConquered = myMap.getAdjacentStatesOfState(conqueredState,
					(byte) 2);
			for (State adjState : allAdjToConquered) {
				if (adjState.getCountryID() == myCountryID)
					ownAdjToConquered.add(adjState);
			}

			// For each own state which is adjacent to defenseless state, check
			// if it's in supporting state list. If it has enough army, move 1/3
			// of its armies to defenseless state and conquer it
			outerloop: for (State adjState : ownAdjToConquered) {
				for (State supportingState : supportingStates) {
					if (adjState.getStateID() == supportingState.getStateID()) {
						int supportingStateStrength = supportingState
								.getArmyDetail().getArmyStrength(
										supportingState.getArmyDetail());
						if (supportingStateStrength / 3 >= 1) {
							int extraArmies = (int) Math
									.floor((double) supportingStateStrength / 3);

							battleArmy = new BattleArmy();
							battleArmy = supportingState.reduceArmyDetail(
									supportingState, extraArmies);

							movingArmyDetail = new ArmyDetail();
							movingArmyDetail = battleArmy.getStateCasualties();
							remainingArmyDetail = new ArmyDetail();
							remainingArmyDetail = battleArmy
									.getStateRemainingArmy();

							conqueredState.addArmyDetail(movingArmyDetail);
							conqueredState.setCountryID(myCountryID);

							supportingState.setArmyDetail(remainingArmyDetail);

							Logger.logMessage("State '"
									+ supportingState.getStateName()
									+ "' conquers recently attacked state '"
									+ conqueredState.getStateName()
									+ "' by moving armies to it.");
							logMovement(movingArmyDetail,
									supportingState.getStateName(),
									conqueredState.getStateName());
							conqueredStatesID.add(conqueredState.getStateID());

							map.setMyStateList(myStateList);
							updateLinks(conqueredState, myMap);
							updateMap();

							break outerloop;
						}
					}
				}
			}
		}
		removeStates(conqueredStatesID);

		// If still there are any defenseless state, try to move the minimum
		// army from a defending state to it, to conquer it
		if (conqueredStates.size() > 0) {
			conqueredStatesID = new ArrayList<>();
			for (int j = 0; j < conqueredStates.size(); j++) {
				State conqueredState = conqueredStates.get(j);
				ArrayList<State> allAdjToConquered = new ArrayList<State>();
				ArrayList<State> ownAdjToConquered = new ArrayList<State>();
				allAdjToConquered = myMap.getAdjacentStatesOfState(
						conqueredState, (byte) 2);

				for (State adjState : allAdjToConquered) {
					if (adjState.getCountryID() == myCountryID)
						ownAdjToConquered.add(adjState);
				}
				outerloop: for (State adjState : ownAdjToConquered) {
					for (int i = 40; i >= 10; i -= 10) {
						int adjStateStrength = adjState.getArmyDetail()
								.getArmyStrength(adjState.getArmyDetail());
						if (adjStateStrength / i >= 1) {
							int extraArmies = (int) Math
									.floor((double) adjStateStrength / i);

							battleArmy = new BattleArmy();
							battleArmy = adjState.reduceArmyDetail(adjState,
									extraArmies);

							movingArmyDetail = new ArmyDetail();
							movingArmyDetail = battleArmy.getStateCasualties();
							remainingArmyDetail = new ArmyDetail();
							remainingArmyDetail = battleArmy
									.getStateRemainingArmy();

							conqueredState.addArmyDetail(movingArmyDetail);
							conqueredState.setCountryID(myCountryID);

							adjState.setArmyDetail(remainingArmyDetail);

							Logger.logMessage("State '"
									+ adjState.getStateName()
									+ "' conquers state '"
									+ conqueredState.getStateName() + "'.");
							logMovement(movingArmyDetail,
									adjState.getStateName(),
									conqueredState.getStateName());
							conqueredStatesID.add(conqueredState.getStateID());

							map.setMyStateList(myStateList);
							updateLinks(conqueredState, myMap);
							updateMap();

							break outerloop;
						}
					}
				}
			}
			removeStates(conqueredStatesID);
			if (conqueredStates.size() > 0) {
				Logger.logMessage("There is not enough army available to move to defenseless state(s)");
				conqueredStates.clear();
			}
		}

		// Check each supporting state individually and find its adjacent
		// states
		// within the same country
		if (supportingStates.size() > 0) {
			for (State supportingState : supportingStates) {
				int supportingStateStrength = supportingState.getArmyDetail()
						.getArmyStrength(supportingState.getArmyDetail());

				adjacentStates = new ArrayList<State>();
				adjacentStates = myMap.getAdjacentStatesOfState(
						supportingState, (byte) 1);

				// For each adjacent state to a defending state, check
				// whether
				// or not it's in defending state list. If yes, add it to
				// list of army receiver
				receiverStates = new ArrayList<State>();
				for (State adjacentState : adjacentStates) {
					for (State activeState : defendingStates) {
						if (adjacentState.getStateID() == activeState
								.getStateID())
							receiverStates.add(activeState);
					}
				}
				// Check if the receiver state list contains any state and
				// whether or not the supporting state has enough army to be
				// able to move it to
				// another army
				if (receiverStates.size() > 0) {
					if (supportingStateStrength / 2 >= 1) {
						int extraArmies = (int) Math
								.floor((double) supportingStateStrength / 2);
						int equalArmies = (int) Math.floor((double) extraArmies
								/ receiverStates.size());

						for (int i = 0; i < receiverStates.size(); i++) {
							battleArmy = new BattleArmy();
							battleArmy = supportingState.reduceArmyDetail(
									supportingState, equalArmies);

							movingArmyDetail = new ArmyDetail();
							movingArmyDetail = battleArmy.getStateCasualties();
							remainingArmyDetail = new ArmyDetail();
							remainingArmyDetail = battleArmy
									.getStateRemainingArmy();

							receiverStates.get(i).addArmyDetail(
									movingArmyDetail);

							supportingState.setArmyDetail(remainingArmyDetail);

							logMovement(movingArmyDetail,
									supportingState.getStateName(),
									receiverStates.get(i).getStateName());
						}
						map.setMyStateList(myStateList);
						updateMap();

					} else
						Logger.logMessage("State '"
								+ supportingState.getStateName()
								+ "' doesn't have enough armies to move to other states which needs aditional armies");
				} else {
					if (supportingState.getArmyDetail().getArmyStrength(
							supportingState.getArmyDetail()) / 2 >= 1)
						Logger.logMessage("There is no state adjacent to state '"
								+ supportingState.getStateName()
								+ "' which needs aditional armies");
				}
			}
		}

		// Moving one third of capital army strength to adjacent
		// stable states
		// Find the capital among active states
		for (State defendingState : defendingStates) {
			int defendingStateStrength = defendingState.getArmyDetail()
					.getArmyStrength(defendingState.getArmyDetail());
			if (defendingState.getIsCapital()) {
				adjacentStates = new ArrayList<State>();
				adjacentStates = myMap.getAdjacentStatesOfState(defendingState,
						(byte) 1);

				// Check whether or not adjacent states needs army
				receiverStates = new ArrayList<State>();
				for (State adjacentState : adjacentStates) {
					for (State activeState : defendingStates) {
						if (adjacentState.getStateID() == activeState
								.getStateID())
							receiverStates.add(activeState);
					}
				}

				// Check if enough army is available to move to any
				// potential army seeker
				if (receiverStates.size() > 0) {
					if (defendingStateStrength / 3 >= 1) {
						int extraArmies = (int) Math
								.floor((double) defendingStateStrength / 3);
						int equalArmies = (int) Math.floor((double) extraArmies
								/ receiverStates.size());

						for (int i = 0; i < receiverStates.size(); i++) {

							battleArmy = new BattleArmy();
							battleArmy = defendingState.reduceArmyDetail(
									defendingState, equalArmies);

							movingArmyDetail = new ArmyDetail();
							movingArmyDetail = battleArmy.getStateCasualties();
							remainingArmyDetail = new ArmyDetail();
							remainingArmyDetail = battleArmy
									.getStateRemainingArmy();

							receiverStates.get(i).addArmyDetail(
									movingArmyDetail);

							defendingState.setArmyDetail(remainingArmyDetail);

							logMovement(movingArmyDetail,
									defendingState.getStateName(),
									receiverStates.get(i).getStateName());
						}
						map.setMyStateList(myStateList);
						updateMap();
					} else
						Logger.logMessage("State '"
								+ defendingState.getStateName()
								+ "' doesn't have enough armies to move to other states which needs aditional armies");
				} else
					Logger.logMessage("There is no state adjacent to state '"
							+ defendingState.getStateName()
							+ "' which needs additional armies");

				break;
			}
		}
	}

	/**
	 * This method removes states from conquered lists using their ID and index
	 * 
	 * @param idLsit
	 *            List of IDs that should be removed
	 */
	private void removeStates(ArrayList<Integer> idLsit) {
		for (int i = 0; i < idLsit.size(); i++) {
			conqueredStates.remove(idLsit.get(i));
		}
	}

	/**
	 * This method logs the result of analysis for movement
	 * 
	 * @param weakStates
	 *            List of weak states
	 * @param resourceOwnerStates
	 *            List of states which have a valuable resource
	 * @param borderStates
	 *            List of states which are ideal for continent possession
	 */
	private void LogMovementAnalysisResult(ArrayList<State> weakStates,
			ArrayList<State> resourceOwnerStates, ArrayList<State> borderStates) {
		String weak = "";
		String resource = "";
		String border = "";

		Logger.logMessage("********** STRATEGIC MOVEMENTS STAGE *********");

		if (weakStates.size() > 0) {
			weak = "States with weak or zero army strength: ";
			for (int i = 0; i < weakStates.size(); i++) {
				weak += weakStates.get(i).getStateName() + ", ";
			}
		} else
			weak = "No state is weaker than its adjacent enemy states.";

		if (resourceOwnerStates.size() > 0) {
			resource = "States which have a valuable resource: ";
			for (int i = 0; i < resourceOwnerStates.size(); i++) {
				resource += resourceOwnerStates.get(i).getStateName() + ", ";
			}
		} else
			resource = "No state has resource which other countries need.";

		if (borderStates.size() > 0) {
			border = "States which are ideal for whole continent possession: ";
			for (int i = 0; i < borderStates.size(); i++) {
				border += borderStates.get(i).getStateName() + ", ";
			}
		} else
			border = "No state is ideal for whole continent possession";

		Logger.logMessage(weak);
		Logger.logMessage(resource);
		Logger.logMessage(border);
		Logger.logMessage("\n");
	}

	/**
	 * This method logs the movement phase
	 * 
	 * @param detail
	 *            Detail of armies that are moving
	 * @param sourceState
	 *            State which moves its armies to another state
	 * @param targetState
	 *            State which receives armies from another state
	 */
	private void logMovement(ArmyDetail detail, String sourceState,
			String targetState) {
		String armyType;
		Hashtable<String, Integer> armiesCount = new Hashtable<String, Integer>();
		armiesCount = detail.getArmyCount(detail);

		Set set = armiesCount.keySet();
		Iterator itr = set.iterator();
		while (itr.hasNext()) {
			armyType = (String) itr.next();
			Logger.logMessage("State '" + sourceState + "' moves "
					+ armiesCount.get(armyType) + " units of " + armyType
					+ " to state '" + targetState + "'.");
		}
	}

	/**
	 * This method finds the resources that a country doesn't own
	 * 
	 * @param myMap
	 *            Map of the game
	 * @param countryID
	 *            ID of the country
	 * @return List of resources that the country needs
	 */
	public ArrayList<Resource> getDesiredResources(Map myMap, int countryID) {
		ArrayList<Resource> ownResources = new ArrayList<Resource>();
		ArrayList<Resource> allResources = new ArrayList<Resource>();
		ArrayList<Resource> desiredResources = new ArrayList<Resource>();
		Resource tempResource = new Resource();

		// Filling ArrayLists with resources of our country and all possible
		// resources
		ownResources = myMap.getResourcesByCountryID(countryID);
		allResources = tempResource.getResourceList();
		desiredResources = tempResource.getResourceList();

		// removing our country's resources from desired resources
		for (Resource ownResource : ownResources) {
			for (int i = 0; i < allResources.size(); i++) {
				if (ownResource.resourceLevel() == allResources.get(i)
						.resourceLevel()) {
					desiredResources = removeResource(desiredResources,
							ownResource.resourceLevel());
					break;
				}
			}
		}
		desiredResources = removeResource(desiredResources, 0);
		return desiredResources;
	}

	/**
	 * This method gets resource list and removes a resource from it
	 * 
	 * @param resources
	 *            List of resources
	 * @param resourceLevel
	 *            Resource which we want to remove
	 * @return List of resources after removing a resource
	 */
	private ArrayList<Resource> removeResource(ArrayList<Resource> resources,
			int resourceLevel) {
		Resource tempResource = new Resource();
		for (int i = 0; i < resources.size(); i++) {
			if (resources.get(i).resourceLevel() == resourceLevel) {
				tempResource = resources.get(i);
				break;
			}
		}
		resources.remove(tempResource);
		return resources;
	}

	/**
	 * This method checks the possession of continents by our country and
	 * returns a list of states to be conquered by our country
	 * 
	 * @param myMap
	 *            Map of the game
	 * @param countryID
	 *            ID of the country
	 * @return List of states to be attacked and conquered
	 */
	public ArrayList<State> getDesiredStates(Map myMap, int countryID) {
		float[] percentageList = new float[myMap.getMyContinentList().size()];
		ArrayList<State> statesOfContinent = new ArrayList<State>();
		ArrayList<State> desiredStates = new ArrayList<State>();
		Continent conqueringContinent;

		percentageList = myMap.getContinentPossession(countryID);
		int continentIndex = -1;
		float continentPercentage = 0;

		// Find the index of continent which has the highest possession
		// percentage
		for (int i = 0; i < percentageList.length; i++) {
			if (percentageList[i] > continentPercentage
					&& percentageList[i] < 1) {
				continentPercentage = percentageList[i];
				continentIndex = i;
			}
		}

		// Find the continent using its index in the Array
		if (continentIndex != -1) {
			conqueringContinent = myMap.getMyContinentList()
					.get(continentIndex);

			// Get States of the desired continent
			statesOfContinent = myMap
					.getStatesByContinentID(conqueringContinent
							.getContinentID());

			for (State tempState : statesOfContinent) {
				if (tempState.getCountryID() != countryID)
					desiredStates.add(tempState);
			}
		}
		// Creating a hashset to remove duplicate states
		HashSet stateHashSet = new HashSet(desiredStates);
		desiredStates.clear();
		desiredStates = new ArrayList(stateHashSet);

		return desiredStates;
	}

	/**
	 * This method updates link information
	 * 
	 * @param state
	 *            The state that is going to be updated in the link
	 * @param map
	 *            Object of map of the game
	 */
	private void updateLinks(State state, Map map) {
		for (int i = 0; i < map.getMyLinkList().size(); i++) {
			if (map.getMyLinkList().get(i).getSourceState().getStateID() == state
					.getStateID())
				map.getMyLinkList().get(i).setSourceState(state);
			else if (map.getMyLinkList().get(i).getDestintionState()
					.getStateID() == state.getStateID())
				map.getMyLinkList().get(i).setDestintionState(state);
		}
	}

	/**
	 * This method gets the arraylist of country objects, and return the
	 * randomly selected sequence of country's turn.
	 * 
	 * @param countryList
	 *            Takes list of countries
	 * @return countryTurnSequence Returns randomly selected order of country's
	 *         turn
	 */
	public ArrayList<Country> countryTurnSequence(ArrayList<Country> countryList) {
		int randomNumber = 0;
		int countryListLength = countryList.size();
		/**
		 * Boolean Array is used to restrict repeated numbers from random
		 * generated numbers
		 */
		boolean[] validateArray = new boolean[countryListLength];
		Random rand = new Random();
		countryTurnSequence.removeAll(countryTurnSequence);
		// assign the value false to complete array
		Arrays.fill(validateArray, false);

		for (int counter = 0; counter < countryListLength;) {
			randomNumber = rand.nextInt(countryListLength);
			if (validateArray[randomNumber] == false) {
				validateArray[randomNumber] = true;
				countryTurnSequence.add(countryList.get(randomNumber));
				counter++;
			} else {
				randomNumber = rand.nextInt(countryListLength);
			}
		}
		nextCountryTurn = (ArrayList<Country>) countryTurnSequence.clone();
		return countryTurnSequence;
	}

	/**
	 * Over loaded method, which do all the initial setup tasks, before
	 * simulation could start.
	 * 
	 * @param countryList
	 *            : Arraylist of countries
	 * @param stateList
	 *            : Arraylist of states
	 * @param continentList
	 *            : Arraylist of continents
	 * @param linkList
	 *            : Arraylist of links
	 */
	public void startUpPhase(ArrayList<Country> countryList,
			ArrayList<State> stateList, ArrayList<Continent> continentList,
			ArrayList<Link> linkList) {

		this.myCountryList = countryList;
		this.myStateList = stateList;
		this.myContinentList = continentList;
		this.myLinkList = linkList;

		this.map.setMyContinentList(this.myContinentList);
		this.map.setMyCountryList(this.myCountryList);
		this.map.setMyStateList(this.myStateList);
		this.map.setMyLinkList(this.myLinkList);

		assignTechnologyLevelToCountry();
		assignResourcesToStates();
		ArrayList<Country> tempCountryArr = (ArrayList<Country>) this.myCountryList
				.clone();
		countryTurnSequence(tempCountryArr);
		this.map.setCountryTurnSequence(this.countryTurnSequence);
		this.map.setNextCountryTurn(this.nextCountryTurn);

		loggingForInitialSetup();
		generateMap();
	}

	/**
	 * Over loaded method, which do all the initial setup tasks, before
	 * simulation could start.
	 * 
	 * @param map
	 *            Instance of map
	 * @param countryList
	 *            Arraylist of countries
	 * @throws Exception
	 *             the method throws the exception
	 */
	public void startUpPhase(Map map, ArrayList<Country> countryList)
			throws Exception {

		this.map = map;

		// Remove all previous capitals
		Country country = new Country();
		myStateList = country.removeCapitals(map.getMyStateList());

		State state = new State();
		ArrayList<State> tempStateArr = new ArrayList<State>();

		myCountryList = countryList;
		myContinentList = map.getMyContinentList();
		myLinkList = map.getMyLinkList();

		assignStateToCountry();
		this.map.setMyCountryList(countryList);
		myStateList = map.getMyStateList();

		if (countryList.size() <= myStateList.size()) {
			// Assign a capital to each country
			for (Country newCountry : myCountryList) {
				tempStateArr = state.getStatesByCountryId(
						newCountry.getCountryID(), myStateList);
				Random randomGenerator = new Random();
				int stateId = tempStateArr.get(
						randomGenerator.nextInt(tempStateArr.size()))
						.getStateID();
				int stateIndex = state.getStateIndexByStateId(stateId,
						myStateList);
				myStateList.get(stateIndex).setIsCapital(true);
			}
			this.map.setMyStateList(myStateList);
			assignTechnologyLevelToCountry();
			assignResourcesToStates();
			countryTurnSequence(myCountryList);
			this.map.setCountryTurnSequence(this.countryTurnSequence);
			this.map.setNextCountryTurn(this.nextCountryTurn);

			loggingForInitialSetup();

			generateMap();
		} else {
			Game game = new Game();
			JOptionPane
					.showMessageDialog(game,
							"The selected map has more countries and less states, Map cannot be created");
		}
	}

	/**
	 * Over loaded method, which do all the initial setup tasks, before
	 * simulation could start.
	 * 
	 * @param map
	 *            : Instance of map.
	 */
	public void startUpPhase(Map map) {

		this.map = map;
		myCountryList = map.getMyCountryList();
		myStateList = map.getMyStateList();
		myContinentList = map.getMyContinentList();
		myLinkList = map.getMyLinkList();
		stage = map.getStage();

		assignTechnologyLevelToCountry();
		assignResourcesToStates();
		countryTurnSequence(myCountryList);
		this.map.setCountryTurnSequence(this.countryTurnSequence);
		this.map.setNextCountryTurn(this.nextCountryTurn);

		loggingForInitialSetup();
		generateMap();
	}

	/**
	 * The method is used load the simulation
	 * 
	 * @param map
	 *            is the map object
	 */
	public void startUpPhaseSameSimulation(Map map) {

		this.map = map;
		myCountryList = map.getMyCountryList();
		myStateList = map.getMyStateList();
		myContinentList = map.getMyContinentList();
		myLinkList = map.getMyLinkList();
		countryTurnSequence = map.getCountryTurnSequence();
		nextCountryTurn = map.getNextCountryTurn();
		stage = map.getStage();

		if (countryTurnSequence.size() == 0 && nextCountryTurn.size() == 0) {
			assignTechnologyLevelToCountry();
			assignResourcesToStates();
			countryTurnSequence(myCountryList);
		}
		this.map.setCountryTurnSequence(this.countryTurnSequence);
		this.map.setNextCountryTurn(this.nextCountryTurn);

		loggingForInitialSetup();
		generateMap();
	}

	/**
	 * This method will assign Technology level basic to all the Country in the
	 * beginning of the game.
	 */
	private void assignTechnologyLevelToCountry() {

		Technology technology = new Technology();
		technology.technologyLevelBasic();

		for (Country country : myCountryList) {
			country.setTechnology(technology);
		}
	}

	/**
	 * This method receives list of states and countries. The method will
	 * randomly assign states to countries according to the values provided in
	 * the list
	 * 
	 * @param stateList
	 *            Arraylist of states
	 * @param countryList
	 *            Arraylsit of countries
	 */
	private void assignStateToCountry() {

		// After resources and technology and are assigned to states and
		// countries, set Map instance

		int stateListLength = myStateList.size();

		// Shuffling StateID list randomly
		ArrayList<Integer> StateIDs = new ArrayList<Integer>();
		for (int i = 1; i <= stateListLength; i++) {
			StateIDs.add(i);
		}
		Collections.shuffle(StateIDs);

		for (int stateListCounter = 0; stateListCounter < stateListLength;) {
			for (int countryListCounter = 0; countryListCounter < this.myCountryList
					.size(); countryListCounter++) {
				if (stateListCounter >= stateListLength) {
					break;
				}
				myStateList.get(StateIDs.get(stateListCounter) - 1)
						.setCountryID(
								(myCountryList.get(countryListCounter)
										.getCountryID()));

				stateListCounter++;
			}
		}
		map.setMyLinkList(myLinkList);
		map.setMyStateList(myStateList);
	}

	/**
	 * This method sets the simulation flow, and starts the simulation
	 * 
	 * @param simulationFlow
	 *            : Which simulation flow to follow.
	 * @throws Exception
	 *             throws the exception
	 */
	public void startSimulation(int simulationFlow) throws Exception {
		switch (simulationFlow) {
		case Constants.SIMULATION_SMOOTH:
			runSimulationSmoothly();
			break;
		case Constants.SIMULATION_PAUSE_AFTER_EACH_COUNTRY_TURN:
			runSimulationByCountryTurn();
			break;
		case Constants.SIMULATION_PAUSE_AFTER_EACH_PHASE:
			runSimulationByEachPhase();
			break;
		case Constants.SIMULATION_PAUSE_AFTER_ALL_COUNTRIES_TURN:
			runSimulationByAllCountriesTurn();
			break;
		}
	}

	/**
	 * Pause the simulation when it is running smoothly
	 * 
	 */
	public void pause() {
		timer.cancel();
	}

	/**
	 * Starts simulation without any pause
	 * 
	 * @throws Exception
	 *             the method throws the exception
	 */
	private void runSimulationSmoothly() throws Exception {
		boolean status = false;
		status = gameStatus(true);

		if (!status) {
			timer = new Timer();
			timer.schedule(new TimerTask() {
				public void run()

				{
					if (!gameStatus(false)) {
						Country nextCountry = nextCountryTurn.get(0);
						int countryId = nextCountry.getCountryID();
						int technologyLevel = nextCountry.getTechnology()
								.technologyLevelNo();

						switch (stage) {

						case Constants.PRODUCTION_STAGE:
							// Call Production Stage
							try {
								productionPhase(countryId, technologyLevel);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							// After Production Stage
							stage = Constants.STRATEGIC_INTELLIGENCE_STAGE;
							map.setStage(Constants.STRATEGIC_INTELLIGENCE_STAGE);

						case Constants.STRATEGIC_INTELLIGENCE_STAGE:
							// Call Strategic Intelligence Gathering Stage
							try {
								analyzeAttack(countryId, true);
							} catch (CloneNotSupportedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							// After Strategic Intelligence Gathering Stage
							stage = Constants.MILITARY_THEATRE_STAGE;
							map.setStage(stage);

						case Constants.MILITARY_THEATRE_STAGE:
							// Call Military Theater Stage
							try {
								if (attackingStates.size() > 0) {
									battlePhase(countryId);
								} else
									Logger.logMessage("Based on states' strengths, no state has the eligibility to attack another state");
							} catch (CloneNotSupportedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							// After Military Theater Stage
							stage = Constants.STRATEGIC_MOVEMENT_STAGE;
							map.setStage(stage);

						case Constants.STRATEGIC_MOVEMENT_STAGE:
							// Call Strategic Movement Stage
							analyzeMovement(countryId);
							// After Strategic Movement Stage
							GameEngine.nextCountryTurn = incrementCountryTurnAndTechnology(
									countryId, nextCountryTurn);
							stage = Constants.PRODUCTION_STAGE;
							map.setStage(stage);
							maintainCountryTurn();
						}
					} else {
						JOptionPane.showMessageDialog(game, nextCountryTurn
								.get(nextCountryTurn.size() - 1)
								.getCountryName()
								+ " wins the game");
						game.btnPlayGame.setEnabled(false);
						game.btnStartGame.setEnabled(false);
						timer.cancel();
					}
				}
			}, 0, 500);
		}
	}

	/**
	 * Starts the simulation by pausing after every country turn
	 * 
	 * @throws Exception
	 *             the method throws the exception
	 */
	private void runSimulationByCountryTurn() throws Exception {
		boolean status = false;
		status = gameStatus(true);
		if (!status) {
			Country nextCountry = nextCountryTurn.get(0);
			int countryId = nextCountry.getCountryID();
			int technologyLevel = nextCountry.getTechnology()
					.technologyLevelNo();
			switch (stage) {

			case Constants.PRODUCTION_STAGE:
				// Call Production Stage
				productionPhase(countryId, technologyLevel);

				// After Production Stage
				stage = Constants.STRATEGIC_INTELLIGENCE_STAGE;
				map.setStage(stage);

			case Constants.STRATEGIC_INTELLIGENCE_STAGE:
				// Call Strategic Intelligence Gathering Stage
				analyzeAttack(countryId, true);

				// After Strategic Intelligence Gathering Stage
				stage = Constants.MILITARY_THEATRE_STAGE;
				map.setStage(stage);
			case Constants.MILITARY_THEATRE_STAGE:
				// Call Military Theater Stage
				if (attackingStates.size() > 0) {
					battlePhase(countryId);
				} else
					Logger.logMessage("Based on states' strengths, no state has the eligibility to attack another state");

				// After Military Theater Stage
				stage = Constants.STRATEGIC_MOVEMENT_STAGE;
				map.setStage(stage);
			case Constants.STRATEGIC_MOVEMENT_STAGE:
				// Call Strategic Movement Stage
				analyzeMovement(countryId);

				// After Strategic Movement Stage
				GameEngine.nextCountryTurn = incrementCountryTurnAndTechnology(
						countryId, nextCountryTurn);

				stage = Constants.PRODUCTION_STAGE;
				map.setStage(stage);
				maintainCountryTurn();
			}
		} else {
			JOptionPane.showMessageDialog(game,
					nextCountryTurn.get(nextCountryTurn.size() - 1)
							.getCountryName() + " wins the game");
			game.btnPlayGame.setEnabled(false);
			game.btnStartGame.setEnabled(false);
		}
	}

	/**
	 * Starts simulation pausing after all countries have completed their turn
	 * 
	 * @throws Exception
	 *             the method throws the generic excception
	 */
	private void runSimulationByAllCountriesTurn() throws Exception {
		boolean status = false;
		for (int i = 0; i < countryTurnSequence.size(); i++) {
			status = gameStatus(true);
			if (!status) {

				Country nextCountry = nextCountryTurn.get(0);
				int countryId = nextCountry.getCountryID();
				int technologyLevel = nextCountry.getTechnology()
						.technologyLevelNo();

				switch (stage) {

				case Constants.PRODUCTION_STAGE:
					// Call Production Stage
					productionPhase(countryId, technologyLevel);

					// After Production Stage
					stage = Constants.STRATEGIC_INTELLIGENCE_STAGE;

				case Constants.STRATEGIC_INTELLIGENCE_STAGE:
					// Call Strategic Intelligence Gathering Stage
					analyzeAttack(countryId, true);

					// After Strategic Intelligence Gatherin)g Stage
					stage = Constants.MILITARY_THEATRE_STAGE;
					map.setStage(stage);

				case Constants.MILITARY_THEATRE_STAGE:
					// Call Military Theater Stage
					if (attackingStates.size() > 0) {
						battlePhase(countryId);
					} else
						Logger.logMessage("Based on states' strengths, no state has the eligibility to attack another state");
					// After Military Theater Stage
					stage = Constants.STRATEGIC_MOVEMENT_STAGE;
					map.setStage(stage);

				case Constants.STRATEGIC_MOVEMENT_STAGE:
					// Call Strategic Movement Stage
					analyzeMovement(countryId);

					// After Strategic Movement Stage
					GameEngine.nextCountryTurn = incrementCountryTurnAndTechnology(
							countryId, nextCountryTurn);

					stage = Constants.PRODUCTION_STAGE;
					map.setStage(stage);

					maintainCountryTurn();
				}
			} else {
				JOptionPane.showMessageDialog(game,
						nextCountryTurn.get(nextCountryTurn.size() - 1)
								.getCountryName() + " wins the game");
				game.btnPlayGame.setEnabled(false);
				game.btnStartGame.setEnabled(false);
				break;
			}
		}
	}

	/**
	 * Starts the simulation by pausing after every phase of a country.
	 * 
	 * @throws Exception
	 *             the method throws the generic exception
	 */
	private void runSimulationByEachPhase() throws Exception {
		boolean status = false;

		status = gameStatus(true);
		Country nextCountry = nextCountryTurn.get(0);
		int countryId = nextCountry.getCountryID();
		int technologyLevel = nextCountry.getTechnology().technologyLevelNo();
		if (!status) {
			switch (stage) {

			case Constants.PRODUCTION_STAGE:
				// Call Production Stage
				productionPhase(countryId, technologyLevel);
				stage = Constants.STRATEGIC_INTELLIGENCE_STAGE;
				map.setStage(stage);
				break;

			case Constants.STRATEGIC_INTELLIGENCE_STAGE:
				// Call Strategic Intelligence Gathering Stage
				analyzeAttack(nextCountry.getCountryID(), true);

				// After Strategic Intelligence Gathering Stage
				stage = Constants.MILITARY_THEATRE_STAGE;
				map.setStage(stage);
				break;

			case Constants.MILITARY_THEATRE_STAGE:
				// Call Military Theater Stage
				if (attackingStates.size() > 0) {
					battlePhase(countryId);
				} else
					Logger.logMessage("Based on states' strengths, no state has the eligibility to attack another state");

				// After Military Theater Stage
				stage = Constants.STRATEGIC_MOVEMENT_STAGE;
				map.setStage(stage);
				break;

			case Constants.STRATEGIC_MOVEMENT_STAGE:
				// Call Strategic Movement Stage
				analyzeMovement(countryId);

				// After Strategic Movement Stage
				GameEngine.nextCountryTurn = incrementCountryTurnAndTechnology(
						countryId, nextCountryTurn);

				stage = Constants.PRODUCTION_STAGE;
				map.setStage(stage);
				maintainCountryTurn();
				break;
			}
		} else {
			// Call Strategic Movement Stage
			analyzeMovement(countryId);

			// After Strategic Movement Stage
			GameEngine.nextCountryTurn = incrementCountryTurnAndTechnology(
					countryId, nextCountryTurn);

			stage = Constants.PRODUCTION_STAGE;
			map.setStage(stage);
			maintainCountryTurn();

			JOptionPane.showMessageDialog(game,
					nextCountryTurn.get(nextCountryTurn.size() - 1)
							.getCountryName() + " wins the game");
			game.btnPlayGame.setEnabled(false);
			game.btnStartGame.setEnabled(false);
		}
	}

	/**
	 * Serves as a stack for maintaining country turns
	 */
	private void maintainCountryTurn() {
		ArrayList<Country> tempArr = new ArrayList<Country>();
		tempArr = (ArrayList<Country>) nextCountryTurn.clone();
		Country country = new Country();
		int index = 0;
		if (nextCountryTurn.size() > 0) {
			nextCountryTurn.removeAll(nextCountryTurn);
			nextCountryTurn = new ArrayList<Country>();
			for (int i = 0; i < tempArr.size() - 1; i++) {
				if (country.isCountryHaveCapital(tempArr.get(i + 1)
						.getCountryID(), this.map.getMyStateList())) {
					nextCountryTurn.add(index, tempArr.get((i + 1)));
					index++;
				}
			}
			if (country.isCountryHaveCapital(tempArr.get(0).getCountryID(),
					this.map.getMyStateList())) {
				nextCountryTurn.add(nextCountryTurn.size(), tempArr.get(0));
			}
			this.map.setNextCountryTurn(nextCountryTurn);
		}
	}

	/**
	 * Increments the Country turn and technology
	 * 
	 * @param countryId
	 *            : Id of the country
	 * @param countryArr
	 *            : Arraylist of the country
	 * @return : Arraylist of the country with incremented turn and technology
	 */
	private ArrayList<Country> incrementCountryTurnAndTechnology(int countryId,
			ArrayList<Country> countryArr) {
		try {
			Country country = new Country();
			Technology technology = new Technology();
			int index = country.getCountryIndexByCountryID(countryId,
					countryArr);
			country = countryArr.get(index);
			int countryTurn = country.getCountryTurn();
			if (countryTurn == 5) {
				country.setTechnology(technology.technologyLevelMedium());
			} else if (countryTurn == 10) {
				country.setTechnology(technology.technologyLevelHigh());
			}
			country.setCountryTurn(countryTurn + 1);
			countryArr.set(index, country);
		} catch (Exception ex) {
			System.out.print(ex.getMessage());
		}
		return countryArr;
	}

	/**
	 * <p>
	 * This method determines whether the game is over, by evaluating conditions
	 * </p>
	 * <p>
	 * The conditions are as follow: </br> 1. If One country occupies all
	 * capitals. 2. If One country conquers all states in the map.
	 * </p>
	 * 
	 * @return gameStatus Boolean value which returns true if it meets win
	 *         conditions.
	 */
	private boolean gameStatus(boolean printResult) {
		boolean gameStatus = false;
		Country country = new Country();
		int countryID = country.getIdOfCountryControllingAllStates(myStateList);
		countryID = country.getCountryControllingAllCapitals(myStateList);

		if (printResult) {
			Logger.logMessage("\n");
			Logger.logMessage("**************** GAME STATUS *****************");
		}
		if (countryID == -1) {
			// there is no such country
			if (printResult)
				Logger.logMessage("No country controls all capitals or states. Game can proceed");
		} else {
			country = map.getCountryByID(countryID);
			gameStatus = true;
			Logger.logMessage("\n");
			Logger.logMessage("All states or capitals are controlled by : "
					+ country.getCountryName() + ". Game cannot proceed.");
		}
		return gameStatus;
	}

	/**
	 * This method will assign resources randomly to states.
	 */
	private void assignResourcesToStates() {

		Resource myResource = new Resource();
		ArrayList<Resource> myResourceList = new ArrayList<Resource>();

		// Retrieve Arraylist of newly created Resource objects and assign it to
		// local arraylist
		myResourceList = myResource.getResourceList();

		// Shuffling resources list to achieve randomization
		Collections.shuffle(myResourceList);

		// Assigning resources to states
		for (int stateListCounter = 0; stateListCounter < myStateList.size();) {
			for (int resourceListCounter = 0; resourceListCounter < myResourceList
					.size(); resourceListCounter++) {
				if (stateListCounter >= myStateList.size()) {
					break;
				}
				myStateList.get(stateListCounter).setResource(
						myResourceList.get(resourceListCounter));
				stateListCounter++;
			}
		}
	}

	/**
	 * This method starts a battle
	 * 
	 * @param countryId
	 *            ID of attacking country
	 * @throws CloneNotSupportedException
	 *             Required class for handling exception
	 */
	public boolean battlePhase(int countryId)
			throws CloneNotSupportedException, InterruptedException {
		boolean finalized = false;

		while (!finalized && !gameStatus(false)) {
			if (attackingStates.size() > 0) {
				MultipleBattleFronts multipleBattleFronts = new MultipleBattleFronts(
						weakestState, attackingStates);
				weakestState
						.setArmyDetail(TargetStateBattleArmyBuilder.totalArmyDetailOfTargetState
								.getArmyDetail());
				logBattlePhase(attackingStates.size(), weakestState);
				TargetStateBattleArmyBuilder.totalArmyDetailOfTargetState = null;
				MultipleBattleFronts.battleFrontCasualtiesStartup = null;
				BattleGround.targetStateCasualties = null;
				BattleGround.attackingStateCasualties = null;
				BattleGround.attackingStateRemainingArmies = null;

				for (State tempState : map.getMyStateList()) {
					if (tempState.getStateID() == weakestState.getStateID()) {
						if (tempState.getArmyDetail().getArmyStrength(
								tempState.getArmyDetail()) == 0) {
							conqueredStates.add(tempState);
							break;
						}
					}
				}
				analyzeAttack(countryId, false);
			} else
				finalized = true;
				return true;
		}
		
		map.setMyStateList(myStateList);
		updateMap();
		return true;
	}

	/**
	 * The method is use to Log the battle phase
	 * 
	 * @param battleFronts
	 *            int value represent battle fronts
	 * @param targetState
	 *            represent target state in the battle phase
	 * @throws InterruptedException
	 *             throws the interrupted excception
	 */
	private void logBattlePhase(int battleFronts, State targetState)
			throws InterruptedException {
		Thread.sleep(500);
		Logger.logMessage("Battle result: \n");
		int x = 0;
		for (int i = 0; i < battleFronts; i++) {
			x = i;
			if (MultipleBattleFronts.battleFrontCasualtiesStartup.size() >= ++x) {
				Logger.logMessage(MultipleBattleFronts.battleFrontCasualtiesStartup
						.get(i));
			}
			x = i;
			if (BattleGround.targetStateCasualties.size() >= ++x) {
				Logger.logMessage(BattleGround.targetStateCasualties.get(i));
			}
			x = i;
			if (BattleGround.attackingStateCasualties.size() >= ++x) {
				Logger.logMessage(BattleGround.attackingStateCasualties.get(i));
			}
			x = i;
			if (BattleGround.battleWinners.size() >= ++x) {
				Logger.logMessage(BattleGround.battleWinners.get(i));
			}
		}
		Logger.logMessage(" \n Post-Battle Armies");
		Logger.logMessage(targetState.getStateName()
				+ ":"
				+ " \n "
				+ targetState.getArmyDetail().getArmyCountStr(
						targetState.getArmyDetail()));
		int y = 0;
		for (int i = 0; i < battleFronts; i++) {
			y = i;
			if (BattleGround.attackingStateRemainingArmies.size() >= ++y) {
				Logger.logMessage(BattleGround.attackingStateRemainingArmies
						.get(i));
			}

		}
		MultipleBattleFronts.battleFrontCasualtiesStartup
				.removeAll(MultipleBattleFronts.battleFrontCasualtiesStartup);
		BattleGround.targetStateCasualties
				.removeAll(BattleGround.targetStateCasualties);
		BattleGround.attackingStateCasualties
				.removeAll(BattleGround.attackingStateCasualties);
		BattleGround.battleWinners.removeAll(BattleGround.battleWinners);
	}

	/**
	 * This is the first stage in player turn here Country will produce army.
	 * 
	 * @param countryID
	 *            : Id of country
	 * @param technologyLevel
	 *            : Technology Level of the country
	 * @throws Exception
	 *             the method throws the generic method
	 */
	public void productionPhase(int countryID, int technologyLevel)
			throws Exception {
		Country country = new Country();
		State state = new State();
		ArrayList<State> countryStates = new ArrayList<State>();
		ArrayList<State> countrySpecialStates = new ArrayList<State>();
		ArrayList<State> continentStates = new ArrayList<State>();
		int totalStates = 0;
		int normalStates = 0;
		String continentsOwnedByCountryStr = "";
		Continent continent = new Continent();
		country = map.getCountryByID(countryID);
		countryStates = state.getStatesByCountryId(countryID, myStateList);
		Resource countryResource = country.getResourceOfCountry(countryStates);
		Logger.logMessage("***************************************************");
		Logger.logMessage("Turn " + getCountryTurn(countryID));
		Logger.logMessage("***************************************************");
		Logger.logMessage("---------------------------------------------------");
		Logger.logMessage(country.getCountryName());
		Logger.logMessage("---------------------------------------------------");
		Logger.logMessage("---------------------------------------------------");
		Logger.logMessage("PRODUCTION PHASE : ");
		Logger.logMessage("---------------------------------------------------");
		Logger.logMessage("Capital : "
				+ country.getCountryCapital(countryID, countryStates)
						.getStateName());

		Technology countryTechnology = country.getTechnology();
		Logger.logMessage(country.getCountryName() + " controls Resources : ["
				+ country.getAllResourcesOfCountryStr(countryStates) + "]");
		Logger.logMessage(country.getCountryName() + " controls Technology : ["
				+ countryTechnology.technologyLevel() + "]");
		ArrayList<Continent> continentOwnedByCountry = continent
				.getContinentsOwnedByCountry(myContinentList, myStateList,
						countryID);
		if (continentOwnedByCountry.size() > 0) {
			for (Continent myContinent : continentOwnedByCountry) {
				continentStates = state.getStateByContinentId(
						myContinent.getContinentID(), myStateList);

				for (State specialState : continentStates) {
					countrySpecialStates.add(specialState);
				}
				continentsOwnedByCountryStr += myContinent.getContinentName()
						+ ",";
			}
			normalStates = countryStates.size() - countrySpecialStates.size();
			totalStates = normalStates + (countrySpecialStates.size() * 10);
			Logger.logMessage(country.getCountryName()
					+ " controls Continent : " + continentsOwnedByCountryStr
					+ " having " + countrySpecialStates.size() + " states : "
					+ "(" + countrySpecialStates.size() + "x10) = "
					+ countrySpecialStates.size() * 10 + " reinforcements");
			Logger.logMessage("Other than these states, "
					+ country.getCountryName() + " controls : " + normalStates
					+ " states = " + normalStates + " reinforcements");
		} else {
			totalStates = countryStates.size();
			Logger.logMessage(country.getCountryName() + " controls : "
					+ normalStates + " states = " + normalStates
					+ " reinforcements");
		}

		armyFactory = new ArmyFactory();

		Army army = armyFactory.ArmyFactory(countryResource.resourceLevel(),
				countryTechnology.technologyLevelNo(), totalStates);

		State capital = country.getCountryCapital(countryID, myStateList);

		country.addArmyDetail(army);
		capital.addArmyDetail(army);
		country.setProductionNumber(totalStates);
		country.setProductionType(army.armyName());

		Logger.logMessage("Total production from " + capital.getStateName()
				+ " factory : " + country.getProductionNumber() + " "
				+ country.getProductionType());

		int countryIndex = country.getCountryIndexByCountryID(countryID,
				myCountryList);
		int capitalIndex = state.getStateIndexByStateId(capital.getStateID(),
				myStateList);

		myStateList.set(capitalIndex, capital);
		map.setMyStateList(myStateList);

		myCountryList.set(countryIndex, country);
		map.setMyCountryList(myCountryList);
		updateMap();
	}

	/**
	 * The method returns the Country turn
	 * 
	 * @param countryId
	 *            represents the countryID
	 * @return returns int value which represents the country turn
	 */
	private int getCountryTurn(int countryId) {
		int countryTurn = 0;
		for (int i = 0; i < countryTurnSequence.size(); i++) {
			if (countryId == countryTurnSequence.get(i).getCountryID()) {
				countryTurn = i + 1;
				break;
			}
		}
		return countryTurn;
	}

	/**
	 * The method is used to Store Log for Initial setup stage
	 */
	private void loggingForInitialSetup() {
		Logger.logMessage("Player selected " + myCountryList.size()
				+ " countries");
		/*
		 * StringBuilder statesToCountries = new StringBuilder(); for (int i =
		 * 0; i < myCountryList.size(); i++) { ArrayList<State> countryStates =
		 * state.getStatesByCountryId( myCountryList.get(i).getCountryID(),
		 * myStateList); for (int y = 0; i < countryStates.size(); y++) {
		 * statesToCountries.append("State " +
		 * countryStates.get(0).getStateName() + " allocated to country " +
		 * myCountryList.get(i).getCountryName()); } }
		 * Logger.logMessage(statesToCountries.toString());
		 */}

	/**
	 * This method get the Game Jframe of the GameEngine
	 * 
	 * @return game the Game Jframe of the GameEngine
	 */

	public static Game getGame() {
		return game;
	}
}
