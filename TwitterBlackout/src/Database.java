
/**
 *
 * @author Mike
 */
import java.sql.*;

public class Database {

    public static Connection getConnection() throws Exception {
        
    String url = "jdbc:mysql://localhost:3306/twitterblackout";
    String user = "root";
    String password = "CPSC240";
    
    Connection c = DriverManager.getConnection(url, user, password);
    return c;
    }

    public static void addUser(int uId, String fN, String lN, String h, String pw, int iP) throws Exception {
        

        
        
        Connection c = null;
        PreparedStatement posted = null;
        try {
            c = getConnection();
            posted = c.prepareStatement("INSERT INTO user (userID, firstName, lastName, handle, password, isPublic) VALUES ('" + uId + "','" + fN + "', '" + lN + "', '" + h + "', '" + pw + "', '" + iP + "')");
            posted.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println("Insert Completed.");
        }
    }
}
