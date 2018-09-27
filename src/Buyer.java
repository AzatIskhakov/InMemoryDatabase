import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Buyer {
	
	private String id;
	private String name;
	private int passportID;
	
	private final Map<Product, Integer> shopList = new HashMap<>();
	
	
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
	
	public Buyer(String id, String name, int passportID) {
		this.id = id;
		this.name = name;
		this.passportID = passportID;
	}
	
	public static Buyer createBuyer(String id, String name, int passportID) {
		return new Buyer(id, name, passportID);
	}
	
	public Map<Product, Integer> getProducts() {
		return shopList;
	}
	
	public void buyProducts(Product product, int quantity) {
		shopList.put(product, quantity);
	}

	@Override
	public String toString() {
		return "Buyer [id=" + id + ", name=" + name + ", passportID=" + passportID + ", shopList=" + shopList + "]";
	}
	
	

}