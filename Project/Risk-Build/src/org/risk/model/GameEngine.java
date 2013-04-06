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
	private AttackPhases attackPhases = new AttackPhases();
	private State state;
	private Link link;
	private Continent continent;
	private Country country;
	public static ArrayList<Country> countryTurnSequence;
	private static ArrayList<Country> nextCountryTurn;
	private static int stage = Constants.PRODUCTION_STAGE;
	public static ArrayList<State> conqueredStates = null;
	static public MapVisualization myMapVisualizer;
	static public Game game;
	public static State weakestState;
	public static ArrayList<State> attackingStates;

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
		attackPhases.setMyStateList(new ArrayList<State>());
		if (conqueredStates == null)
			conqueredStates = new ArrayList<State>();
		if (weakestState == null)
			weakestState = new State();
		if (attackingStates == null)
			attackingStates = new ArrayList<State>();
		attackPhases.populateMap();
	}

	/**
	 * This is used to return the map instance.
	 * 
	 * @return: Returns the map of the game
	 */
	private Map getMap() {
		return attackPhases.getMap();
	}

	/**
	 * This method get the map object and load it in the memory.
	 * 
	 * @param map
	 */
	private void setMap(Map map) {
		attackPhases.setMap(map);
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
		return attackPhases.countryTurnSequence(countryList);
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

		attackPhases.startUpPhase(countryList, stateList, continentList,
				linkList);
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

		attackPhases.startUpPhase(map, countryList);
	}

	/**
	 * Over loaded method, which do all the initial setup tasks, before
	 * simulation could start.
	 * 
	 * @param map
	 *            : Instance of map.
	 */
	public void startUpPhase(Map map) {

		attackPhases.startUpPhase(map);
	}

	/**
	 * The method is used load the simulation
	 * 
	 * @param map
	 *            is the map object
	 */
	public void startUpPhaseSameSimulation(Map map) {

		attackPhases.startUpPhaseSameSimulation(map);
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
		attackPhases.startSimulation(simulationFlow);
	}

	/**
	 * Pause the simulation when it is running smoothly
	 * 
	 */
	public void pause() {
		attackPhases.pause();
	}

	/**
	 * This method get the Game Jframe of the GameEngine
	 * 
	 * @return game the Game Jframe of the GameEngine
	 */

	public static Game getGame() {
		return game;
	}

	static int getStage() {
		return stage;
	}

	public static void setStage(int stage) {
		GameEngine.stage = stage;
	}

	static ArrayList<Country> getNextCountryTurn() {
		return nextCountryTurn;
	}

	public static void setNextCountryTurn(ArrayList<Country> nextCountryTurn) {
		GameEngine.nextCountryTurn = nextCountryTurn;
	}
}

