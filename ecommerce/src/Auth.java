import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Auth extends DatabseAccess{

    static Check check=new Check();

    public static void register(Scanner scanner){
        System.out.println("Enter Email: ");
        String email=scanner.nextLine();
        while(!check.emailCheck(email)){
            System.out.println("Invalid Email retry...");
            email=scanner.nextLine();
        }
        try{
            Statement statement=con.createStatement();
            ResultSet check=statement.executeQuery("Select * from user_details");
            while(check.next()){
                if(check.getString("email").equals(email)){
                    System.out.println("User already found please Login...");
                    return;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Create Password: ");
        String pass=scanner.nextLine();
        while (!check.pass(pass)){
            System.out.println("Invalid Pass retry...");
            pass=scanner.nextLine();
        }
        try {
            PreparedStatement addUser=con.prepareStatement("INSERT INTO user_details (email, pass)  values(?, ?)");
            addUser.setString(1, email);
            addUser.setString(2, pass);
            addUser.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static String login(Scanner scanner){
        System.out.println("Enter Email: ");
        String email=scanner.nextLine();
        while(!check.emailCheck(email)){
            System.out.println("Invalid Email retry...");
            email=scanner.nextLine();
        }
        System.out.println("Enter password: ");
        String pass=scanner.nextLine();
        try{
            Statement statement=con.createStatement();
            ResultSet check=statement.executeQuery("Select * from user_details");
            while(check.next()){
                if(check.getString("email").equals(email) && check.getString("pass").equals(pass)){
                   return email;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
