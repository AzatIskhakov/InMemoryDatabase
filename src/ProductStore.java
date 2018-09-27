import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ProductStore {
	
	private final Map<Product, Integer> list = new HashMap<>();
	
	
	
	public void printProducts() {
		System.out.println("Product list");
//		FileHandler.readFile();
		
		
		Iterator<Entry<Product, Integer>> iter = list.entrySet().iterator();
		
		while (iter.hasNext()) {
			Entry<Product, Integer> en = iter.next();
			System.out.println( en.getKey() + ", with quantity= " + en.getValue() );
		}
	}
	
	public void addNewProduct(Product product, Integer quantity) {

		if (product != null) {
		list.merge(product, quantity, Integer::sum);
		}
		else {
			System.err.println("Bad product");
		}
//		try {
//			FileHandler.writeToFile(FileHandler.products);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	
	
	public boolean sellProduct(Product product, int quantity) { // product.getId(),

		
		if ( product != null) {
			int quant = list.get(product);
			if (quant >= quantity) {
				list.replace(product, quant - quantity);
				return true;
			} else {
				System.out.println("not sufficient quantity");
			}
//			FileHandler.products.merge(product, quantity, Integer::sum);
//			System.out.println(product.getId());
//		} else {
//			System.out.println("Sorry. Insufficient quantity.");
//		}
//		try {
//			FileHandler.writeToFile(FileHandler.products);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		} else {
			System.out.println("No such product");
		}
		return false;
	}

	public Product findProductById(String id) {
		return list.entrySet().stream().map(Entry::getKey).filter(p -> p.getId().equals(id)).findFirst().get();		
	}
	
	public Map<Product, Integer> prodList() {
		return list;
	}
	
}
