package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Console;
import java.util.ArrayList;

import javax.swing.JComboBox;

import org.risk.model.Constants;
import org.risk.model.Country;
import org.risk.model.Item;
import org.risk.model.Map;
import org.risk.model.MapVisualization;
import org.risk.model.Resource;
import org.risk.model.State;
import org.risk.model.Technology;
import org.risk.view.ControlPanel;
import org.risk.view.Game;

/**
 * This class helps provide explicit controls of technology, resources and
 * simulation control.
 * 
 * @author Arij
 * 
 */
public class CControlPanel {

	private ControlPanel controlPanel;
	private boolean countryCmbBox;
	private boolean stateCmbBox;
	private Map map;
	private ArrayList<State> states;
	private ArrayList<Country> countries;
	private State stateObj;
	private Country countryObj;
	private static int simulationFlow = Constants.SIMULATION_PAUSE_AFTER_EACH_COUNTRY_TURN;
	private MapVisualization mv = new MapVisualization();

	/**
	 * Constructor
	 * 
	 * @param controlPanel
	 *            : Instance of ControlPanel class.
	 */
	public CControlPanel(ControlPanel controlPanel) {
		this.controlPanel = controlPanel;
		map = Map.getInstance();
		states = map.getMyStateList();
		countries = map.getMyCountryList();
		stateObj = new State();
		countryObj = new Country();
		countryCmbBox = false;
		stateCmbBox = false;
		map.addObserver(mv);
	}

	/**
	 * This method sets the simulation flow
	 * 
	 * @param simulationFlow
	 *            : Simulation flow number.
	 */
	public void setSimulationFlow(int simulationFlow) {
		CControlPanel.simulationFlow = simulationFlow;
	}

	/**
	 * This method returns the Simulation flow.
	 * 
	 * @return : Simulation Flow type
	 */
	public static int getSimulationFlow() {
		return CControlPanel.simulationFlow;
	}

