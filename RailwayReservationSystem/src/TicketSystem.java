import java.util.*;
public class TicketSystem {

    public static final List<String> availableBerth=new ArrayList<>(Arrays.asList("L", "U", "M"));

    public static final Queue<Passenger> rac=new LinkedList<>();

    public static final Queue<Passenger> waitingList=new LinkedList<>();

    private static final List <Passenger> confirmed=new LinkedList<>();

    private static int ticketcount=1;

    public void bookTicket(String name, int age, String gender, String berth){
        int ticketId=ticketcount++;
        Passenger passenger;

        if(!availableBerth.isEmpty()){
            String allocatted= allottedBerth(age, gender, berth);
            passenger =new Passenger(age, name, gender, berth, allocatted, ticketId);
            confirmed.add(passenger);
            availableBerth.remove(allocatted);
            System.out.println("Ticker confirmed: "+passenger);
        }

        else if(rac.size()<1){
            passenger=new Passenger(age, name, gender, berth, "RAC", ticketId);
            rac.add(passenger);
            System.out.println("Ticker in Rac: "+passenger);
        }else if(waitingList.size()<1){

            passenger=new Passenger(age, name, gender, berth, "RAC", ticketId);
            waitingList.add(passenger);
            System.out.println("Ticker in waitingList: "+passenger);

        }else{
            System.out.println("No ticket available");
        }
    }

    private static String allottedBerth(int age, String gender, String berth){
        if(age>60 ||gender.equalsIgnoreCase("female") && availableBerth.contains("L")){
            return "L";
        }
        if(availableBerth.contains(berth)){
            return berth;
        }
        return availableBerth.get(0);
    }


    public static void cancel(int ticketId){
        boolean found = false;
        Iterator<Passenger> iterator = confirmed.iterator();

        while (iterator.hasNext()) {
            Passenger p = iterator.next();
            if (p.ticketId==(ticketId)) {
                iterator.remove(); // safe removal
                found = true;
                availableBerth.add(p.allottedBerth);
                break; // assuming ticketId is unique
            }
        }
        if(found) {
            if (!rac.isEmpty()) {
                Passenger passenger = rac.poll();
                String allocatted = allottedBerth(passenger.age, passenger.gender, passenger.berth);
                passenger.allottedBerth = allocatted;
                availableBerth.remove(allocatted);
                confirmed.add(passenger);
                System.out.println("Ticket from rac to confirmed: " + passenger);
            }
            if (!waitingList.isEmpty()) {
                Passenger passenger = waitingList.poll();
                passenger.allottedBerth = "RAC";
                rac.add(passenger);
                System.out.println("Ticket from watingList to rac: " + passenger);
            }
        }else{
            System.out.println("Not found");
        }
    }


    public static void printStatus(){
        if(confirmed.isEmpty()){
            System.out.println("None");
        }else{
            System.out.println("confirmed ticket: ");
            for(Passenger passenger:confirmed){
                System.out.println(passenger);
            }
        }
    }


    public static void available(){
        System.out.println("Beth: "+availableBerth.size());
        System.out.println("RAC: "+rac.size());
        System.out.println("Waiting list: "+waitingList.size());
    }


    public static void racList(){
        if(rac.isEmpty()){
            System.out.println("None");
        }else{
            System.out.println("RAC ticket: ");
            for(Passenger passenger:rac){
                System.out.println(passenger);
            }
        }
    }

    public static void WaitingList(){
        if(waitingList.isEmpty()){
            System.out.println("None");
        }else{
            System.out.println("Waiting list");
            for(Passenger passenger:waitingList){
                System.out.println(passenger);
            }
        }
    }
}
