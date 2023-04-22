/**
 * This class implements a smart lamp whose brightness can be adjustable.
 */
public class SmartLamp extends BaseDevice {
    private int kelvin;
    private int brightness;

    /**
     * no argument constructor
     */
    public SmartLamp() {
        setName(name);
        state = "Off";
        kelvin = 4000;
        brightness = 100;
    }

    /**
     * @param name name of the smart lamp
     */
    public SmartLamp(String name) {
        setName(name);
        state = "Off";
        kelvin = 4000;
        brightness = 100;
    }

    /**
     * @param name name of the smart lamp
     * @param state state of the smart lamp
     */
    public SmartLamp(String name, String state) {
        setName(name);
        setState(state);
        kelvin = 4000;
        brightness = 100;
    }

    /**
     * @param name name of the smart lamp
     * @param state state of the smart lamp
     * @param kelvin kelvin value of the smart lamp
     * @param brightness brightness value of the smart lamp
     */
    public SmartLamp(String name, String state, int kelvin, int brightness) {
        setName(name);
        setState(state);
        setKelvin(kelvin);
        setBrightness(brightness);
    }


    /**
     * @return the kelvin value of the smart lamp
     */
    public int getKelvin() {
        return kelvin;
    }

    /**
     * @return the brightness value of the smart lamp
     */
    public int getBrightness() {
        return brightness;
    }

    /**
     * @param kelvin kelvin value of the smart lamp
     */
    public void setKelvin(int kelvin) {
        this.kelvin = kelvin;
    }

    /**
     * @param brightness brightness value of the smart lamp
     */
    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

}
