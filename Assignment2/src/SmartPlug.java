/**
 * This class implements a smart plug which can be switched on/off.
 */
public class SmartPlug {
    private String plugState;
    private String switchState;
    private int voltage;
    private int ampere;

    /**
     * Assigns default values for properties.
     * @param ampere the ampere value of the SmartPlug
     */
    public SmartPlug(int ampere){
        plugState = "out";
        switchState = "off";
        voltage = 220;
        this.ampere = ampere;

    }
}
