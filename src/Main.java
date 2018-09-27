import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Main {

	private static Scanner scanner = new Scanner(System.in);
	private static ProductStore productStore = new ProductStore();

	final static List<Buyer> listOfBuyers = new LinkedList<>();
	final static List<Supplier> listOfSuppliers = new LinkedList<>();

	public static void main(String[] args) throws IOException {

		checkFile();

		Product prod1 = new Product("001", "paper", 1);
		Product prod2 = new Product("002", "pencil", 2);
		Product prod3 = new Product("003", "folder", 3);
		Product prod4 = new Product("004", "display", 50);
		Product prod5 = new Product("005", "mouse", 7);
		Product prod6 = new Product("006", "FlashCard", 5);

		Buyer buy1 = new Buyer("111", "Buyer1", 555222);
		Buyer buy2 = new Buyer("112", "Buyer2", 777666);
		Buyer buy3 = new Buyer("113", "Buyer3", 888777);

		Supplier sup1 = new Supplier("221", "Sup1", 121212);
		Supplier sup2 = new Supplier("222", "Sup2", 123123);
		Supplier sup3 = new Supplier("223", "Sup3", 321321);

		/* Suppliers supply the products */
		sup1.addProductToSupplier(prod1, 100);
		sup1.addProductToSupplier(prod2, 50);
		sup2.addProductToSupplier(prod3, 70);
		sup2.addProductToSupplier(prod4, 10);
		sup3.addProductToSupplier(prod5, 30);
		sup3.addProductToSupplier(prod6, 80);

		listOfBuyers.add(buy1);
		listOfBuyers.add(buy2);
		listOfBuyers.add(buy3);

		// printBuyers(listOfBuyers);

		listOfSuppliers.add(sup1);
		listOfSuppliers.add(sup2);
		listOfSuppliers.add(sup3);

		// printSupplyers(listOfSuppliers);

		boolean quit = false;
		printActions();

		while (!quit) {
			System.out.println("\nEnter action: (7 to show available actions)");
			int action = scanner.nextInt();
			scanner.nextLine();

			switch (action) {

			case 0:
				System.out.println("\nExit from Menu...");
				quit = true;
				FileHandler.writeToFile(productStore.prodList());
				break;

			case 1:
				productStore.printProducts();
				break;

			case 2:
				addNewProduct();
				break;

			case 3:
				getSuppliersProducts(); // 3 - to show supplier's products
				break;

			case 4:
				getBuyersProducts(); // 4 -to show buyer's products
				break;

			case 5:
				supplyingProductToStore(); // 5 - to supply the product to Store
				break;

			case 6:
				buyingProduct(); // 6 - to buy the product
				break;

			case 7:
				// 7 - to show Log info.
				break;

			default:
				System.err.println("Choose another option!");
			}

		}

	}

	public static void printBuyers(List<Buyer> list) {

		ListIterator<Buyer> lb = list.listIterator();
		while (lb.hasNext()) {

			System.out.println(lb.next());
		}

	}

	public static void supplyingProductToStore() {

		System.out.println("Enter supplier's ID: ");
		String supplId = scanner.nextLine();
		// findSupplierById(supplId).getProducts();

		Supplier sup = findSupplierById(supplId);

		if (sup == null) {
			System.err.println("Thre's no supplier");
			return;
		}

		System.out.println("Enter product ID: ");
		String prodId = scanner.nextLine();

		System.out.println("Enter the product quantity to supply: ");
		int quantity = scanner.nextInt();

		Product foundProduct = null;
		Integer quant = 0;
		for (Entry<Product, Integer> p : sup.getProducts().entrySet()) {
			if (p.getKey().getId().equals(prodId)) {
				foundProduct = p.getKey();
				quant = p.getValue();
				break;
			}
		}
		if (foundProduct == null) {
			System.err.println("Thre's no product with such ID");
			return;
		}
		if (quant >= quantity) {
			sup.getProducts().put(foundProduct, quant - quantity);
			productStore.addNewProduct(foundProduct, quantity);
		} else {
			System.err.println("No sufficient quantity");
		}
	}

	public static void buyingProduct() {

		System.out.println("Enter buyer's ID: ");
		String buyerId = scanner.nextLine();

		Buyer buy = findBuyerById(buyerId);

		if (buy == null) {
			System.err.println("Thre's no buyer");
			return;
		}

		System.out.println("Enter product ID: ");
		String prodId = scanner.nextLine();

		System.out.println("Enter the product quantity to buy: ");
		int quantity = scanner.nextInt();

		Product foundProduct = productStore.findProductById(prodId);
		if (foundProduct == null) {
			System.err.println("No such product in a warehouse");
			return;
		}

		if (productStore.sellProduct(foundProduct, quantity)) {
			buy.buyProducts(foundProduct, quantity);
		} else {
			System.err.println("No sufficient quantity");
		}
	}

	public static void printBuyer(Buyer buy) {
		System.out.println(buy);
	}

	public static void printSupplyers(List<Supplier> list) {
		String n;
		for (Supplier s : list) {
			System.out.println(s);
		}
	}

	public static void printSupplier(Supplier supplier) {
		System.out.println(supplier);
	}

	public static Supplier findSupplierById(String id) {

		for (Supplier s : listOfSuppliers) {
			if (s.getId().equals(id)) { // םוכüחÿ םאמבמנמע !!!
				printSupplier(s); // comment/uncomment
				return s;
			}
		}
		return null;
	}

	public static Buyer findBuyerById(String id) {

		for (Buyer b : listOfBuyers) {
			if (b.getId().equals(id)) {
				printBuyer(b); // comment/uncomment
				return b;
			}
		}
		return null;
	}

	public static Map<Product, Integer> findProductById(String id) {

		Map<Product, Integer> supplierProd = findSupplierById(id).getProducts();

		System.out.println(findSupplierById(id).getProducts());
		for (Map.Entry<Product, Integer> entry : supplierProd.entrySet()) {
			Product prod = entry.getKey();

			System.out.println(prod.getId());

		}

		return supplierProd;
	}

	public static Product findSupplierProduct(String supplId, String prodId) {

		Map<Product, Integer> supplierProd = findSupplierById(supplId).getProducts();

		for (Map.Entry<Product, Integer> entry : supplierProd.entrySet()) {
			Product prod = entry.getKey();

			if (prod != null && prod.getId().equals(prodId)) {
				System.out.println(prod);

				return prod;
			}
		}
		return null;
	}

	public static Product findBuyerProduct(String buyerId, String prodId) {

		Map<Product, Integer> buyerProd = findBuyerById(buyerId).getProducts();

		for (Map.Entry<Product, Integer> entry : buyerProd.entrySet()) {
			Product prod = entry.getKey();

			if (prod != null && prod.getId().equals(prodId)) {
				System.out.println(prod);
				return prod;
			}
		}
		return null;
	}

	private static void getSuppliersProducts() {

		System.out.println("Enter supplier's ID: ");
		String id = scanner.nextLine();
		Supplier s = findSupplierById(id);
		if (s != null) {
			System.out.println(s.getProducts());
		} else {
			System.err.println("Thre's no supplier with such ID");
		}

	}

	private static void getBuyersProducts() {
		System.out.println("Enter buyer's ID: ");
		String id = scanner.nextLine();

		Buyer b = findBuyerById(id);
		if (b != null) {
			System.out.println(b.getProducts());
		} else {
			System.err.println("Thre's no buyer with such ID");
		}
	}

	private static void addNewProduct() {
		System.out.println("Enter new product ID: ");
		String id = scanner.nextLine();

		System.out.println("Enter new product name: ");
		String name = scanner.nextLine();

		System.out.println("Enter new product price: ");
		int price = scanner.nextInt();

		Product newProduct = Product.createProduct(id, name, price);

		System.out.println("Enter the quantity: ");
		Integer quantity = scanner.nextInt();

		productStore.addNewProduct(newProduct, quantity);
	}

	private static void printActions() {
		System.out.println("\nAvailable actions:\npress");
		System.out.println("0 - to exit\n" + "1 - to print products\n" + "2 - to add a new product\n"
				+ "3 - to show supplier's products\n" + "4 - to show buyer's products\n"
				+ "5 - to supply the product to Store\n" + "6 - to buy the product\n" + "7 - to show Log info.");
		System.out.println("Choose your action: ");
	}

	private static void checkFile() {
		File sourceFile = new File(FileHandler.filePath);
		if (sourceFile.exists()) {
			Map<Product, Integer> map = FileHandler.readFile();
			productStore.prodList().putAll(map);
		}
	}

}
