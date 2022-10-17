package main;

import java.util.ArrayList;

/**
 * The abstract class on the other side of the bridge
 * The abstract product of the factory method.
 */
public interface ProductMenu {

	//private Person person;
	
	public abstract ArrayList<String> getMenu();
	public abstract void showMenu();
	public abstract void fill();
	public abstract void defaultMenu();

	/**
	 * To show the add buttons.
	 */
	public abstract void showAddButton();

	/**
	 * To show the view buttons.
	 */
	public abstract void showViewButton();

	/**
	 * To show the view buttons.
	 */
	public abstract void showRadioButton();

	/**
	 * To show the view buttons.
	 */
	public abstract void showLabels();

	/**
	 * To show the comboxes
	 */
	public abstract void showComboxes();

}
