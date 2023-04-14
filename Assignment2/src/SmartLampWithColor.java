/**
 * This class implements a smart lamp but with a color mode.
 */
public class SmartLampWithColor {
    private int kelvin;
    private int brightness;
    private int colorCode;

    /**
     * Assigns default values for properties.
     * @param colorCode the color code of the SmartLampWithColor
     */
    public SmartLampWithColor(int colorCode) {
        kelvin = 4000;
        brightness = 100;
        this.colorCode = colorCode;
    }


}
