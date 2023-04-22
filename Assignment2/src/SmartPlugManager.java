import java.time.Duration;

/**
 * The SmartPlugManager class implements the ISmartPlugManager interface and provides methods
 * for managing smart plug devices.
 */
public class SmartPlugManager implements ISmartPlugManager {
	/**
	 * Validates the given smart plug to check if it can be added to the system.
	 *
	 * @param smartPlug the smart plug to validate
	 * @return an error message if the smart plug is not valid, otherwise an empty string
	 */
	@Override
	public String validate(SmartPlug smartPlug) {
		if (smartPlug.getAmpere() < 0) {//ampere
			return "ERROR: Ampere value must be a positive number!\n";
		} else if (Main.deviceNames.contains(smartPlug.getName())) {//name
			return "ERROR: There is already a smart device with same name!\n";
		} else if (!(smartPlug.getState().equals("On") || smartPlug.getState().equals("Off"))) {
			return "ERROR: Erroneous command!\n";
		} else {
			return "";
		}
	}

	/**
	 * Adds the given smart plug to the system.
	 *
	 * @param smartPlug the smart plug to add
	 * @return an error message if the smart plug is not valid, otherwise an empty string
	 */
	@Override
	public String add(SmartPlug smartPlug) {
		if (validate(smartPlug).equals("")) {
			Main.deviceNames.add(smartPlug.getName());
			Main.smartPlugs.put(smartPlug.getName(), smartPlug);
			Main.allDevices.put(smartPlug.getName(), smartPlug.getSwitchTime());
			return "";
		} else {
			return validate(smartPlug);
		}
	}

	/**
	 * Removes the smart plug with the given name from the system.
	 *
	 * @param name the name of the smart plug to remove
	 * @return a message indicating the success or failure of the operation, and information about the removed device
	 */
	@Override
	public String remove(String name) {
		String result = "";
		SmartPlug smartPlug = Main.smartPlugs.get(name);
		//information displaying
		result += String.format("SUCCESS: Information about removed smart device is as follows:\n" +
						"Smart Plug %s is %s and consumed %.2fW so far (excluding current device)," +
						" and its time to switch its status is %s.\n", smartPlug.getName(),
				smartPlug.getState(), smartPlug.getEnergyConsumption(), smartPlug.getSwitchTimeStr());
		//removing
		Main.smartPlugs.remove(name);
		Main.deviceNames.remove(smartPlug.getName());
		return result;
	}

	/**
	 * Switches the state of the smart plug with the given name to the given state.
	 *
	 * @param name  the name of the smart plug to switch
	 * @param state the state to switch to
	 * @return an error message if the switch cannot be performed, otherwise an empty string
	 */
	@Override
	public String _switch(String name, String state) {
		SmartPlug smartPlug = Main.smartPlugs.get(name);
		if (smartPlug.getState().equals(state) && state.equals("Off")) {
			return "ERROR: This device is already switched off!\n";
		} else if (smartPlug.getState().equals(state) && state.equals("On")) {
			return "ERROR: This device is already switched on!\n";
		} else {
			smartPlug.setState(state);
			return "";
		}
	}

	/**
	 * Changes the name of a SmartPlug device.
	 *
	 * @param name    The current name of the device.
	 * @param newName The new name for the device.
	 * @return An error message if the device with the new name already exists, or if the names are the same, otherwise an empty string.
	 */
	@Override
	public String changeName(String name, String newName) {
		SmartPlug smartPlug = Main.smartPlugs.get(name);
		if (Main.deviceNames.contains(newName) && !name.equals(smartPlug.getName())) {
			return "ERROR: There is already a smart device with same name!\n";
		} else if (Main.deviceNames.contains(newName) && name.equals(smartPlug.getName())) {
			return "ERROR: Both of the names are the same, nothing changed!\n";
		} else {
			smartPlug.setName(newName);
			return "";
		}
	}

	/**
	 * Switches SmartPlug devices on or off based on their scheduled switch time.
	 */
	@Override
	public void switchByTime() {
		for (String name : Main.smartPlugs.keySet()) {
			if (Main.smartPlugs.get(name).getSwitchTime() == null) {
				continue;
			}
			if (Main.smartPlugs.get(name).getSwitchTime().compareTo(Main.time.getPresentTime()) <= 0) {
				if (Main.smartPlugs.get(name).getState().equals("Off")) {
					Main.smartPlugs.get(name).setState("On");
					Main.smartPlugs.get(name).setSwitchTime(null);
					Main.allDevices.put(name, Main.smartPlugs.get(name).getSwitchTime());
				} else {
					Main.smartPlugs.get(name).setState("Off");
					Main.smartPlugs.get(name).setSwitchTime(null);
					Main.allDevices.put(name, Main.smartPlugs.get(name).getSwitchTime());
				}
			}
		}
	}

	/**
	 * Calculates the energy consumed by a SmartPlug device.
	 *
	 * @param smartPlug The SmartPlug device.
	 * @param time      The current time.
	 * @return The amount of energy consumed by the device.
	 */
	@Override
	public double calculateConsumedEnergy(SmartPlug smartPlug, Time time) {
		double result;
		if (smartPlug.getState().equals("On") && !(smartPlug.getSwitchTime() == null)
				&& smartPlug.getPlugState().equals("In")) {
			Duration duration = Duration.between(time.getPresentTime(), smartPlug.getSwitchTime());
			duration.abs();
			result = duration.toMinutes() * smartPlug.getAmpere() * smartPlug.getVoltage();
			return result;
		} else {
			return 0;
		}
	}

	/**
	 * Plugs an item into a SmartPlug device.
	 *
	 * @param name   The name of the SmartPlug device.
	 * @param ampere The ampere value of the item being plugged in.
	 * @return An error message if the device does not exist, the ampere value is negative, or there is already an item plugged in, otherwise an empty string.
	 */
	@Override
	public String plugIn(String name, int ampere) {
		if (Main.smartPlugs.containsKey(name)) {
			SmartPlug smartPlug = Main.smartPlugs.get(name);
			if (ampere <= 0) {
				return "ERROR: Ampere value must be a positive number!\n";
			} else if (smartPlug.getPlugState().equals("In")) {
				return "ERROR: There is already an item plugged in to that plug!\n";
			} else {
				smartPlug.setPlugState("In");
				smartPlug.setAmpere(ampere);
				return "";
			}
		} else {
			return "ERROR: This device is not a smart plug!\n";
		}
	}

	/**
	 * Plugs an item out of a SmartPlug device.
	 *
	 * @param name The name of the SmartPlug device.
	 * @return An error message if the device does not exist or there is no item to unplug, otherwise an empty string.
	 */
	@Override
	public String plugOut(String name) {
		if (Main.deviceNames.contains(name)) {
			SmartPlug smartPlug = Main.smartPlugs.get(name);
			if (smartPlug.getPlugState().equals("Out")) {
				return "ERROR: This plug has no item to plug out from that plug!\n";
			} else {
				smartPlug.setPlugState("Out");
				return "";
			}
		} else {
			return "ERROR: This device is not a smart plug!\n";
		}
	}
}
