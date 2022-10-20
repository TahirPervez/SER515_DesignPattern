package main;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * One concrete implementation of ProductMenu for the produce product.
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
			if(choice != -1) buy(choice);
		} else {
			choice = getChoice(false);
			if(choice != -1) sell(choice);
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
		String chosen = list.get(choice-1);
		
		Scanner offeringScanner, ownedScanner;
		try {
			offeringScanner = new Scanner(new File(main.OFFERINGS));
			ownedScanner = new Scanner(new File(main.OWNED));
			
			boolean foundOffering = false;
			boolean foundOwned = false;
			StringBuffer offeringBuffer = new StringBuffer();
			StringBuffer ownedBuffer = new StringBuffer();

			while (offeringScanner.hasNext()) {
				String entry = offeringScanner.nextLine();
				String[] input = entry.split(":");
				String request = input[0];
				String owner = input[1];
				String item = input[2];
				//			is a seller			has the item	none have been bought	
				if(request.equals("SELL") && item.equals(chosen) && !foundOffering) { // has the item	
					foundOffering = true; // Essentially remove the one you buy
					while (ownedScanner.hasNext()) {
						String entry2 = ownedScanner.nextLine();
						String[] input2 = entry2.split(":");
						String owner2 = input2[0];
						String item2 = input2[1];
						//			is a seller					has the item		none have been bought
						if(owner.equals(owner2) && item2.equals(item) && !foundOwned) { // has the item	
							foundOwned = true;
						} else {
							ownedBuffer.append(entry + System.lineSeparator());
						}
					}
					ownedBuffer.append(person.getUII().getUsername() + ":" + chosen + System.lineSeparator());
				} else {
					offeringBuffer.append(entry + System.lineSeparator());
				}
			}
			
			try {
				FileWriter offeringFileWriter = new FileWriter(main.OFFERINGS, false);
				BufferedWriter offeringBufferedWriter = new BufferedWriter(offeringFileWriter);
				offeringBufferedWriter.write(offeringBuffer.toString());
				offeringBufferedWriter.newLine();
				offeringBufferedWriter.close();

				FileWriter ownedFileWriter = new FileWriter(main.OWNED, false);
				BufferedWriter ownedBufferedWriter = new BufferedWriter(ownedFileWriter);
				ownedBufferedWriter.write(ownedBuffer.toString());
				ownedBufferedWriter.newLine();
				ownedBufferedWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			offeringScanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(main.debug) System.out.println("Sucessfully Bought.");
	}
	
	private void sell(int choice) {
		String chosen = list.get(choice-1);
		
		Scanner fScan;
		try {
			fScan = new Scanner(new File(main.OWNED));
			boolean found = false;
			StringBuffer ownedBuffer = new StringBuffer();
			String offering = "";
			while (fScan.hasNext()) {
				String next = fScan.nextLine();
				String[] input = next.split(":");
				String owner = input[0];
				String item = input[1];
				//			is theirs								has the item		none have been bought
				if(owner.equals(person.getUII().getUsername()) && item.equals(chosen) && !found) { // has the item	
					found = true;
					offering = "SELL:"+ person.getUII().getUsername() + ":" + item;
				} else {
					ownedBuffer.append(next + System.lineSeparator());
				}
			}
			
			try {
				FileWriter ownedFileWriter = new FileWriter(main.OWNED, false);
				BufferedWriter ownedBuffewrWriter = new BufferedWriter(ownedFileWriter);
				ownedBuffewrWriter.write(ownedBuffer.toString());
				ownedBuffewrWriter.newLine();
				ownedBuffewrWriter.close();

				FileWriter offeringFileWriter = new FileWriter(main.OFFERINGS, true);
				BufferedWriter offeringBuffewrWriter = new BufferedWriter(offeringFileWriter);
				offeringBuffewrWriter.write(offering);
				offeringBuffewrWriter.newLine();
				offeringBuffewrWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fScan.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(main.debug) System.out.println("Sucessfully Set to Available.");
	}
	
	
	
	
	/**
	 *  
	 */
	public ArrayList<String> getMenu() {
		fill();
		if (person.getUII().getType() == 0) { // buyer
			
			ArrayList<String> allAvailable = new ArrayList<String>();
			ArrayList<String> available = new ArrayList<String>();		
			
			try {
				Scanner fScan = new Scanner(new File(main.OFFERINGS));
				while(fScan.hasNext()) {
					String entry = fScan.next();
					String[] input = entry.split(":");
					String request = input[0]; // BUY or SELL
					String owner = input[1];
					String item = input[2];
					if (request.equalsIgnoreCase("SELL")) {
						allAvailable.add(owner + ":" + item);
					}
				}
				fScan.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			for (String ownerProduct:allAvailable) {
				String[] entry = ownerProduct.split(":");
				String product = entry[1];
				if(list.contains(product)) { // just the meats
					available.add(product);
				}
			}
			return available;
		} else if (person.getUII().getType() == 1) { // Seller
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
