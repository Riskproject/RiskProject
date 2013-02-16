package org.risk.model;

/**
 * The instance of this Class is used to hold the id and name for State,
 * Continent, etc. beforre storing it in any component like JCombo etc.
 * 
 * @author Arij
 * 
 */
public class Item {

	private int id;
	private String name;

	/**
	 * Regular Constructor- Assigns value according to provided input
	 * 
	 * @param id
	 *            : Id of the item
	 * @param name
	 *            : Name of the Item
	 */
	public Item(int id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * This method is used to retrieve the ID
	 * 
	 * @return Id of the Item
	 */
	public int getId() {
		return id;
	}

	/**
	 * This method is used to retrieve Description
	 * 
	 * @return Name of the item
	 */
	public String getDescription() {
		return name;
	}

	/**
	 * This method is used to return name
	 * 
	 * returns the name of the item
	 */
	public String toString() {
		return name;
	}
}
