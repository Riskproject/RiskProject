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

package controller;

import org.risk.model.*;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import org.risk.model.State;
import org.risk.view.NewGame;
import org.risk.view.StartGame;

/**
 * The instance of this class would represent the controller for New Game Class
 * 
 * @author Arij
 * 
 */
public class CNewGame {

	private NewGame newGame;
	private StartGame startGame;
	private ArrayList<Continent> continents = new ArrayList<Continent>();
	private ArrayList<State> states = new ArrayList<State>();
	private ArrayList<Country> countries = new ArrayList<Country>();
	private ArrayList<Link> links = new ArrayList<Link>();

	/**
	 * Constructor
	 * 
	 * @param newGame
	 *            Instance of class New Game.
	 */
	public CNewGame(NewGame newGame) {
		this.newGame = newGame;
	}

	/**
	 * This method is used to return list of continents
	 * 
	 * @return Arraylist of Continent
	 */
	public ArrayList<Continent> Continents() {
		return this.continents;
	}

	/**
	 * This method is used to set continents
	 * 
	 * @param continents
	 *            : Arraylist of Continents
	 */
	public void setContinents(ArrayList<Continent> continents) {
		this.continents = continents;
	}

	/**
	 * This method is used to return countries list
	 * 
	 * @return Arraylist of countries
	 */
	public ArrayList<Country> Countries() {
		return this.countries;
	}

	/**
	 * This method is used to set countries
	 * 
	 * @param countries
	 *            : Arraylist of countries
	 */
	public void setCountries(ArrayList<Country> countries) {
		this.countries = countries;
	}

	/**
	 * This method is used to return list of states
	 * 
	 * @return Arraylist of States
	 */
	public ArrayList<State> States() {
		return this.states;
	}

	/**
	 * This method is used to set the states
	 * 
	 * @param states
	 *            Arraylist of States
	 */
	public void setStates(ArrayList<State> states) {
		this.states = states;
	}

