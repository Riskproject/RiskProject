package org.risk.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * An instance of this class represent a connection between two states.
 * 
 * @author Omer
 * 
 */
@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class Link {
	private State sourceState;
	private State destintionState;

	/**
	 * Default Constructor
	 */
	public Link() {
	}

	/**
	 * Constructs and Initialises Link's object based on values receive from
	 * parameters
	 * 
	 * @param name					represents the name of link
	 * 
	 * @param sourceState			is the state object connected to source end of the link 
	 * 
	 * @param destinationState		is the state object connected to destination end of the link
	 * 
	 * @param linkId				represents link id and its an int value
	 * 
	 * 
	 */
	public Link(String name, State sourceState, State destinationState,
			int linkId) {
		this.sourceState = sourceState;
		this.destintionState = destinationState;
	}

	/**
	 * Returns source state object
	 * 
	 * @return Source State
	 */
	public State getSourceState() {
		return sourceState;
	}

	/**
	 * Sets the source state
	 * 
	 * @param sourceState		is the state object. and also source end of the link
	 */
	public void setSourceState(State sourceState) {
		this.sourceState = sourceState;
	}

	/**
	 * Returns destination state object
	 * 
	 * @return destintionState
	 */
	public State getDestintionState() {
		return destintionState;
	}

	/**
	 * Sets destination state
	 * 
	 * @param destintionState	represents the destination state in the link
	 */
	public void setDestintionState(State destintionState) {
		this.destintionState = destintionState;
	}
}
