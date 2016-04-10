
import java.sql.*;

/**
 *  The Database class contains the scripts used to connect the TwitterBlackout 
 *  program with the database.
 * 
 * @author mrey4_000
 */
public class Database {

    public static Connection getConnection() throws Exception {
        
    String url = "jdbc:mysql://localhost:3306/twitterblackout";
    String user = "root";
    String password = "CPSC240";
    
    Connection c = DriverManager.getConnection(url, user, password);
    return c;
    }

    public static void addUser(User var) throws Exception {
        
        int uId = var.getUserId();
        String fN = var.getFirstName();
        String lN = var.getLastName();
        String h = var.getHandle();
        String pw = var.getPassword();
        boolean iiP = var.getIsPublic();
        int iP;
        if (iiP) 
            iP = 1;
        else
            iP = 0;
        
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
