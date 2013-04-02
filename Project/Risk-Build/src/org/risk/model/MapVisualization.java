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

import java.awt.Color;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.swing.JOptionPane;

import org.apache.commons.collections15.Predicate;
import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Context;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import edu.uci.ics.jung.visualization.picking.PickedState;
import edu.uci.ics.jung.visualization.renderers.Renderer;

//import org.risk.model.GraphMouseEvents.*;

/**
 * This Class is used to Create the map on the screen using JUNG Class Library
 * 
 * 
 * 
 */
public class MapVisualization implements Observer {

	/**
	 * the graph
	 */
	Graph<Integer, Number> graph;

	/**
	 * the visual component and renderer for the graph
	 */
	public VisualizationViewer<Integer, Number> vv;

	ArrayList<State> states = new ArrayList<State>();
	ArrayList<Country> countries = new ArrayList<Country>();
	ArrayList<Continent> continents = new ArrayList<Continent>();
	ArrayList<Link> links = new ArrayList<Link>();

	/**
	 * This method receives map object. This method will extract properties and
	 * assign to a desired variables.
	 * 
	 * @param map
	 *            Map object
	 */
	public void extractMap(Map map) {
		// retrieve all information about map object
		states = map.getMyStateList();
		countries = map.getMyCountryList();
		continents = map.getMyContinentList();
		links = map.getMyLinkList();
		createMap(map);
	}

	/**
	 * Creates the map on the screen
	 * 
	 * @param map		Map object
	 */
	private void createMap(Map map) {

		createStates();
		// links = removeDuplicateLinks(links);
		createLinks();
		customizeGraph(map);
		showStateInfo();
		mapPlayerToState();
		mapCapitalAndContinentToState();
		//new Game(vv, map);
	}

	/**
	 * Creates state on the screen
	 * 
	 * @param state		is the arraylist of states
	 */
	private void createStates() {
		graph = new DirectedSparseGraph<Integer, Number>();
		for (int i = 0; i < states.size(); i++) {
			graph.addVertex(states.get(i).getStateID());
		}
	}

	/**
	 * This method is used to remove duplicate links
	 * 
	 * @param link	is the list of links
	 * @return ArrayList		Returns the list(ArrayList) of Link objects
	 */
	public ArrayList<Link> removeDuplicateLinks(ArrayList<Link> link) {
		return new ArrayList<Link>();
	}

	/**
	 * This method is used to create links
	 * @param link	is the array list of links
	 */
	private void createLinks() {
		for (int i = 0; i < links.size(); i++) {
			graph.addEdge(new Double(Math.random()), links.get(i)
					.getSourceState().getStateID(), links.get(i)
					.getDestintionState().getStateID(), EdgeType.DIRECTED);
		}
	}

	/**
	 * This method is used to map list of states to specific players
	 * 
	 * @param state		is th arraylist of states
	 */
	private void mapPlayerToState() {

		// Transformer maps the vertex number to a vertex property
		Transformer<Integer, Paint> vertexColor = new Transformer<Integer, Paint>() {
			public Paint transform(Integer i) {
				if (states.get(i - 1).getCountryID() == Constants.COUNTRY1ID) {
					return Constants.COUNTRY1COLOR;
				} else if (states.get(i - 1).getCountryID() == Constants.COUNTRY2ID) {
					return Constants.COUNTRY2COLOR;
				} else if (states.get(i - 1).getCountryID() == Constants.COUNTRY3ID) {
					return Constants.COUNTRY3COLOR;
				} else if (states.get(i - 1).getCountryID() == Constants.COUNTRY4ID) {
					return Constants.COUNTRY4COLOR;
				} else {
					return Color.RED;
				}
			}
		};
		vv.getRenderContext().setVertexFillPaintTransformer(vertexColor);
	}

