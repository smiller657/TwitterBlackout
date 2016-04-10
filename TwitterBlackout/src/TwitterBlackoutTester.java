
import java.util.ArrayList;

/**
 * Establishes a work space to test out various parts of the code.
 * @author Samantha
 */
public class TwitterBlackoutTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<User>();
        ArrayList<Tweet> tweets = new ArrayList<Tweet>();
        ArrayList<Subscription> subs = new ArrayList<Subscription>();
        ArrayList<Hashtag> hashtags = new ArrayList<Hashtag>();
        User sm = new User(0, "Sam", "Miller", "swordgirl", "password", true);
        User pk = new User(1, "Paul", "Kartage", "rennman", "pass2", false);
        users.add(sm);
        users.add(pk);
        Tweet sm1 = new Tweet(0, 0, "sam's public tweet 1", true, "160101");
        Tweet sm2 = new Tweet(1, 0, "sam's private tweet 2", false, "160102");
        tweets.add(sm1);
        tweets.add(sm2);
        Tweet pk1 = new Tweet(2, 1, "Paul's private tweet #1", false, "160302");
        Tweet pk2 = new Tweet(3, 1, "Paul's public tweet #2", true, "160202");
        tweets.add(pk1);
        tweets.add(pk2);
        Subscription sub1 = new Subscription(100, 1, 0);
        subs.add(sub1);
        TwitterBlackout tb = new TwitterBlackout();
        tb.runApp(tweets, users, subs, hashtags);
    }
    
}
