
import java.sql.*;
import java.util.*;

/**
 * The Database class contains the scripts used to connect the TwitterBlackout
 * program with the database.
 *
 * @author Mike
 */
public class Database {

    /**
     * The Connection class initializes the driver and provides the connection
     * to the MySQL database.
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
     * The addUser class adds the user information to the user table in the
     * MySQL database.
     *
     * @param var The user object that is being added to the user table
     * @throws Exception if connection is not made
     */
    public static void addUser(User var) throws Exception {

        //  I used the textbook "A Guide to SQL" by Phillip J. Pratt and Mary Z. Last,
        //  Course Technology, Boston, MA, 2009, for ideas on writing the database scripts.
        
        int uId = var.getUserId();
        String fN = var.getFirstName();
        String lN = var.getLastName();
        String h = var.getHandle();
        String pw = var.getPassword();
        boolean iiP = var.getIsPublic();
        int iP;
        if (iiP) {
            iP = 1;
        } else {
            iP = 0;
        }

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
     * The updateUser class allows a user to update fields of their user profile.
     * 
     * @param var a User object
     * @param uId a userId integer
     * @throws Exception if there is no connection
     */
    public static void updateUser(User var, int uId) throws Exception {

        //int uId = var.getUserId();
        String fN = var.getFirstName();
        String lN = var.getLastName();
        String h = var.getHandle();
        String pw = var.getPassword();
        boolean iiP = var.getIsPublic();
        int iP;
        if (iiP) {
            iP = 1;
        } else {
            iP = 0;
        }

        Connection c = null;
        PreparedStatement posted = null;
        try {
            c = getConnection();
            posted = c.prepareStatement("UPDATE user SET firstName = ?, lastName = ?, handle = ?, password = ?, isPublic = ? WHERE userID = ?");
            //posted.setInt(1, uId);
            posted.setString(1, fN);
            posted.setString(2, lN);
            posted.setString(3, h);
            posted.setString(4, pw);
            posted.setInt(5, iP);
            posted.setInt(6, uId);
            posted.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println("Insert Completed.");
        }
    }
    /**
     * The addTweet class adds tweets to the tweet table in the MySQL database.
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
        if (iiP) {
            iP = 1;
        } else {
            iP = 0;
        }
        String ts = var.getTimestamp();

        Connection c = null;
        PreparedStatement posted = null;
        try {
            c = getConnection();
            posted = c.prepareStatement("INSERT INTO tweet (tweetId, userId, phrase, isPublic, timestamp) VALUES (?, ?, ?, ?, ?)");
            posted.setInt(1, tId);
            posted.setInt(2, uId);
            posted.setString(3, ph);
            posted.setInt(4, iP);
            posted.setString(5, ts);
            posted.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println("Insert Completed.");
        }
    }

    /**
     * The class addSubscription adds subscriptions to the subscriptions table
     * in the MySQL database.
     *
     * @param var The subscription object being added to the database
     * @throws Exception if a connection is not made
     */
    public static void addSubscription(Subscription var) throws Exception {

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

    /**
     * The deleteSubscription class will delete a row from the subscription
     * table in the database.
     * 
     * @param subId a subscriptionId integer
     * @throws Exception if there is no connection
     */
    public static void deleteSubscription(int subId) throws Exception {

        Connection c = null;
        PreparedStatement posted = null;
        try {
            c = getConnection();
            posted = c.prepareStatement("DELETE FROM subscriptions Where subscriptionId = ?");
            posted.setInt(1, subId);
            posted.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println("Insert Completed.");
        }
    }
    
    /**
     * The getTweets class populates the tweets ArrayList in the program.
     * 
     * @param tweets an ArrayList of Tweet objects
     * @throws Exception if there is no connection
     */
    public static void getTweets(ArrayList tweets) throws Exception {

        Connection c = null;

        try {
            c = getConnection();

            //  SELECT query
            String query = "SELECT * from tweet";
            Statement st = c.createStatement();
            ResultSet result = st.executeQuery(query);

            // iterate through the query
            while (result.next()) {
                int tweetId = result.getInt("tweetId");
                int userId = result.getInt("userId");
                String phrase = result.getString("phrase");
                boolean isPublic;
                int ip = result.getInt("isPublic");
                if (ip == 1) {
                    isPublic = true;
                } else {
                    isPublic = false;
                }
                String timestamp = result.getString("timestamp");

                // add info to tweets arraylist
                Tweet t = new Tweet(tweetId, userId, phrase, isPublic, timestamp);
                tweets.add(t);

            }
            
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }
    
    /**
     * The getUser method draws a list of the users from the database and adds them 
     * to an ArrayList in the program.
     * 
     * @param users the ArrayList of User objects
     * @throws Exception if a connection is not made
     */
    public static void getUser(ArrayList users) throws Exception {
        
        Connection c = null;

        try {
            c = getConnection();

            //  SELECT query
            String query = "SELECT * from user";
            Statement st = c.createStatement();
            ResultSet result = st.executeQuery(query);

            // iterate through the query
            while (result.next()) {
                int userId = result.getInt("userId");
                String firstName = result.getString("firstName");
                String lastName = result.getString("lastName");
                String handle = result.getString("handle");
                String password = result.getString("password");
                boolean isPublic;
                int ip = result.getInt("isPublic");
                if (ip == 1) {
                    isPublic = true;
                } else {
                    isPublic = false;
                }

                // add info to users arraylist
                User u = new User(userId, firstName, lastName, handle, password, isPublic);
                users.add(u);

            }
            
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    /**
     * The getSubscriptions class populates the subscriptions ArrayList in the
     * program.
     * 
     * @param subscriptions an ArrayList of Subscription objects
     * @throws Exception 
     */
    public static void getSubscriptions(ArrayList subscriptions) throws Exception {
        
        Connection c = null;

        try {
            c = getConnection();

            //  SELECT query
            String query = "SELECT * from subscriptions";
            Statement st = c.createStatement();
            ResultSet result = st.executeQuery(query);

            // iterate through the query
            while (result.next()) {
                int subscriptionId = result.getInt("subscriptionId");
                int subscriberId = result.getInt("subscriberId");
                int subscribeeId = result.getInt("subscribeeId");

                // add info to users arraylist
                Subscription s = new Subscription(subscriptionId, subscriberId, subscribeeId);
                subscriptions.add(s);

            }
            
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
}
