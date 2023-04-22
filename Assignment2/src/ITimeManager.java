/**
 An interface for managing time-related operations in a smart home system.
 */
public interface ITimeManager {
	/**

	 Sets the initial time of the system.
	 @param initialTimeStr A string representing the initial time in the format "HH:mm".
	 */
	void setInitialTime(String initialTimeStr);
	/**

	 Skips the specified number of minutes in the system's time.
	 @param timeToSkipped The number of minutes to skip.
	 @return A string message indicating whether the operation was successful.
	 */
	String skipMinutes(int timeToSkipped);
	/**

	 Sets a switch time for a specified smart plug.
	 @param name The name of the smart plug to set the switch time for.
	 @param switchTimeStr A string representing the switch time in the format "HH:mm".
	 @return A string message indicating whether the operation was successful.
	 */
	String setSwitchTime(String name, String switchTimeStr);
	/**

	 Sets the present time of the system.
	 @param presentTime A string representing the present time in the format "HH:mm".
	 @return A string message indicating whether the operation was successful.
	 */
	String setTime(String presentTime);
	/**

	 Performs a "no operation" operation, used for testing purposes.
	 @return A string message indicating whether the operation was successful.
	 */
	String nop();
	/**

	 Generates a report of the current state of the system, including energy consumption and device statuses.
	 @return A string containing the system report.
	 */
	String zReport();
}
