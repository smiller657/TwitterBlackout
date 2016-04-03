/**
 * The Tweet class contains a constructor, getters, and setters for an individual Tweet.
 * @author Samantha
 */
public class Tweet {
    
<<<<<<< HEAD
    private int tweetId;
    private int userId;
    private String phrase;
    private boolean isPublic;
    private String timestamp;
    
    /**
     * Constructor for a Tweet which is a 140-character phrase a user may post to the News Feed.
     * @param tweetId The unique id number for a tweet.
     * @param userId The user who posted the tweet.
     * @param phrase The 140-character maximum for the text of the tweet.
     * @param isPublic True if the tweet is public.
     * @param timestamp When the tweet was created.
     */
    public Tweet(int tweetId, int userId, String phrase, boolean isPublic, String timestamp) {
        this.tweetId = tweetId;
        this.userId = userId;
        this.phrase = phrase;
        this.isPublic = isPublic;
        this.timestamp = timestamp;
    }
    /**
     * Getter for a tweet's id set by the database.
     * @return The unique id for a tweet.
     */
    public int getTweetId() {
        return tweetId;
    }
    /**
     * Getter for a user's id number, established by the database.
     * @return The userId number for a user who posted a tweet.
     */
    public int getUserId() {
        return userId;
    }
    /**
     * Getter for the message of the tweet.
     * @return The phrase of the tweet object.
     */
    public String getPhrase() {
        return phrase;
    }
    /**
     * Return whether the tweet is public or to subscribed users only.
     * @return True if the tweet is posted as public.
     */
    public boolean getIsPublic() {
        return isPublic;
    }
    /**
     * Return the timestamp of the tweet.
     * @return The date and time of the tweet.
     */
    public String getTimestamp() {
        return timestamp;
    }
    /**
     * Setter for a tweetId, which is a unique, database assigned id number.
     * @param tweetId 
     */
    public void setTweetId(int tweetId) {
        this.tweetId = tweetId;
    }
    /**
     * Setter a userId for a tweet.
     * @param userId The userId 
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }
    /**
     * Setter for the phrase of a tweet.
     * @param phrase 
     */
    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }
    /**
     * Setter for whether the tweet is public or subscribed only.
     * @param isPublic True if the tweet is public.
     */
    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }
    /**
     * Sets the timestamp for the tweet.
     * @param timestamp When the tweet was created.
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
=======
    
>>>>>>> refs/remotes/origin/master
}
