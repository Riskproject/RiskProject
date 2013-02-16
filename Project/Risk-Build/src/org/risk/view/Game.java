package org.risk.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.risk.model.Constants;
import org.risk.model.Country;
import org.risk.model.Map;
import org.risk.model.MapVisualization;

import controller.CGame;

import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.CrossoverScalingControl;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ScalingControl;

/**
 * Displays the GUI for the Game
 * 
 * @author Arij
 * 
 */
public class Game extends JFrame {

	public final JFrame frame = new JFrame();
	Container content = frame.getContentPane();
	GraphZoomScrollPane panel;
	JPanel controls = new JPanel();
	JButton btnEditMap;
	JButton btnSaveAs;
	public JButton btnValidateMap;
	public JFileChooser folderChooser;
	private CGame cGame;
	private Map map;
	public JButton btnMainMenu;
	public JButton btnControlPanel;
	public JButton btnStartGame;
	public JButton btnPlayGame;
	public JButton btnShowLog;
	ModalGraphMouse gm;
	JComboBox combo;

	// public JFrame popupWindow;

	/**
	 * Create the frame to hold the graph.
	 */
	public Game(final MapVisualization myMapVisualizer, Map map) {

		this.cGame = new CGame(this, map);
		setMap(map);
		setZoom(myMapVisualizer);
		frame.pack();
		frame.setVisible(true);
		this.cGame.init();
	}

	/**
	 * Default Constructor
	 */
	public Game() {
		setTitle("Risk");
	}

	/**
	 * This method is used to return Map
	 * 
	 * @return Instance of Map
	 */
	private Map getMap() {
		return map;
	}

	/**
	 * This method is used to set Map object
	 * 
	 * @param map
	 *            Instance of Map
	 */
	private void setMap(Map map) {
		this.map = map;
	}

	/**
	 * This method is used to set the size for the map
	 * 
	 * @param myMapVisualizer
	 *            Instance of MapVisualization
	 */
	public void setZoom(final MapVisualization myMapVisualizer) {
		final VisualizationViewer<Integer, Number> vv = myMapVisualizer.vv;
		panel = new GraphZoomScrollPane(vv);
		content.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		gm = new DefaultModalGraphMouse<Integer, Number>();
		vv.setGraphMouse(gm);
		
		final ScalingControl scaler = new CrossoverScalingControl();

		JButton plus = new JButton("+");
		plus.addActionListener(new ActionListener() {
			/**
			 * This method is used to add Action Listener
			 */
			public void actionPerformed(ActionEvent e) {
				scaler.scale(vv, 1.1f, vv.getCenter());
			}
		});
		JButton minus = new JButton("-");
		minus.addActionListener(new ActionListener() {
			/**
			 * This method is used to add Action Listener
			 */
			public void actionPerformed(ActionEvent e) {
				scaler.scale(vv, 1 / 1.1f, vv.getCenter());
			}
		});

		btnEditMap = new JButton("Edit Map");
		btnSaveAs = new JButton("Save Map As");
		btnValidateMap = new JButton("Validate Map");
		btnMainMenu = new JButton("Main Menu");
		btnControlPanel = new JButton("Control Panel");
		btnStartGame = new JButton("Start Game");
		btnShowLog = new JButton("Show Log");
		btnPlayGame = new JButton("Play");

		btnPlayGame.setEnabled(false);

		setLegends(map.getMyCountryList());
		controls.add(btnEditMap);
		controls.add(btnSaveAs);
		controls.add(btnValidateMap);
		controls.add(plus);
		controls.add(minus);

		controls.add(btnControlPanel);
		controls.add(btnStartGame);
		controls.add(btnPlayGame);
		controls.add(btnShowLog);
		controls.add(btnMainMenu);
		content.add(controls, BorderLayout.SOUTH);
		
		combo = ((DefaultModalGraphMouse<Integer, Number>) gm)
				.getModeComboBox();
		controls.add(combo);
		gm.setMode(ModalGraphMouse.Mode.PICKING);
	}

