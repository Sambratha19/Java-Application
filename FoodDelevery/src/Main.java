import java.util.*;

public class Main {


    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (true) {
            System.out.println("Enter Your order...");
            int choose= scanner.nextInt();;
            switch (choose) {
                case 1:
                    Function.assignOrder(scanner);
                    break;
                case 2:
                    Function.print();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid...");
            }
        }
    }



}