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

package org.risk.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;
import javax.swing.JTextArea;

import org.risk.model.Map;
import java.awt.Color;

/**
 * This class has the GUI of the log window
 * 
 * 
 * 
 */
public class LogWindow extends JFrame {

	private JPanel contentPane;
	private static JTextArea textArea;
	private JScrollPane scroll;
	private static LogWindow instance = null;

	/**
	 * Create the frame.
	 */
	private LogWindow() {
		setTitle("Log");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setAlwaysOnTop(true);
		setBounds(800, 100, 450, 394);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textArea = new JTextArea();

		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);

		textArea.setEditable(false);
		scroll = new JScrollPane(textArea);
		scroll.setBounds(0, 0, 444, 272);
		contentPane.add(scroll);
		DefaultCaret caret = (DefaultCaret) textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		contentPane.setLayout(new GridLayout(1, 1));

	}

	/**
	 * This method implements Singleton for LogWindow
	 * 
	 * @return : Instance of LogWindow Class
	 */
	public static LogWindow getInstance() {
		if (instance == null) {
			instance = new LogWindow();
		}
		return instance;
	}

	/**
	 * Append content to Log Window
	 * 
	 * @param msg
	 *            : Log message
	 */
	public static void appendToTextArea(String msg) {
		if (msg == null)
			msg = "";
		textArea.append(msg);
		textArea.append("\n");
		textArea.setCaretPosition(textArea.getText().length());

	}
}