	/**
	 * This method updates the view of Map with the changes in country and
	 * states.
	 * 
	 * @param myMapVisualizer
	 *            : Instance of Map MapVisualization
	 */
	public void updateView(final MapVisualization myMapVisualizer) {
		content.remove(panel);
		controls.remove(combo);
		final VisualizationViewer<Integer, Number> vv = myMapVisualizer.vv;
		panel = new GraphZoomScrollPane(vv);
		content.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		gm = new DefaultModalGraphMouse<Integer, Number>();
		vv.setGraphMouse(gm);
		combo = ((DefaultModalGraphMouse<Integer, Number>) gm)
				.getModeComboBox();
		controls.add(combo);
		gm.setMode(ModalGraphMouse.Mode.PICKING);

	}

	/**
	 * This method is used to add Action Listener
	 * 
	 * @param a
	 *            : Action Listener
	 */
	public void addEditMapActionListener(ActionListener a) {
		btnEditMap.addActionListener(a);
	}

	/**
	 * This method is used to add Action Listener
	 * 
	 * @param a
	 *            : Action Listener
	 */
	public void addSaveAsActionListener(ActionListener a) {
		btnSaveAs.addActionListener(a);
	}

	/**
	 * This method is used to add Action Listener
	 * 
	 * @param a
	 *            : Action Listener
	 */
	public void addValidateMapActionListener(ActionListener a) {
		btnValidateMap.addActionListener(a);
	}

	/**
	 * This method is used to add Action Listener
	 * 
	 * @param a
	 *            : Action Listener
	 */
	public void addMainMenuActionListener(ActionListener a) {
		btnMainMenu.addActionListener(a);
	}

	/**
	 * This method is used to add Action Listener
	 * 
	 * @param a
	 *            : Action Listener
	 */
	public void addControlPanelActionListener(ActionListener a) {
		btnControlPanel.addActionListener(a);
	}

	/**
	 * This method is used to add Action Listener
	 * 
	 * @param a
	 *            : Action Listener
	 */
	public void addStartGameActionListener(ActionListener a) {
		btnStartGame.addActionListener(a);
	}

	/**
	 * This method is used to add Action Listener
	 * 
	 * @param a
	 *            : Action Listener
	 */
	public void addPlayGameActionListener(ActionListener a) {
		btnPlayGame.addActionListener(a);
	}

	/**
	 * This method is used to add Action Listener
	 * 
	 * @param a
	 *            : Action Listener
	 */
	public void addShowGameActionListener(ActionListener a) {
		
		btnShowLog.addActionListener(a);
	}

	/**
	 * This method is used to set legend on the screen. this will help
	 * indentifying differnet players
	 * 
	 * @param country
	 *            : Arraylist of Countries
	 */
	private void setLegends(ArrayList<Country> country) {
		JLabel lblCountry1;
		JLabel lblCountry2;
		JLabel lblCountry3;
		JLabel lblCountry4;
		for (int i = 0; i < country.size(); i++) {
			switch (country.get(i).getCountryID()) {
			case Constants.COUNTRY1ID:
				lblCountry1 = new JLabel(country.get(i).getCountryName());
				lblCountry1.setForeground(Color.BLACK);
				lblCountry1.setBackground(Constants.COUNTRY1COLOR);
				lblCountry1.setOpaque(true);
				controls.add(lblCountry1);
				break;
			case Constants.COUNTRY2ID:
				lblCountry2 = new JLabel(country.get(i).getCountryName());
				lblCountry2.setForeground(Color.BLACK);
				lblCountry2.setBackground(Constants.COUNTRY2COLOR);
				lblCountry2.setOpaque(true);
				controls.add(lblCountry2);
				break;
			case Constants.COUNTRY3ID:
				lblCountry3 = new JLabel(country.get(i).getCountryName());
				lblCountry3.setForeground(Color.BLACK);
				lblCountry3.setBackground(Constants.COUNTRY3COLOR);
				lblCountry3.setOpaque(true);
				controls.add(lblCountry3);
				break;
			case Constants.COUNTRY4ID:
				lblCountry4 = new JLabel(country.get(i).getCountryName());
				lblCountry4.setForeground(Color.BLACK);
				lblCountry4.setBackground(Constants.COUNTRY4COLOR);
				lblCountry4.setOpaque(true);
				controls.add(lblCountry4);
				break;
			}
		}
	}
}
