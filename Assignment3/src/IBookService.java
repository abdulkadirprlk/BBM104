import java.time.LocalDate;

public interface IBookService {
	String addBook(String type);
	String extendBook(int idOfBook, int idOfLibraryMember, LocalDate currentDate);
	String returnBook(int idOfBook, int idOfLibraryMember, LocalDate returnDate);
	String borrowBook(int idOfBook, int idOfLibraryMember, LocalDate borrowDate);
	String readInLibrary(int idOfBook, int idOfLibraryMember, LocalDate readDate);
	String getTheHistory();
}
