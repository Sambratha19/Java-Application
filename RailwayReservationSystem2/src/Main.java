import jdk.jshell.spi.SPIResolutionException;

import java.util.*;
public class Main {
    public static void main(String[] args) {

        Scanner scan=new Scanner(System.in);
        while(true){
            System.out.println("1. Booking Ticket\n2. Cancel Ticket\n3. Confirmed Ticket\n4. Revenue\n5. Exit");
            int choose= scan.nextInt();

            switch (choose){
                case 1:
                    System.out.println("---Book Your Ticket---");
                    scan.nextLine();
                    System.out.println("Enter Your Name: ");
                    String name=scan.nextLine();
                    Passenger passenger=new Passenger(name);
                    System.out.println(passenger.name+" "+passenger.id);
                    System.out.println("Enter Coach Type (Normal/Premium):");
                    String type=scan.next();
                    System.out.println("Route: 1- Chennai-> 2- Katpadi-> 3- Salem-> 4- Erode-> 5- Coimbatore");
                    System.out.println("Enter You Source Station: ");
                    int s= scan.nextInt();
                    Stations stations=new Stations();
                    String source=stations.getStation(s);
                    System.out.println("Enter You Destination Station: ");
                    int d= scan.nextInt();
                    String destination=stations.getStation(d);
                    scan.nextLine();
                    System.out.println("Enter date (yyyy-mm-dd): ");
                    String date=scan.nextLine();
                    TrainDetails trainDetails=new TrainDetails(type, passenger, source, destination, date);
                    trainDetails.confirmTicket();
                    int seatNo=BookingData.getSeatNo();
                    int price=BookingData.getPrice();
                    System.out.println("--Ticket--\nName: "+passenger.getName()+"\nID: "+passenger.id+"\nSeat NO.: "+seatNo+"\nPrice: "+ price);
                    break;
                case 2:
                    System.out.println("---Cancel Your Ticket with ID---");
                    System.out.println("Enter Your Id: ");
                    int id= scan.nextInt();
                    if(TrainDetails.bookings.containsKey(id)){
                        if((TrainDetails.cancelTicket(id))){
                            System.out.println("Your Ticket is canceled...");
                        }
                    }else{
                        System.out.println("Unavailable ID no.");
                    }
                    break;
                case 3:
                    TrainDetails.ConfirmedTicket();
                    break;
                case 4:
                    System.out.println("---Revenue---");
                    TrainDetails.revenue();
                    break;
                case 5:
                    System.out.println("Thank You for Visiting...");
                    scan.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Choose");
                    break;
            }
        }
    }
}