	/**
	 * This method initializes all the listener of the class ControlPanel.
	 * 
	 * @param lastgame
	 *            : Instance of the Game class.
	 */
	public void init(final Game lastgame) {

		/**
		 * This method is used to add Action Listener
		 */
		controlPanel.addChangeStateActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				try {
					JComboBox cbs = (JComboBox) evt.getSource();
					int selectedIndex = cbs.getSelectedIndex();
					if (selectedIndex > -1) {
						int stateId = ((Item) cbs.getItemAt(cbs
								.getSelectedIndex())).getId();
						controlPanel.btnChangeResource.setEnabled(true);
						controlPanel.cmbResource.setEnabled(true);

						controlPanel.cmbResource
								.setSelectedIndex(getIndexOfResourceByResourceLevel(stateObj
										.getResourceLevelByStateId(stateId,
												states)));
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		});
		/**
		 * This method is used to add Action Listener
		 */
		controlPanel.addChangeCountryActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				try {
					JComboBox cbs = (JComboBox) evt.getSource();
					int selectedIndex = cbs.getSelectedIndex();
					if (selectedIndex > -1) {
						int countryId = ((Item) cbs.getItemAt(cbs
								.getSelectedIndex())).getId();
						controlPanel.btnChangeTechnology.setEnabled(true);
						controlPanel.cmbTechnology.setEnabled(true);

						controlPanel.cmbTechnology
								.setSelectedIndex(getIndexOfTechnologyByTechnologyLevel(countryObj
										.getTechnologyLevelByCountryId(
												countryId, countries)));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		// 8

		/**
		 * This method is used to add Action Listener
		 */
		controlPanel
				.addChangeCountryResourceActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent evt) {
						try {
							JComboBox cbs = (JComboBox) evt.getSource();
							int selectedIndex = cbs.getSelectedIndex();
							if (selectedIndex > -1) {
								int countryId = ((Item) cbs.getItemAt(cbs
										.getSelectedIndex())).getId();
								controlPanel.btnChangeResource.setEnabled(true);
								controlPanel.cmbResource.setEnabled(true);
								countryCmbBox = true;

								controlPanel.cmbTechnology
										.setSelectedIndex(getIndexOfTechnologyByTechnologyLevel(countryObj
												.getTechnologyLevelByCountryId(
														countryId, countries)));
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});



		/**
		 * This method is used to add Action Listener
		 */
		controlPanel.addChangeResourceActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {

				try {
					State tempState = new State();
					ArrayList<Resource> tempResourceList = new ArrayList<Resource>();
					ArrayList<State> tempStateList = new ArrayList<State>();
					if (countryCmbBox) {
						Item itemCountry = (Item) controlPanel.cmbCountryResources
								.getSelectedItem();
						Item itemCountryResource = (Item) controlPanel.cmbResource
								.getSelectedItem();
						int countryID = itemCountry.getId();
						tempStateList = tempState.getStatesByCountryId(
								countryID, states);
						int resourceLevel = itemCountryResource.getId();
						Resource tempResource = new Resource();
						tempResourceList = tempResource.getResourceList();
						
						switch (resourceLevel) {

						case 0: {
							for (int stateListCounter = 0; stateListCounter < tempStateList.size(); stateListCounter++) {
								int index = tempState.getStateIndexByStateId(tempStateList.get(stateListCounter).getStateID(), states);
								states.get(index).setResource(tempResourceList.get(0));							
							}
							break;
						}
						case 1: {
							int index = -1;
							for (int stateListCounter = 0; stateListCounter < tempStateList
									.size(); stateListCounter++) {
								index = tempState.getStateIndexByStateId(
										tempStateList.get(stateListCounter)
												.getStateID(), states);
								states.get(index).setResource(
										tempResourceList.get(1));
							}
							break;
						}
						case 2: {
							int index = -1;
							tempResourceList.removeAll(tempResourceList);
							tempResourceList.add(tempResource.metalResource());
							tempResourceList.add(tempResource.energyResource());
							for (int stateListCounter = 0; stateListCounter < tempStateList
									.size();) {
								for (int resourceListCounter = 0; resourceListCounter < tempResourceList
										.size(); resourceListCounter++) {
									index = tempState.getStateIndexByStateId(
											tempStateList.get(stateListCounter)
													.getStateID(), states);
									states.get(index).setResource(
											tempResourceList
													.get(resourceListCounter));
									stateListCounter++;
								}
							}
							break;
						}
						case 3: {
							int index = -1;
							tempResourceList.removeAll(tempResourceList);
							tempResourceList.add(tempResource.metalResource());
							tempResourceList.add(tempResource.energyResource());
							tempResourceList.add(tempResource
									.knowledgeResource());
							for (int stateListCounter = 0; stateListCounter < tempStateList
									.size();) {
								for (int resourceListCounter = 0; resourceListCounter < tempResourceList
										.size(); resourceListCounter++) {
									if (stateListCounter >= tempStateList
											.size()) {
										break;
									}
									index = tempState.getStateIndexByStateId(
											tempStateList.get(stateListCounter)
													.getStateID(), states);
									states.get(index).setResource(
											tempResourceList
													.get(resourceListCounter));
									stateListCounter++;
								}
							}
							break;
						}

					}
						controlPanel.dispose();
						map.setMyStateList(states);
						lastgame.updateView(mv);
						lastgame.frame.revalidate();
						lastgame.frame.repaint();

					} else {
						Item itemState = (Item) controlPanel.cmbState
								.getSelectedItem();
						Item itemResource = (Item) controlPanel.cmbResource
								.getSelectedItem();
						int stateId = itemState.getId();
						int resourceLevel = itemResource.getId();
						map = Map.getInstance();
						states = map.getMyStateList();
						State state = new State();
						state = state.getStateByStateId(stateId, states);
						Resource newResource = new Resource();
						newResource = newResource
								.getResourceByResourceLevel(resourceLevel);
						state.setResource(newResource);
						int stateIndex = state.getStateIndexByStateId(stateId,
								states);
						states.get(stateIndex).setResource(newResource);
						controlPanel.dispose();
						map.setMyStateList(states);
						lastgame.updateView(mv);
						lastgame.frame.revalidate();
						lastgame.frame.repaint();
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		/**
		 * This method is used to add Action Listener
		 */
		controlPanel.addChangeTechnologyActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				try {
					Item itemCountry = (Item) controlPanel.cmbCountry
							.getSelectedItem();
					Item itemTechnology = (Item) controlPanel.cmbTechnology
							.getSelectedItem();
					int countryID = itemCountry.getId();
					int technologyLevel = itemTechnology.getId();
					map = Map.getInstance();
					countries = map.getMyCountryList();
					Country country = new Country();
					country = map.getCountryByID(countryID);
					Technology newTechnology = new Technology();
					newTechnology = newTechnology
							.getTechnologyByTechnologyLevel(technologyLevel);
					country.setTechnology(newTechnology);
					int countryIndex = country.getCountryIndexByCountryID(
							countryID, countries);
					countries.get(countryIndex).setTechnology(newTechnology);
					// lastgame.frame.dispose();
					controlPanel.dispose();
					map.setMyCountryList(countries);
					lastgame.updateView(mv);
					lastgame.frame.revalidate();
					lastgame.frame.repaint();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		/**
		 * This method is used to add Action Listener
		 */
		controlPanel.addChangeGameIntervalActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				if (controlPanel.rdbtnSmoothlyRunSimulation.isSelected()) {
					setSimulationFlow(Constants.SIMULATION_SMOOTH);
					controlPanel.rdbtnSmoothlyRunSimulation.isSelected();
				} else if (controlPanel.rdbtnPauseAfterCountryTurn.isSelected()) {
					setSimulationFlow(Constants.SIMULATION_PAUSE_AFTER_EACH_COUNTRY_TURN);
					controlPanel.rdbtnPauseAfterCountryTurn.isSelected();
				} else if (controlPanel.rdbtnPauseAfterEachFullTurn
						.isSelected()) {
					setSimulationFlow(Constants.SIMULATION_PAUSE_AFTER_ALL_COUNTRIES_TURN);
					controlPanel.rdbtnPauseAfterEachFullTurn.isSelected();
				} else if (controlPanel.rdbtnPauseAfterEachPhase.isSelected()) {
					setSimulationFlow(Constants.SIMULATION_PAUSE_AFTER_EACH_PHASE);
					controlPanel.rdbtnPauseAfterEachPhase.isSelected();
				}
			}
		});
		/**
		 * This method is used to add Action Listener
		 */
		controlPanel.addCancelActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				controlPanel.setVisible(false);
			}
		});

	}

	/**
	 * This method is used to return Index of resource level
	 * 
	 * @param resourceLevel
	 *            : Resource Level of the selected state
	 * @return : Index of resource to be selected
	 */
	private int getIndexOfResourceByResourceLevel(int resourceLevel) {
		int indexToBeReturned = 0;
		for (int i = 0; i < controlPanel.cmbResource.getItemCount(); i++) {
			Item item = (Item) controlPanel.cmbResource.getItemAt(i);
			if (resourceLevel == item.getId()) {
				indexToBeReturned = i;
				break;
			}
		}
		return indexToBeReturned;
	}

	/**
	 * This method is used to return Index of technology level
	 * 
	 * @param technologyLevel
	 *            : Technology Level of the selected state
	 * @return : Index of technology to be selected
	 */
	private int getIndexOfTechnologyByTechnologyLevel(int technologyLevel) {
		int indexToBeReturned = 0;
		for (int i = 0; i < controlPanel.cmbTechnology.getItemCount(); i++) {
			Item item = (Item) controlPanel.cmbTechnology.getItemAt(i);
			if (technologyLevel == item.getId()) {
				indexToBeReturned = i;
				break;
			}
		}
		return indexToBeReturned;
	}
}
