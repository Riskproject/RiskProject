package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.risk.view.MapVerifier;
import org.risk.view.StartGame;

/**
 * The instance of this class would represent the controller for New Map
 * Verifier Class
 * 
 * @author Arij
 * 
 */

public class CMapVerifier {

	private StartGame startGame;
	private MapVerifier mapVerifier;

	/**
	 * Constructor
	 * 
	 * @param mapVerifier
	 *            Instance of Map verifier class
	 */
	public CMapVerifier(MapVerifier mapVerifier) {
		this.mapVerifier = mapVerifier;
	}

	/**
	 * This method is used to add action to a Action Listener
	 */
	public void init() {

		/**
		 * This method is used to add action to a Action Listener
		 */
		mapVerifier.addMainMenuActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				mapVerifier.setVisible(false);
			}
		});
	}
}
