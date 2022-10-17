package old;
import old.Facade;

public class main {
	public static boolean debug = true;
	
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
	}
	/**
	 * Delimeter message for console outputs
	 */
	public static void dash() {
		System.out.println("\n-------------------------------------------------------------\n");
	}
}
