package org.risk.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JInternalFrame;
import javax.swing.JList;

import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import controller.CNewGame;

import java.awt.List;
import java.awt.Button;
import java.util.Vector;

/**
 * The instance of this class represents New game form
 * 
 * @author Arij
 * 
 */
public class NewGame extends JFrame {

	private JPanel contentPane;
	public JButton btnAddContinents;
	public JInternalFrame frmContinent;
	private CNewGame cNewGame;
	public JTextField txtContinent;
	public JTextField txtState;
	public JTextField txtCountry;
	public JComboBox cmbCapital;
	private JLabel lblCapital;
	public JButton btnFinishCountry;
	public JButton btnAddMoreCountry;
	private JLabel lblCountry;
	public JInternalFrame frmCountry;
	public JButton btnFinishContinent;
	public JButton btnAddMoreContinent;
	private JLabel lblcontinent;
	public JButton btnFinishState;
	public JButton btnAddMoreState;
	public JComboBox cmbContinent;
	private JLabel lblContinent;
	private JLabel lblState;
	public JInternalFrame frmState;
	public JButton btnAddCountries;
	public JButton btnAddStates;
	public JButton btnGenerateMap;
	public JButton btnAddLinks;
	public JInternalFrame frmLinks;
	private JLabel lblState_1;
	public JComboBox cmbStateLink;
	private JLabel lblNeighbours;
	private Button btnAddMoreLinks;
	private Button btnFinishLinks;
	private JLabel lblCountry_1;
	public JComboBox cmbCountryState;
	public JList listLink;
	public Vector model;
	private JButton btnMainMenu;

	/**
	 * Create the frame.
	 */
	public NewGame() {

		setTitle("New Game");

		this.cNewGame = new CNewGame(this);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 421, 346);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		frmState = new JInternalFrame("Add States");
		frmState.setBounds(85, 11, 234, 211);
		contentPane.add(frmState);
		frmState.getContentPane().setLayout(null);
		frmState.setVisible(false);

		lblState = new JLabel("State");
		lblState.setBounds(10, 11, 65, 14);
		frmState.getContentPane().add(lblState);

		txtState = new JTextField();
		txtState.setBounds(95, 8, 103, 20);
		frmState.getContentPane().add(txtState);
		txtState.setColumns(10);

		lblContinent = new JLabel("Continent");
		lblContinent.setBounds(10, 43, 65, 14);
		frmState.getContentPane().add(lblContinent);

		cmbContinent = new JComboBox();
		cmbContinent.setBounds(95, 40, 103, 20);
		frmState.getContentPane().add(cmbContinent);

		btnAddMoreState = new JButton("Add more");
		btnAddMoreState.setBounds(10, 148, 89, 23);
		frmState.getContentPane().add(btnAddMoreState);

		btnFinishState = new JButton("Finish");
		btnFinishState.setBounds(109, 148, 89, 23);
		frmState.getContentPane().add(btnFinishState);

		lblCapital = new JLabel("Capital");
		lblCapital.setBounds(10, 76, 46, 14);
		frmState.getContentPane().add(lblCapital);

		cmbCapital = new JComboBox();
		cmbCapital.setBounds(95, 73, 103, 20);
		frmState.getContentPane().add(cmbCapital);
		cmbCapital.insertItemAt("Yes", 0);
		cmbCapital.insertItemAt("No", 1);
		cmbCapital.setSelectedIndex(1);

		lblCountry_1 = new JLabel("Country");
		lblCountry_1.setBounds(10, 107, 46, 14);
		frmState.getContentPane().add(lblCountry_1);

		cmbCountryState = new JComboBox();
		cmbCountryState.setBounds(95, 104, 103, 20);
		frmState.getContentPane().add(cmbCountryState);

		btnAddCountries = new JButton("Add Countries");
		btnAddCountries.setBounds(135, 45, 126, 23);
		getContentPane().add(btnAddCountries);

		btnAddStates = new JButton("Add States");
		btnAddStates.setBounds(135, 79, 126, 23);
		getContentPane().add(btnAddStates);

		btnAddLinks = new JButton("Add Links");
		btnAddLinks.setBounds(135, 107, 127, 23);
		getContentPane().add(btnAddLinks);

		btnAddContinents = new JButton("Add Continents");
		btnAddContinents.setBounds(135, 11, 126, 23);
		contentPane.add(btnAddContinents);

		frmContinent = new JInternalFrame("Add Continents");
		frmContinent.setBounds(85, 22, 224, 147);
		contentPane.add(frmContinent);
		frmContinent.getContentPane().setLayout(null);
		frmContinent.setVisible(false);

		lblcontinent = new JLabel("Continent");
		lblcontinent.setBounds(22, 26, 57, 14);
		frmContinent.getContentPane().add(lblcontinent);

		txtContinent = new JTextField();
		txtContinent.setBounds(99, 23, 86, 20);
		frmContinent.getContentPane().add(txtContinent);
		txtContinent.setColumns(10);

		btnAddMoreContinent = new JButton("Add more");
		btnAddMoreContinent.setBounds(10, 84, 89, 23);
		frmContinent.getContentPane().add(btnAddMoreContinent);

		btnFinishContinent = new JButton("Finish");
		btnFinishContinent.setBounds(109, 84, 89, 23);
		frmContinent.getContentPane().add(btnFinishContinent);

