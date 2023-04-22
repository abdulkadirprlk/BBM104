/**
 The ISmartColorLampManager interface extends the IBaseDeviceManager interface, and provides additional methods
 specific to managing SmartColorLamp devices.
 */
public interface ISmartColorLampManager extends IBaseDeviceManager{
	/**
	 * Validates a SmartColorLamp object to ensure it is in a valid state.
	 * @param smartColorLamp the SmartColorLamp object to validate
	 * @return a String indicating whether the SmartColorLamp object is valid or not
	 */
	String validate(SmartColorLamp smartColorLamp);

	/**
	 * Adds a SmartColorLamp device to the system.
	 * @param smartColorLamp the SmartColorLamp object to add to the system
	 * @return a String indicating whether the SmartColorLamp device was successfully added or not
	 */
	String add(SmartColorLamp smartColorLamp);

	/**
	 *Sets the kelvin value for a SmartColorLamp device.
	 * @param name the name of the SmartColorLamp device to set the kelvin value for
	 * @param kelvin the kelvin value to set for the SmartColorLamp device
	 * @return a String indicating whether the kelvin value was successfully set or not
	 */
	String setKelvin(String name, int kelvin);

	/**

	 Sets the brightness value for a SmartColorLamp device.
	 @param name the name of the SmartColorLamp device to set the brightness value for
	 @param brightness the brightness value to set for the SmartColorLamp device
	 @return a String indicating whether the brightness value was successfully set or not
	 */
	String setBrightness(String name, int brightness);
	/**

	 Sets the color code for a SmartColorLamp device.
	 @param name the name of the SmartColorLamp device to set the color code for
	 @param colorCode the color code to set for the SmartColorLamp device
	 @return a String indicating whether the color code was successfully set or not
	 */
	String setColorCode(String name, long colorCode);
	/**

	 Sets the kelvin and brightness values for a SmartColorLamp device to create a white color.
	 @param name the name of the SmartColorLamp device to set the white color for
	 @param kelvin the kelvin value to set for the SmartColorLamp device to create a white color
	 @param brightness the brightness value to set for the SmartColorLamp device to create a white color
	 @return a String indicating whether the white color was successfully set or not
	 */
	String setWhite(String name, int kelvin, int brightness);
	/**

	 Sets the color code and brightness values for a SmartColorLamp device to create a color.
	 @param name the name of the SmartColorLamp device to set the color for
	 @param colorCode the color code to set for the SmartColorLamp device to create a color
	 @param brightness the brightness value to set for the SmartColorLamp device to create a color
	 @return a String indicating whether the color was successfully set or not
	 */
	String setColor(String name, long colorCode, int brightness);
}
