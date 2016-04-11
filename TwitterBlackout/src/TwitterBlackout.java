
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

    public void runApp(ArrayList<Tweet> tweets, ArrayList<User> users, ArrayList<Subscription> subscriptions, ArrayList<Hashtag> hashtags) {

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
                    //TODO: Remove if db generated
                    Date dNow = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyymmdd");
                    Tweet newTweet = new Tweet(tweetAccountCounter, currentUser.getUserId(), phrase, isPublic, sdf.format(dNow));
                    tweets.add(newTweet);
                    displayPrivateTweets();
                } else {
                    System.out.println("Only users who are logged in may post a tweet.");
                }

            } else if (input.equals("search")) { //searches for a substring within the available tweets

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
    private void displayPublicTweets() {
        for (Tweet tweet : tweets) {
            if (tweet.getIsPublic()) {
                int tweeterId = tweet.getUserId();
                String handle = "";
                for (User user : users) { //get the handle of the user
                    if (user.getUserId() == tweeterId) {
                        handle = user.getHandle();
                    }
                }
                System.out.println(handle + " " + tweet.getPhrase());
            }
        }
    }

    /**
     * Prints the subscribed only tweets.
     */
    private void displayPrivateTweets() {
        if (currentUser != null) {
            int userId = currentUser.getUserId();
            for (Tweet tweet : tweets) {
                if (tweet.getUserId() == currentUser.getUserId()) { //grab tweets this own user posts
                    System.out.println(currentUser.getHandle() + " " + tweet.getPhrase());
                } else if (!tweet.getIsPublic()) { //look for tweets that another user has post to whom this user subscribes.
                    int tweeterId = tweet.getUserId();
                    String handle = "";
                    for (Subscription sub : subs) {
                        if (sub.getSubscriberId() == tweeterId && sub.getSubscribeeId() == userId) {
                            for (User user : users) { //get the handle of the user
                                if (user.getUserId() == tweeterId) {
                                    handle = user.getHandle();
                                    System.out.println(handle + " " + tweet.getPhrase());
                                }
                            }
                        }
                    }
                }
            }
        }

    }

}
