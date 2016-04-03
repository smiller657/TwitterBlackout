
import java.util.ArrayList;


/**
 * The News Feed mainpulates a Tweets collection to show the user applicable tweets.
 * @author Samantha
 */
public class NewsFeed {
    private ArrayList<Tweet> newsfeed;
    private boolean isLoggedIn;
    
    public NewsFeed(ArrayList<Tweet> newsfeed) {
        this.newsfeed = newsfeed;
        this.isLoggedIn = false;
    }
    
    public void addTweet(Tweet tweet) {
        // database insert method
        // will this require a tweet constructor?
        newsfeed.add(tweet);
    }
    /**
     * Deletes a tweet from the database and the current collection of tweets.
     * @param tweet 
     */
    public void deleteTweet(Tweet tweet) {
        //database delete method
        newsfeed.remove(tweet);
    }  

    public ArrayList getNewsFeed() {
        return newsfeed;
    }
    
    public ArrayList getNFTweets() {
        ArrayList listOfTweets = new ArrayList();
        String phrase;
        for (int i = 0; i < newsfeed.size(); i++) {
            if (listOfTweets.isEmpty()) {
                phrase = newsfeed.get(i).getPhrase();
                listOfTweets.add(phrase);
            } else {
                //need to compare?
                phrase = newsfeed.get(i).getPhrase();
                listOfTweets.add(phrase);
            }
        }
        return listOfTweets;
    }
    
}
