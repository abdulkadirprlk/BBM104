/**
 Interface for managing smart plugs.
 */
public interface ISmartPlugManager extends IBaseDeviceManager{
	/**

	 Validates a smart plug object.
	 @param smartPlug The smart plug object to be validated.
	 @return An error message if the object is not valid, otherwise an empty string.
	 */
	String validate(SmartPlug smartPlug);
	/**

	 Adds a new smart plug to the system.
	 @param smartPlug The smart plug object to be added.
	 @return An error message if the smart plug could not be added, otherwise an empty string.
	 */
	String add(SmartPlug smartPlug);
	/**

	 Calculates the consumed energy by a smart plug within a specified time frame.
	 @param smartPlug The smart plug for which the energy consumption is calculated.
	 @param time The time frame for which the energy consumption is calculated.
	 @return The consumed energy in kWh.
	*/
	double calculateConsumedEnergy(SmartPlug smartPlug, Time time);
	/**

	 Plugs in a smart plug.
	 @param name The name of the smart plug to be plugged in.
	 @param ampere The maximum current in ampere that the plug can handle.
	 @return An error message if the smart plug could not be plugged in, otherwise an empty string.
	 */
	String plugIn(String name, int ampere);
	/**

	 Plugs out a smart plug.
	 @param name The name of the smart plug to be unplugged.
	 @return An error message if the smart plug could not be unplugged, otherwise an empty string.
	 */
	String plugOut(String name);


}
