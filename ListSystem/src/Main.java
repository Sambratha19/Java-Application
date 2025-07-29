import java.util.*;
public class Main {
    static List<Lift> lift = new LinkedList<>();

    public static void main(String[] args) {
        lift.add(new Lift("L1", 0, 5));
        lift.add(new Lift("L2", 0, 5));
        lift.add(new Lift("L3", 6, 10));
        lift.add(new Lift("L4", 6, 10));
        lift.add(new Lift("L5", 0, 10));

        Scanner scan = new Scanner(System.in);
        System.out.println("Current Floor: ");
        int me = scan.nextInt();
        System.out.println("You Choose: ");
        int to = scan.nextInt();

        int assignedIndex = assignLift(me, to);
        if (assignedIndex == -1) {
            System.out.println("No lift can be assigned!");
        } else {
            Lift assignedLift = lift.get(assignedIndex);
            System.out.println("Assigned Lift: " + assignedLift.id);
            assignedLift.addStop(me);
            assignedLift.addStop(to);
            assignedLift.currentFloor = to;
        }
    }



    public static int assignLift(int me, int to) {
        int bestLift = -1;
        int minDistance = Integer.MAX_VALUE;
        int minStops = Integer.MAX_VALUE;

        for(int i=0;i<lift.size();i++){
            Lift l = lift.get(i);
            if (!l.canServe(me, to)) continue;

            int distance=Math.abs(l.currentFloor-me);
            int direction=Integer.compare(to, me); //down (-ve), up (+ve)
            boolean sameDirection=(direction>0 && l.currentFloor<=me) || (direction<0 && l.currentFloor>=me);


            if (distance < minDistance || (distance == minDistance && sameDirection && l.numberOfStops() < minStops)) {
                bestLift = i;
                minDistance = distance;
                minStops = l.numberOfStops();
            }

        }
        return bestLift;
    }
}