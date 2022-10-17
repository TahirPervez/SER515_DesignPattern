package old;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The interface class between the GUI and the underlining system, the
 * control logic and many of the operating functions are included in this class
 */
public class Facade {
	
	/**
	 * The type of the user: Buyer: 0, Seller 1
	 */
	private int UserType;

	/**
	 * The object that holds the currently selected product.
	 */
	private Product theSelectedProduct;

	/**
	 * The selected product category: 0: Meat, 1: Produce
	 */
	private int nProductCategory = -1;

	/**
	 * The list of products of the entire system.
	 */
	private ClassProductList theProductList;
	private ArrayList<Product> productList;

	/**
	 * The current user.
	 */
	private Person thePerson;

	/**
	 * Show login GUI and return the login result.
	 */
	public boolean login() {
		Scanner scan = new Scanner(System.in);
		main.dash();
		System.out.println("Welcome to the PTBS System.\n"
				+ "Please enter your username\n"
				+ "Enter EXIT to exit\n"
				+ "Enter NEW to create an account:");
		int productType = -1;

		String input;
		do {
			input = scan.nextLine();
			if (input == "") {} // nothing happens
			else if (input.equalsIgnoreCase("NEW")) {
				createUser();
				scan.close();
				return false;
			} else if(input.equalsIgnoreCase("EXIT")) {
				System.out.println("Goodbye");
				scan.close();
				return false;
			} else if (verify(input, "files/BuyerInfo.txt")) {
				if (main.debug) System.out.println(input + " is a Buyer");
				UserType = 0;
				System.out.println("Welcome back " + input);
				main.dash();
				
				askProductType();
				scan.close();
				if(nProductCategory == 0) {
					MeatProductMenu menu = new MeatProductMenu();
					menu.showMenu();
				} else if (nProductCategory == 1){
					ProduceProductMenu menu = new ProduceProductMenu();
					menu.showMenu();
				}
				return true;
			} else if (verify(input, "files/SellerInfo.txt")) {
				if (main.debug) System.out.println(input + " is a Seller");
				System.out.println("Welcome back " + input);
				UserType = 1;
				scan.close();
				return true;
			}
			else {
				if (main.debug) System.out.println(input + " is not a user");
			}
		} while (!input.equalsIgnoreCase("EXIT"));
		scan.close();
		return false;
	}
	
	/**
	 * get user input to determine meat or produce
	 */
	private void askProductType() {
		System.out.println("Press 1 for Meat or 2 for Produce");
		Scanner scan = new Scanner(System.in);
				
		while (nProductCategory == -1) {
			int input = scan.nextInt();
			if (input == 1) nProductCategory = 0;
			else if (input == 2) nProductCategory = 1;
			else System.out.println("Invalid Input.\nPress 1 for Meat or 2 for Produce.");
		}
		scan.close();
	}

	/**
	 * Checks for valid user
	 * @param username - user to look for
	 * @param filename - file to check
	 * @return true if found, false if not
	 */
	private boolean verify(String username, String filename) {
		try {
			Scanner scan = new Scanner(new File(filename));
			String str = "";
			while (scan.hasNextLine()) {
				str = scan.nextLine();
				String[] user = str.split(":");
				if (username.equals(user[0])) {
					scan.close();
					return true;
				}
			}
			scan.close();
			return false;
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}
	}
	

	/**
	 * When clicking the add button of the ProductMenu, call this
	 * function. This function will add a new trade and fill in the
	 * required information. This function will be called
	 * SellerTradingMenu or BuyerTradingMenu based on the type of
	 * the user. It will not update the product menu. The product menu
	 * needs to be refreshed outside the function
	 * 
	 *  
	 */
	public void addTrading() {

	}

	/**
	 * When clicking the view button of the ProductMenu, call this
	 * function and pass the pointer of the Trading and the person
	 * pointer to this function.
	 * This function will view the trading information.
	 * This function will call SellerTradingMenu or BuyerTradingMenu
	 * according to the type of the user
	 * 
	 *  
	 */
	public void viewTrading() {

	}

