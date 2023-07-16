import java.time.LocalDate;

public class BorrowHistory {
	private int bookId;
	private int libraryMemberId;
	private LocalDate borrowDate;
	private LocalDate returnDate;
	private boolean isExtended;
	public BorrowHistory(int bookId, int libraryMemberId, LocalDate borrowDate){
		this.bookId = bookId;
		this.borrowDate = borrowDate;
		this.libraryMemberId = libraryMemberId;
		this.isExtended = false;
	}

	public LocalDate getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(LocalDate borrowDate) {
		this.borrowDate = borrowDate;
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

	public boolean isExtended() {
		return isExtended;
	}

	public void setExtended(boolean extended) {
		isExtended = extended;
	}
}
