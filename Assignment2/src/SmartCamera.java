/**
 * This class implements a smart camera.
 */
public class SmartCamera {
    private String state;
    private int MPM;//Megabytes per minute

    /**
     * Assigns default values for properties.
     * @param MPM the megabytes per minute that are occupied in the storage
     */
    public SmartCamera(int MPM){
        state = "off";
        this.MPM = MPM;
    }
}
