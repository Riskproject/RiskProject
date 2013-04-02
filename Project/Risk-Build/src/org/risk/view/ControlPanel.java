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
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JRadioButton;

import org.risk.model.Constants;
import org.risk.model.Country;
import org.risk.model.Item;
import org.risk.model.Map;
import org.risk.model.Resource;
import org.risk.model.State;
import org.risk.model.Technology;

import controller.CControlPanel;

/**
 * This class contains the GUI of controlling the resources, technology and
 * Simulation Flow.
 * 
 * 
 * 
 */
public class ControlPanel extends JFrame {

	private JPanel contentPane;
	private CControlPanel controlPanel;
	private JLabel lblState;
	private JLabel lblCountryResource;
	public JComboBox cmbState;
	public JComboBox cmbCountryResources;
	private JLabel lblResource;
	public JComboBox cmbResource;
	private JLabel lblCountry;
	public JComboBox cmbCountry;
	private JLabel lblTechnology;
	public JComboBox cmbTechnology;
	public JButton btnChangeResource;
	public JButton btnChangeTechnology;
	public JRadioButton rdbtnPauseAfterEachFullTurn;
	public JRadioButton rdbtnPauseAfterCountryTurn;
	public JRadioButton rdbtnPauseAfterEachPhase;
	public JRadioButton rdbtnSmoothlyRunSimulation;
	public JButton btnChangeGameInterval;
	private ButtonGroup group;
	private Map map;
	ArrayList<State> states = new ArrayList<State>();
	ArrayList<Country> countries = new ArrayList<Country>();
	ArrayList<Resource> resources = new ArrayList<Resource>();
	ArrayList<Technology> technologies = new ArrayList<Technology>();
	private Resource resourceObj;
	private Technology technologyObj;
	public JButton btnCancel;

