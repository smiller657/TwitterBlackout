
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Tweets class creates a collection of Tweets.  This class interacts with the database and News Feed class.
 * @author Samantha
 */
public class Tweets {
    private ArrayList tweets;
    
    /**
     * Adds a tweet to the current collection of tweets and to the database.
     * @param tweet 
     */
    public void addTweet(Tweet tweet) {
        // database insert method
        // will this require a tweet constructor?
        tweets.add(tweet);
    }
    /**
     * Deletes a tweet from the database and the current collection of tweets.
     * @param tweet 
     */
    public void deleteTweet(Tweet tweet) {
        //database delete method
        tweets.remove(tweet);
    }
    
}
