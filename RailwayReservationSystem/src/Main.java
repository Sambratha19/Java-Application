import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TicketSystem ticketSystem=new TicketSystem();

        while(true)
        {
            System.out.println("1. Book Ticket\n2. Cancel Ticket\n3. View confirmed\n4. view available\n5. view RAC\n6. view Wishlist\n7. exit");
            Scanner scan=new Scanner(System.in);
            int in= scan.nextInt();
            switch (in) {
                case 1:
                     scan.nextLine();
                     System.out.println("Enter Name: ");
                     String name = scan.nextLine();
                     System.out.println("Enter age: ");
                     int age = scan.nextInt();
                     System.out.println("Enter gender: ");
                     String gender = scan.nextLine();
                     System.out.println("Berth: ");
                     String berth = scan.nextLine();
                     ticketSystem.bookTicket(name, age, gender, berth);
                     break;
                case 2:
                    System.out.println("Enter id: ");
                    int id= scan.nextInt();
                    ticketSystem.cancel(id);
                    break;
                case 3:
                    ticketSystem.printStatus();
                    break;
                case 4:
                    ticketSystem.available();
                    break;
                case 5:
                    ticketSystem.racList();
                    break;
                case 6:
                    ticketSystem.WaitingList();
                    break;
                case 7:
                    System.exit(0);
                    break;
            }
        }
    }
}