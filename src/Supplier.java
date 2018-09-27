import java.util.HashMap;
import java.util.Map;

public class Supplier {
	
	private String id;
	private String name;
	private int passportID;
	
	private final Map<Product, Integer> productsSupply = new HashMap<>();
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPassportID() {
		return passportID;
	}

	public void setPassportID(int passportID) {
		this.passportID = passportID;
	}
	
	public Supplier(String id, String name, int passportID) {
		this.id = id;
		this.name = name;
		this.passportID = passportID;
	}

	public static Supplier createSupplier(String id, String name, int passportID) {
		return new Supplier(id, name, passportID);
	}
	
	public Map<Product, Integer> getProducts() {
				
			return productsSupply;
	}
	
	public void addProductToSupplier(Product product, int quantity) {
		productsSupply.put(product, quantity);
	}
	

	@Override
	public String toString() {
		return "Supplier [id=" + id + ", name=" + name + ", passportID=" + passportID + ", productsSupply="
				+ productsSupply + "]";
	}
	
}