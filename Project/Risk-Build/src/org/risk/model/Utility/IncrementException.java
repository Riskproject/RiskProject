package org.risk.model.Utility;
/**
 * An instance of this class represents Increment exception. It should be thrown when increment is negative.
 * @author Omer
 *
 */
public class IncrementException extends Exception {
	/**
	 * Default Constructor initialises exception with default message
	 */
	public IncrementException (){
		super("Negative Increment not allowed!!");
	}
	/**
	 * Constructor initialises exception with specified message
	 * @param message	is a string variable which describes exception cause
	 */
	public IncrementException (String message){
		super(message);
	}

}
