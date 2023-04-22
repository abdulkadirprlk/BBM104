/**
 This is an interface called "IBaseDeviceManager" that defines the behavior of a device manager.
 */
public interface IBaseDeviceManager {
	/**
	 * @param name name of the device
	 * @return An informative message if a device is removed and an error message if there is an issue
	 */
	String remove(String name);

	/**
	 * @param name name of the device
	 * @param state state of the device
	 * @return An error message if there is an issue about switching the device on or off
	 */
	String _switch(String name, String state);

	/**
	 * @param name name of the device
	 * @param newName new name value of the device
	 * @return An error message if there is an issue about changed the device
	 */
	String changeName(String name, String newName);

	/**
	 * switch the device on or off if its switch time is came
	 */
	void switchByTime();
}
