package org.risk.model.Utility;
/**
 * An instance of this class represents Item not found exception. It should be thrown when an item from the hash table or list is not found.
 * @author Omer
 *
 */
public class ItemNotFoundException extends Exception{
	/**
	 * Default Constructor initialises exception with default message
	 */
	public ItemNotFoundException() {
		super("No item found");
	}
	/**
	 * Constructor initialises exception with specified message
	 * @param message	is a string variable which describes exception cause
	 */
	public ItemNotFoundException(String message){
		super(message);
	}

}
