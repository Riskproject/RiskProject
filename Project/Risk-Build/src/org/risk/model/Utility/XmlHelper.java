/*
* Copyright (C) 2013 author Arij,Omer
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

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
