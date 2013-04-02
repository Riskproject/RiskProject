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

package org.risk.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.color.CMMException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import org.risk.model.Continent;
import org.risk.model.Country;
import org.risk.model.Item;
import org.risk.model.Link;
import org.risk.model.Map;
import org.risk.model.State;

import controller.CEditMap;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * The instance of this class represent Map editor where user can edit an
 * existing map
 * 
 * 
 * 
 */
public class EditMap extends JFrame {

	private JPanel contentPane;
	private CEditMap cEditGame;
	private Map map;
	ArrayList<State> states = new ArrayList<State>();
	ArrayList<Country> countries = new ArrayList<Country>();
	ArrayList<Continent> continents = new ArrayList<Continent>();
	ArrayList<Link> links = new ArrayList<Link>();
	JButton btnCancel;
	public JButton btnEditMap;
	public JList linkList;
	JLabel lblNeighbours;
	public JComboBox cmbContinent;
	JLabel lblContinent;
	public JComboBox cmbCountry;
	JLabel lblCountry;
	public JComboBox cmbState;
	JLabel lblState;
	JScrollPane jsp;

	/**
	 * Create the frame.
	 * 
	 * @param game 	Instnace
	 *            of Game class
	 */
	public EditMap(final Game game) {
		this.cEditGame = new CEditMap(this);
		this.map = Map.getInstance();
		extractMapData();

		setTitle("Edit Map");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblState = new JLabel("State");
		lblState.setBounds(78, 14, 46, 14);
		contentPane.add(lblState);

		cmbState = new JComboBox(fillCmbState());
		cmbState.setBounds(161, 11, 135, 20);
		contentPane.add(cmbState);

		lblCountry = new JLabel("Country");
		lblCountry.setBounds(78, 45, 46, 14);
		contentPane.add(lblCountry);

		cmbCountry = new JComboBox(fillCmbCountry());
		cmbCountry.setBounds(161, 42, 135, 20);
		contentPane.add(cmbCountry);

		lblContinent = new JLabel("Continent");
		lblContinent.setBounds(78, 76, 61, 14);
		contentPane.add(lblContinent);

		cmbContinent = new JComboBox(fillCmbContinent());
		cmbContinent.setBounds(161, 73, 135, 20);
		contentPane.add(cmbContinent);

		lblNeighbours = new JLabel("Neighbors");
		lblNeighbours.setBounds(78, 105, 61, 14);
		contentPane.add(lblNeighbours);

		linkList = new JList(fillListLinks());
		jsp = new JScrollPane(linkList);
		jsp.setBounds(161, 104, 135, 116);
		contentPane.add(jsp);

		btnEditMap = new JButton("Apply Changes");
		btnEditMap.setBounds(102, 228, 120, 23);
		contentPane.add(btnEditMap);

		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(234, 228, 120, 23);
		contentPane.add(btnCancel);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				cmbContinent.setSelectedIndex(-1);
				cmbCountry.setSelectedIndex(-1);
				cmbContinent.setEnabled(false);
				cmbCountry.setEnabled(false);
				linkList.setEnabled(false);
				btnEditMap.setEnabled(false);
			}
		});

		cmbState.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cbs = (JComboBox) e.getSource();

				int stateID = ((Item) cbs.getItemAt(cbs.getSelectedIndex()))
						.getId();

				if (stateID >= 1) {
					// Enable elements of the window
					cmbContinent.setEnabled(true);
					cmbCountry.setEnabled(true);
					linkList.setEnabled(true);
					btnEditMap.setEnabled(true);

					// Selecting continent of the state in its combo box
					cmbContinent
							.setSelectedIndex(getIndexOfContinentByState(states
									.get((cbs.getSelectedIndex() - 1))
									.getContinentID()));

					// Selecting country of the state in its combo box
					cmbCountry.setSelectedIndex(getIndexOfCountryByState(states
							.get((cbs.getSelectedIndex() - 1)).getCountryID()));

					// A list to store neighbors of selected state
					ArrayList<State> neighborsList = new ArrayList<State>();

					// Find neighbors of the selected state and store them
					for (Link myLink : links) {
						if (myLink.getSourceState().getStateID() == stateID)
							neighborsList.add(myLink.getDestintionState());
						else if (myLink.getDestintionState().getStateID() == stateID)
							neighborsList.add(myLink.getSourceState());
					}

					// find the index of neighbors in linkList and select them
					List<Integer> selectedIndexList = new ArrayList<Integer>();
					for (State state : neighborsList) {
						for (int i = 0; i < linkList.getModel().getSize(); i++) {
							if (((Item) linkList.getModel().getElementAt(i))
									.getId() == state.getStateID()) {
								selectedIndexList.add(i);
								break;
							}
						}
					}
					linkList.setSelectedIndices(convertIntegers(selectedIndexList));
				} else {
					int[] emptyList = { -1 };
					// Disable elements of the window
					cmbContinent.setSelectedIndex(-1);
					cmbCountry.setSelectedIndex(-1);
					linkList.setSelectedIndices(emptyList);
					cmbContinent.setEnabled(false);
					cmbCountry.setEnabled(false);
					linkList.setEnabled(false);
					btnEditMap.setEnabled(false);
				}
			}
		});
		this.cEditGame.init(game);
	}

	/**
	 * This method converts an array list of integers to array of integers
	 * 
	 * @param integers
	 *            ArrayList of integers
	 * @return Array of integers
	 */
	public static int[] convertIntegers(List<Integer> integers) {
		int[] returnValue = new int[integers.size()];
		for (int i = 0; i < returnValue.length; i++) {
			returnValue[i] = integers.get(i).intValue();
		}
		return returnValue;
	}

	/**
	 * This method is used to return Index of continent
	 * 
	 * @param continentId
	 *            : Id of continent
	 * @return Return of Index of Continent
	 */
	private int getIndexOfContinentByState(int continentId) {
		int indexToBeReturned = 0;
		for (int i = 0; i < cmbContinent.getItemCount(); i++) {
			Item item = (Item) cmbContinent.getItemAt(i);
			if (continentId == item.getId()) {
				indexToBeReturned = i;
				break;
			}
		}
		return indexToBeReturned;
	}

	/**
	 * this method is used to return index of country
	 * 
	 * @param countryId
	 *            : Id of Country
	 * @return : Index of Country
	 */
	private int getIndexOfCountryByState(int countryId) {
		int indexToBeReturned = 0;
		for (int i = 0; i < cmbCountry.getItemCount(); i++) {
			Item item = (Item) cmbCountry.getItemAt(i);
			if (countryId == item.getId()) {
				indexToBeReturned = i;
				break;
			}
		}
		return indexToBeReturned;
	}

	/**
	 * this method is used to return index of Link
	 * 
	 * @param linkId
	 *            : Id of Link
	 * @return : Returns the Index of link
	 */
	private int getIndexOfLinkByState(int linkId) {
		int indexToBeReturned = 0;
		for (int i = 0; i < linkList.getModel().getSize(); i++) {
			Item item = (Item) linkList.getModel().getElementAt(i);
			if (linkId == item.getId()) {
				indexToBeReturned = i;
				break;
			}
		}
		return indexToBeReturned;
	}

	/**
	 * This method is used to extract properties from map object
	 */
	private void extractMapData() {
		this.states = map.getMyStateList();
		this.countries = map.getMyCountryList();
		this.continents = map.getMyContinentList();
		this.links = map.getMyLinkList();
	}

	/**
	 * This method is used to fill state combo box
	 * 
	 * @return data of State Combo box
	 */
	private Vector fillCmbState() {
		Vector model = new Vector();
		model.addElement(new Item(-1, "Please select an item"));
		for (int i = 0; i < states.size(); i++) {
			model.addElement(new Item(states.get(i).getStateID(), states.get(i)
					.getStateName()));
		}
		return model;
	}

	/**
	 * This method is used to fill Country's combo box
	 * 
	 * @return data of Country Combo box
	 */
	private Vector fillCmbCountry() {
		Vector model = new Vector();
		for (int i = 0; i < countries.size(); i++) {
			model.addElement(new Item(countries.get(i).getCountryID(),
					countries.get(i).getCountryName()));
		}
		return model;
	}

	/**
	 * This method is used to fill Continent's combo box
	 * 
	 * @return data of country Combo box
	 */
	private Vector fillCmbContinent() {
		Vector model = new Vector();
		for (int i = 0; i < continents.size(); i++) {
			model.addElement(new Item(continents.get(i).getContinentID(),
					continents.get(i).getContinentName()));
		}
		return model;
	}

	/**
	 * This method is used to fill list of links
	 * 
	 * @return data of link combox box.
	 */
	private Vector fillListLinks() {
		Vector model = new Vector();
		for (int i = 0; i < states.size(); i++) {
			model.addElement(new Item(states.get(i).getStateID(), states.get(i)
					.getStateName()));
		}
		return model;
	}

	/**
	 * This method is used to add Action Listener
	 * 
	 * @param a
	 *            : Action Listener
	 */
	public void addEditMapActionListener(ActionListener a) {
		btnEditMap.addActionListener(a);
	}

	/**
	 * This method is used to add Action Listener
	 * 
	 * @param a
	 *            : Action Listener
	 */
	public void addCancelActionListener(ActionListener a) {
		btnCancel.addActionListener(a);
	}
}
