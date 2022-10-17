package old;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import old.Person;
import old.ProductMenu;

/**
 * The concrete subclass of Person.
 */
public class Seller extends Person {

	/**
	 * According to the need of seller show the appropriate items on
	 * the menu
	 * 
	 *  
	 */
	public void showMenu() {
		
	}

	/**
	 * According to the Product type create a concrete product menu:
	 * meat or produce
	 */
	public ProductMenu CreateProductMenu() {
		return null;
	}

}
