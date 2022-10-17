package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import main.Person;
import main.ProductMenu;
import main.main;

/**
 * One concrete implementation of ProductMenu for the meat product.
 * A subclass of ProductMenu. One of the concrete products of the factor
 * method.
 */
public class MeatProductMenu implements ProductMenu {
	private Person person;
	private ArrayList<String> list;
	
	public MeatProductMenu(Person person) {
		super();
		this.person = person;
		list = new ArrayList<String>();
	}

	public void showMenu() {
		list = getMenu();
		main.dash();
		if(main.debug) System.out.print("Meat Menu for ");
		if(person.getUII().getType() == 0) System.out.println("Available:");
		else if (person.getUII().getType() == 1) System.out.println("Owned:");
		else System.out.println("Error");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(i + ". " + list.get(i));
		}
		
	}
	/**
	 *  
	 */
	public ArrayList<String> getMenu() {
		fill();
		if (person.getUII().getType() == 0) { // buyer
			if(main.debug) System.out.println("Buyer");
			
			ArrayList<String> sellers = new ArrayList<String>();
			ArrayList<String> allAvailable = new ArrayList<String>();
			ArrayList<String> available = new ArrayList<String>();
			try {
				Scanner fScan = new Scanner(new File("files/SellerInfo.txt"));
				String str = "";
				while (fScan.hasNextLine()) {
					str = fScan.nextLine();
					String[] user = str.split(":");
					sellers.add(user[0]); // get all sellers
				}
				fScan.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			
			try {
				Scanner fScan = new Scanner(new File("files/UserProduct.txt"));
				while(fScan.hasNext()) {
					String str = fScan.next();
					String[] split = str.split(":");
					String owner = split[0];
					String item = split[1];
					if (sellers.contains(owner)) {
						allAvailable.add(item);
					}
				}
				fScan.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			main.dash();
			System.out.println("A:");
			for (String product:allAvailable) {
				if(list.contains(product)) { // just the meats
					available.add(product);
				}
			}
			return available;
		} else if (person.getUII().getType() == 1) {
			if(main.debug) System.out.println("Seller");
			ArrayList<String> allOwned = new ArrayList<String>();
			ArrayList<String> owned = new ArrayList<String>();
			try {
				Scanner fScan = new Scanner(new File("files/UserProduct.txt"));
				while(fScan.hasNext()) {
					String str = fScan.next();
					String[] split = str.split(":");
					String owner = split[0];
					String item = split[1];
					if (owner.equals(person.getUII().getUsername())) {
						allOwned.add(item);
					}
				}
				fScan.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			main.dash();
			
			for (String product:allOwned) {
				owned.add(product);
			}
			return owned;
		} else {
			defaultMenu();
			return list;
		}
	}

	public void fill() {
		list = new ArrayList<String>();
		try {
			Scanner fScan = new Scanner(new File("files/ProductInfo.txt"));
			while (fScan.hasNext()) {
				String str = fScan.next();
				String[] split = str.split(":");
				String type = split[0];
				String item = split[1];
				if (type.equals("Meat")) {
					list.add(item);
				}
			}
			fScan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void defaultMenu() {
		main.dash();
		System.out.println("Meat:");
		for (int i = 0; i < list.size(); i++) {
			System.out.println((i+1) + ". " + list.get(i));
		}
	}

	/**
	 * To show the add buttons.
	 */
	public void showAddButton() {

	}

	/**
	 * To show the view buttons.
	 */
	public void showViewButton() {

	}

	/**
	 * To show the radio buttons.
	 */
	public void showRadioButton() {

	}

	/**
	 * To show the labels.
	 */
	public void showLabels() {

	}

	/**
	 *  To show comboxes
	 */
	public void showComboxes() {

	}


}