	/**
	 * This method is use to initialize the action listener
	 */
	public void init() {
		newGame.setVisible(true);
		/**
		 * This method is used to add action to a Action Listener
		 */
		newGame.addContinentActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				hideButtons();
				newGame.frmContinent.setVisible(true);
			}
		});
		/**
		 * This method is used to add action to a Action Listener
		 */
		newGame.addStateActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				newGame.frmState.setVisible(true);
				hideButtons();
				if (continents.size() > 0) {
					newGame.cmbContinent.removeAllItems();
					for (int i = 0; i < continents.size(); i++) {
						newGame.cmbContinent.insertItemAt(continents.get(i)
								.getContinentName(), i);
					}
					newGame.cmbContinent.setSelectedIndex(0);
				}
				if (countries.size() > 0) {
					newGame.cmbCountryState.removeAllItems();
					for (int i = 0; i < countries.size(); i++) {
						newGame.cmbCountryState.insertItemAt(countries.get(i)
								.getCountryName(), i);
					}
					newGame.cmbCountryState.setSelectedIndex(0);
				}
			}
		});
		/**
		 * This method is used to add action to a Action Listener
		 */
		newGame.addCountryActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				hideButtons();
				newGame.frmCountry.setVisible(true);
			}
		});
		/**
		 * This method is used to add action to a Action Listener
		 */
		newGame.addMoreContinentActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				if (continents.size() >= 7) {
					JOptionPane.showMessageDialog(newGame,
							"Not more than seven continents can be added");
				} else {
					setContinentData();
				}
			}
		});
		/**
		 * This method is used to add action to a Action Listener
		 */
		newGame.addFinishContinentActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				setContinentData();
				newGame.frmContinent.setVisible(false);
				showButtons();
			}
		});
		/**
		 * This method is used to add action to a Action Listener
		 */
		newGame.addMoreStateActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				setStateData();
			}
		});
		/**
		 * This method is used to add action to a Action Listener
		 */
		newGame.addFinishStateActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				setStateData();
				newGame.frmState.setVisible(false);
				showButtons();
			}
		});
		/**
		 * This method is used to add action to a Action Listener
		 */
		newGame.addMoreCountryActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				if (countries.size() >= 4) {
					JOptionPane.showMessageDialog(newGame,
							"Not more than four countries can be added");
				} else {
					setCountryData();
				}
			}
		});
		/**
		 * This method is used to add action to a Action Listener
		 */
		newGame.addFinishCountryActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				setCountryData();
				newGame.frmCountry.setVisible(false);
				showButtons();
			}
		});
		/**
		 * This method is used to add action to a Action Listener
		 */
		newGame.addAddLinksActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				newGame.frmLinks.setVisible(true);
				hideButtons();
				Vector model = new Vector();
				newGame.listLink = new JList(model);

				JScrollPane jScroll = new JScrollPane(newGame.listLink);
				jScroll.setBounds(65, 34, 110, 82);
				newGame.frmLinks.getContentPane().add(jScroll);
				if (states.size() > 0) {
					newGame.cmbStateLink.removeAllItems();
					for (int i = 0; i < states.size(); i++) {
						newGame.cmbStateLink.insertItemAt(states.get(i)
								.getStateName(), i);
						model.addElement(new Item(states.get(i).getStateID(),
								states.get(i).getStateName()));
					}
					newGame.cmbStateLink.setSelectedIndex(0);
					jScroll.setViewportView(newGame.listLink);
				}
			}
		});
		/**
		 * This method is used to add action to a Action Listener
		 */
		newGame.addMoreLinksActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				int sourceIdIndex = newGame.cmbStateLink.getSelectedIndex();
				int[] destinantionIdIndices = newGame.listLink
						.getSelectedIndices();
				ArrayList<Integer> stateLinks = new ArrayList<Integer>();
				int stateLinksIndex = 0;
				if (destinantionIdIndices.length > 0) {
					for (int i = 0; i < destinantionIdIndices.length; i++) {
						if (sourceIdIndex != destinantionIdIndices[i]) {
							Link link = new Link();
							int index = links.size();
							int linkId = index + 1;
							link.setSourceState(states.get(sourceIdIndex));
							link.setDestintionState(states
									.get(destinantionIdIndices[i]));
							links.add(index, link);
							stateLinks.add(stateLinksIndex, linkId);
							stateLinksIndex++;
						}
					}
				}
			}
		});
		/**
		 * This method is used to add action to a Action Listener
		 */
		newGame.addFinishLinksActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				newGame.frmLinks.setVisible(false);
				showButtons();
			}
		});
		/**
		 * This method is used to add action to a Action Listener
		 */
		newGame.addMainMenuActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				newGame.setVisible(false);
				startGame = new StartGame();
				startGame.setVisible(true);
			}
		});
		/**
		 * This method is used to add action to a Action Listener
		 */
		newGame.addGenerateMapActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				newGame.dispose();
				links = removeDuplicateLinks(links);
				GameEngine myGameEngine = new GameEngine();
				myGameEngine.startUpPhase(countries, states, continents, links);
			}
		});
	}

	/**
	 * This method is used to remove duplicate values from the list
	 * 
	 * @param link
	 *            : Arraylist of duplicated links
	 * @return : Arraylist of non duplicated links
	 */
	private ArrayList<Link> removeDuplicateLinks(ArrayList<Link> link) {
		for (int i = 0; i < link.size(); i++) {
			Link linkObj = link.get(i);
			for (int j = i + 1; j < link.size(); j++) {
				Link linkObjNext = link.get(j);
				if ((linkObj.getDestintionState() == linkObjNext
						.getSourceState())
						&& (linkObj.getSourceState() == linkObjNext
								.getDestintionState())) {
					int stateId = linkObjNext.getSourceState().getStateID();
					int indexOfState = stateId - 1;
					State linkToBeAddedToState = states.get(indexOfState);
					link.remove(j);
				}
			}
		}
		return link;
	}

	/**
	 * This method is used to set data for the continent
	 */
	private void setContinentData() {

		int index = 0;
		Continent continent = new Continent();
		if (!continents.isEmpty())
			index = continents.size();
		int id = index + 1; // for matching constants id of continent
		if (newGame.txtContinent.getText().trim().length() > 0) {
			continent.setContinentID(id);
			continent.setContinentName(newGame.txtContinent.getText());
			// continents.add(index, newGame.txtContinent.getText());
			continents.add(index, continent);
			setContinents(continents);
		}
		newGame.txtContinent.setText("");

	}

	/**
	 * This method is used to set data for the state
	 */
	private void setStateData() {
		if (continents.size() > 0 && countries.size() > 0) {
			int index = 0;
			State state = new State();
			if (!states.isEmpty())
				index = states.size();
			int id = index + 1;
			int continentId = newGame.cmbContinent.getSelectedIndex() + 1;
			int countryId = newGame.cmbCountryState.getSelectedIndex() + 1;
			if (newGame.txtState.getText().trim().length() > 0) {
				state.setStateID(id);
				state.setStateName(newGame.txtState.getText());
				state.setContinentID(continentId);
				state.setCountryID(countryId);
				if (newGame.cmbCapital.getSelectedItem() == "No") {
					state.setIsCapital(false);
				} else {
					state.setIsCapital(true);
				}

				states.add(index, state);
				setStates(states);
				newGame.cmbContinent.setSelectedIndex(0);
			}
			newGame.cmbCapital.setSelectedIndex(1);
		} else {
			JOptionPane.showMessageDialog(newGame,
					"Add Countries and Continents");
		}
		newGame.txtState.setText("");

	}

	/**
	 * This method is used to set data for the country
	 */
	private void setCountryData() {

		int index = 0;
		Country country = new Country();
		if (!countries.isEmpty())
			index = countries.size();
		int id = index + 1;// for matching constants id of country
		if (newGame.txtCountry.getText().trim().length() > 0) {
			country.setCountryID(id);
			country.setCountryName(newGame.txtCountry.getText());
			// countries.add(index, newGame.txtCountry.getText());
			countries.add(index, country);
			setCountries(countries);
		}
		newGame.txtCountry.setText("");
	}

	/**
	 * This method is used to hide all the buttons on the screen
	 */
	private void hideButtons() {
		newGame.btnAddCountries.setVisible(false);
		newGame.btnAddStates.setVisible(false);
		newGame.btnAddContinents.setVisible(false);
		newGame.btnAddLinks.setVisible(false);
	}

	/**
	 * This method is used to display the buttons on the screen
	 */
	private void showButtons() {
		newGame.btnAddCountries.setVisible(true);
		newGame.btnAddStates.setVisible(true);
		newGame.btnAddContinents.setVisible(true);
		newGame.btnAddLinks.setVisible(true);
	}
}