package org.risk.model.Utility;
/**
 * An instance of this class represents divide by zero exception. It should be thrown when the number is divided by zero.
 * @author Omer
 *
 */
public class DivideByZeroException extends Exception {
	/**
	 * Default Constructor initialises exception with default message  
	 */
	public DivideByZeroException () {
		super("Division by zero not allowed!!");			
	}
	/**
	 * Constructor initialises exception with specified message
	 * @param message	is a string variable which describes exception cause   
	 */
	public DivideByZeroException (String message) {
		super(message);
	}
}