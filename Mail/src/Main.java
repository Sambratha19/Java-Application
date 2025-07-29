import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        Function function = new Function();
        Scanner scanner = new Scanner(System.in);


        System.out.println("SignUP");
        function.singUp(scanner);
        System.out.println("Finish Signup!!!");

        System.out.println("\nLogin");

        String email = function.logIn(scanner);
        if (!email.equals("null")) {
            System.out.println("Successfully Log In...");
            inboxModifier(email);
        } else {
            System.out.println("User Not found");
        }
        while (true) {
            System.out.println("1, Send Mail" +
                    "\n2, Inbox" +
                    "\n3, Sent Mails" +
                    "\n4, Delete Sent Mail" +
                    "\n5, Delete Account" +
                    "\n6, Logout");
            int choose = scanner.nextInt();
            try {
                DBConnector connector = new DBConnector();
                Connection connection = connector.con();
                Statement statement = connection.createStatement();
                switch (choose) {
                    case 1:
                        System.out.println("Send a Mail");
                        connector.writeMail(scanner, email);
                        System.out.println("Mail is successfully send!!!!!!");
                        break;
                    case 2:
                        System.out.println("Inbox List");
                        ResultSet rs = statement.executeQuery("SELECT * FROM inbox_email");
                        int list = 1;
                        while (rs.next()) {
                            System.out.println((list++) + "Email: " + rs.getString(1) +
                                    "\nSubject: " + rs.getString(2) +
                                    "\n" + rs.getString(3));
                            System.out.println();
                        }
                        break;
                    case 3:
                        System.out.println("Sent Mail List");
                        ResultSet rs1 = statement.executeQuery("SELECT * FROM sent_email");
                        int list1 = 1;
                        while (rs1.next()) {
                            System.out.println((list1++) + "Email: " + rs1.getString(1) +
                                    "\nSubject: " + rs1.getString(2) +
                                    "\n" + rs1.getString(3));
                            System.out.println();
                        }
                        break;
                    case 4:
                        System.out.println("Enter the mail Id you want to delete from the List");
                        String sentEmail = scanner.nextLine();
                        PreparedStatement bye = connection.prepareStatement("DELETE FROM sent_email WHERE email=?");
                        bye.setString(1, sentEmail);
                        bye.executeUpdate();
                        System.out.println("Email is Successfully deleted");
                        break;
                    case 5:
                        PreparedStatement bye1 = connection.prepareStatement("DELETE FROM user_detail WHERE email=?");
                        bye1.setString(1, email);
                        bye1.executeUpdate();
                        System.out.println("Your Account is Deleted...");
                        System.exit(0);
                        break;
                    case 6:
                        System.out.println("User Log OUT!!!");
                        PreparedStatement deleteInbox = connection.prepareStatement("truncate table inbox_email");
                        deleteInbox.executeUpdate();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid Choose");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private static void inboxModifier (String email){
        try {
            DBConnector connector = new DBConnector();
            Connection connection = connector.con();
            Statement statement = connection.createStatement();
            PreparedStatement pre = connection.prepareStatement("SELECT sent_by, sub, body From sent_email WHERE email=?");
            pre.setString(1, email);
            ResultSet rs = pre.executeQuery();
            int index = 1;
            PreparedStatement inbox = connection.prepareStatement("INSERT INTO inbox_email (email, sub, body) values(?, ?, ?)");
            while (rs.next()) {
                inbox.setString(1, rs.getString("sent_by"));
                inbox.setString(2, rs.getString("sub"));
                inbox.setString(3, rs.getString("body"));
                inbox.executeUpdate();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}