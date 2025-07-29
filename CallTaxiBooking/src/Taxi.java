import java.beans.Customizer;
import java.util.*;
public class Taxi {
    static int nextId=1;
    int id;
    char currentStop;
    int earning;
    int freeTime;
    List<Booking> bookings;

    static String[] stops={"A", "B", "C", "D", "E", "F"};
    final int MAX_TIME=23;
    HashMap<String, Integer> amount=new HashMap<>();
    HashMap<String, Integer> times=new HashMap<>();


    Taxi(){
        this.id=nextId++;
        this.currentStop='A';
        this.earning=0;
        this.freeTime=0;
        this.bookings=new ArrayList<>();
    }

    public int calculateFare(char from, char to) {
        int distance = Math.abs(from - to) * 15;
        if (distance <= 5)
            return 100;
        return 100 + (distance - 5) * 10;
    }
    public void assignRide(int name, char pickup, char drop, int pickupTime) {
        int travelDuration = Math.abs(pickup - drop); // in hours

        int dropTime = pickupTime + travelDuration;

        this.freeTime = pickupTime + travelDuration;
        this.earning += calculateFare(pickup, drop);
        this.currentStop = drop;

        bookings.add(new Booking(name, pickup, drop, pickupTime, dropTime, calculateFare(pickup, drop)));
    }
    public void printStatus() {
        System.out.println("Taxi-" + id + ": Current Stop=" + currentStop + ", Free Time=" + freeTime + ", Earning=Rs." + earning);
        for (Booking b : bookings) {
            System.out.println( b.customerId + "\t" + b.pickup + "\t" + b.drop + "\t" + b.pickupTime + "\t" + b.dropTime + "\t" + b.amount);
        }

    }

    public boolean isAvailable(char pickup, int pickupTime) {
        int travelTime = Math.abs(pickup - currentStop); // 1 hour per hop
        return (freeTime + travelTime) <= pickupTime;
    }

}
