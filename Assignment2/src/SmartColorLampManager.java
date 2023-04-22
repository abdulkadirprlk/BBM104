/**
 Implementation of the {@link ISmartColorLampManager} interface for managing Smart Color Lamps.
 */
public class SmartColorLampManager implements ISmartColorLampManager {
	/**

	 Validates the given Smart Color Lamp object and returns an error message if it does not meet the requirements.
	 @param smartColorLamp the Smart Color Lamp to be validated
	 @return an error message if the Smart Color Lamp does not meet the requirements, an empty string otherwise
	 */
	@Override
	public String validate(SmartColorLamp smartColorLamp) {
		if (!(smartColorLamp.getKelvin() >= 2000 && smartColorLamp.getKelvin() <= 6500)) {
			return "ERROR: Kelvin value must be in range of 2000K-6500K!\n";
		} else if (!(smartColorLamp.getColorCode() >= 0x0 && smartColorLamp.getColorCode() <= 0xFFFFFF)) {
			return "ERROR: Color code value must be in range of 0x0-0xFFFFFF!\n";
		} else if (!(smartColorLamp.getBrightness() >= 0 && smartColorLamp.getBrightness() <= 100)) {
			return "ERROR: Brightness must be in range of 0%-100%!\n";
		} else if (Main.deviceNames.contains(smartColorLamp.getName())) {
			return "ERROR: There is already a smart device with same name!\n";
		} else if (!(smartColorLamp.getState().equals("On") || smartColorLamp.getState().equals("Off"))) {
			return "ERROR: Erroneous command!\n";
		} else {
			return "";
		}
	}
	/**

	 Adds the given Smart Color Lamp to the system if it passes validation, and returns an error message otherwise.
	 @param smartColorLamp the Smart Color Lamp to be added
	 @return an empty string if the Smart Color Lamp is successfully added, or an error message if it does not pass validation
	 */
	@Override
	public String add(SmartColorLamp smartColorLamp) {
		if (validate(smartColorLamp).equals("")) {
			Main.smartColorLamps.put(smartColorLamp.getName(), smartColorLamp);
			Main.deviceNames.add(smartColorLamp.getName());
			Main.allDevices.put(smartColorLamp.getName(), smartColorLamp.getSwitchTime());
			return "";
		} else {
			return validate(smartColorLamp);
		}
	}
	/**

	 Removes the Smart Color Lamp with the given name from the system, and returns information about the removed device.
	 @param name the name of the Smart Color Lamp to be removed
	 @return information about the removed Smart Color Lamp if it exists, an empty string otherwise
	 */
	@Override
	public String remove(String name) {
		String result = "";
		SmartColorLamp smartColorLamp = Main.smartColorLamps.get(name);
		//information displaying
		result += String.format("SUCCESS: Information about removed smart device is as follows:\n" +
						"Smart Color Lamp %s is %s and its kelvin value is %dK with %d brightness," +
						" and its time to switch its status is %s.\n", smartColorLamp.getName(),
				smartColorLamp.getState(), smartColorLamp.getKelvin(), smartColorLamp.getBrightness(),
				smartColorLamp.getSwitchTimeStr());
		//removing
		Main.smartLamps.remove(name);
		Main.deviceNames.remove(smartColorLamp.getName());
		return result;
	}
	/**

	 This method is used to switch a SmartColorLamp on or off.
	 @param name - The name of the SmartColorLamp to be switched on or off.
	 @param state - The desired state of the SmartColorLamp, either "On" or "Off".
	 @return A string indicating the success or failure of the operation.
	 */
	@Override
	public String _switch(String name, String state) {
		SmartColorLamp smartColorLamp = Main.smartColorLamps.get(name);
		if (smartColorLamp.getState().equals(state) && state.equals("Off")) {
			return "ERROR: This device is already switched off!\n";
		} else if (smartColorLamp.getState().equals(state) && state.equals("On")) {
			return "ERROR: This device is already switched on!\n";
		} else if (!(state.equals("Off") || state.equals("On"))) {
			return "Erroneous Command!\n";
		} else {
			smartColorLamp.setState(state);
			return "";
		}
	}
	/**

	This method is used to change the name of a SmartColorLamp.
	@param name - The current name of the SmartColorLamp to be renamed.
	@param newName - The new name to give the SmartColorLamp.
	@return A string indicating the success or failure of the operation.
	 */
	@Override
	public String changeName(String name, String newName) {
		SmartColorLamp smartColorLamp = Main.smartColorLamps.get(name);
		if (Main.deviceNames.contains(newName) && !name.equals(smartColorLamp.getName())) {
			return "ERROR: There is already a smart device with same name!\n";
		} else if (Main.deviceNames.contains(newName) && name.equals(smartColorLamp.getName())) {
			return "ERROR: Both of the names are the same, nothing changed!\n";
		} else {
			smartColorLamp.setName(newName);
			return "";
		}
	}
	/**

	 This method is used to switch SmartColorLamps on or off automatically based on their scheduled switch time.
	 */
	@Override
	public void switchByTime() {
		for (String name : Main.smartColorLamps.keySet()) {
			if (Main.smartColorLamps.get(name).getSwitchTime() == null) {
				continue;
			}
			if (Main.smartColorLamps.get(name).getSwitchTime().compareTo(Main.time.getPresentTime()) <= 0) {
				if (Main.smartColorLamps.get(name).getState().equals("Off")) {
					Main.smartColorLamps.get(name).setState("On");
					Main.smartColorLamps.get(name).setSwitchTime(null);
					Main.allDevices.put(name, Main.smartColorLamps.get(name).getSwitchTime());
				} else {
					Main.smartColorLamps.get(name).setState("On");
					Main.smartColorLamps.get(name).setSwitchTime(null);
					Main.allDevices.put(name, Main.smartColorLamps.get(name).getSwitchTime());
				}
			}
		}
	}
	/**
	 * Sets the Kelvin value for a SmartColorLamp.
	 *
	 * @param name The name of the SmartColorLamp.
	 * @param kelvin The Kelvin value to set.
	 * @return An empty string if successful, or an error message if the Kelvin value is out of range.
	 */
	@Override
	public String setKelvin(String name, int kelvin) {
		SmartColorLamp smartColorLamp = Main.smartColorLamps.get(name);
		if (!(kelvin >= 2000 && kelvin <= 6500)) {
			return "ERROR: Kelvin value must be in range of 2000K-6500K!\n";
		} else {
			smartColorLamp.setKelvin(kelvin);
			return "";
		}
	}
	/**
	 * Sets the brightness for a SmartColorLamp.
	 *
	 * @param name The name of the SmartColorLamp.
	 * @param brightness The brightness value to set.
	 * @return An empty string if successful, or an error message if the brightness value is out of range.
	 */
	@Override
	public String setBrightness(String name, int brightness) {
		SmartColorLamp smartColorLamp = Main.smartColorLamps.get(name);
		if (!(brightness >= 2000 && brightness <= 6500)) {
			return "ERROR: Brightness must be in range of 0%-100%!\n";
		} else {
			smartColorLamp.setBrightness(brightness);
			return "";
		}
	}
	/**
	 * Sets the color code for a SmartColorLamp.
	 *
	 * @param name The name of the SmartColorLamp.
	 * @param colorCode The color code to set.
	 * @return An empty string if successful, or an error message if the color code value is out of range.
	 */
	@Override
	public String setColorCode(String name, long colorCode) {
		SmartColorLamp smartColorLamp = Main.smartColorLamps.get(name);
		if (!(smartColorLamp.getColorCode() >= 0x0 && smartColorLamp.getColorCode() <= 0xFFFFFF)) {
			return "ERROR: Color code value must be in range of 0x0-0xFFFFFF!\n";
		} else {
			smartColorLamp.setColorCode(colorCode);
			return "";
		}
	}
	/**
	 * Sets the Kelvin value and brightness for a SmartColorLamp to create a white light.
	 *
	 * @param name The name of the SmartColorLamp.
	 * @param kelvin The Kelvin value to set.
	 * @param brightness The brightness value to set.
	 * @return An empty string if successful, or an error message if the Kelvin or brightness value is out of range.
	 */
	@Override
	public String setWhite(String name, int kelvin, int brightness) {
		SmartColorLamp smartColorLamp = Main.smartColorLamps.get(name);
		if (!(kelvin >= 2000 && kelvin <= 6500)) {
			return "ERROR: Kelvin value must be in range of 2000K-6500K!\n";
		} else if (!(brightness >= 2000 && brightness <= 6500)) {
			return "ERROR: Brightness must be in range of 0%-100%!\n";
		} else {
			smartColorLamp.setKelvin(kelvin);
			smartColorLamp.setBrightness(brightness);
			return "";
		}
	}
	/**
	 * Sets the color code and brightness for a SmartColorLamp to create a colored light.
	 *
	 * @param name The name of the SmartColorLamp.
	 * @param colorCode The color code to set.
	 * @param brightness The brightness value to set.
	 * @return An empty string if successful, or an error message if the color code or brightness value is out of range.
	 */
	@Override
	public String setColor(String name, long colorCode, int brightness) {
		SmartColorLamp smartColorLamp = Main.smartColorLamps.get(name);
		if (!(colorCode >= 0x0 && colorCode <= 0xFFFFFF)) {
			return "ERROR: Color code value must be in range of 0x0-0xFFFFFF!\n";
		} else if (!(brightness >= 2000 && brightness <= 6500)) {
			return "ERROR: Brightness must be in range of 0%-100%!\n";
		} else {
			smartColorLamp.setColorCode(colorCode);
			smartColorLamp.setBrightness(brightness);
			return "";
		}
	}
}
