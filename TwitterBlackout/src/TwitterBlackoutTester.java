
import java.util.ArrayList;

/**
 * Establishes a work space to test out various parts of the code.
 * @author Samantha
 */
public class TwitterBlackoutTester {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) throws Exception {
        ArrayList<User> users = new ArrayList<User>();
        ArrayList<Tweet> tweets = new ArrayList<Tweet>();
        /*ist<Hashtag> hashtags = new ArrayList<Hashtag>();
        User sm = new User(0, "Sam", "Miller", "swordgirl", "password", true);
        User pk = new User(1, "Paul", "Kartage", "rennman", "pass2", false);
        User mr = new User(2, "Mike", "Reynolds", "mrmr", "1234", true);
        /* Testing code tools before db implementation: 
        users.add(sm);
        users.add(pk);
        users.add(mr);
        Tweet sm1 = new Tweet(0, 0, "sam's tweet 1", true, "160101");
        Tweet sm2 = new Tweet(1, 0, "sam's tweet 2", true, "160102");
        Tweets tweets = new Tweets();
        tweets.addTweet(sm1);
        tweets.addTweet(sm2); 
        tweets.add(sm1);
        tweets.add(sm2);
        Tweet pk1 = new Tweet(2, 1, "Paul's private tweet #1", false, "160302");
        Tweet pk2 = new Tweet(3, 1, "Paul's public tweet #2", true, "160202");
        tweets.add(pk1);
        tweets.add(pk2);
        Tweet mr1 = new Tweet(4, 2, "Mike @swordgirl hi", false, "160202");
        tweets.add(mr1);
        Subscription sub1 = new Subscription(100, 1, 0);  //Paul is the subscriber, sam is the subscribee.
        subs.add(sub1);
        
        
        TwitterBlackout tb = new TwitterBlackout();
        tb.runApp(tweets, users, subs, hashtags);
        
        
        // test connection
        /* try {
            Connect c = new Connect();
            System.out.println("Connection established");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Connection failed");
        } */
        
        // test insert into user table
        /* int uId = sm.getUserId();
        String fN = sm.getFirstName();
        String lN = sm.getLastName();
        String h = sm.getHandle();
        String pw = sm.getPassword();
        boolean iiP = sm.getIsPublic();
        int iP;
        if (iiP) 
            iP = 1;
        else
            iP = 0; */
        //User mr = new User(0, "Richard", "Starkey", "Ringo", "drums", false);
        //User var = mr;
        //Database.addUser(var);
            
       //Database.addUser(mr);
       //Database.getUser(users);
       //for (int i = 0; i < users.size(); i++) {
       //         System.out.println(((User)users.get(i)).getUserId() + ", " + ((User)users.get(i)).getFirstName() + ", " + ((User)users.get(i)).getLastName() + ", " + ((User)users.get(i)).getHandle() + ", " + ((User)users.get(i)).getPassword() + ", " + ((User)users.get(i)).getIsPublic());
       //     }
       // String timestamp = SimpleDateFormatter.getTimestamp();
        //Tweet m1 = new Tweet(0, 1, "another timestamp check", true, timestamp);
        //Tweet var = m1;
//        
        //Database.addTweet(var);
        
       Database.getTweets(tweets);
       for (int i = 0; i < tweets.size(); i++) {
                System.out.println(((Tweet)tweets.get(i)).getTweetId() + ", " + ((Tweet)tweets.get(i)).getUserId() + ", " + ((Tweet)tweets.get(i)).getPhrase() + ", " + ((Tweet)tweets.get(i)).getIsPublic() + ", " + ((Tweet)tweets.get(i)).getTimestamp());
            }
        //String h = "mrmr";
        //String password = Database.getPassword(h);
        //System.out.println(password);
    }
    
}
