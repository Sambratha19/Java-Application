import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        while(true){
            System.out.println("1. Book Ticket\n2. Cancel Ticket\n3. Status checking\n4. Exit");
            int choose= scan.nextInt();
            switch (choose){
                case 1:
                    scan.nextLine();
                    System.out.println("Enter you Name: ");
                    String name= scan.nextLine();
                    System.out.println("Preferred Coach (AC/nonAC/Seater): ");
                    String coach=scan.next();
                    Coach coach1=new Coach(name, coach);
                    Passenger passenger=new Passenger(name, coach);
                    if(coach1.bookTicket()){
                        System.out.println(name+" Booking your id is "+ passenger.getId());
                    }else{
                        System.out.println("Ticket Not available");
                    }
                    break;
                case 2:
                    System.out.println("Enter you id: ");
                    int id= scan.nextInt();

                    Coach.cancel(id);


                    break;
                case 3:
                    Coach.status();
                    break;
                case 4:
                    System.exit(0);
                    scan.close();
                    break;
                default:
                    System.out.println("Invalid choose...");
                    break;
            }
        }
    }
}