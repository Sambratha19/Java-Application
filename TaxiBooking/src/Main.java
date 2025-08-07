
import java.util.*;

public class Main {

    public static void main(String[] args) {
        //4 taxis

        Scanner scanner=new Scanner(System.in);
        while(true){
            System.out.println("a - Book\te - Exit");
            char choose=scanner.next().toLowerCase().charAt(0);

            switch (choose){
                case 'e':
                    System.exit(0);
                    break;
                default:
                    System.out.print("Current Location: ");
                    char curtLocation=scanner.next().toLowerCase().charAt(0);
                    System.out.print("Destination: ");
                    char destination=scanner.next().toLowerCase().charAt(0);
                    System.out.println("Time: ");
                    scanner. nextLine();
                    String time=scanner.nextLine();
                    User user=new User(curtLocation, destination, time);
                    //user.distance();
            }
        }
    }
}