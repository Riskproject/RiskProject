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

package org.risk.view;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import controller.CStartGame;

import java.awt.event.ActionListener;

/**
 * An instance of this class represents the initial view of the project in which
 * user can choose between creating a map, loading a map, or other available
 * options
 * 
 * @author Arij
 * 
 */
public class StartGame extends JFrame {

	private JPanel contentPane;
	private JButton btnLoadGame;
	private JButton btnNewGame;
	private JButton btnVerifyMap;
	public JFileChooser mapChooser;
	private CStartGame cStartGame;

	/**
	 * Constructor. Creates the frame.
	 */
	public StartGame() {

		setTitle("Risk");

		this.cStartGame = new CStartGame(this);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnLoadGame = new JButton("Load an Existing Map");
		btnLoadGame.setBounds(118, 117, 167, 23);
		contentPane.add(btnLoadGame);

		btnNewGame = new JButton("Create a New Map");
		btnNewGame.setBounds(118, 83, 167, 23);
		contentPane.add(btnNewGame);

		btnVerifyMap = new JButton("Verify an Existing Map");
		btnVerifyMap.setBounds(118, 151, 167, 23);
		contentPane.add(btnVerifyMap);

		this.cStartGame.init();
	}

	/**
	 * ActionListener of "Load Game" button
	 * 
	 * @param a
	 *            ActionListener
	 */
	public void addLoadActionListener(ActionListener a) {
		btnLoadGame.addActionListener(a);
	}

	/**
	 * ActionListener of "New Game" button
	 * 
	 * @param a
	 *            ActionListener
	 */
	public void addNewActionListener(ActionListener a) {
		btnNewGame.addActionListener(a);
	}

	/**
	 * ActionListener of "Verify Map" button
	 * 
	 * @param a
	 *            ActionListener
	 */
	public void addVerifyActionListener(ActionListener a) {
		btnVerifyMap.addActionListener(a);
	}
}