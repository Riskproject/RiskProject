package org.risk.model.Utility;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.risk.model.Map;

/**
 * This class provides basic function for marshalling and unmarshalling
 * 
 * @author Kian
 */
public class XmlHelper {

	/**
	 * Default constructor
	 */
	public XmlHelper() {

	}

	/**
	 * This method unmarshals the XML file and converts it to Map object
	 * 
	 * @param file		The file uploaded by the user
	 * @return 			An object of Map class
	 * @throws JAXBException
	 */
	public static Map convertFromXml(File file) throws JAXBException {
		Map returnMap = null;
		Object object = new Object();

		JAXBContext jaxbContext = JAXBContext.newInstance(Map.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		object = unmarshaller.unmarshal(file);

		if (object instanceof Map) {
			returnMap = (Map) object;
		}
		return returnMap;
	}

	/**
	 * This method marshals the Map object and stores it in a new file
	 * 
	 * @param map
	 *            The object of Map class that needs to be saved
	 * @param path
	 *            The path which will be used to store the serialized Map object
	 * @throws JAXBException
	 */
	public static void convertToXml(Map map, String path) throws JAXBException {
		if (!path.toUpperCase().endsWith(".XML"))
			path += ".xml";
		JAXBContext jaxbContext = JAXBContext.newInstance(Map.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.marshal(map, new File(path));
	}

}
