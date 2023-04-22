/**
 * This class implements a smart camera.
 */
public class SmartCamera extends BaseDevice {
    private double MPM;//Megabytes per minute
    private double occupiedStorage;

    /**
     * Assigns default values for properties.
     * @param MPM the megabytes per minute that are occupied in the storage
     */
    public SmartCamera(String name, double MPM) {
        state = "Off";
        setName(name);
        setMPM(MPM);
        occupiedStorage = 0;
    }

    /**
     * @param name name of the smart camera
     * @param MPM the megabytes per minute that are occupied in the storage
     * @param state state of the smart camera
     */
    public SmartCamera(String name, double MPM, String state) {
        setName(name);
        setState(state);
        setMPM(MPM);
        occupiedStorage = 0;
    }

    /**
     * @return the MPM value of the smart camera
     */
    public double getMPM() {
        return MPM;
    }

    /**
     * @param MPM MPM value of the smart camera
     */
    public void setMPM(double MPM) {
        this.MPM = MPM;
    }

    public double getOccupiedStorage() {
        return occupiedStorage;
    }

    public void setOccupiedStorage(double occupiedStorage) {
        this.occupiedStorage = occupiedStorage;
    }
}
