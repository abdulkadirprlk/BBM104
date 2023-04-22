import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class keeps the attributes that all devices have and methods that are identical for all devices.
 */
public class BaseDevice {
	protected String name;
	protected String state;
	protected LocalDateTime switchTime;
	protected String switchTimeStr;

	/**
	 * @return the name of the smart lamp
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the state of the smart lamp
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param name name of the smart device
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param state state of the smart device
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return returns the switch time of a device
	 */
	public LocalDateTime getSwitchTime() {
		return switchTime;
	}

	/**
	 * Sets the switch time of a device but
	 * at the same time sets the string form of it to switchTimeStr.
	 * @param switchTime switch time of a device
	 */
	public void setSwitchTime(LocalDateTime switchTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");
		if (switchTime == null) {
			setSwitchTimeStr("null");
		} else {
			setSwitchTimeStr(switchTime.format(formatter));
		}
		this.switchTime = switchTime;

    }

	/**
	 * @return the switch time of the device in the String format
	 */
	public String getSwitchTimeStr() {
		return switchTimeStr;
	}

	/**
	 * @param switchTimeStr the switch time of the device in the String format
	 */
	public void setSwitchTimeStr(String switchTimeStr) {
		this.switchTimeStr = switchTimeStr;
	}
}
