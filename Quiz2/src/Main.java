//Abdulkadir Parlak 2210765025

import java.io.*;
import java.time.LocalTime;

public class Main {
    static TripSchedule tripSchedule = new TripSchedule();
    static TripController tripController = new TripController();

    public static void main(String[] args) {
        readInputFile(args[0]);
        writeOutputFile(args[1]);
    }

    public static void readInputFile(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                Trip trip = new Trip();//new Trip object
                String[] lineArray = line.split("\t");
                trip.setTripName(lineArray[0]);//set the tripName
                trip.setDepartureTime(LocalTime.parse(lineArray[1]));//set the departureTime
                trip.setDuration(Integer.parseInt(lineArray[2]));//set duration
                trip.calculateArrival();
                tripSchedule.trips.add(trip);
            }

            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeOutputFile(String fileName) {
        tripController.departureSchedule(tripSchedule);//departure time sort
        tripController.idleOrDelayedDeparture(tripSchedule);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write("Departure Order:\n");
            for(Trip trip: tripSchedule.trips){
                writer.write(trip.getTripName() + " depart at " + trip.getDepartureTime() + "\t" +
                        "Trip State:" + trip.getState() + "\n");
            }
            writer.write("\n");
            tripController.departureArrivalEditor(tripSchedule);
            tripController.arrivalSchedule(tripSchedule);//arrival time sort
            tripController.idleOrDelayedArrival(tripSchedule);

            writer.write("Arrival Order:\n");
            for(Trip trip: tripSchedule.trips){
                writer.write(trip.getTripName() + " arrive at " + trip.getArrivalTime() + "\t" +
                        "Trip State: " + trip.getState() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}