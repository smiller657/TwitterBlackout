

/**
 * Establishes a work space to test out various parts of the code.
 * @author Samantha
 */
public class TwitterBlackoutTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        User sm = new User(0, "Sam", "Miller", "swordgirl", "password", true);
        Tweet sm1 = new Tweet(0, 0, "sam's tweet 1", true, "160101");
        Tweet sm2 = new Tweet(1, 0, "sam's tweet 2", true, "160102");
        Tweets tweets = new Tweets();
        tweets.addTweet(sm1);
        tweets.addTweet(sm2);
    }
    
}
