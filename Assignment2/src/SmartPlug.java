/**
 * This class implements a smart plug which can be switched on/off.
 */
public class SmartPlug extends BaseDevice{
    private String plugState;
    private int voltage;
    private double ampere;
    private double energyConsumption;

    /**
     * @param name name of the smart plug
     */
    public SmartPlug(String name){
        setName(name);
        voltage = 220;
        state = "Off";
        plugState = "Out";
        energyConsumption = 0;
    }

    /**
     * @param name name of the smart plug
     * @param state state of the smart plug
     */
    public SmartPlug(String name, String state){
        setName(name);
        setState(state);
        voltage = 220;
        plugState = "Out";
        energyConsumption = 0;
    }

    /**
     * @param name name of the smart plug
     * @param state state of the smart plug
     * @param ampere ampere value of the smart plug
     */
    public SmartPlug(String name, String state, double ampere){
        setName(name);
        setState(state);
        setAmpere(ampere);
        voltage = 220;
        plugState = "In";
        energyConsumption = 0;
    }

    /**
     * @return the state of the smart plug(in or out)
     */
    public String getPlugState() {
        return plugState;
    }

    /**
     * @param plugState the state of the smart plug(in or out)
     */
    public void setPlugState(String plugState) {
        this.plugState = plugState;
    }

    /**
     * @return the voltage value of the smart plug
     */
    public int getVoltage() {
        return voltage;
    }

    /**
     * @param voltage the voltage value of the smart plug
     */
    public void setVoltage(int voltage) {
        this.voltage = voltage;
    }

    /**
     * @return the ampere value of the smart plug
     */
    public double getAmpere() {
        return ampere;
    }

    /**
     * @param ampere the ampere value of the smart plug
     */
    public void setAmpere(double ampere) {
        this.ampere = ampere;
    }

    public double getEnergyConsumption() {
        return energyConsumption;
    }

    public void setEnergyConsumption(double energyConsumption) {
        this.energyConsumption = energyConsumption;
    }
}
