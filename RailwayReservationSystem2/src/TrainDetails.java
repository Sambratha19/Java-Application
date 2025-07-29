

import java.util.*;
public class TrainDetails {
    String type;
    Passenger passenger;
    String source, destination;
    String date;

    static final int MAX_NORMAL=10, MAX_PREMIUM=5;
    static int normal_seat=1, premium_seat=1;

    static boolean normal[]=new boolean[MAX_NORMAL+1];

    static boolean premium[]=new boolean[MAX_PREMIUM+1];

    static HashMap<String, Integer> revenuePerDate = new HashMap<>(); //date, price
    static HashMap<Integer, BookingData> bookings = new HashMap<>();
    public TrainDetails(String type,  Passenger passenger,String source, String destination, String date) {
        this.passenger = passenger;
        this.source = source;
        this.destination = destination;
        this.type = type;
        this.date = date;
    }
    int surge;
    int price;
    int count=0;
    public BookingData confirmTicket(){
        int seatNo=-1;
        int start=Stations.getStationValue(source);
        int end=Stations.getStationValue(destination);
        int difference=0;
        if(start!=-1 || end!=-1){
            difference=Math.abs(end-start);
        }else{
            System.out.println("Invalid Source or Destination...");
            return new BookingData(-1, -1, type);
        }

        if(type.equalsIgnoreCase("Normal")){

            for(int i=0;i<normal.length;i++){
                if(normal[i]==false)
                {
                    normal_seat=i+1;
                    seatNo=normal_seat;
                    normal[i]=true;
                    break;
                }
            }
            if(seatNo==-1){
                System.out.println("Seat in Normal coach No Available...");
            }
            price=10*difference;

        }else if(type.equalsIgnoreCase("Premium")){
            count++;
            if(count>1) surge+=5;
            for(int i=0;i<premium.length;i++){
                if(premium[i]==false)
                {
                    premium_seat=i+1;
                    seatNo=premium_seat;
                    premium[i]=true;
                    break;
                }

            }

            if(seatNo==-1){
                System.out.println("Seat in Premium Not Available...");
            }
            price=(20*difference)+surge;
        }else{
            System.out.println("Invalid Data");
            return new BookingData(-1, -1, type);
        }
        revenuePerDate.put(date, revenuePerDate.getOrDefault(date, 0)+price);
        BookingData bookingData=new BookingData(seatNo, price, type);
        bookings.put(passenger.getId(), bookingData);
        return bookingData;


    }

    public static boolean cancelTicket(int id){
        int seat=bookings.get(id).seatNo;
        if(bookings.get(id).type.equals("Normal")){
            normal[seat]=false;
        }else if (bookings.get(id).type.equals("Premium")){
            premium[seat]=false;
        }
        bookings.remove(id);
        return true;
    }

    public static void ConfirmedTicket(){
        if (bookings.isEmpty()) {
            System.out.println("No confirmed bookings found.");
            return;
        }

        System.out.println("Confirmed Tickets:");
        for (Map.Entry<Integer, BookingData> entry : bookings.entrySet()) {
            int id = entry.getKey();
            BookingData data = entry.getValue();
            System.out.println("Passenger ID: " + id +
//                    ", Date: " + data.date +
                    ", Type: " + data.type +
                    ", Seat No: " + data.seatNo +
                    ", Price: ₹" + data.price);
        }
    }

    public static void revenue(){
        for (Map.Entry<String, Integer> entry : revenuePerDate.entrySet()) {
            System.out.println("Date: " + entry.getKey() + ", Revenue: " + entry.getValue());
        }
    }



}
