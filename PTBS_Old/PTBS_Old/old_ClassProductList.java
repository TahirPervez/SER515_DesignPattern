package old;
import java.util.ArrayList;

/**
 * The abstract class of the list to be iterated
 */
public class ClassProductList {

	private ArrayList<Product> product;

	private ReminderVisitor reminderVisitor;

	private ProductIterator productIterator;

	public void accept(NodeVisitor visitor) {
		
	}

	public ArrayList<Product> getProduct() {
		return product;
	}

	public ReminderVisitor getReminderVisitor() {
		return reminderVisitor;
	}

	public ProductIterator getProdutIterator() {
		return productIterator;
	}

	public void add(Product next) {
		product.add(next);
	}

	public void setReminderVisitor(ReminderVisitor reminderVisitor) {
		this.reminderVisitor = reminderVisitor;
	}

	public void setProductIterator(ProductIterator productIterator) {
		this.productIterator = productIterator;
	}

}
