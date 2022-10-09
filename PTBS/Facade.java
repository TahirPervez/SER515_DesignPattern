import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * The interface class between the GUI and the underlining system, the
 * control logic and many of the operating functions are included in this class
 */
public class Facade {
	boolean debug = true;
	
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
	private int nProductCategory;

	/**
	 * The list of products of the entire system.
	 */
	private ClassProductList theProductList;

	/**
	 * The current user.
	 */
	private Person thePerson;

	/**
	 * Show login GUI and return the login result.
	 */
	public boolean login() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to the PTBS System. Please enter your username:");
		String input = scan.nextLine();
		if (verify(input, "files/BuyerInfo.txt")) {
			if (debug) System.out.println(input + " is a Buyer");
			System.out.println("Welcome back " + input);
			return true;
		}
		if (verify(input, "files/SellerInfo.txt")) {
			if (debug) System.out.println(input + " is a Seller");
			System.out.println("Welcome back " + input);
			return true;
		}
		
		return false;
	}
	private  boolean verify(String username, String filename) {
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
	public void createUser(UserInfoItem userinfootem) {

	}

	/**
	 * Create the product list of the entire system
	 * 
	 *  
	 */
	public void createProductList() {

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
