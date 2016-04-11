
import java.sql.*;

/**
 *  The Database class contains the scripts used to connect the TwitterBlackout 
 *  program with the database.
 * 
 * @author mrey4_000
 */
public class Database {

    /** 
     *  The Connection class initializes the driver and provides the connection to 
     *  the MySQL database.
     * 
     * @param url The url of the MySQL database
     * @param user The username of the MySQL database
     * @param password The password of the MySQL database
     * @return the connection instance
     * @throws Exception if connection not established
     */
    public static Connection getConnection() throws Exception {
        
    String url = "jdbc:mysql://localhost:3306/twitterblackout";
    String user = "root";
    String password = "CPSC240";
    
    Connection c = DriverManager.getConnection(url, user, password);
    return c;
    }

    /**
     *  The addUser class adds the user information to the user table in the
     *  MySQL database.
     * 
     * @param var The user object that is being added to the user table
     * @throws Exception if connection is not made
     */
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
    
    /**
     *  The addTweet class adds tweets to the tweet table in the MySQL database.
     * 
     * @param var The tweet object being added to the database
     * @throws Exception if a connection is not made
     */
    public static void addTweet(Tweet var) throws Exception {
        
        int tId = var.getTweetId();
        int uId = var.getUserId();
        String ph = var.getPhrase();
        boolean iiP = var.getIsPublic();
        int iP;
        if (iiP) 
            iP = 1;
        else
            iP = 0;
        String ts = var.getTimestamp();
        
        Connection c = null;
        PreparedStatement posted = null;
        try {
            c = getConnection();
            posted = c.prepareStatement("INSERT INTO tweet (tweetId, userId, phrase, isPublic, timestamp) VALUES ('" + tId + "','" + uId + "','" + ph + "', '" + iP + "', '" + ts + "')");
            posted.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println("Insert Completed.");
        }
    }
    
    /**
     *  The class addSubscription adds subscriptions to the subscriptions table
     *  in the MySQL database.
     * 
     * @param var The subscription object being added to the database
     * @throws Exception if a connection is not made
     */
    public static void addSubscription (Subscription var) throws Exception {
        
        int sId = var.getSubscriptionId();
        int subscriberId = var.getSubscriberId();
        int subscribeeId = var.getSubscribeeId();
        
        Connection c = null;
        PreparedStatement posted = null;
        try {
            c = getConnection();
            posted = c.prepareStatement("INSERT INTO subscriptions (subscriptionId, subscriberId, subscribeeId) VALUES ('" + sId + "','" + subscriberId + "', '" + subscribeeId + "')");
            posted.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println("Insert Completed.");
        }
    }
}
