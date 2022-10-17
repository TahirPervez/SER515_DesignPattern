package main;
import main.ProductMenu;
import main.UserInfoItem;

/**
 * The abstract class on one side of the bridge
 * The Person class is involved in the bridge pattern to show the Menu, and
 * in factory menu to create proper menu object. It has no idea of the
 * concrete high-level or low-level menu.
 */
public abstract class Person {

	/**
	 * Variable of ProductMenu. Use this variable to point to a concrete
	 * productMenu object. Later, it will operate the object
	 */
	
	private ProductMenu productMenu;
	private UserInfoItem uii;

	/**
	 * Overridden by the class: buyer and seller to show the menu
	 */
	public abstract void showMenu();
	
	public UserInfoItem getUII() {
		return uii;
	}
	public void setUII(UserInfoItem uii) {
		this.uii = uii;
	}

	/**
	 * Call the implementation to show the "add" buttons.
	 */
	public void showAddButton() {}

	/**
	 * Call the implementation to show the "view" buttons.
	 */
	public void showViewButton() {}

	/**
	 * implementation to show the "view" buttons.
	 */
	public void showRadioButton() {}

	/**
	 * Call the implementation to show the labels.
	 */
	public void showLabels() {}

	/**
	 * The abstract factory method.
	 */
	public abstract ProductMenu CreateProductMenu();

}
