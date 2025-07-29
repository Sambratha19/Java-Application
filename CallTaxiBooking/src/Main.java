import java.util.*;


public class Main {
    static List<Taxi> taxis = new ArrayList<>();

    public static void main(String[] args) {
        // Create 4 taxis
        for (int i = 0; i < 4; i++) {
            taxis.add(new Taxi());
        }

        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Call Taxi Booking System ---");
            System.out.println("1. Book a Taxi");
            System.out.println("2. View Taxi Status");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");
            int option = scan.nextInt();
            scan.nextLine(); // consume newline

            if (option == 1) {
                System.out.print("Enter Customer Id: ");
                int name = scan.nextInt();
                System.out.print("Enter Pickup Point (A-F): ");
                char pickup = scan.next().toUpperCase().charAt(0);
                System.out.print("Enter Drop Point (A-F): ");
                char drop = scan.next().toUpperCase().charAt(0);
                System.out.print("Enter Pickup Time (0-23): ");
                int time = scan.nextInt();

                Taxi assigned = allocateTaxi(name, pickup, drop, time);

                if (assigned != null) {
                    System.out.println("Taxi can be allotted.");
                    System.out.println("Taxi-" + assigned.id + " is allotted.");
                } else {
                    System.out.println("No taxi is available at this time. Booking rejected.");
                }

            } else if (option == 2) {
                for (Taxi taxi : taxis) {
                    taxi.printStatus();
                }
            } else {
                System.out.println("Exiting.");
                break;
            }
        }

        scan.close();
    }

    public static Taxi allocateTaxi(int name, char pickup, char drop, int pickupTime) {
        List<Taxi> available = new ArrayList<>();
        for (Taxi taxi : taxis) {
            if (taxi.isAvailable(pickup, pickupTime)) {
                available.add(taxi);
            }
        }

        if (available.isEmpty()) return null;

        // Sort by distance to pickup, then by earning
        available.sort((a, b) -> {
            int distA = Math.abs(a.currentStop - pickup);
            int distB = Math.abs(b.currentStop - pickup);
            if (distA != distB) return distA - distB;
            return a.earning - b.earning;
        });

        Taxi selected = available.get(0);
        selected.assignRide(name, pickup, drop, pickupTime);

        return selected;
    }
}
