package org.risk.core;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.UIManager;
import org.risk.view.StartGame;

import org.risk.model.*;
/**
 * The instance of this class is used to run the application. It has main method in it.
 * @author Omer
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


