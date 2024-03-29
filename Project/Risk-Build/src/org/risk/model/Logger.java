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
