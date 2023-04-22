import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 The Time class represents a time interval between an initial time and a present time, both of which are
 stored as LocalDateTime objects. Additionally, the initial and present times can also be represented as
 formatted strings.
 */
public class Time {
	// The initial time as a LocalDateTime object and a formatted string
	private LocalDateTime initialTime;
	private String initialTimeStr;
	private LocalDateTime presentTime;
	private String presentTimeStr;
	/**
	 Default constructor for the Time class.
	 */
	public Time(){
	}
	/**
	 Returns the formatted string representation of the initial time.
	 @return The formatted string representation of the initial time.
	 */
	public String getInitialTimeStr() {
		return initialTimeStr;
	}
	/**

	 Returns the formatted string representation of the present time.
	 @return The formatted string representation of the present time.
	 */
	public String getPresentTimeStr() {
		return presentTimeStr;
	}
	/**

	 Sets the formatted string representation of the present time.
	 @param presentTimeStr The formatted string representation of the present time.
	 */
	public void setPresentTimeStr(String presentTimeStr) {
		this.presentTimeStr = presentTimeStr;
	}
	/**

	 Sets the formatted string representation of the initial time.
	 @param initialTimeStr The formatted string representation of the initial time.
	 */
	public void setInitialTimeStr(String initialTimeStr) {
		this.initialTimeStr = initialTimeStr;
	}
	/**

	 Returns the initial time as a LocalDateTime object.
	 @return The initial time as a LocalDateTime object.
	 */
	public LocalDateTime getInitialTime() {
		return initialTime;
	}
	/**

	 Sets the initial time as a LocalDateTime object and updates the formatted string representation.
	 @param initialTime The initial time as a LocalDateTime object.
	 */
	public void setInitialTime(LocalDateTime initialTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");
		setInitialTimeStr(initialTime.format(formatter));
		this.initialTime = initialTime;
	}
	/**

	 Returns the present time as a LocalDateTime object.
	 @return The present time as a LocalDateTime object.
	 */
	public LocalDateTime getPresentTime() {
		return presentTime;

	}
	/**

	 Sets the present time as a LocalDateTime object and updates the formatted string representation.
	 @param presentTime The present time as a LocalDateTime object.
	 */
	public void setPresentTime(LocalDateTime presentTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");
		setPresentTimeStr(presentTime.format(formatter));
		this.presentTime = presentTime;

	}
}
