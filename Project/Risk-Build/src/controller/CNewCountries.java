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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.risk.model.Country;
import org.risk.model.GameEngine;
import org.risk.model.Map;
import org.risk.model.MapLoader;
import org.risk.view.NewCountries;

/**
 * This class is responsible of adding new countries to existing map.
 * 
 * @author Arij
 * 
 */
public class CNewCountries {

	private NewCountries newCountries;
	private ArrayList<Country> countries = new ArrayList<Country>();
	private MapLoader mapLoader;
	private CStartGame cStartGame;

	/**
	 * Constructor
	 * 
	 * 
	 * @param newCountries
	 *            : Instance of class NewCountries.
	 */
	public CNewCountries(NewCountries newCountries) {
		this.newCountries = newCountries;
	}

	/**
	 * This method is used to return countries list
	 * 
	 * @return Array list of Countries.
	 */
	public ArrayList<Country> Countries() {
		return this.countries;
	}

	/**
	 * This method is used to set countries
	 * 
	 * @param countries
	 *            Arraylist of Countries.
	 */
	public void setCountries(ArrayList<Country> countries) {
		this.countries = countries;
	}

	/**
	 * This method is use to initialize the action listener
	 */
	public void init() {
		newCountries.setVisible(true);
		/**
		 * This method is used to add action to a Action Listener
		 */
		newCountries.addNextActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (newCountries.rdbtnContinueWithExistingCountries
						.isSelected()) {
					mapLoader = new MapLoader();
					Map map = mapLoader.getLoadedMapObject(newCountries
							.getLoadedFile());
					newCountries.frmCountry.setVisible(false);
					GameEngine gameEngine = new GameEngine();
					gameEngine.startUpPhase(map);
					newCountries.setVisible(false);
				} else if (newCountries.rdbtnAddNewCountries.isSelected()) {
					hideControls();
					newCountries.frmCountry.setVisible(true);
				}
				else if(newCountries.rdbtnContinueWithSameSimulation.isSelected()){
					mapLoader = new MapLoader();
					Map map = mapLoader.getLoadedMapObject(newCountries
							.getLoadedFile());
					newCountries.frmCountry.setVisible(false);
					GameEngine gameEngine = new GameEngine();
					gameEngine.startUpPhaseSameSimulation(map);
					gameEngine.getGame().btnPlayGame.setEnabled(true);
					newCountries.setVisible(false);
				}
			}
		});
		/**
		 * This method is used to add action to a Action Listener
		 */
		newCountries.addMoreCountryActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				if (countries.size() >= 4) {
					JOptionPane.showMessageDialog(newCountries,
							"Not more than four countries can be added");
				} else {
					setCountryData();
				}
			}
		});
		/**
		 * This method is used to add action to a Action Listener
		 */
		newCountries.addFinishCountryActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				try {
					setCountryData();
					if (countries.size() < 2) {
						JOptionPane.showMessageDialog(newCountries,
								"Add atleast two new countries");
					} else {
						mapLoader = new MapLoader();
						Map map = mapLoader.getLoadedMapObject(newCountries
								.getLoadedFile());
						newCountries.frmCountry.setVisible(false);
						newCountries.setVisible(false);
						GameEngine gameEngine = new GameEngine();
						gameEngine.startUpPhase(map, countries);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		/**
		 * This method is used to add action to a Action Listener
		 */
		newCountries.addCancelCountryActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				newCountries.frmCountry.setVisible(false);
				showControls();
			}
		});
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
		if (newCountries.txtCountry.getText().trim().length() > 0) {
			country.setCountryID(id);
			country.setCountryName(newCountries.txtCountry.getText());
			countries.add(index, country);
			setCountries(countries);
		}
		newCountries.txtCountry.setText("");
	}

	/**
	 * Hides controls to get Jframe visible.
	 */
	private void hideControls() {
		newCountries.rdbtnAddNewCountries.setVisible(false);
		newCountries.rdbtnContinueWithExistingCountries.setVisible(false);
		newCountries.rdbtnContinueWithSameSimulation.setVisible(false);
		newCountries.btnNext.setVisible(false);
	}

	/**
	 * Shows controls when JFrmae is closed.
	 */
	private void showControls() {
		newCountries.rdbtnAddNewCountries.setVisible(true);
		newCountries.rdbtnContinueWithExistingCountries.setVisible(true);
		newCountries.rdbtnContinueWithSameSimulation.setVisible(true);
		newCountries.btnNext.setVisible(true);
	}

}
