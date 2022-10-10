public class main {
	static boolean debug = true;
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				Facade facade = new Facade();
				facade.login();
			}
		});
	}
}
