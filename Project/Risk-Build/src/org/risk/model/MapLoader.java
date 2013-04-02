package org.risk.model;

import java.io.File;

import javax.xml.bind.JAXBException;

import org.risk.model.Utility.XmlHelper;
import org.risk.view.MapVerifier;

/**
 * An instance of this class gets the XML from the controller and converts it to
 * Map object by calling related methods.
 * 
 * @author Kian
 * 
 */
public class MapLoader {
	private Map loadedMap;
	private MapVisualization mapVisualization;

	/**
	 * Default constructor
	 */
	public MapLoader() {
		loadedMap = Map.getInstance("My Map");
		mapVisualization = new MapVisualization();
	}

	/**
	 * This method gets the file and sends it to XML converter and receives a
	 * Map object from XML converter If the returned Map object is not null,
	 * then the method calls an appropriate method to convert it to a graph
	 * 
	 * @param file
	 *            Uploaded file by the user
	 * @param validateRequired
	 *            This parameter indicates whether the map should be drawn or
	 *            validated
	 */
	public void createMap(File file, Boolean validateRequired) {
		try {
			// TODO send this loaded map to model
			loadedMap = XmlHelper.convertFromXml(file);
			Logger.logMessage("*********************************************************************");
			Logger.logMessage("Game Startup:");
			Logger.logMessage("*********************************************************************");
			Logger.logMessage("Loading from file " + "'"+file.getName()+"'");
			Logger.logMessage("File properly Loaded");

			if (!loadedMap.equals(null)) {
				if (validateRequired) {

					String result = null;
					String returnResult = null;

					result = loadedMap.validateMap();

					if (result.equals(null) || result.equals(""))
						returnResult = "This map is valid, based on the defined condtions";
					else {
						returnResult = "<html>This map is not valid due to following results:<br>";
						returnResult += result;
						returnResult += "</html>";
					}

					MapVerifier mapVerifier = new MapVerifier(returnResult);
				} else {
					Map.setInstance(loadedMap);
					mapVisualization.extractMap(loadedMap);
				}
			} else {
				// TODO show a pop-up error message
				System.out.println("The selected file is not a Map file");
			}
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method gets the file and sends it to XML converter and receives a
	 * Map object from XML converter
	 * 
	 * @param file
	 *            : XML File
	 * @return : Map object
	 */
	public Map getLoadedMapObject(File file) {
		try {
			loadedMap = XmlHelper.convertFromXml(file);
			Logger.logMessage("*********************************************************************");
			Logger.logMessage("Game Startup:");
			Logger.logMessage("*********************************************************************");
			Logger.logMessage("Loading from file " + "'"+file.getName()+"'");
			Logger.logMessage("File properly Loaded");
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return loadedMap;
		}
	}
}
