package old;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import old.ProductMenu;

/**
 * One concrete implementation of ProductMenu for the produce product
 * A subclass of ProductMenu. One of the concrete products of the factor
 * method.
 */
public class ProduceProductMenu implements ProductMenu {

	/**
	 *  
	 */
	public void showMenu() {
		/*
		JFrame frame = new JFrame("Produce Product Menu");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setVisible(true);
		JPanel ContentPane = new JPanel();
		ContentPane.setBorder(new EmptyBorder(5,5,5,5));
		frame.setContentPane(ContentPane);
		
		JLabel lbl_username = new JLabel("Username:");
		JTextField txtbx_username = new JTextField("Produce");
		txtbx_username.setColumns(30);
		ContentPane.add(txtbx_username);		
		*/
		ArrayList<String> list = new ArrayList<String>();
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
	 * To show the radio buttons.
	 * 
	 *  
	 */
	public void showRadioButton() {

	}

	/**
	 * To show the labels
	 * 
	 *  
	 */
	public void showLabels() {

	}

	/**
	 * To show the view buttons.
	 * 
	 *  
	 */
	public void showViewButton() {

	}

	/**
	 *  
	 */
	public void showComboxes() {

	}

}
