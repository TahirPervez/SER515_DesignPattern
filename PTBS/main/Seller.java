package main;
import java.util.Scanner;

import main.MeatProductMenu;
import main.Person;
import main.ProduceProductMenu;
import main.ProductMenu;

public class Seller extends Person {

	public Seller(UserInfoItem uii) {
		this.setUII(uii);
	}

	@Override
	public void showMenu() {
		ProductMenu menu = CreateProductMenu();
		
	}

	@Override
	public ProductMenu CreateProductMenu() {
		ProductMenu menu;
		int type = askProductType();
		while (type == -1) {
			type = askProductType();
		}
		if(type == 1) menu = new MeatProductMenu(this);
		else menu = new ProduceProductMenu(this);
		menu.showMenu();
		return menu;
		
	}
	
	/**
	 * get user input to determine meat or produce
	 */
	private int askProductType() {
		System.out.println("Press 1 for Meat or 2 for Produce");
		Scanner scan = new Scanner(System.in);
		
		int type = -1;
		while (type == -1) {
			int input = scan.nextInt();
			if (input == 1) type = 1;
			else if (input == 2) type = 2;
			else System.out.println("Invalid Input.\nPress 1 for Meat or 2 for Produce.");
		}
		scan.close();
		return -1;
	}
}
