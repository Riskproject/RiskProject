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

package org.risk.core;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.UIManager;
import org.risk.view.StartGame;

import org.risk.model.*;
/**
 * The instance of this class is used to run the application. It has main method in it.
 *
 *
 */
public class Risk {

	private static StartGame startGame;
	/** Initializes the Risk Game
	 * 
	 * @param args
	 * 
	 */
	public static void main(String[] args) {
				
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				      startGame = new StartGame();
				      startGame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	
	}
	
}


