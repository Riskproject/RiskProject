package org.risk.model;

import java.io.File;

import javax.xml.bind.JAXBException;

import org.risk.model.Utility.XmlHelper;
import org.risk.view.Game;

/**
 * An instance of this class gets the path from the controller and converts the
 * map object to XML by calling related methods.
 * 
 * @author Kian
 * 
 */
public class MapSaver {
	private Map currentMap;

	/**
	 * Default constructor
	 */
	public MapSaver() {
		currentMap = Map.getInstance();
		
	}

	
	/**
	 * This method is use to save the map object
	 * @param filePath			is the url for the xml file
	 * @param currentMap	is the current map in the memory
	 */
	public Boolean saveMap(String filePath, Map currentMap) {
		Boolean fileSaved=false;
		try {
			
			if (!currentMap.equals(null)) {
				XmlHelper.convertToXml(currentMap, filePath);
				fileSaved=true;
			} else {
				// TODO show a pop-up error message
				System.out.println("This map is empty and cannot be saved");
				fileSaved=false;
			}
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileSaved;
	}
}
