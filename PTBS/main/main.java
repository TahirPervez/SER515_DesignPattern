package main;

public class main {
	public static boolean debug = true;
	public static final String BUYERS = "files/BuyerInfo.txt";
	public static final String SELLERS = "files/SellerInfo.txt";
	public static final String PRODUCTS = "files/ProductInfo.txt";
	public static final String OWNED = "files/UserProduct.txt";
	
	public static void main(String[] args) {
		/*
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				Facade facade = new Facade();
				facade.login();
			}
		});
		*/
		Facade facade = new Facade();
		facade.login();
		facade.viewTrading();
	}
	/**
	 * Delimeter message for console outputs
	 */
	public static void dash() {
		System.out.println("\n-------------------------------------------------------------\n");
	}
}
