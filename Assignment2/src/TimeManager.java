import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
/**

 This class implements the ITimeManager interface and provides methods for managing time in the smart home system.
 */
public class TimeManager implements ITimeManager {
	/**

	 Sets the initial time of the system to the given time.
	 @param initialTimeStr the string representation of the initial time in "yyyy-MM-dd_HH:mm:ss" format
	 */
	@Override
	public void setInitialTime(String initialTimeStr) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");
		Main.time.setInitialTime(LocalDateTime.parse(initialTimeStr, formatter));
		Main.time.setInitialTimeStr(initialTimeStr);
		Main.time.setPresentTime(LocalDateTime.parse(initialTimeStr, formatter));
		Main.time.setPresentTimeStr(initialTimeStr);
	}
	/**

	 Skips the given number of minutes from the current time.
	 @param timeToSkipped the number of minutes to skip
	 @return an empty string if successful, an error message otherwise
	 */
	@Override
	public String skipMinutes(int timeToSkipped) {
		if (timeToSkipped > 0) {
			Main.time.setPresentTime(Main.time.getPresentTime().plusMinutes(timeToSkipped));
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");
			Main.time.setPresentTimeStr(Main.time.getPresentTime().format(formatter));
			return "";
		} else if (timeToSkipped == 0) {
			return "ERROR: There is nothing to skip!\n";
		} else {
			return "ERROR: Time cannot be reversed!\n";
		}
	}
	/**

	 Sets the switch time of the specified device to the given time.
	 @param name the name of the device
	 @param switchTimeStr the string representation of the switch time in "yyyy-MM-dd_HH:mm:ss" format
	 @return an empty string if successful, an error message otherwise
	 */
	@Override
	public String setSwitchTime(String name, String switchTimeStr) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");
			LocalDateTime switchTime = LocalDateTime.parse(switchTimeStr, formatter);//switchTime -> LocalDateTime

			if(switchTime.isBefore(Main.time.getPresentTime())){
				return "ERROR: Switch time cannot be in the past!\n";
			}
			if (!Main.deviceNames.contains(name)) {
				return "ERROR: There is not such a device!\n";
			} else {//There is such a device:
				if (Main.smartLamps.containsKey(name)) {
					Main.smartLamps.get(name).setSwitchTime(switchTime);
					Main.allDevices.put(name, Main.smartLamps.get(name).getSwitchTime());
					return "";
				} else if (Main.smartColorLamps.containsKey(name)) {
					Main.smartColorLamps.get(name).setSwitchTime(switchTime);
					Main.allDevices.put(name, Main.smartColorLamps.get(name).getSwitchTime());
					return "";
				} else if (Main.smartCameras.containsKey(name)) {
					Main.smartCameras.get(name).setSwitchTime(switchTime);
					Main.allDevices.put(name, Main.smartCameras.get(name).getSwitchTime());
					return "";
				} else if (Main.smartPlugs.containsKey(name)) {
					Main.smartPlugs.get(name).setSwitchTime(switchTime);
					Main.allDevices.put(name, Main.smartPlugs.get(name).getSwitchTime());
					return "";
				}
				return "";
			}
		} catch (Exception e) {
			return "Date format is wrong!\n";
		}
	}
	/**
	 * Sets the present time of the system. Validates the input time string and checks if it's not before the initial time.
	 * If the input is valid, sets the present time and updates the present time string.
	 * @param presentTimeStr A string representing the present time in the format "yyyy-MM-dd_HH:mm:ss".
	 * @return An empty string if the time was successfully set, or an error message if the time format is wrong or if the time is before the initial time.
	 */
	@Override
	public String setTime(String presentTimeStr) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");
			LocalDateTime presentTime = LocalDateTime.parse(presentTimeStr, formatter);

			if (presentTime.isBefore(Main.time.getInitialTime())) {
				return "ERROR: Time cannot be reversed!\n";
			} else {
				Main.time.setPresentTime(presentTime);
				Main.time.setPresentTimeStr(presentTimeStr);
				return "";
			}
		} catch (Exception e) {
			return "Time format is wrong!\n";
		}
	}
	/**
	 * Switches the state of the device that is scheduled to switch next based on its switch time.
	 * If there are no devices to switch, returns an error message.
	 * @return An empty string if a device was successfully switched, or an error message if there are no devices to switch.
	 */
	@Override
	public String nop() {
		LocalDateTime minDate = null;
		for (LocalDateTime date:Main.allDevices.values()) {
			if(date == null){
				continue;
			}
			if(date.compareTo(Main.time.getPresentTime()) == 0){
				continue;
			}
			if (minDate == null || date.compareTo(minDate) <= 0) {
				minDate = date;
			}
		}
		if (minDate == null) {
			return "ERROR: There is nothing to switch!\n";
		} else {
			Main.time.setPresentTime(minDate);
			return "";
		}
	}
	/**
	 * Generates a report of all devices and their statuses in a specific format.
	 * Devices are listed in two groups: those with a scheduled switch time and those without.
	 * Devices are sorted in ascending order of their switch times for the first group.
	 * Devices without switch times are sorted in ascending order of their creation time.
	 * @return A string containing the report of all devices and their statuses.
	 */
	@Override
	public String zReport() {//first the ones that have a switchTime, then the ones with null. nulls sort by creation time (stable sort)
		SmartLamp smartLamp = null;
		SmartColorLamp smartColorLamp = null;
		SmartCamera smartCamera = null;
		SmartPlug smartPlug = null;
		String result = "";

		List<String> nulls = new ArrayList();//keeps the ones with no switchTimes
		List<String> notNulls = new ArrayList();//keeps the ones with switchTimes

		for(String name:Main.allDevices.keySet()){
			if(Main.allDevices.get(name) != null){
				notNulls.add(name);
			}else{
				nulls.add(name);
			}
		}

		for(String name:notNulls){//with switchTimes
			if(Main.smartLamps.containsKey(name)){//SmartLamp
				smartLamp = Main.smartLamps.get(name);
				result += String.format("Smart Lamp %s is %s and its kelvin value is %dK with %d  brightness," +
								" and its time to switch its status is %s.\n",
						smartLamp.getName(), smartLamp.getState(), smartLamp.getKelvin(), smartLamp.getBrightness(),
						smartLamp.getSwitchTimeStr());
			}else if(Main.smartColorLamps.containsKey(name)){//SmartColorLamp
				smartColorLamp = Main.smartColorLamps.get(name);
				if(smartColorLamp.getKelvin() == 0){
					result += String.format("Smart Color Lamp %s is %s and its color value is %d with %d  brightness," +
									" and its time to switch its status is %s.\n",
							smartColorLamp.getName(), smartColorLamp.getState(), smartColorLamp.getColorCode(),
							smartColorLamp.getBrightness(), smartColorLamp.getSwitchTimeStr());
				}else{
					result += String.format("Smart Color Lamp %s is %s and its color value is %dK with %d  brightness," +
									" and its time to switch its status is %s.\n",
							smartColorLamp.getName(), smartColorLamp.getState(), smartColorLamp.getKelvin(),
							smartColorLamp.getBrightness(), smartColorLamp.getSwitchTimeStr());
				}
			}else if(Main.smartCameras.containsKey(name)){//SmartCamera
				smartCamera = Main.smartCameras.get(name);
				result += String.format("Smart Camera %s is %s and used %.2fMB of storage so far" +
						" (excluding current status), and its time to switch its status is %s.\n", smartCamera.getName(),
						smartCamera.getState(), smartCamera.getOccupiedStorage(), smartCamera.getSwitchTimeStr());
			}else if(Main.smartPlugs.containsKey(name)){//SmartPlug
				smartPlug = Main.smartPlugs.get(name);
				result += String.format("Smart Plug %s is %s and consumed %.2fW so far (excluding current device)," +
								" and its time to switch its status is %s.\n", smartPlug.getName(),
						smartPlug.getState(), smartPlug.getEnergyConsumption(), smartPlug.getSwitchTimeStr());
			}
		}
		for(String name:nulls){//null switchTimes
			if(Main.smartLamps.containsKey(name)){//SmartLamp
				smartLamp = Main.smartLamps.get(name);
				result += String.format("Smart Lamp %s is %s and its kelvin value is %dK with %d  brightness," +
								" and its time to switch its status is %s.\n",
						smartLamp.getName(), smartLamp.getState(), smartLamp.getKelvin(), smartLamp.getBrightness(),
						smartLamp.getSwitchTimeStr());
			}else if(Main.smartColorLamps.containsKey(name)){//SmartColorLamp
				smartColorLamp = Main.smartColorLamps.get(name);
				if(smartColorLamp.getKelvin() == 0){
					result += String.format("Smart Color Lamp %s is %s and its color value is %d with %d  brightness," +
									" and its time to switch its status is %s.\n",
							smartColorLamp.getName(), smartColorLamp.getState(), smartColorLamp.getColorCode(),
							smartColorLamp.getBrightness(), smartColorLamp.getSwitchTimeStr());
				}else{
					result += String.format("Smart Color Lamp %s is %s and its color value is %dK with %d  brightness," +
									" and its time to switch its status is %s.\n",
							smartColorLamp.getName(), smartColorLamp.getState(), smartColorLamp.getKelvin(),
							smartColorLamp.getBrightness(), smartColorLamp.getSwitchTimeStr());
				}
			}else if(Main.smartCameras.containsKey(name)){//SmartCamera
				smartCamera = Main.smartCameras.get(name);
				result += String.format("Smart Camera %s is %s and used %.2fMB of storage so far" +
								" (excluding current status), and its time to switch its status is %s.\n", smartCamera.getName(),
						smartCamera.getState(), smartCamera.getOccupiedStorage(), smartCamera.getSwitchTimeStr());
			}else if(Main.smartPlugs.containsKey(name)){//SmartPlug
				smartPlug = Main.smartPlugs.get(name);
				result += String.format("Smart Plug %s is %s and consumed %.2fW so far (excluding current device)," +
								" and its time to switch its status is %s.\n", smartPlug.getName(),
						smartPlug.getState(), smartPlug.getEnergyConsumption(), smartPlug.getSwitchTimeStr());
			}
		}
		return result;
	}
}

