package org.risk.model.Utility;
/**
 * An instance of this class represents negative value exception.It should be thrown when value of a variable is negative.
 * @author Omer
 *
 */
public class NegativeValueException extends Exception {
	/**
	 * Default Constructor initialises exception with default message
	 */
	public NegativeValueException (){
		super("Negative Value not allowed!!");
	}
	/**
	 * Constructor initialises exception with specified message
	 * @param message	is a string variable which describes exception cause
	 */
	public NegativeValueException (String message){
		super(message);
	}

}
