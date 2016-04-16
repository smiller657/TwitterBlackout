
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Launches the application for Twitter Blackout.
 *
 * @author Samantha
 */
public class TwitterBlackout {

    private User currentUser;
    private ArrayList<Tweet> tweets;
    private ArrayList<User> users;
    private ArrayList<Subscription> subs;
    private ArrayList<Hashtag> hashtags;

    //TODO: local variables until db inacted
    private int userAccountCounter = 2;
    private int tweetAccountCounter = 4;

    /**
     * Runs the Twitter Blackout Application, which interfaces the GUI and
     * Database to display a newsfeed and user options.
     *
     * @param tweets An arraylist of tweets, or messages, pulled from the
     * database.
     * @param users An arraylist of users pulled from the database.
     * @param subscriptions An arraylist of subscriptions, which join when a
     * user follows another user, pulled from the database.
     * @param hashtags An arraylist of tweets, organized by hashtag, pulled from
     * the database.
     * @throws java.lang.CloneNotSupportedException
     */
    public void runApp(ArrayList<Tweet> tweets, ArrayList<User> users, ArrayList<Subscription> subscriptions, ArrayList<Hashtag> hashtags) throws CloneNotSupportedException {

        //Generate arraylists from database?  Not sure if should be parameters.
        this.tweets = tweets;
        this.users = users;
        this.subs = subscriptions;
        this.hashtags = hashtags;

        //first display the newsfeed.  GUI methods to launch window?
        //loop through the tweets to display the current tweets to the guest user
        displayPublicTweets();
        //News Feed Menu options
        System.out.print("Type log, signup, post, search, hashtag, or close: ");
        Scanner in = new Scanner(System.in);
        String input = in.next();
        while (!input.equals("close")) {
            if (input.equals("signup")) { //sign up for an account
                if (currentUser == null) {
                    System.out.print("Enter your first name: ");
                    String fn = in.next();
                    System.out.print("Enter your last name: ");
                    String ln = in.next();
                    System.out.print("Enter your handle: ");
                    String handle = in.next();
                    //Check to see if handle is unique
                    boolean isTaken = false;
                    for (User user : users) {
                        if (user.getHandle().equals(handle)) {
                            isTaken = true;
                        }
                    }
                    while (isTaken) {
                        System.out.print("Handle is taken. Enter your handle: ");
                        handle = in.next();
                        for (User user : users) {
                            if (user.getHandle().equals(handle)) {
                                isTaken = true;
                            } else {
                                isTaken = false;
                            }
                        }
                    }
                    System.out.print("Enter your password: ");
                    String password = in.next();
                    System.out.print("Type 'true' if your profile is public, otherwise type false: ");
                    boolean isPublic = in.nextBoolean();

                    //Then create a new user's account
                    User newUser = new User(userAccountCounter, fn, ln, handle, password, isPublic);
                    users.add(newUser); //TODO: will need db method
                    userAccountCounter++;
                    System.out.println(newUser.toString());
                } else {
                    System.out.println("You are already logged in.");
                }

            } else if (input.equals("log")) { //logs a user into or out of TB
                if (currentUser == null) { //log in
                    System.out.print("Enter your handle: ");
                    String handle = in.next();
                    System.out.print("Enter your password: ");
                    String password = in.next();
                    boolean goodLogin = false;
                    for (User user : users) {
                        //log the user in
                        if (user.getHandle().equals(handle) && user.getPassword().equals(password)) {
                            currentUser = user;
                            System.out.println("Welcome back " + handle + ". Your subscribed tweets: ");
                            displayPrivateTweets();//Display subscribed only tweets
                            goodLogin = true;
                        }
                    }
                    if (!goodLogin) {
                        System.out.println("Handle/password does not match. You could not be logged in.");
                    }
                } else { //log out
                    currentUser = null;
                    System.out.println("You have been logged out.");

                }
            } else if (input.equals("post")) { //if a user is logged in, create a post
                if (currentUser != null) {
                    System.out.print("Type a tweet to post: ");
                    in.nextLine();
                    String phrase = in.nextLine();
                    while (phrase.length() > 140) {
                        System.out.print("Your tweet is too long. Type a phrase: ");
                        phrase = in.nextLine();
                    }
                    System.out.print("Type true if this tweet is public, otherwise type false: ");
                    boolean isPublic = in.nextBoolean();
                    for (int i = 0; i < phrase.length(); i++) {
                        if (phrase.charAt(i) == '@') {
                            int endHandle = phrase.indexOf(" ", i);
                            String handle = phrase.substring((i + 1), endHandle);
                            for (User user : users) {
                                if (user.getHandle().equals(handle)) {
                                    phrase = phrase.substring(0, i) + user.getUserId() + phrase.substring(endHandle);
                                }
                            }
                        }
                    }
                    //TODO: Remove if db generated
                    Tweet newTweet = new Tweet(tweetAccountCounter, currentUser.getUserId(), phrase, isPublic, SimpleDateFormatter.getTimestamp());
                    tweets.add(newTweet);
                    displayPrivateTweets();
                } else {
                    System.out.println("Only users who are logged in may post a tweet.");
                }

            } else if (input.equals("search")) { //searches for a substring within the available tweets
                System.out.print("Type a hashtag or phrase to search: ");
                in.nextLine();
                String phrase = in.nextLine();
                boolean isFound = false;
                for (Tweet tweet : tweets) {
                    Tweet tempTweet = displayMessage(tweet);
                    if (tempTweet.getPhrase().contains(phrase)) {
                        if ((currentUser != null) && tempTweet.getPhrase().contains("@" + currentUser.getHandle())) {
                            System.out.println(hashtagLookup(tempTweet.getUserId()) + " " + tempTweet.getPhrase() + " " + tweet.getTimestamp());
                            isFound = true;
                        } else if (tempTweet.getIsPublic()) {
                            System.out.println(hashtagLookup(tempTweet.getUserId()) + " " + tempTweet.getPhrase() + " " + tweet.getTimestamp());
                            isFound = true;
                        } else if ((currentUser != null) && (isSubscribed(tempTweet.getUserId()))) {
                            System.out.println(hashtagLookup(tempTweet.getUserId()) + " " + tempTweet.getPhrase() + " " + tweet.getTimestamp());
                            isFound = true;
                        }
                    }
                }
                if (!isFound) {
                    System.out.println("No results.");
                }

            } else if (input.equals("hashtag")) { //on click of a hashtag, display all tweets with that hashtag

            } else {
                System.out.println("Incorrect input.  Try again.");
            }
            System.out.print("Type log, signup, post, search, hashtag, or close: ");
            input = in.next();
        }
    }

