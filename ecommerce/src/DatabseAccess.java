import java.sql.Connection;

public class DatabseAccess {
    static DBConnection connection=new DBConnection();
    static Connection con=connection.database();
}
