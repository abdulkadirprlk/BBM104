/**
 * This class implements a smart lamp but with a color mode.
 */
public class SmartColorLamp extends SmartLamp{
    private long colorCode;

    /**
     * Assigns default values for properties.
     * @param name name of the smart color lamp
     */
    public SmartColorLamp(String name) {
        setName(name);
    }

    /**
     * @param name name of the smart color lamp
     * @param state state of the smart color lamp
     */
    public SmartColorLamp(String name, String state) {
        setName(name);
        setState(state);
    }

    /**
     * @param name name of the smart color lamp
     * @param state state of the smart color lamp
     * @param kelvin kelvin value of the smart color lamp
     * @param brightness brightness value of the smart color lamp
     */
    public SmartColorLamp(String name, String state, int kelvin, int brightness) {
        setName(name);
        setState(state);
        setKelvin(kelvin);
        setBrightness(brightness);
    }

    /**
     * @param name name of the smart color lamp
     * @param state state of the smart color lamp
     * @param colorCode color code of the smart color lamp
     * @param brightness brightness value of the smart color lamp
     */
    public SmartColorLamp(String name, String state, long colorCode, int brightness) {
        setName(name);
        setState(state);
        setColorCode(colorCode);
        setBrightness(brightness);
    }

    /**
     * @return color code of the smart lamp with color
     */
    public long getColorCode() {
        return colorCode;
    }

    /**
     * @param colorCode color code of the smart color lamp
     */
    public void setColorCode(long colorCode) {
        this.colorCode = colorCode;
    }
}
