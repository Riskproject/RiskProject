/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.risk.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * The class hold the instance of the Resource.
 * 
 * @author Arij
 *
 */
@XmlType
@XmlAccessorType(XmlAccessType.FIELD) 
public class Resource {
	private String resourceName;
	private int resourceLevel;
	/**
	 * The method sets the resource name
	 * 
	 * @param resourceName : Name of the resource.
	 */
	private void setResourceName(String resourceName){
		this.resourceName = resourceName;
	}
	/**
	 * The method returns the name of the resource.
	 * 
	 * @return : Name of the resource
	 */
	public String resourceName(){
		return this.resourceName;
	}
	/**
	 * The method sets the resource level.
	 * 
	 * @param resourceLevel		is the resource level of the Resource object
	 */
	private void setResourceLevel(int resourceLevel){
		this.resourceLevel = resourceLevel;
	}
	/**
	 * The method returns the level of the resource.
	 * 
	 * @return : Level of the resource.
	 */
	public int resourceLevel(){
		return this.resourceLevel;
	}
	/**
	 * The method sets the concrete value for no resource.
	 * 
	 * @return : 		Returns the Resource object
	 */
	public Resource noResource(){
		setResourceLevel(0);
		setResourceName("None");
		
		return getResource();
	}
	/**
	 * The method sets the concrete value for metal resource.
	 * 
	 * @return : Returns the Resource object
	 */
	public Resource metalResource(){
		setResourceName("Metal");
		setResourceLevel(1);
		return getResource();
	}
	/**
	 * The method sets the concrete value for energy resource.
	 * 
	 * @return : Returns the Resource object
	 */
	public Resource energyResource(){
		setResourceName("Energy");
		setResourceLevel(2);
		return getResource();
	}
	/**
	 * The method sets the concrete value for knowledge resource.
	 * 
	 * @return : Returns the Resource object
	 */
	public Resource knowledgeResource(){
		setResourceName("Knowledge");
		setResourceLevel(3);
		return getResource();
	}
	/**
	 * This method returns the Object of Resource
	 * 
	 * @return:	 Returns the Resource object
	 */
	private Resource getResource(){
		Resource resource = new Resource();
		resource.setResourceLevel(this.resourceLevel());
		resource.setResourceName(this.resourceName());
		return resource;
	}
	/**
	 * This method returns the list of Resources Array List
	 * 
	 * @return ArrayList:	  Arraylist of resources
	 */
	public ArrayList<Resource> getResourceList(){
		ArrayList<Resource> resources = new ArrayList<Resource>();
		resources.add(noResource());
		resources.add(metalResource());
		resources.add(energyResource());
		resources.add(knowledgeResource());
		return resources;
	}
	/**
	 * The method is used to return the resource by taking resource level as a parameter 
	 * @param resourceLevel		is the level of resource 
	 * @return Resource:		Returns the Resource object
	 */
	public Resource getResourceByResourceLevel(int resourceLevel){
		ArrayList<Resource> resources = getResourceList();
		Resource resource = new Resource();
		for(int i = 0; i < resources.size(); i++){
			if(resourceLevel == resources.get(i).resourceLevel()){
				resource = resources.get(i);
				break;
			}
		}
		return resource;
	}
}