	/**
	 * This method is used to map the Capital and Continent to the the State
	 * 
	 * @param state		is the arraylist of state objects
	 */
	private void mapCapitalAndContinentToState() {

		Transformer<Integer, Shape> vertexSize = new Transformer<Integer, Shape>() {
			public Shape transform(Integer i) {
				switch (states.get(i - 1).getContinentID()) {
				case Constants.CONTINENT1ID:
					if (states.get(i - 1).getIsCapital()) {
						return AffineTransform.getScaleInstance(1.5, 1.5)
								.createTransformedShape(
										Constants.CONTINENT1SHAPE);
					} else {
						return Constants.CONTINENT1SHAPE;
					}
				case Constants.CONTINENT2ID:
					if (states.get(i - 1).getIsCapital()) {
						return AffineTransform.getScaleInstance(1.5, 1.5)
								.createTransformedShape(
										Constants.CONTINENT2SHAPE);
					} else {
						return Constants.CONTINENT2SHAPE;
					}
				case Constants.CONTINENT3ID:
					if (states.get(i - 1).getIsCapital()) {
						return AffineTransform.getScaleInstance(1.5, 1.5)
								.createTransformedShape(
										Constants.CONTINENT3SHAPE);
					} else {
						return Constants.CONTINENT3SHAPE;
					}
				case Constants.CONTINENT4ID:
					if (states.get(i - 1).getIsCapital()) {
						return AffineTransform.getScaleInstance(1.5, 1.5)
								.createTransformedShape(
										Constants.CONTINENT4SHAPE);
					} else {
						return Constants.CONTINENT4SHAPE;
					}
				case Constants.CONTINENT5ID:
					if (states.get(i - 1).getIsCapital()) {
						return AffineTransform.getScaleInstance(1.5, 1.5)
								.createTransformedShape(
										Constants.CONTINENT5SHAPE);
					} else {
						return Constants.CONTINENT5SHAPE;
					}
				case Constants.CONTINENT6ID:
					if (states.get(i - 1).getIsCapital()) {
						return AffineTransform.getScaleInstance(1.5, 1.5)
								.createTransformedShape(
										Constants.CONTINENT6SHAPE);
					} else {
						return Constants.CONTINENT6SHAPE;
					}
				case Constants.CONTINENT7ID:
					if (states.get(i - 1).getIsCapital()) {
						return AffineTransform.getScaleInstance(1.5, 1.5)
								.createTransformedShape(
										Constants.CONTINENT7SHAPE);
					} else {
						return Constants.CONTINENT7SHAPE;
					}
				default:
					return Constants.CONTINENT1SHAPE;
				}
			}

		};
		vv.getRenderContext().setVertexShapeTransformer(vertexSize);
	}
	
	/**
	 * This method gets the ID of a state and calls appropriate methods to finds
	 * its adjacent states, the continent to which it belongs and the country to
	 * which it belongs and shows the result to the user
	 * 
	 * @param myMap
	 *            Object of the Map class that contains all the information
	 * 
	 * @param stateID
	 *            ID of desired state
	 * @return Information about the neighbors, continent and country of the
	 *         state
	 */
	public String inspectState(Map myMap, int stateID) {
		String result = null;
		State state = new State();
		Country country = new Country();

		state = myMap.getStateByID(stateID);
		country = myMap.getCountryByState(state);

		result = "State Name: " + state.getStateName();
		result += "\n\nContinent: "
				+ myMap.getContinentByState(state).getContinentName();
		result += "\n\nCountry: "
				+ country.getCountryName();
		
		if (state.getIsCapital()) {
			result += "\n\nProduction Rate: \n"
					+ country.getProductionNumber()
					+ " ";
			if (country.getProductionType()!=null)
					result += country.getProductionType();
		}
			
		
		result += "\n\nArmy Strength: ";
				if (state.getArmyDetail().getVolunteer().armyStrength()>0) {
				result+= "\nVolunteer: "
				+ state.getArmyDetail().getVolunteer().armyStrength();}
				if (state.getArmyDetail().getConscript().armyStrength()>0) {
				result+= "\nConscript: "
				+ state.getArmyDetail().getConscript().armyStrength()/2;}
				if (state.getArmyDetail().getProfessionalSoldier().armyStrength()>0) {
				result+= "\nProfessional soldier: "
				+ state.getArmyDetail().getProfessionalSoldier().armyStrength()/5;}
				if (state.getArmyDetail().getSpecialOperationsSoldier().armyStrength()>0) {
				result+= "\nSpecial operations soldier: "
				+ state.getArmyDetail().getSpecialOperationsSoldier().armyStrength()/10;}
				if (state.getArmyDetail().getArtillery().armyStrength()>0) {
				result+= "\nArtillery: "
				+ state.getArmyDetail().getArtillery().armyStrength()/20;}
				if (state.getArmyDetail().getMechanizedInfantry().armyStrength()>0) {
				result+= "\nMechanized infantry: "
				+ state.getArmyDetail().getMechanizedInfantry().armyStrength()/50;}
				if (state.getArmyDetail().getAircraft().armyStrength()>0) {
				result+= "\nAircraft: "
				+ state.getArmyDetail().getAircraft().armyStrength()/100;}
				if (state.getArmyDetail().getHeavyPrecisionArtillery().armyStrength()>0) {
				result+= "\nHeavy precision artillery: "
				+ state.getArmyDetail().getHeavyPrecisionArtillery().armyStrength()/200;}
				if (state.getArmyDetail().getTacticalMissile().armyStrength()>0) {
				result+= "\nTactical missile: "
				+ state.getArmyDetail().getTacticalMissile().armyStrength()/500;}
				if (state.getArmyDetail().getNuclearMissile().armyStrength()>0) {
				result+= "\nNuclear missile: "
				+ state.getArmyDetail().getNuclearMissile().armyStrength()/1000;}			
		/*
		 * result += "\n\nCountry Technology Level: " + country
		 * .getCountryByCountryId(
		 * myMap.getCountryByState(state).getCountryID(),
		 * map.getMyCountryList()).getTechnology() .technologyLevel();
		 */
		result += "\n\nAdjacent States:";

		for (State tempState : myMap.getAdjacentStatesOfState(state, (byte) 0)) {
			result += "\n  " + tempState.getStateName();
		}
		return result;
	}

	
	
