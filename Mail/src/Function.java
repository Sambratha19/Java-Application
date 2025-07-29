import java.sql.*;
import java.util.Scanner;


public class Function {
    public static void singUp(Scanner scan) {
        try {
            System.out.print("Create an Mail ID: ");

            String email = scan.nextLine();
            while (!emailCheck(email)) {
                System.out.println("Invalid");
                email = scan.nextLine();
            }


            DBConnector connector = new DBConnector();
            Connection connection = connector.con();
            Statement statement = connection.createStatement();
            PreparedStatement check=connection.prepareStatement("SELECT * FROM user_detail WHERE email=?");
            check.setString(1, email);
            ResultSet rs= check.executeQuery();
            if(rs.next()){
                System.out.println("User already found Pls try to Login");
                return;
            }
            System.out.print("Create password: ");
            String pass = scan.nextLine();
            while (!passCheck(pass)) {
                System.out.println("Invalid Pass Retry");
                pass = scan.nextLine();
            }
            User user = new User(email, pass);
            PreparedStatement preSignUp = connection.prepareStatement("INSERT INTO user_detail (email, pass, hashed_pass) values (?, ?, ?)");
            preSignUp.setString(1, email);
            preSignUp.setString(2, pass);
            preSignUp.setString(3, user.getHashedPass());
            preSignUp.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public static String logIn(Scanner scanner){
        try {
            System.out.print("Enter Mail ID: ");
            String email=scanner.nextLine();
            System.out.print("Enter Valid Password: ");
            String pass=scanner.nextLine();
//            User user=new User(email, pass);
            DBConnector connector=new DBConnector();
            Connection connection=connector.con();
            Statement statement=connection.createStatement();
            ResultSet rs=statement.executeQuery("SELECT * FROM user_detail");
            while (rs.next()){
                if(rs.getString(2).equals(email) && rs.getString(3).equals(pass)){
                    return email;
                }
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static boolean emailCheck(String email){
        int i=0;
        while(i<email.length())
        {
            String verify=email.substring(i);
            if(verify.equals("@gmail.com")){
                return true;
            }else if(email.charAt(i)>='A' && email.charAt(i)<='Z'){
                return false;
            }
            i++;
        }
        return false;
    }
    private static boolean passCheck(String pass){
        if(pass.length()<=5) return false;
        boolean upper=false, number=false;
        for(int i=0;i<pass.length();i++){
            if(pass.charAt(i)>='A' && pass.charAt(i)<='Z'){
                upper=true;
            }else if(pass.charAt(i)>='0' && pass.charAt(i)<='9'){
                number=true;
            }

            if(upper && number) return true;
        }

        return upper && number;
    }
    public static String hashedPassword(String pass){
        char[] ch =pass.toCharArray();
        for(int i=0;i<pass.length();i++){
            for(int j=i+1;j<pass.length();j++){
                if(ch[i]==ch[j]){
                    if(ch[j]=='z') ch[j]='a';
                    else if (ch[j]=='Z') ch[j]='Z';
                    else if(ch[j]=='9') ch[j]='0';
                    else ch[j]++;
                }
            }
        }
        String s=new String (ch);
        return s;
    }
}
