import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

public class FileHandler {

	static String filePath = "Products.txt";

	public static Map<Product, Integer> readFile() {

		File f = new File(filePath);
		if (f.exists()) {
			InputStream input = null;
			ObjectInputStream objectInput = null;
			try {
				input = new FileInputStream(filePath);
				objectInput = new ObjectInputStream(input);
				return (Map<Product, Integer>) objectInput.readObject();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (input != null) {
					try {
						input.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				if (objectInput != null) {
					try {
						objectInput.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		}
		return null;

	}

	public static void writeToFile(Map<Product, Integer> products3) throws IOException {
		try {
			FileOutputStream output = new FileOutputStream(filePath);
			ObjectOutputStream objectOutput = new ObjectOutputStream(output);
			objectOutput.writeObject(products3);
			objectOutput.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
