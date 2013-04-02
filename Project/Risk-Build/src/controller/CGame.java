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
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.risk.model.Constants;
import org.risk.model.GameEngine;
import org.risk.model.Logger;
import org.risk.model.Map;
import org.risk.model.MapSaver;
import org.risk.view.ControlPanel;
import org.risk.view.EditMap;
import org.risk.view.Game;
import org.risk.view.LogWindow;
import org.risk.view.NewGame;
import org.risk.view.StartGame;

/**
 * The instance of this class would represent the controller for Game Class
 * 
 * @author Arij
 * 
 */
public class CGame {

	private Game game;
	private EditMap editMap;
	private MapSaver mapSaver;
	private Map map;
	private StartGame startGame;
	private NewGame newGame;
	private ControlPanel controlPanel;
	private GameEngine gameEngine;

	/**
	 * Constructor
	 * 
	 * @param game
	 *            : Instance of Game class
	 * @param map
	 *            : Instance of Map class
	 */
	public CGame(Game game, Map map) {
		this.game = game;
		mapSaver = new MapSaver();
		this.map = map;
	}

	/**
	 * This method is used to add action to a Action Listener
	 */
	public void init() {

		/**
		 * This method is used to add action to a Action Listener
		 */
		game.addEditMapActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				// game.frame.setVisible(false);
				// game.frame.dispose();
				editMap = new EditMap(game);
				editMap.setVisible(true);
			}
		});
		/**
		 * This method is used to add action to a Action Listener
		 */
		game.addMainMenuActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				StartGame sg = new StartGame();
				game.frame.dispose();
			}
		});
		/**
		 * This method is used to add action to a Action Listener
		 */
		game.addValidateMapActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				String validateMapResult = map.validateMap();
				if (validateMapResult != "") {
					JOptionPane.showMessageDialog(game,
							validateMapResult.replace("<br>", "\n"));
				} else {
					JOptionPane.showMessageDialog(game, "It's a valid map");
				}
			}
		});
		/**
		 * This method is used to add action to a Action Listener
		 */
		game.addSaveAsActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				game.folderChooser = new JFileChooser();
				game.folderChooser
						.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				int returnVal = game.folderChooser.showSaveDialog(game);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					if (game.folderChooser.getSelectedFile() != null) {
						File file = game.folderChooser.getSelectedFile();

						mapSaver.saveMap(file.getPath(), Map.getInstance());
					}
				}
			}
		});
		/**
		 * This method is used to add action to a Action Listener
		 */
		game.addControlPanelActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				controlPanel = new ControlPanel(game);
				controlPanel.setVisible(true);
			}
		});
		/**
		 * This method is used to add action to a Action Listener
		 */
		game.addStartGameActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				try {
					String validateMapResult = map.validateMap();
					if (validateMapResult != "") {
						JOptionPane.showMessageDialog(
								game,
								"Risk cannot start : "
										+ validateMapResult.replace("<br>",
												"\n"));
					} else {
						JOptionPane.showMessageDialog(game,
								"It's a valid map, you can play RISK!");
						game.btnPlayGame.setEnabled(true);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		/**
		 * This method is used to add action to a Action Listener
		 */
		game.addPlayGameActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				if (evt.getActionCommand().equals("Play")) {
					try {
						gameEngine = new GameEngine();
						gameEngine.startSimulation(CControlPanel
								.getSimulationFlow());
						if (CControlPanel.getSimulationFlow() == Constants.SIMULATION_SMOOTH)
							game.btnPlayGame.setText("Pause");
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				} else {
					gameEngine.pause();
					game.btnPlayGame.setText("Play");
				}
			}
		});
		/**
		 * This method is used to add action to a Action Listener
		 */
		game.addShowGameActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				LogWindow logWindow = LogWindow.getInstance();
				logWindow.setVisible(true);
			}
		});
	}
}
