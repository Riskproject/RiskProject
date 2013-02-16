package org.risk.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.risk.view.LogWindow;

/**
 *  The Class is used to maintain the log of the game.
 * @author Arij
 * 
 */
public class Logger {
	/**
	 * The method is used to update the log.
	 * @param message
	 *            : Message to be logged
	 */
	public static void logMessage(String message) {
		LogWindow logWindow = LogWindow.getInstance();
		LogWindow.appendToTextArea(message);
	}
}
