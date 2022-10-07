/**
 * The abstract class on the other side of the bridge
 * The abstract product of the factory method.
 */
public interface ProductMenu {

//	private Person person;

	public abstract void showMenu();

	/**
	 * To show the add buttons.
	 * 
	 *  
	 */
	public abstract void showAddButton();

	/**
	 * To show the view buttons.
	 * 
	 *  
	 */
	public abstract void showViewButton();

	/**
	 * To show the view buttons.
	 * 
	 *  
	 */
	public abstract void showRadioButton();

	/**
	 * To show the view buttons.
	 * 
	 *  
	 */
	public abstract void showLabels();

	/**
	 *  
	 */
	public abstract void showComboxes();

}
