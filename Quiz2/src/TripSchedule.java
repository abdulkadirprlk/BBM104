import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TripSchedule {
    public ArrayList<Trip> trips;
    public TripSchedule(){
        trips = new ArrayList<>();
    }

    public void departureSchedule() {//sort
        Collections.sort(trips, Comparator.comparing(Trip::getDepartureTime));
    }
    public void arrivalSchedule() {//sort
        Collections.sort(trips, Comparator.comparing(Trip::getArrivalTime));
    }
    public void idleOrDelayedDeparture() {//idle or delayed departure
        for (int i = 0; i < trips.size(); i++) {
            for (int j = i + 1; j < trips.size(); j++) {
                if(trips.get(i).getDepartureTime().compareTo(trips.get(j).getDepartureTime()) == 0){
                    trips.get(i).setState("DELAYED");
                    trips.get(j).setState("DELAYED");
                }
            }
        }
    }
    public void departureArrivalEditor(){
        for(Trip trip:trips){
            trip.setState("IDLE");
        }
    }
    public void idleOrDelayedArrival() {//idle or delayed arrival
        for (int i = 0; i < trips.size(); i++) {
            for (int j = i + 1; j < trips.size(); j++) {
                if(trips.get(i).getArrivalTime().compareTo(trips.get(j).getArrivalTime()) == 0){
                    trips.get(i).setState("DELAYED");
                    trips.get(j).setState("DELAYED");
                }
            }
        }
    }

}