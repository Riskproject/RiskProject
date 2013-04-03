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

import org.risk.view.MapVerifier;
import org.risk.view.StartGame;

/**
 * The instance of this class would represent the controller for New Map
 * Verifier Class
 * 
 * @author Arij
 * 
 */

public class CMapVerifier {

	private StartGame startGame;
	private MapVerifier mapVerifier;

	/**
	 * Constructor
	 * 
	 * @param mapVerifier
	 *            Instance of Map verifier class
	 */
	public CMapVerifier(MapVerifier mapVerifier) {
		this.mapVerifier = mapVerifier;
	}

	/**
	 * This method is used to add action to a Action Listener
	 */
	public void init() {

		/**
		 * This method is used to add action to a Action Listener
		 */
		mapVerifier.addMainMenuActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				mapVerifier.setVisible(false);
			}
		});
	}
}
