import java.util.*;
public class Lift {
    String id;
    int currentFloor;
    List<Integer> stops;
    int minFloor, maxFloor;
    int capacity;

    Lift(String id, int minFloor, int maxFloor) {
        this.id = id;
        this.currentFloor = 0;
        this.capacity = 5;
        this.stops = new ArrayList<>();
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
    }

    boolean canServe(int from, int to) {
        return from >= minFloor && from <= maxFloor && to >= minFloor && to <= maxFloor;
    }

    void addStop(int floor) {
        if (!stops.contains(floor)) {
            stops.add(floor);
        }
    }

    int numberOfStops() {
        return stops.size();
    }

}
