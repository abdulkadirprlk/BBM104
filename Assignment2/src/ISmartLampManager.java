/**

 Interface for managing smart lamps, extends the IBaseDeviceManager interface.
 */
public interface ISmartLampManager extends IBaseDeviceManager{
    /**

     Validates the given SmartLamp object.
     @param smartLamp the SmartLamp object to validate
     @return an error message if the SmartLamp object is not valid, otherwise an empty string
     */
    String validate(SmartLamp smartLamp);
    /**

     Adds a SmartLamp object to the list of managed devices.
     @param smartLamp the SmartLamp object to add
     @return an error message if the SmartLamp object is not valid or could not be added, otherwise an empty string
     */
    String add(SmartLamp smartLamp);
    /**

     Sets the kelvin value of the SmartColorLamp with the given name.
     @param name the name of the SmartColorLamp to set the kelvin value of
     @param kelvin the kelvin value to set
     @return an error message if the kelvin value is not in range or the SmartColorLamp does not exist, otherwise an empty string
     */
    String setKelvin(String name, int kelvin);
    /**

     Sets the brightness value of the SmartColorLamp with the given name.
     @param name the name of the SmartColorLamp to set the brightness value of
     @param brightness the brightness value to set
     @return an error message if the brightness value is not in range or the SmartColorLamp does not exist, otherwise an empty string
     */
    String setBrightness(String name, int brightness);
}
