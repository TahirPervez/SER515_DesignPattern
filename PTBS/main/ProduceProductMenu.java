package main;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
public class ProduceProductMenu implements ProductMenu {
	private Person person;
	private ArrayList<String> list;
	
	
	public ProduceProductMenu(Person person) {
		super();
		this.person = person;
		list = new ArrayList<String>();
	}

	public void showMenu() {
		list = getMenu();
		main.dash();
		if(main.debug) System.out.println("Produce Menu");
		int type = person.getUII().getType();
		if(type == 0) {
			System.out.println("Available:");
		}
		else {
			System.out.println("Owned:");
		}
		for (int i = 0; i < list.size(); i++) {
			System.out.println((i+1) + ". " + list.get(i));
		}
		int choice = -1;
		if(type == 0) {
			choice = getChoice(true);
			buy(choice);
		} else {
			choice = getChoice(false);
			sell(choice);
		}
	}
	
	private int getChoice(boolean buyer) {
		Scanner scan = new Scanner(System.in);
		int choice = -1;
		
		while(choice < 1 || choice > list.size()) {
			if (buyer) System.out.println("Enter the number of the item you wish to buy.");
			else System.out.println("Enter the number of the item you wish to sell.");
			choice = scan.nextInt();
			if (choice < 1 || choice > list.size() + 1) {
				System.out.println("Invalid Entry");
			}
		}
		scan.close();
		return choice;
	}
	
	private void buy(int choice) {
		ArrayList<String> sellers = new ArrayList<String>();
		String item = list.get(choice-1);
		try {
			Scanner fScan = new Scanner(new File(main.SELLERS));
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
		
		Scanner fScan;
		try {
			fScan = new Scanner(new File(main.OWNED));
			boolean found = false;
			StringBuffer strb = new StringBuffer();
			
			while (fScan.hasNext()) {
				String next = fScan.nextLine();
				String[] input = next.split(":");
				//			is a seller					has the item		none have been bought
				if(sellers.contains(input[0]) && (input[1].equalsIgnoreCase(item) && !found)) { // has the item	
					found = true;
				} else {
					strb.append(next + System.lineSeparator());
				}
			}
			strb.append(person.getUII().getUsername() + ":" + item + System.lineSeparator());
			
			try {
				FileWriter fw = new FileWriter(main.OWNED, false);
				BufferedWriter bw = new BufferedWriter(fw);
				// bw.write(person.getUII().getUsername() + ":" + input[1]);
				bw.write(strb.toString());
				bw.newLine();
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fScan.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(main.debug) System.out.println("Sucessfully Bought.");
	}
	
	private void sell(int choice) {
		String item = list.get(choice-1);
		
		Scanner fScan;
		try {
			fScan = new Scanner(new File(main.OWNED));
			boolean found = false;
			StringBuffer strb = new StringBuffer();
			
			while (fScan.hasNext()) {
				String next = fScan.nextLine();
				String[] input = next.split(":");
				//			is theirs								has the item		none have been bought
				if(input[0].equals(person.getUII().getUsername()) && input[1].equalsIgnoreCase(item) && !found) { // has the item	
					found = true;
				} else {
					strb.append(next + System.lineSeparator());
				}
			}
			
			try {
				FileWriter fw = new FileWriter(main.OWNED, false);
				BufferedWriter bw = new BufferedWriter(fw);
				// bw.write(person.getUII().getUsername() + ":" + input[1]);
				bw.write(strb.toString());
				bw.newLine();
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fScan.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(main.debug) System.out.println("Sucessfully Sold.");
	}
	
	
	
	
	/**
	 *  
	 */
	public ArrayList<String> getMenu() {
		fill();
		if (person.getUII().getType() == 0) { // buyer
			
			ArrayList<String> sellers = new ArrayList<String>();
			ArrayList<String> allAvailable = new ArrayList<String>();
			ArrayList<String> available = new ArrayList<String>();
			try {
				Scanner fScan = new Scanner(new File(main.SELLERS));
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
				Scanner fScan = new Scanner(new File(main.OWNED));
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
			for (String product:allAvailable) {
				if(list.contains(product)) { // just the meats
					available.add(product);
				}
			}
			return available;
		} else if (person.getUII().getType() == 1) {
			ArrayList<String> allOwned = new ArrayList<String>();
			ArrayList<String> owned = new ArrayList<String>();
			try {
				Scanner fScan = new Scanner(new File(main.OWNED));
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
			Scanner fScan = new Scanner(new File(main.PRODUCTS));
			while (fScan.hasNext()) {
				String str = fScan.next();
				String[] split = str.split(":");
				String type = split[0];
				String item = split[1];
				if (type.equals("Produce")) {
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
		System.out.println("Produce:");
		for (int i = 0; i < list.size(); i++) {
			System.out.println((i + ". " + list.get(i)));
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
