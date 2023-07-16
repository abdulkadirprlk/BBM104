import java.time.LocalDate;

public class ReadHistory {
	private int bookId;
	private int libraryMemberId;
	private LocalDate readDate;
	private LocalDate returnDate;

	public ReadHistory(int bookId, int libraryMemberId, LocalDate readDate) {
		this.bookId = bookId;
		this.readDate = readDate;
		this.libraryMemberId = libraryMemberId;
	}

	public LocalDate getReadDate() {
		return readDate;
	}

	public void setReadDate(LocalDate readDate) {
		this.readDate = readDate;
	}

	public int getLibraryMemberId() {
		return libraryMemberId;
	}

	public void setLibraryMemberId(int libraryMemberId) {
		this.libraryMemberId = libraryMemberId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}
}
