package old;
import old.Facade;

/**
 * The abstract class of the visitor, it can visit class: Façade, Trading, Product.
 * The real work that need to be done will be implemented in the concrete
 * visitor classes
 */
public abstract class NodeVisitor {

	/**
	 *  
	 */
	public abstract void visitProduct(Product product);

	/**
	 *  
	 */
	public abstract void visitTrading(Trading trading);

	public abstract void visitFacade(Facade facade);

}
