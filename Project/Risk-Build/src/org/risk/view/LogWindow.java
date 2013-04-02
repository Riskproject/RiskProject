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
 * @author Arij
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
