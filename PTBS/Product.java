public class Product {

	private ClassProductList classProductList;

	private Trading trading;
	
	private String type;
	private String name;
	
	public Product(String type, String name) {
		this.type = type;
		this.name = name;
	}
	
	public ClassProductList getClassProductList() {
		return classProductList;
	}
	public Trading getTrading() {
		return trading;
	}
	public String getType() {
		return type;
	}
	public String getName() {
		return name;
	}
	public void setClassProductList(ClassProductList classProductList) {
		this.classProductList = classProductList;
	}
	public void setTrading(Trading trading) {
		this.trading = trading;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setName(String name) {
		this.name = name;
	}

	
}
