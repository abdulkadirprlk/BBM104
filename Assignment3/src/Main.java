//Abdulkadir Parlak 2210765025

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
	public static ArrayList<Book> books = new ArrayList<>();// stores the books with their IDs.
	public static ArrayList<LibraryMember> members = new ArrayList<>();// stores the members with their IDs.
	public static LinkedHashMap<Integer, BorrowHistory> borrowHistoryList = new LinkedHashMap<>();
	public static LinkedHashMap<Integer, ReadHistory> readHistoryList = new LinkedHashMap<>();


	public static void main(String[] args) {
		String inputFile = args[0];
		String outputFile = args[1];
		launch(inputFile, outputFile);
	}

	public static void launch(String inputFile, String outputFile) {
		BookManager bookManager = new BookManager();
		MemberManager memberManager = new MemberManager();
		try {
			BufferedWriter writer = new BufferedWriter(new BufferedWriter(new FileWriter(outputFile)));
			try {
				BufferedReader reader = new BufferedReader(new FileReader(inputFile));
				String line = null;
				while ((line = reader.readLine()) != null) {
					List<String> lineList = null;
					lineList = Arrays.asList(line.split("\t"));
					String command = lineList.get(0);
					//Command Indication
					if (command.equals("addBook")) {// addBook	P
						writer.write(bookManager.addBook(lineList.get(1)));
					} else if (command.equals("addMember")) {// addMember	S
						writer.write(memberManager.addMember(lineList.get(1)));
					} else if (command.equals("borrowBook")) {// borrowBook	1	1	2023-04-07
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
						LocalDate dateTime = LocalDate.parse(lineList.get(3), formatter);
						writer.write(bookManager.borrowBook(Integer.parseInt(lineList.get(1)),
								Integer.parseInt(lineList.get(2)), dateTime));
					} else if (command.equals("returnBook")) {// returnBook	2	1	2023-04-13
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
						LocalDate dateTime = LocalDate.parse(lineList.get(3), formatter);
						writer.write(bookManager.returnBook(Integer.parseInt(lineList.get(1)),
								Integer.parseInt(lineList.get(2)), dateTime));
					} else if (command.equals("extendBook")) {// extendBook	1	1	2023-04-14
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
						LocalDate dateTime = LocalDate.parse(lineList.get(3), formatter);
						writer.write(bookManager.extendBook(Integer.parseInt(lineList.get(1)),
								Integer.parseInt(lineList.get(2)), dateTime));
					} else if (command.equals("readInLibrary")) {// readInLibrary	3	3	2023-04-14
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
						LocalDate dateTime = LocalDate.parse(lineList.get(3), formatter);
						writer.write(bookManager.readInLibrary(Integer.parseInt(lineList.get(1)),
								Integer.parseInt(lineList.get(2)), dateTime));
					} else if (command.equals("getTheHistory")) {// getTheHistory
						writer.write(bookManager.getTheHistory());
					} else {
						writer.write("Erroneous Command!\n");
					}
				}
				reader.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			writer.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}