	/**
	 * This method is used to show resource and technology of a state
	 */
    public class stateInfoTip<E>
	implements Transformer<Integer,String> {
    /**
     * The method is used to transform the map
     */
    public String transform(Integer v) {
    	return "<html><center><h3>"
				+ states.get(v - 1).getStateName()
				+ "</h3><center>Resource: "
				+ states.get(v - 1).getResource()
						.resourceName()
				+ "<center>Technology: "
				+ countries
						.get(states.get(v - 1)
								.getCountryID() - 1)
						.getTechnology().technologyLevel();
    }
}
	/**
	 * This method is used to show army and production of a state
	 */    
	private void showStateInfo() {
		vv.getRenderContext().setVertexLabelTransformer(
				new Transformer<Integer, String>() {

					public String transform(Integer v) {
					return "<html><center><b><font size=\"5\" color=\"black\">"+states.get(v-1).getArmyDetail().getArmyStrength(states.get(v-1).getArmyDetail());
					}
				});	
	}

	/**
	 * This method is used to customize the graph which represents map of the
	 * game
	 * @param map 
	 */
	private void customizeGraph(final Map map) {

		Transformer<Integer, Point2D> locationTransformer = new Transformer<Integer, Point2D>() {

			@Override
			public Point2D transform(Integer v) {

				float xPoint = states.get(v-1).getxPoint();
				float yPoint = states.get(v-1).getyPoint();
				return new Point2D.Float(xPoint, yPoint);
			}
		};

		StaticLayout<Integer, Number> layout = new StaticLayout<Integer, Number>(graph, locationTransformer);
		vv = new VisualizationViewer<Integer, Number>(layout);
		
		
		//vv.setBackground(Color.white);
		// add my listener for ToolTips
		
		vv.getRenderContext().setEdgeShapeTransformer(new EdgeShape.Line<Integer,Number>());
		vv.setVertexToolTipTransformer(new stateInfoTip<Number>());
		vv.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.CNTR);
		
		// Remove Arrows
		vv.getRenderContext().setEdgeArrowPredicate(new Predicate<Context<Graph<Integer, Number>, Number>>() {
					public boolean evaluate(Context<Graph<Integer, Number>, Number> context) {
						return false;
					}});

		final PickedState<Integer> pickedState = vv.getPickedVertexState();

		pickedState.addItemListener(new ItemListener() {

			/**
			 * This method is used to override itemStateChanged
			 */
			@Override
			public void itemStateChanged(ItemEvent e) {
				Object selectedNode = e.getItem();
				if (selectedNode instanceof Integer) {
					Integer vertex = (Integer) selectedNode;

					if (pickedState.isPicked(vertex)) {
						String inspectionDetail = inspectState(map,
								vertex);
						JOptionPane.showMessageDialog(vv, inspectionDetail);
					}
				}
			}
		});
	}

	/**
	 * This method is used to update all the observable objects
	 */
	@Override
	public void update(Observable o, Object arg) {
		Map map = (Map) o;
		states = map.getMyStateList();
		countries = map.getMyCountryList();
		continents = map.getMyContinentList();
		links = map.getMyLinkList();

		createStates();
		// links = removeDuplicateLinks(links);
		createLinks();
		customizeGraph(map);
		showStateInfo();
		mapPlayerToState();
		mapCapitalAndContinentToState();
		
	}
}