	/**
	 * Create the frame.
	 * 
	 * @param game  Instance
	 *            of Game class.
	 */
	public ControlPanel(final Game game) {
		this.controlPanel = new CControlPanel(this);
		this.map = Map.getInstance();// check getInstance
		resourceObj = new Resource();
		technologyObj = new Technology();

		extractMapData();

		setTitle("Control Panel");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblState = new JLabel("State");
		lblState.setBounds(10, 11, 46, 14);
		contentPane.add(lblState);
		//new
		lblCountryResource = new JLabel("Country");
		lblCountryResource.setBounds(10, 46, 46, 14);
		contentPane.add(lblCountryResource);
		//
		//new
		cmbCountryResources = new JComboBox(fillCmbCountry());
		cmbCountryResources.setBounds(66, 43, 103, 20);
		contentPane.add(cmbCountryResources);
		//
		
		
		cmbState = new JComboBox(fillCmbState());
		cmbState.setBounds(66, 8, 103, 20);
		contentPane.add(cmbState);

		lblResource = new JLabel("Resource");
		lblResource.setBounds(10, 77, 46, 14);
		contentPane.add(lblResource);
		
		cmbResource = new JComboBox(fillCmbResource());
		cmbResource.setBounds(66, 77, 103, 20);
		contentPane.add(cmbResource);

		lblCountry = new JLabel("Country");
		lblCountry.setBounds(235, 11, 46, 14);
		contentPane.add(lblCountry);

		cmbCountry = new JComboBox(fillCmbCountry());
		cmbCountry.setBounds(305, 8, 103, 20);
		contentPane.add(cmbCountry);

		lblTechnology = new JLabel("Technology");
		lblTechnology.setBounds(235, 46, 70, 14);
		contentPane.add(lblTechnology);

		cmbTechnology = new JComboBox(fillCmbTechnology());
		cmbTechnology.setBounds(305, 43, 103, 20);
		contentPane.add(cmbTechnology);

		btnChangeResource = new JButton("Change Resource");
		btnChangeResource.setBounds(10, 107, 159, 23);
		contentPane.add(btnChangeResource);

		btnChangeTechnology = new JButton("Change Technology");
		btnChangeTechnology.setBounds(235, 107, 173, 23);
		contentPane.add(btnChangeTechnology);

		rdbtnPauseAfterEachFullTurn = new JRadioButton(
				"Pause after each full turn");
		rdbtnPauseAfterEachFullTurn.setBounds(10, 131, 192, 23);
		contentPane.add(rdbtnPauseAfterEachFullTurn);

		rdbtnPauseAfterCountryTurn = new JRadioButton(
				"Pause after each country turn");
		rdbtnPauseAfterCountryTurn.setBounds(219, 131, 219, 23);
		contentPane.add(rdbtnPauseAfterCountryTurn);

		rdbtnPauseAfterEachPhase = new JRadioButton("Pause after each phase");
		rdbtnPauseAfterEachPhase.setBounds(10, 167, 192, 23);
		contentPane.add(rdbtnPauseAfterEachPhase);

		rdbtnSmoothlyRunSimulation = new JRadioButton("Smoothly run simulation");
		rdbtnSmoothlyRunSimulation.setBounds(219, 167, 219, 23);
		contentPane.add(rdbtnSmoothlyRunSimulation);

		group = new ButtonGroup();
		group.add(rdbtnPauseAfterCountryTurn);
		group.add(rdbtnPauseAfterEachFullTurn);
		group.add(rdbtnPauseAfterEachPhase);
		group.add(rdbtnSmoothlyRunSimulation);

		switch (CControlPanel.getSimulationFlow()) {
		case Constants.SIMULATION_SMOOTH:
			rdbtnSmoothlyRunSimulation.setSelected(true);
			break;
		case Constants.SIMULATION_PAUSE_AFTER_ALL_COUNTRIES_TURN:
			rdbtnPauseAfterEachFullTurn.setSelected(true);
			break;
		case Constants.SIMULATION_PAUSE_AFTER_EACH_COUNTRY_TURN:
			rdbtnPauseAfterCountryTurn.setSelected(true);
			break;
		case Constants.SIMULATION_PAUSE_AFTER_EACH_PHASE:
			rdbtnPauseAfterEachPhase.setSelected(true);
			break;
		}

		btnChangeGameInterval = new JButton("Change game interval");
		btnChangeGameInterval.setBounds(118, 197, 163, 23);
		contentPane.add(btnChangeGameInterval);

		btnCancel = new JButton("Hide");
		btnCancel.setBounds(156, 231, 89, 23);
		contentPane.add(btnCancel);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				cmbState.setSelectedIndex(-1);
				cmbCountry.setSelectedIndex(-1);
				cmbResource.setEnabled(false);
				cmbTechnology.setEnabled(false);
				btnChangeResource.setEnabled(false);
				btnChangeTechnology.setEnabled(false);
			}
		});

		this.controlPanel.init(game);
	}

	/**
	 * ActionListener of "Change Resource" button
	 * 
	 * @param a
	 *            ActionListener
	 */
	public void addChangeResourceActionListener(ActionListener a) {
		btnChangeResource.addActionListener(a);
	}

	/**
	 * ActionListener of "Change technology" button
	 * 
	 * @param a
	 *            ActionListener
	 */
	public void addChangeTechnologyActionListener(ActionListener a) {
		btnChangeTechnology.addActionListener(a);
	}

	/**
	 * ActionListener of "Change Game Interval" button
	 * 
	 * @param a
	 *            ActionListener
	 */
	public void addChangeGameIntervalActionListener(ActionListener a) {
		btnChangeGameInterval.addActionListener(a);
	}

	/**
	 * ActionListener of "Change State" combo box
	 * 
	 * @param a
	 *            ActionListener
	 */
	public void addChangeStateActionListener(ActionListener a) {
		cmbState.addActionListener(a);
	}
	
	/**
	 * ActionListener of "Change Country" (Change Country Resource) combo box
	 * @param a		ActionListener
	 */
	public void addChangeCountryResourceActionListener(ActionListener a) {
		cmbCountryResources.addActionListener(a);
	}

	/**
	 * ActionListener of "Change Country" combo box
	 * 
	 * @param a
	 *            ActionListener
	 */
	public void addChangeCountryActionListener(ActionListener a) {
		cmbCountry.addActionListener(a);
	}

	/**
	 * ActionListener of "Change Country" combo box
	 * 
	 * @param a
	 *            ActionListener
	 */
	public void addCancelActionListener(ActionListener a) {
		btnCancel.addActionListener(a);
	}

	/**
	 * This method is used to extract properties from map object
	 */
	private void extractMapData() {
		this.states = map.getMyStateList();
		this.countries = map.getMyCountryList();
		this.resources = resourceObj.getResourceList();
		this.technologies = technologyObj.getTechnologyList();
	}

	/**
	 * This method is used to fill state combo box
	 * 
	 * @return List of States
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
	 * @return List of Countries
	 */
	private Vector fillCmbCountry() {
		Vector model = new Vector();
		model.addElement(new Item(-1, "Please select an item"));
		for (int i = 0; i < countries.size(); i++) {
			model.addElement(new Item(countries.get(i).getCountryID(),
					countries.get(i).getCountryName()));
		}
		return model;
	}

	/**
	 * This method is used to fill Technology combo box
	 * 
	 * @return List of Technologies
	 */
	private Vector fillCmbTechnology() {
		Vector model = new Vector();
		for (int i = 0; i < technologies.size(); i++) {
			model.addElement(new Item(technologies.get(i).technologyLevelNo(),
					technologies.get(i).technologyLevel()));
		}
		return model;
	}

	/**
	 * This method is used to fill Resources combo box
	 * 
	 * @return List of Resources
	 */
	private Vector fillCmbResource() {
		Vector model = new Vector();
		for (int i = 0; i < resources.size(); i++) {
			model.addElement(new Item(resources.get(i).resourceLevel(),
					resources.get(i).resourceName()));
		}
		return model;
	}
}
