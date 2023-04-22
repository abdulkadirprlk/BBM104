import java.time.Duration;

/**
 * This is the manager class of the smart camera class.
 * It contains certain add, validate, remove methods etc.
 */
public class SmartCameraManager implements ISmartCameraManager {
	/**
	 * @param smartCamera smart camera object to be checked
	 * @return "" if the fields of the device is valid otherwise an error message to be printed
	 */
	@Override
	public String validate(SmartCamera smartCamera) {
		if (smartCamera.getMPM() <= 0) {
			return "ERROR: Megabyte value must be a positive number!\n";
		} else if (Main.deviceNames.contains(smartCamera.getName())) {
			return "ERROR: There is already a smart device with same name!\n";
		} else if (!(smartCamera.getState().equals("On") || smartCamera.getState().equals("Off"))) {
			return "ERROR: Erroneous command!\n";
		} else {
			return "";
		}
	}

	/**
	 * @param smartCamera smart camera object to be added
	 * @return the output of the validate method
	 */
	@Override
	public String add(SmartCamera smartCamera) {
		if (validate(smartCamera).equals("")) {
			Main.smartCameras.put(smartCamera.getName(), smartCamera);
			Main.deviceNames.add(smartCamera.getName());
			Main.allDevices.put(smartCamera.getName(), smartCamera.getSwitchTime());
			return "";
		} else {
			return validate(smartCamera);
		}
	}

	/**
	 * @param name name of the device
	 * @return An informative message if a device is removed and an error message if there is an issue
	 */
	@Override
	public String remove(String name) {
		String result = "";
		SmartCamera smartCamera = Main.smartCameras.get(name);
		//information displaying
		result += String.format("SUCCESS: Information about removed smart device is as follows:\n" +
						"Smart Camera %s is %s and used %.2f MB of storage so far (excluding current status)," +
						" and its time to switch its status is %s.\n", smartCamera.getName(),
				smartCamera.getState(), smartCamera.getOccupiedStorage(), smartCamera.getSwitchTimeStr());
		//removing
		Main.smartCameras.remove(name);
		Main.deviceNames.remove(smartCamera.getName());
		return result;
	}

	/**
	 * @param name name of the device
	 * @param state state of the device
	 * @return An error message if there is an issue about switching the device on or off
	 */
	@Override
	public String _switch(String name, String state) {
		SmartCamera smartCamera = Main.smartCameras.get(name);
		if (smartCamera.getState().equals(state) && state.equals("Off")) {
			return "ERROR: This device is already switched off!\n";
		} else if (smartCamera.getState().equals(state) && state.equals("On")) {
			return "ERROR: This device is already switched on!\n";
		} else if (!(state.equals("Off") || state.equals("On"))) {
			return "Erroneous Command!\n";
		} else {
			smartCamera.setState(state);
			return "";
		}
	}

	/**
	 * @param name name of the device
	 * @param newName new name value of the device
	 * @return An error message if there is an issue about changed the device
	 */
	@Override
	public String changeName(String name, String newName) {
		SmartCamera smartCamera = Main.smartCameras.get(name);
		if (Main.deviceNames.contains(newName) && !name.equals(smartCamera.getName())) {
			return "ERROR: There is already a smart device with same name!\n";
		} else if (Main.deviceNames.contains(newName) && name.equals(smartCamera.getName())) {
			return "ERROR: Both of the names are the same, nothing changed!\n";
		} else {
			smartCamera.setName(newName);
			return "";
		}
	}

	/**
	 * switch the device on or off if its switch time is came
	 */
	@Override
	public void switchByTime() {
		for (String name : Main.smartCameras.keySet()) {
			if (Main.smartCameras.get(name).getSwitchTime() == null) {
				continue;
			}
			if (Main.smartCameras.get(name).getSwitchTime().compareTo(Main.time.getPresentTime()) <= 0) {
				if (Main.smartCameras.get(name).getState().equals("Off")) {
					Main.smartCameras.get(name).setState("On");
					Main.smartCameras.get(name).setSwitchTime(null);
					Main.allDevices.put(name, Main.smartCameras.get(name).getSwitchTime());
				} else {
					Main.smartCameras.get(name).setState("On");
					Main.smartCameras.get(name).setSwitchTime(null);
					Main.allDevices.put(name, Main.smartCameras.get(name).getSwitchTime());
				}
			}
		}
	}
	public double calculateOccupiedStorage(SmartCamera smartCamera, Time time) {
		double result;
		if (smartCamera.getState().equals("On") && !(smartCamera.getSwitchTime() == null)) {
			Duration duration = Duration.between(time.getPresentTime(), smartCamera.getSwitchTime());
			duration.abs();
			result = duration.toMinutes() * smartCamera.getMPM();
			return result;
		} else {
			return 0;
		}
	}
}