    /**
     * Prints the public tweets.
     */
    private void displayPublicTweets() throws CloneNotSupportedException {
        for (Tweet tweet : tweets) {
            if (tweet.getIsPublic()) {
                if (tweet.getPhrase().contains("@")) {
                    Tweet tempTweet = displayMessage(tweet);
                    System.out.println(hashtagLookup(tweet.getUserId()) + " " + tempTweet.getPhrase() + " " + tweet.getTimestamp());
                } else {
                    int tweeterId = tweet.getUserId();
                    String handle = "";
                    System.out.println(hashtagLookup(tweet.getUserId()) + " " + tweet.getPhrase() + " " + tweet.getTimestamp());
                }

            }
        }
    }

    /**
     * Prints the subscribed only tweets.
     *
     * @throws CloneNotSupportedException
     */
    private void displayPrivateTweets() throws CloneNotSupportedException {
        if (currentUser != null) {
            int userId = currentUser.getUserId();
            for (Tweet tweet : tweets) {
                if (tweet.getUserId() == currentUser.getUserId()) { //grab tweets this own user posts
                    Tweet tempTweet = displayMessage(tweet);
                    System.out.println(hashtagLookup(tweet.getUserId()) + " " + tempTweet.getPhrase() + " " + tweet.getTimestamp() + " " + tweet.getTimestamp());
                } else if (tweet.getPhrase().contains("@" + currentUser.getUserId())) {//look for messages to the user, regardless of public/private
                    Tweet tempTweet = displayMessage(tweet);
                    System.out.println(hashtagLookup(tweet.getUserId()) + " " + tempTweet.getPhrase() + " " + tweet.getTimestamp() + " " + tweet.getTimestamp());
                } else if (!tweet.getIsPublic()) { //look for tweets that another user has post to whom this user subscribes.
                    int tweeterId = tweet.getUserId();
                    for (Subscription sub : subs) {
                        if (sub.getSubscriberId() == tweeterId && sub.getSubscribeeId() == userId) {
                            System.out.println(hashtagLookup(tweet.getUserId()) + " " + tweet.getPhrase() + " " + tweet.getTimestamp() + " " + tweet.getTimestamp());
                        }
                    }
                }
            }
        }
    }

    /**
     * Searches through the available users given a userId.
     *
     * @param userId A user's userId number.
     * @return Their hashtag identifier.
     */
    private String hashtagLookup(int userId) {
        String handle = "";
        for (User user : users) { //get the handle of the user
            if (user.getUserId() == userId) {
                handle = user.getHandle();
            }
        }
        return handle;
    }

    /**
     * Checks to see if the current user is subscribed to a tweet in question.
     *
     * @param tweeterId The userId of the person tweeting a post.
     * @return True if the user subscribes to a given user.
     */
    private boolean isSubscribed(int tweeterId) {
        boolean isSubscribed = false;
        for (Subscription sub : subs) { //get the handle of the user
            if (sub.getSubscriberId() == currentUser.getUserId() && sub.getSubscribeeId() == tweeterId) {
                isSubscribed = true;
            }
        }
        return isSubscribed;
    }

    /**
     * Method checks every instance of a phrase of a tweet for the '@userId' and
     * converts it to '@handle'
     *
     * @param tweet A tweet, with a '@'. Superfluous if no message notation in
     * tweet.
     * @return The tweet with a userIds swapped for handles. Recommend storing
     * in a temp variable.
     * @throws CloneNotSupportedException If the tweet is not cloneable.
     */
    private Tweet displayMessage(Tweet tweet) throws CloneNotSupportedException {
        Tweet tempTweet = (Tweet) tweet.clone();
        String phrase = tempTweet.getPhrase();
        for (int i = 0; i < phrase.length(); i++) {
            if (phrase.charAt(i) == '@') {
                int endHandle = phrase.indexOf(" ", i);
                String uid = phrase.substring((i + 1), endHandle);
                int userId = Integer.parseInt(uid);
                for (User user : users) {
                    if (user.getUserId() == userId) {
                        phrase = phrase.substring(0, i + 1) + user.getHandle() + phrase.substring(endHandle);
                    }
                }
            }
        }
        tempTweet.setPhrase(phrase);
        return tempTweet;
    }
}
