
/**
 *
 * @author Mike
 */
import com.mysql.jdbc.Driver;
import java.sql.*;

public class Connect {

    public Connect() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/twitterblackout";
        String user = "root";
        String password = "CPSC240";

        try {
            // get connection to database
            Connection myConn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
