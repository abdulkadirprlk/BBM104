public class TripController implements DepartureController, ArrivalController {
    protected TripSchedule tripSchedule;

    @Override
    public void arrivalSchedule(TripSchedule tripSchedule) {
        tripSchedule.arrivalSchedule();

    }

    @Override
    public void departureSchedule(TripSchedule tripSchedule) {
        tripSchedule.departureSchedule();

    }
    public void idleOrDelayedDeparture(TripSchedule tripSchedule){
        tripSchedule.idleOrDelayedDeparture();
    }
    public void departureArrivalEditor(TripSchedule tripSchedule){
        tripSchedule.departureArrivalEditor();
    }
    public void idleOrDelayedArrival(TripSchedule tripSchedule){
        tripSchedule.idleOrDelayedArrival();
    }

}
