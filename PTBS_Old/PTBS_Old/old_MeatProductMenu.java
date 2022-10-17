package old;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import old.ProductMenu;

/**
 * One concrete implementation of ProductMenu for the meat product.
 * A subclass of ProductMenu. One of the concrete products of the factor
 * method.
 */
public class MeatProductMenu implements ProductMenu {
	/**
	 *  
	 */
	public void showMenu() {
		ArrayList<String> list = new ArrayList<String>();
		Scanner scan;
		main.dash();
		System.out.println("Meat:");
		try {
			scan = new Scanner(new File("files/ProductInfo.txt"));
			while (scan.hasNext()) {
				String str = scan.next();
				String[] split = str.split(":");
				String type = split[0];
				String item = split[1];
				if (type.equals("Meat")) {
					list.add(item);
				}
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < list.size(); i++) {
			System.out.println((i+1) + ". " + list.get(i));
		}
	}

	/**
	 * To show the add buttons.
	 * 
	 *  
	 */
	public void showAddButton() {

	}

	/**
	 * To show the view buttons.
	 * 
	 *  
	 */
	public void showViewButton() {

	}

	/**
	 * To show the radio buttons.
	 * 
	 *  
	 */
	public void showRadioButton() {

	}

	/**
	 * To show the labels.
	 * 
	 *  
	 */
	public void showLabels() {

	}

	/**
	 *  
	 */
	public void showComboxes() {

	}

}
