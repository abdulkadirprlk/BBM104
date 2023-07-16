import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class BookManager implements IBookService {
	@Override
	public String addBook(String type) {
		if (type.equals("P")) {
			Printed printed = new Printed();// created a Printed book.
			Main.books.add(printed);// added to the books list.
			printed.setId(Main.books.indexOf(printed) + 1);
			return String.format("Created new book: Printed [id: %d]\n", printed.getId());
		} else if (type.equals("H")) {
			Handwritten handwritten = new Handwritten();// created a Handwritten book.
			Main.books.add(handwritten);// added to the books list.
			handwritten.setId(Main.books.indexOf(handwritten) + 1);
			return String.format("Created new book: Handwritten [id: %d]\n", handwritten.getId());
		} else {
			return "Wrong book type: Book type must be only (P)rinted or (H)andwritten!\n";
		}
	}

	@Override
	public String extendBook(int idOfBook, int idOfLibraryMember, LocalDate currentDate) {
		Book book = Main.books.get(idOfBook - 1);
		LibraryMember libraryMember = Main.members.get(idOfLibraryMember - 1);
		BorrowHistory borrowHistory = Main.borrowHistoryList.get(idOfBook);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String currentDateStr = currentDate.format(formatter);

		if (borrowHistory.isExtended()) {
			return "You cannot extend the deadline!\n";
		} else {
			if (libraryMember instanceof Student) {
				borrowHistory.setReturnDate(borrowHistory.getReturnDate().plusDays(7));
				borrowHistory.setExtended(true);
				return String.format("The deadline of book [%d] was extended by member [%d] at %s\n" +
								"New deadline of book [%d] is %s\n", idOfBook, idOfLibraryMember, currentDateStr, idOfBook,
						borrowHistory.getReturnDate());
			} else if (libraryMember instanceof Academic) {
				borrowHistory.setReturnDate(borrowHistory.getReturnDate().plusDays(14));
				borrowHistory.setExtended(true);
				return String.format("The deadline of book [%d] was extended by member [%d] at %s\n" +
								"New deadline of book [%d] is %s\n", idOfBook, idOfLibraryMember, currentDateStr, idOfBook,
						borrowHistory.getReturnDate());
			} else {
				return "The deadline is due!\n";
			}
		}
	}

	@Override
	public String returnBook(int idOfBook, int idOfLibraryMember, LocalDate returnDate) {
		Book book = Main.books.get(idOfBook - 1);
		LibraryMember libraryMember = Main.members.get(idOfLibraryMember - 1);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String returnDateStr = returnDate.format(formatter);
		long daysBetween = 0;

		if (Main.borrowHistoryList.containsKey(idOfBook)) {// book is borrowed
			if (Main.borrowHistoryList.get(idOfBook).isExtended()) {// return date is extended
				daysBetween = ChronoUnit.DAYS.between(returnDate, Main.borrowHistoryList.get(idOfBook).getReturnDate());
			} else {
				daysBetween = ChronoUnit.DAYS.between(Main.borrowHistoryList.get(idOfBook).getBorrowDate(), returnDate);
			}
			Main.borrowHistoryList.remove(book.getId());// book is returned
			libraryMember.setBorrowCount(libraryMember.getBorrowCount() - 1);// borrow count decreased by 1
			if (libraryMember instanceof Student) {
				if (daysBetween > 7) {
					return String.format("The book [%d] was returned by member [%d] at %s Fee: %d\n", idOfBook,
							idOfLibraryMember, returnDateStr, daysBetween - 7);
				} else {
					return String.format("The book [%d] was returned by member [%d] at %s Fee: 0\n", idOfBook,
							idOfLibraryMember, returnDateStr);
				}
			} else {// for Academicians
				if (daysBetween > 14) {
					return String.format("The book [%d] was returned by member [%d] at %s Fee: %d\n", idOfBook,
							idOfLibraryMember, returnDateStr, daysBetween - 14);
				} else {
					return String.format("The book [%d] was returned by member [%d] at %s Fee: 0\n", idOfBook,
							idOfLibraryMember, returnDateStr);
				}
			}
		} else {// book is reading in library
			libraryMember.setBorrowCount(libraryMember.getBorrowCount() - 1);// borrow count decreased by 1
			Main.readHistoryList.remove(book.getId());
			return String.format("The book [%d] was returned by member [%d] at %s Fee: 0\n", idOfBook,
					idOfLibraryMember, returnDateStr);
		}
	}


	@Override
	public String borrowBook(int idOfBook, int idOfLibraryMember, LocalDate borrowDate) {
		Book book = Main.books.get(idOfBook - 1);
		LibraryMember libraryMember = Main.members.get(idOfLibraryMember - 1);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDate = borrowDate.format(formatter);
		if (Main.borrowHistoryList.containsKey(idOfBook) || Main.readHistoryList.containsKey(idOfBook)) {
			return "You cannot borrow this book!\n";// someone borrowed or is reading the book.
		} else if (libraryMember instanceof Student) {
			if (book instanceof Handwritten) {
				return "This book cannot be borrowed!\n";
			} else if (libraryMember.getBorrowCount() == 2) {
				return "You have exceeded the borrowing limit!\n";
			} else {
				BorrowHistory borrowHistory = new BorrowHistory(idOfBook, idOfLibraryMember, borrowDate);
				borrowHistory.setReturnDate(borrowDate.plusWeeks(1));
				Main.borrowHistoryList.put(book.getId(), borrowHistory);
				libraryMember.setBorrowCount(libraryMember.getBorrowCount() + 1);// borrow count increased by 1
				return String.format("The book [%d] was borrowed by member [%d] at %s\n", book.getId(), libraryMember.getId(),
						formattedDate);
			}
		} else {// for academicians
			if (book instanceof Handwritten) {
				return "This book cannot be borrowed!\n";
			} else if (libraryMember.getBorrowCount() == 4) {
				return "You have exceeded the borrowing limit!\n";
			} else {
				BorrowHistory borrowHistory = new BorrowHistory(idOfBook, idOfLibraryMember, borrowDate);
				borrowHistory.setReturnDate(borrowDate.plusWeeks(2));
				Main.borrowHistoryList.put(book.getId(), borrowHistory);
				libraryMember.setBorrowCount(libraryMember.getBorrowCount() + 1);// borrow count increased by 1
				return String.format("The book [%d] was borrowed by member [%d] at %s\n", book.getId(), libraryMember.getId(),
						formattedDate);
			}
		}
	}

	@Override
	public String readInLibrary(int idOfBook, int idOfLibraryMember, LocalDate readDate) {
		Book book = Main.books.get(idOfBook - 1);
		LibraryMember libraryMember = Main.members.get(idOfLibraryMember - 1);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String readDateStr = readDate.format(formatter);
		if (Main.borrowHistoryList.containsKey(idOfBook) || Main.readHistoryList.containsKey(idOfBook)) {
			return "You cannot read this book!\n";// someone borrowed or is reading the book.
		} else if (libraryMember instanceof Student && book instanceof Handwritten) {
			return "Students can not read handwritten books!\n";
		} else {
			ReadHistory readHistory = new ReadHistory(idOfBook, idOfLibraryMember, readDate);
			Main.readHistoryList.put(book.getId(), readHistory);
			return String.format("The book [%d] was read in library by member [%d] at %s\n", idOfBook,
					idOfLibraryMember, readDateStr);
		}
	}


	@Override
	public String getTheHistory() {
		String result = "";
		result += ("History of library:\n");
		ArrayList<Student> students = new ArrayList<>();
		ArrayList<Academic> academics = new ArrayList<>();
		for (LibraryMember member : Main.members) {
			if (member instanceof Student) {
				students.add((Student) member);
			} else {
				academics.add((Academic) member);
			}
		}
		// Students displaying
		result += (String.format("\nNumber of students: %d\n", students.size()));
		for (Student student : students) {
			result += (String.format("Student [id: %d]\n", student.getId()));
		}
		// Academics displaying
		result += (String.format("\nNumber of academics: %d\n", academics.size()));
		for (Academic academic : academics) {
			result += (String.format("Academic [id: %d]\n", academic.getId()));
		}
		ArrayList<Printed> printedBooks = new ArrayList<>();
		ArrayList<Handwritten> handwrittenBooks = new ArrayList<>();
		for (Book book : Main.books) {
			if (book instanceof Printed) {
				printedBooks.add((Printed) book);
			} else {
				handwrittenBooks.add((Handwritten) book);
			}
		}
		// Printed books displaying
		result += (String.format("\nNumber of printed books: %d\n", printedBooks.size()));
		for (Printed printedBook : printedBooks) {
			result += (String.format("Printed [id: %d]\n", printedBook.getId()));
		}
		// Handwritten books displaying
		result += (String.format("\nNumber of handwritten books: %d\n", handwrittenBooks.size()));
		for (Handwritten handwrittenBook : handwrittenBooks) {
			result += (String.format("Handwritten [id: %d]\n", handwrittenBook.getId()));
		}
		// borrowed books displaying
		result += (String.format("\nNumber of borrowed books: %d\n", Main.borrowHistoryList.size()));
		for (BorrowHistory borrowHistory : Main.borrowHistoryList.values()) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String borrowDateStr = borrowHistory.getBorrowDate().format(formatter);
			result += (String.format("The book [%d] was borrowed by member [%d] at %s\n", borrowHistory.getBookId(),
					borrowHistory.getLibraryMemberId(), borrowDateStr));
		}
		// reading books displaying
		result += (String.format("\nNumber of books reading in library: %d\n", Main.readHistoryList.size()));
		for (ReadHistory readHistory : Main.readHistoryList.values()) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String readDateStr = readHistory.getReadDate().format(formatter);
			result += String.format("The book [%d] was read in library by member [%d] at %s\n", readHistory.getBookId(),
					readHistory.getLibraryMemberId(), readDateStr);
		}
		return result;
	}
}
