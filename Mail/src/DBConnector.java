
import java.sql.*;
import java.util.Scanner;

public class DBConnector {
    public static Connection con(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/zoho_email_db", "root", "Shakthi*19"
            );
//            Statement statement = connection.createStatement();

            return connection;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public static void writeMail(Scanner scanner, String sent_by){
        scanner.nextLine();
        System.out.print(sent_by+"\nSent Mail to: ");String reciver= scanner.nextLine();
        System.out.print("Subject: "); String subject= scanner.nextLine();
        while(subject.length()<0){
            System.out.println("Subject Should not be Empty");
            subject=scanner.nextLine();
        }
        System.out.println("Body: ");String body=scanner.nextLine();
        while (body.length()<0){
            System.out.println("Body Should not be Empty. ");
            body=scanner.nextLine();
        }
        try {
            DBConnector connector = new DBConnector();
            Connection connection = con();
//            Statement statement = connection.createStatement();
            PreparedStatement sendMail=connection.prepareStatement("INSERT INTO sent_email (email, sub, body, sent_by) values (?, ?, ?, ?)");
            sendMail.setString(1,reciver);
            sendMail.setString(2, subject);
            sendMail.setString(3, body);
            sendMail.setString(4, sent_by);
            sendMail.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
