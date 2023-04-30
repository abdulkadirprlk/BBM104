import java.io.*;

public class Main {
	public static void main(String[] args) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt", true));
			String inputFile = args[0];
			try {
				BufferedReader reader = new BufferedReader(new FileReader(inputFile));
				String line = reader.readLine();
				if (args.length != 1) {//Case 1 Parameter number error
					writer.write("There should be only 1 parameter\n");
					writer.close();
					System.exit(0);
				}
				if (line == null) {//Case 2 Input file empty error
					writer.write("The input file should not be empty\n");
				} else if (line.matches(".*[^a-zA-Z ].*")) {//Case 3 Invalid character error
					writer.write("The input file should not contains unexpected characters\n");
				} else {//Case 0 No error
					writer.write(line + "\n");
				}

				reader.close();
			} catch (IOException e) {//Case 4 Input file not found error
				writer.write("There should be an input file in the specified path\n");
			}
			writer.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
