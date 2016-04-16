
import java.util.ArrayList;

/**
 * Establishes a work space to test out various parts of the code.
 * Comment out the one you don't want to use.
 *
 * @author Samantha
 */
public class TwitterBlackoutTester {

    /**
     * Tests TwitterBlackout logic.
     * @param args the command line arguments
     */
    public static void main(String[] args) throws CloneNotSupportedException {
        ArrayList<User> users = new ArrayList<User>();
         ArrayList<Tweet> tweets = new ArrayList<Tweet>();
         ArrayList<Subscription> subs = new ArrayList<Subscription>();
         User sm = new User(0, "Sam", "Miller", "swordgirl", "password", true);
         User pk = new User(1, "Paul", "Kartage", "rennman", "pass2", false);
         User mr = new User(2, "Mike", "Reynolds", "mrmr", "1234", true);
         users.add(sm);
         users.add(pk);
         users.add(mr);
         Tweet sm1 = new Tweet(0, 0, "sam's tweet 1", true, "160101");
         Tweet sm2 = new Tweet(1, 0, "sam's tweet 2", true, "160102");
         Tweet sm3 = new Tweet(2, 0, "Sam to Mike @2 backatcha @1.", true, "160102");
         tweets.add(sm1);
         tweets.add(sm2);
         tweets.add(sm3);
         Tweet pk1 = new Tweet(2, 1, "Paul's private tweet #1", false, "160302");
         Tweet pk2 = new Tweet(3, 1, "Paul's public tweet #2", true, "160202");
         tweets.add(pk1);
         tweets.add(pk2);
         Tweet mr1 = new Tweet(4, 2, "Mike @0 hi", false, "160202");
         tweets.add(mr1);
         Subscription sub1 = new Subscription(100, 1, 0);  //Paul is the subscriber, sam is the subscribee.
         subs.add(sub1);
         TwitterBlackout tb = new TwitterBlackout();
         tb.runApp(tweets, users, subs);
    }
    /**
     * Tests DB queries and scripts.
     * @param args the command line arguments
     * @throws Exception if db failed.
     */
//    public static void main(String[] args) throws Exception {
//        // test connection
//        try {
//            Connect c = new Connect();
//            System.out.println("Connection established");
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Connection failed");
//        }
//        // test insert into user table
//        User sm = new User(0, "Sam", "Miller", "swordgirl", "password", true);
//        int uId = sm.getUserId();
//         String fN = sm.getFirstName();
//         String lN = sm.getLastName();
//         String h = sm.getHandle();
//         String pw = sm.getPassword();
//         boolean iiP = sm.getIsPublic();
//         int iP;
//         if (iiP) 
//            iP = 1;
//         else
//            iP = 0;
//        Tweets tweets = new Tweets();
//        Tweet sm1 = new Tweet(0, 0, "sam's tweet 1", true, "160101");
//        Tweet sm2 = new Tweet(1, 0, "sam's tweet 2", true, "160102");
//        tweets.addTweet(sm1);
//        tweets.addTweet(sm2); 
//        User var = mr;
//        Database.addUser(var);
//        Database.addUser(var);
//        String timestamp = SimpleDateFormatter.getTimestamp();
//        Tweet m1 = new Tweet(4, 1, "Mike's apostrphe check", true, timestamp);
//        Tweet var = m1;
//        Database.addTweet(var);
//        Database.getTweets();
//    }
}
