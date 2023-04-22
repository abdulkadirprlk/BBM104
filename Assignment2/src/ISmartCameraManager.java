/**
 * This interface has the special methods for just smart cameras.
 */
public interface ISmartCameraManager extends IBaseDeviceManager{
	/**
	 * @param smartCamera smart camera object to be checked
	 * @return "" if the fields of the device is valid otherwise an error message to be printed
	 */
	String validate(SmartCamera smartCamera);

	/**
	 * @param smartCamera smart camera object to be added
	 * @return the output of the validate method
	 */
	String add(SmartCamera smartCamera);
	double calculateOccupiedStorage(SmartCamera smartCamera, Time time);
}
