import java.util.LinkedList;
import java.util.*;

public class Authentication {
    static List<User> user=new LinkedList<>();
    static char category;

    public static char getCategory() {
        return category;
    }

    public void register(Scanner scanner){
        System.out.print("Enter email: ");
        String email=scanner.nextLine();
        while (!emailCheck(email)){
            System.out.println("Invalid email try again...");
            email=scanner.nextLine();
        }
        for(User user1:user){
            if(user1.getEmail().equals(email)){
                System.out.println("Email Already Found... Pls try to login");
                return;
            }
        }

        String username=pickName(email);
        System.out.print("Create Password: ");
        String pass= scanner.nextLine();
        while(!passCheck(pass)){
            System.out.println("Invalid pass try again.. ");
            pass=scanner.nextLine();
        }
        System.out.print("Wanted to be seller/buyer (S/B): ");
        char sb=scanner.next().toUpperCase().charAt(0);

        user.add(new User(email, pass, username, sb));
        System.out.println(username+" your successfully registered as "+ (sb=='S'? "Seller":"Buyer"));
    }

    public static String login(Scanner scanner){
        System.out.print("Enter email: ");
        String email=scanner.nextLine();
        while (!emailCheck(email)){
            System.out.println("Invalid email try again...");
            email=scanner.nextLine();
        }

        System.out.print("Enter password: ");
        String pass=scanner.nextLine();

        for(User user1:user){
            if(user1.getEmail().equals(email) && user1.getPass().equals(pass)){
                category=user1.getSellerORbuyer();
                return email;
            }
        }
        return null;

    }


    private static boolean emailCheck(String email){

        for(int i=0;i<email.length();i++){
            String sub=email.substring(i, email.length());
            if(email.charAt(i)>='A'&& email.charAt(i)<='Z') return false;
            if(sub.equals("@gmail.com")) return true;
        }
        return false;
    }

    private static String pickName(String email){
        int i=0, found=email.lastIndexOf("@");
        return email.substring(0, found-1);

    }

    private static boolean passCheck(String pass){
        if(pass.length()<5) return false;
        boolean number=false, upper=false;
        for(int i=0;i<pass.length();i++){
            if(pass.charAt(i)>='A' && pass.charAt(i)<='Z') upper=true;
            else if(pass.charAt(i)>='0' && pass.charAt(i)<='9') number=true;
        }

        return (upper&&number)? true:false;
    }
}
