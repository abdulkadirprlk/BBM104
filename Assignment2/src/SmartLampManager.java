/**
 SmartLampManager class implements ISmartLampManager interface.
 It manages the smart lamps in the system and provides functionalities such as adding, removing, switching,
 changing name, setting kelvin value and brightness value.
 */
public class SmartLampManager implements ISmartLampManager {
	/**

	 Validates if the given SmartLamp object is valid or not.
	 @param smartLamp the SmartLamp object to be validated
	 @return an empty string if the SmartLamp is valid, an error message otherwise
	 */
	@Override
	public String validate(SmartLamp smartLamp) {
		if (!(smartLamp.getKelvin() >= 2000 && smartLamp.getKelvin() <= 6500)) {
			return "ERROR: Kelvin value must be in range of 2000K-6500K!\n";
		} else if (!(smartLamp.getBrightness() >= 0 && smartLamp.getBrightness() <= 100)) {
			return "ERROR: Brightness must be in range of 0%-100%!\n";
		} else if (Main.deviceNames.contains(smartLamp.getName())) {
			return "ERROR: There is already a smart device with same name!\n";
		} else if (!(smartLamp.getState().equals("On") || smartLamp.getState().equals("Off"))) {
			return "ERROR: Erroneous command!\n";
		} else {
			return "";
		}
	}
	/**

	 Adds the given SmartLamp object to the system.
	 @param smartLamp the SmartLamp object to be added
	 @return an empty string if the SmartLamp is added successfully, an error message otherwise
	 */
	@Override
	public String add(SmartLamp smartLamp) {
		if (validate(smartLamp).equals("")) {
			Main.smartLamps.put(smartLamp.getName(), smartLamp);
			Main.deviceNames.add(smartLamp.getName());
			Main.allDevices.put(smartLamp.getName(), smartLamp.getSwitchTime());
			return "";
		} else {
			return validate(smartLamp);
		}
	}
	/**

	 Removes the SmartLamp object with the given name from the system.
	 @param name the name of the SmartLamp object to be removed
	 @return a string with information about the removed SmartLamp object if it was successfully removed,
	 an error message otherwise
	 */
	@Override
	public String remove(String name) {
		String result = "";
		SmartLamp smartLamp = Main.smartLamps.get(name);
		//information displaying
		result += String.format("SUCCESS: Information about removed smart device is as follows:\n" +
						"Smart Lamp %s is %s and its kelvin value is %dK with %d brightness," +
						" and its time to switch its status is %s.\n", smartLamp.getName(),
				smartLamp.getState(), smartLamp.getKelvin(), smartLamp.getBrightness(), smartLamp.getSwitchTimeStr());
		//removing
		Main.smartLamps.remove(name);
		Main.deviceNames.remove(smartLamp.getName());
		return result;
	}
	/**

	 Switches the SmartLamp object with the given name to the given state.
	 @param name the name of the SmartLamp object to be switched
	 @param state the state to which the SmartLamp object should be switched
	 @return an empty string if the SmartLamp is switched successfully, an error message otherwise
	 */
	@Override
	public String _switch(String name, String state) {
		SmartLamp smartLamp = Main.smartLamps.get(name);
		if (smartLamp.getState().equals(state) && state.equals("Off")) {
			return "ERROR: This device is already switched off!\n";
		} else if (smartLamp.getState().equals(state) && state.equals("On")) {
			return "ERROR: This device is already switched on!\n";
		} else {
			smartLamp.setState(state);
			return "";
		}
	}
	/**
	 * Changes the name of a smart lamp to a new name.
	 *
	 * @param name    the current name of the smart lamp
	 * @param newName the new name to assign to the smart lamp
	 * @return        an error message if the new name already exists or both names are the same,
	 *                otherwise an empty string
	 */
	@Override
	public String changeName(String name, String newName) {
		SmartLamp smartLamp = Main.smartLamps.get(name);
		if (Main.deviceNames.contains(newName) && !name.equals(smartLamp.getName())) {
			return "ERROR: There is already a smart device with same name!\n";
		} else if (Main.deviceNames.contains(newName) && name.equals(smartLamp.getName())) {
			return "ERROR: Both of the names are the same, nothing changed!\n";
		} else {
			smartLamp.setName(newName);
			return "";
		}
	}
	/**
	 * Switches on/off the smart lamps based on their set switch time.
	 */
	@Override
	public void switchByTime() {
		for (String name : Main.smartLamps.keySet()) {
			if (Main.smartLamps.get(name).getSwitchTime() == null) {
				continue;
			}
			if (Main.smartLamps.get(name).getSwitchTime().compareTo(Main.time.getPresentTime()) <= 0) {
				if (Main.smartLamps.get(name).getState().equals("Off")) {
					Main.smartLamps.get(name).setState("On");
					Main.smartLamps.get(name).setSwitchTime(null);
					Main.allDevices.put(name, Main.smartLamps.get(name).getSwitchTime());
				} else {
					Main.smartLamps.get(name).setState("On");
					Main.smartLamps.get(name).setSwitchTime(null);
					Main.allDevices.put(name, Main.smartLamps.get(name).getSwitchTime());
				}
			}
		}
	}
	/**
	 * Sets the kelvin value of a smart lamp to a new value.
	 *
	 * @param name     the name of the smart lamp to set the kelvin value for
	 * @param kelvin   the new kelvin value to set
	 * @return         an error message if the kelvin value is not in the range of 2000K-6500K,
	 *                 otherwise an empty string
	 */
	@Override
	public String setKelvin(String name, int kelvin) {
		SmartLamp smartLamp = Main.smartLamps.get(name);
		if (!(kelvin >= 2000 && kelvin <= 6500)) {
			return "ERROR: Kelvin value must be in range of 2000K-6500K!\n";
		} else {
			smartLamp.setKelvin(kelvin);
			return "";
		}
	}
	/**
	 * Sets the brightness of a smart lamp to a new value.
	 *
	 * @param name        the name of the smart lamp to set the brightness for
	 * @param brightness  the new brightness value to set (in the range of 0%-100%)
	 * @return            an error message if the brightness value is not in the range of 0%-100%,
	 *                    otherwise an empty string
	 */
	@Override
	public String setBrightness(String name, int brightness) {
		SmartLamp smartLamp = Main.smartLamps.get(name);
		if (!(brightness >= 2000 && brightness <= 6500)) {
			return "ERROR: Brightness must be in range of 0%-100%!\n";
		} else {
			smartLamp.setBrightness(brightness);
			return "";
		}
	}
}