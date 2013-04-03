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

import java.awt.event.*;
import java.io.File;

import javax.activation.FileDataSource;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.risk.view.NewCountries;
import org.risk.view.NewGame;
import org.risk.view.StartGame;
import org.risk.model.MapLoader;

/**
 * Controller for StartGame view An instance of this class can checks the data
 * provided to it by view and performs an appropriate action
 * 
 * @author Arij
 */
public class CStartGame {

	private StartGame startGame;
	private NewGame newGame;
	private MapLoader mapLoader;
	private NewCountries newCountries;

	/**
	 * Constructor
	 * 
	 * @param startGame		Instance of class Start Game.
	 */
	public CStartGame(StartGame startGame) {
		this.startGame = startGame;
		mapLoader = new MapLoader();
	}

	/**
	 * Initializes the view (StartGame) and sets the visibility of different
	 * objects
	 */
	public void init() {
		startGame.setVisible(true);

		/**
		 * ActionListener for "New Map" button in StartGame class Shows correct
		 * views to the user
		 */
		startGame.addNewActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				newGame = new NewGame();
				startGame.setVisible(false);
				newGame.setVisible(true);
			}
		});

		/**
		 * ActionListener for "Load Map" button in StartGame class Shows a file
		 * chooser to the user and receives the selected file and sends it to
		 * the model
		 */
		startGame.addLoadActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"XML Files", "xml");

				startGame.mapChooser = new JFileChooser();
				startGame.mapChooser
						.setFileSelectionMode(JFileChooser.FILES_ONLY);
				startGame.mapChooser.setFileFilter(filter);

				int returnVal = startGame.mapChooser.showOpenDialog(startGame);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = startGame.mapChooser.getSelectedFile();
					if (file.getPath().toUpperCase().endsWith(".XML")) {
						// mapLoader.createMap(file, false);
						newCountries = new NewCountries();
						newCountries.setLoadedFile(file);
						startGame.setVisible(false);
						newCountries.setVisible(true);
					} else
						JOptionPane.showMessageDialog(startGame,
								"File format is not XML");
				}
			}
		});
		/**
		 * This method is used to add action to a Action Listener
		 */
		startGame.addVerifyActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"XML Files", "xml");

				startGame.mapChooser = new JFileChooser();
				startGame.mapChooser
						.setFileSelectionMode(JFileChooser.FILES_ONLY);
				startGame.mapChooser.setFileFilter(filter);

				int returnVal = startGame.mapChooser.showOpenDialog(startGame);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = startGame.mapChooser.getSelectedFile();
					if (file.getPath().toUpperCase().endsWith(".XML"))
						mapLoader.createMap(file, true);
					else
						JOptionPane.showMessageDialog(startGame,
								"File format is not XML");
				}
			}
		});

	}
}
