import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection database(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/e_commerce", "root", "Shakthi*19"
            );
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