		btnGenerateMap = new JButton("Generate Map");
		btnGenerateMap.setBounds(122, 222, 152, 23);
		contentPane.add(btnGenerateMap);

		btnAddMoreCountry = new JButton("Add more");
		btnAddMoreCountry.setBounds(10, 54, 89, 23);

		btnFinishCountry = new JButton("Finish");
		btnFinishCountry.setBounds(99, 54, 89, 23);

		frmCountry = new JInternalFrame("Add Country");
		frmCountry.setBounds(85, 11, 224, 147);
		contentPane.add(frmCountry);
		frmCountry.getContentPane().setLayout(null);
		frmCountry.setVisible(false);

		lblCountry = new JLabel("Country");
		lblCountry.setBounds(10, 11, 46, 14);
		frmCountry.getContentPane().add(lblCountry);

		txtCountry = new JTextField();
		txtCountry.setBounds(92, 8, 86, 20);
		frmCountry.getContentPane().add(txtCountry);
		txtCountry.setColumns(10);
		frmCountry.getContentPane().add(btnAddMoreCountry);
		frmCountry.getContentPane().add(btnFinishCountry);

		frmLinks = new JInternalFrame("Add Links");
		frmLinks.setBounds(95, 22, 207, 192);
		contentPane.add(frmLinks);
		frmLinks.getContentPane().setLayout(null);

		lblState_1 = new JLabel("State");
		lblState_1.setBounds(10, 11, 46, 14);
		frmLinks.getContentPane().add(lblState_1);

		cmbStateLink = new JComboBox();
		cmbStateLink.setBounds(68, 8, 97, 20);
		frmLinks.getContentPane().add(cmbStateLink);

		lblNeighbours = new JLabel("Neighbours");
		lblNeighbours.setBounds(10, 36, 64, 14);
		frmLinks.getContentPane().add(lblNeighbours);

		// listLink = new JList();
		// listLink.setBounds(65, 34, 110, 82);
		// frmLinks.getContentPane().add(listLink);

		btnAddMoreLinks = new Button("Assign");
		btnAddMoreLinks.setBounds(4, 131, 70, 22);
		frmLinks.getContentPane().add(btnAddMoreLinks);

		btnFinishLinks = new Button("Finish");
		btnFinishLinks.setBounds(95, 131, 70, 22);
		frmLinks.getContentPane().add(btnFinishLinks);

		btnMainMenu = new JButton("Main Menu");
		btnMainMenu.setBounds(122, 256, 152, 23);
		contentPane.add(btnMainMenu);

		this.cNewGame.init();
	}

	/**
	 * This method is used to add Action Listener
	 * 
	 * @param a
	 *            Action Listener
	 */
	public void addContinentActionListener(ActionListener a) {
		btnAddContinents.addActionListener(a);
	}

	/**
	 * This method is used to add Action Listener
	 * 
	 * @param a
	 *            Action Listener
	 */
	public void addStateActionListener(ActionListener a) {
		btnAddStates.addActionListener(a);
	}

	/**
	 * This method is used to add Action Listener
	 * 
	 * @param a
	 *            Action Listener
	 */
	public void addCountryActionListener(ActionListener a) {
		btnAddCountries.addActionListener(a);
	}

	/**
	 * This method is used to add Action Listener
	 * 
	 * @param a
	 *            Action Listener
	 */
	public void addMoreContinentActionListener(ActionListener a) {
		btnAddMoreContinent.addActionListener(a);
	}

	/**
	 * This method is used to add Action Listener
	 * 
	 * @param a
	 *            Action Listener
	 */
	public void addFinishContinentActionListener(ActionListener a) {
		btnFinishContinent.addActionListener(a);
	}

	/**
	 * This method is used to add Action Listener
	 * 
	 * @param a
	 *            Action Listener
	 */
	public void addMoreStateActionListener(ActionListener a) {
		btnAddMoreState.addActionListener(a);
	}

	/**
	 * This method is used to add Action Listener
	 * 
	 * @param a
	 *            Action Listener
	 */
	public void addFinishStateActionListener(ActionListener a) {
		btnFinishState.addActionListener(a);
	}

	/**
	 * This method is used to add Action Listener
	 * 
	 * @param a
	 *            Action Listener
	 */
	public void addMoreCountryActionListener(ActionListener a) {
		btnAddMoreCountry.addActionListener(a);
	}

	/**
	 * This method is used to add Action Listener
	 * 
	 * @param a
	 *            Action Listener
	 */
	public void addFinishCountryActionListener(ActionListener a) {
		btnFinishCountry.addActionListener(a);
	}

	/**
	 * This method is used to add Action Listener
	 * 
	 * @param a
	 *            Action Listener
	 */
	public void addGenerateMapActionListener(ActionListener a) {
		btnGenerateMap.addActionListener(a);
	}

	/**
	 * This method is used to add Action Listener
	 * 
	 * @param a
	 *            Action Listener
	 */
	public void addAddLinksActionListener(ActionListener a) {
		btnAddLinks.addActionListener(a);
	}

	/**
	 * This method is used to add Action Listener
	 * 
	 * @param a
	 *            Action Listener
	 */
	public void addMoreLinksActionListener(ActionListener a) {
		btnAddMoreLinks.addActionListener(a);
	}

	/**
	 * This method is used to add Action Listener
	 * 
	 * @param a
	 *            Action Listener
	 */
	public void addFinishLinksActionListener(ActionListener a) {
		btnFinishLinks.addActionListener(a);
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
