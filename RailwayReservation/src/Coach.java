import java.util.HashMap;
import java.util.LinkedList;
import java.util.*;

public class Coach {
    Passenger passenger1;
    String coach;
    Coach(String passenger, String c){
        this.coach=c;
        passenger1=new Passenger(passenger, c);
    }

    List <Passenger> AC=new LinkedList<>();
    List <Passenger> nonAC=new LinkedList<>();
    List <Passenger> Seater=new LinkedList<>();
    static List<Passenger> confirmed=new LinkedList<>();
    static Queue<Passenger> waitingList=new LinkedList<>();


    public boolean bookTicket(){
        if(coach.equalsIgnoreCase("AC") && AC.size()<60){
            AC.add(passenger1);
        }else if(coach.equalsIgnoreCase("nonAC")&& nonAC.size()<60){
            nonAC.add(passenger1);
        }else if(coach.equalsIgnoreCase("Seater") && Seater.size()<60){
            Seater.add(passenger1);
        }

        if(confirmed.size()<60){
            confirmed.add(passenger1);
            System.out.println("Ticket is Booked");
            return true;
        }else if(waitingList.size()<10){
            waitingList.add(passenger1);
            System.out.println("Ticket in Waiting List");
            return true;
        }else{
            return false;
        }


    }


    public static void cancel(int id){
        Passenger remove=null;
        for(Passenger passenger: confirmed){
            if(passenger.getId()==id) {
                remove=passenger;
            }
        }

        if(remove!=null){
            confirmed.remove(remove);
            System.out.println("Cancelled ticket for ID: " + id);

            if(!waitingList.isEmpty()){
                confirmed.add(waitingList.poll());
            }
        }else{
            System.out.println("Not found");
        }

    }


    public static void status(){
        for(Passenger passenger: confirmed){
            System.out.println("ID: "+passenger.getId()+" - Confirmed Ticket");
        }

        for(Passenger passenger: waitingList){
            System.out.println("ID: "+passenger.getId()+" - In Waiting list");
        }
    }
}
