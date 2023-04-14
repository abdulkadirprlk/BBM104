import java.time.LocalTime;

public class Trip {
    private String tripName;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private int duration;
    private String state;

    public Trip() {//constructor for Trip object
        state = "IDLE";//default value of state is Idle
    }

    //GETTERS
    public String getTripName() {
        return tripName;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public int getDuration() {
        return duration;
    }

    public String getState() {
        return state;
    }

    //SETTERS
    public void setTripName(String name) {
        this.tripName = name;
    }

    public void setState(String s) {
        this.state = s;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void calculateArrival() {//setter of arrivalTime
        //arrivalTime = departureTime + duration
        this.arrivalTime = getDepartureTime().plusMinutes(getDuration());
    }


}

