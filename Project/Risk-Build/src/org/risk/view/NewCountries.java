package org.risk.view;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;

import java.io.File;

import controller.CNewCountries;

/**
 * This class build the GUI for the new country after choosing to upload a map.
 * 
 * @author Arij
 * 
 */
public class NewCountries extends JFrame {

	private JPanel contentPane;
	public JInternalFrame frmCountry;
	public JRadioButton rdbtnAddNewCountries;
	public JRadioButton rdbtnContinueWithExistingCountries;
	public JRadioButton rdbtnContinueWithSameSimulation;
	private JLabel lblCountry;
	public JTextField txtCountry;
	public JButton btnFinishCountry;
	public JButton btnAddMoreCountry;
	public JButton btnNext;
	private CNewCountries cNewCountries;
	private File file;
	private JButton btnCancelCountry;
	private ButtonGroup group;

	/**
	 * Create the frame.
	 */
	public NewCountries() {
		setTitle("Countries");
		this.cNewCountries = new CNewCountries(this);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		rdbtnContinueWithExistingCountries = new JRadioButton(
				"Continue with existing countries and new simulation");
		rdbtnContinueWithExistingCountries.setBounds(80, 89, 313, 23);
		contentPane.add(rdbtnContinueWithExistingCountries);
		rdbtnContinueWithExistingCountries.setSelected(true);

		rdbtnAddNewCountries = new JRadioButton("Add new countries and new simulation");
		rdbtnAddNewCountries.setBounds(80, 115, 268, 23);
		contentPane.add(rdbtnAddNewCountries);
		
		rdbtnContinueWithSameSimulation = new JRadioButton("Continue with same simulation");
		rdbtnContinueWithSameSimulation.setBounds(80, 63, 284, 23);
		contentPane.add(rdbtnContinueWithSameSimulation);

		group = new ButtonGroup();
		group.add(rdbtnAddNewCountries);
		group.add(rdbtnContinueWithExistingCountries);
		group.add(rdbtnContinueWithSameSimulation);

		frmCountry = new JInternalFrame("Add new Countries");
		frmCountry.setBounds(50, 29, 330, 147);
		contentPane.add(frmCountry);
		frmCountry.getContentPane().setLayout(null);
		frmCountry.setVisible(false);

		lblCountry = new JLabel("Country");
		lblCountry.setBounds(53, 11, 46, 14);
		frmCountry.getContentPane().add(lblCountry);

		txtCountry = new JTextField();
		txtCountry.setBounds(119, 8, 137, 20);

		btnAddMoreCountry = new JButton("Add more");
		btnAddMoreCountry.setBounds(10, 54, 89, 23);

		btnFinishCountry = new JButton("Finish");
		btnFinishCountry.setBounds(116, 54, 89, 23);

		frmCountry.getContentPane().add(txtCountry);
		txtCountry.setColumns(10);
		frmCountry.getContentPane().add(btnAddMoreCountry);
		frmCountry.getContentPane().add(btnFinishCountry);

		btnCancelCountry = new JButton("Cancel");
		btnCancelCountry.setBounds(215, 54, 89, 23);
		frmCountry.getContentPane().add(btnCancelCountry);

		btnNext = new JButton("Next");
		btnNext.setBounds(151, 213, 89, 23);
		contentPane.add(btnNext);
		

		this.cNewCountries.init();
	}

	/**
	 * Sets the XML File of the Map
	 * 
	 * @param file
	 *            : XML file of the map
	 */
	public void setLoadedFile(File file) {
		this.file = file;
	}

	/**
	 * Gets the XML File of the Map
	 * 
	 * @return XML File of the map
	 */
	public File getLoadedFile() {
		return this.file;
	}

	/**
	 * This method is used to add Action Listener
	 * 
	 * @param a
	 *            Action Listener
	 */
	public void addNextActionListener(ActionListener a) {
		btnNext.addActionListener(a);
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
	public void addCancelCountryActionListener(ActionListener a) {
		btnCancelCountry.addActionListener(a);
	}
}
