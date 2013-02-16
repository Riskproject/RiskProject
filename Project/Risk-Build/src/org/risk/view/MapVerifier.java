package org.risk.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import controller.CMapVerifier;

/**
 * An instance of this class represents the view in which the user will be able
 * to see whether or not a selected map meets several conditions
 * 
 * @author Kian
 * 
 */
public class MapVerifier extends JFrame {

	private JPanel contentPane;
	// public JTextArea textArea;
	private JLabel lblResult;
	private JButton btnMainMenu;
	private CMapVerifier cMapVerifier;

	/**
	 * Create the frame.
	 */
	public MapVerifier(String result) {

		this.cMapVerifier = new CMapVerifier(this);
		setResizable(true);
		setTitle("Map Verifier");
		setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 577, 364);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblConditions = new JLabel(
				"<html>Conditions:<br>\r\n1. Does the map contain any isolated state?<br>\r\n2. Do all of the countries have exactly one capital?<br>\r\n3. Does the map have more than one countries?<br>");
		lblConditions.setVerticalAlignment(SwingConstants.TOP);
		lblConditions.setBounds(10, 11, 328, 68);
		contentPane.add(lblConditions);

		lblResult = new JLabel();
		lblResult.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblResult.setBounds(10, 90, 541, 200);

		lblResult.setText(result);
		contentPane.add(lblResult);

		btnMainMenu = new JButton("Main Menu");
		btnMainMenu.setBounds(236, 291, 118, 23);
		contentPane.add(btnMainMenu);
		this.cMapVerifier.init();
	}

	/**
	 * This method is used to add Action Listener
	 * 
	 * @param a
	 *            Action Listener
	 */
	public void addMainMenuActionListener(ActionListener a) {
		btnMainMenu.addActionListener(a);
	}
}
