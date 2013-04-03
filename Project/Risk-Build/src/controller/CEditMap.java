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

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;

import org.risk.model.Item;
import org.risk.model.Link;
import org.risk.model.Map;
import org.risk.model.MapVisualization;
import org.risk.model.State;
import org.risk.view.EditMap;
import org.risk.view.Game;

/**
 * The instance of this class represents the controller Edit Map class
 * 
 * @author Arij
 * 
 */
public class CEditMap {

	private EditMap editMap;
	private Map map;
	private Game game;
	ArrayList<State> states = new ArrayList<State>();
	private MapVisualization mv = new MapVisualization();

	/**
	 * Constructor
	 * 
	 * @param editMap
	 *            : Instance of EditMap class.
	 * 
	 */
	public CEditMap(EditMap editMap) {
		this.editMap = editMap;
		this.map = Map.getInstance();
		map.addObserver(mv);
	}

	/**
	 * Add the action listener for class Edit Map.
	 * 
	 * @param lastgame
	 *            : Instance of Game class
	 */
	public void init(final Game lastgame) {
		editMap.addEditMapActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				// Getting the selected items in combo boxes
				Item state = (Item) editMap.cmbState.getSelectedItem();
				Item continent = (Item) editMap.cmbContinent.getSelectedItem();
				Item country = (Item) editMap.cmbCountry.getSelectedItem();

				int[] selectedIndex = editMap.linkList.getSelectedIndices();
				ArrayList<Integer> link = new ArrayList<Integer>();

				ArrayList<Item> selectedItemList = new ArrayList<Item>();
				for (int i = 0; i < selectedIndex.length; i++) {
					selectedItemList.add((Item) editMap.linkList.getModel()
							.getElementAt(selectedIndex[i]));
				}

				// First delete all neighbors of selected state
				ArrayList<Link> newLinkList = new ArrayList<Link>();
				newLinkList = (ArrayList<Link>) map.getMyLinkList().clone();

				for (Link myLink : map.getMyLinkList()) {
					if (myLink.getSourceState().getStateID() == state.getId()
							|| myLink.getDestintionState().getStateID() == state
									.getId())
						newLinkList.remove(newLinkList.indexOf(myLink));
				}

				// Now add new links to the list
				Link newLink;
				for (Item myItem : selectedItemList) {
					if (myItem.getId() != state.getId()) {
						newLink = new Link();
						newLink.setSourceState(getStateByStateID(state.getId()));
						newLink.setDestintionState(getStateByStateID(myItem
								.getId()));
						newLinkList.add(newLink);
					}
				}

				// Update the information of selected state
				states = map.getMyStateList();
				for (int i = 0; i < states.size(); i++) {
					if (state.getId() == states.get(i).getStateID()) {
						if (continent != null)
							states.get(i).setContinentID(continent.getId());
						if (country != null)
							states.get(i).setCountryID(country.getId());
						break;
					}
				}

				// Update the states and links in the map object
				map.setMyStateList(states);
				map.setMyLinkList(newLinkList);
				editMap.setVisible(false);
				Map.setInstance(map);

				lastgame.updateView(mv);
				lastgame.frame.revalidate();
				lastgame.frame.repaint();
			}
		});

		/**
		 * This method is used to add Action Listener
		 */
		editMap.addCancelActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				editMap.setVisible(false);
			}
		});
	}

	/**
	 * This method gets the StateID and returns the state from Map object
	 * 
	 * @param stateID
	 *            ID of desired state
	 * @return The state which has mentioned StateID
	 */
	private State getStateByStateID(int stateID) {
		State requestedState = new State();
		for (State myState : map.getMyStateList()) {
			if (myState.getStateID() == stateID) {
				requestedState = myState;
				break;
			}
		}
		return requestedState;
	}
}