	/**
	 * This function will view the given offering.
	 * 
	 *  
	 */
	public void decideBidding() {

	}

	/**
	 * Set the deal flag of the given offering.
	 * 
	 *  
	 */
	public void discussBidding() {

	}

	/**
	 * Used by the buyer to submit the offering.
	 * 
	 *  
	 */
	public void submitBidding() {

	}

	/**
	 * Show the remind box to remind buyer of the upcoming overdue
	 * trading window
	 * 
	 *  
	 */
	public void remind() {

	}

	/**
	 * Create a user object according to the userinfoitem, the object
	 * can be a buyer or a seller
	 */
	public void createUser(UserInfoItem uii) {
		String filename = "";
		if (uii.getType().equals("Buyer")) {
			filename = "files/BuyerInfo.txt";
		} else {
			filename = "files/SellerInfo.txt";
		}
		
		try { // https://www.baeldung.com/java-append-to-file
			FileWriter fw = new FileWriter(filename, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.newLine();
			bw.write(uii.getUsername() + ":" + uii.getPassword());
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Account Created.");
	}
	/**
	 * Create user in console
	 */
	public void createUser() {
		main.dash();
		Scanner scan = new Scanner(System.in);
		boolean done = false;
		do {
			System.out.println("Enter what you would like your username to be.\nEnter EXIT to exit user creation:");
			String input = scan.nextLine();
			if (input.equalsIgnoreCase("EXIT")) {
				return;
			}
			if (verify(input, "files/BuyerInfo.txt") || verify(input, "files/BuyerInfo.txt")) {
				System.out.println("Sorry, name already in use.");
			} else if (input.equalsIgnoreCase("NEW")) {
				System.out.println("Invalid Name.\nEnter what you would like your username to be.\\nEnter EXIT to exit user creation:");
			} else {
				System.out.println("Enter what you would like your password to be.\nEnter EXIT to exit user creation:");
				String password = scan.nextLine();
				if (password.equalsIgnoreCase("EXIT")) {
					return;
				}				
				
				System.out.println("Press 1 for Buyer or 2 for Seller");
				int accountType = -1;
				while (accountType == -1) {
					int type = scan.nextInt();
					if (type == 1) accountType = 0;
					else if (type == 2) accountType = 1;
					else System.out.println("Invalid Input.\nPress 1 for Meat or 2 for Produce.");
				}
				scan.close();
				
				UserInfoItem uii = new UserInfoItem(input, password, accountType);
				createUser(uii);
				done = true;
			}
		} while (!done);
		scan.close();
	}

	/**
	 * Create the product list of the entire system
	 * 
	 *  
	 */
	public void createProductList() {
		ArrayList<Product> list = new ArrayList<Product>();
		Scanner scan;
		main.dash();
		System.out.println("Produce:");
		try {
			scan = new Scanner(new File("files/ProductInfo.txt"));
			while (scan.hasNext()) {
				String str = scan.next();
				String[] split = str.split(":");
				String type = split[0];
				String item = split[1];
				list.add(new Product(type, item));
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		theProductList = null;
	}

	/**
	 * Call this function after creating the user. Create productList by
	 * reading the UserProduct.txt file. Match the product name with
	 * theProductList. Attach the matched product object to the new
	 * create user: Facade.thePerson. ProductLis
	 * 
	 *  
	 */
	public void AttatchProductToUser() {

	}

	/**
	 * Show the Product list in a Dialog and return the selected product.
	 */
	public Product SelectProduct() {
		return null;
	}

	/**
	 * This function will call the thePerson. CreateProductMenu0
	 * According to the real object (buyer or seller) and the
	 * productLevel, it will call different menu creator and show the
	 * menu differently according to the usertype
	 * 
	 *  
	 */
	public void productOperation() {

	}

}
