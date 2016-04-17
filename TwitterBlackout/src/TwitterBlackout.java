
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    //TODO: local variables until db inacted
    private int userAccountCounter = 3;
    private int tweetAccountCounter = 6;
    private int subCounter = 1;

    /**
     * Runs the Twitter Blackout Application, which interfaces the GUI and
     * Database to display a newsfeed and user options.
     *
     * @param tweets An arraylist of tweets, or messages, pulled from the
     * database.
     * @param users An arraylist of users pulled from the database.
     * @param subscriptions An arraylist of subscriptions, which join when a
     * user follows another user, pulled from the database.
     * @throws java.lang.CloneNotSupportedException
     */
    public void runApp(ArrayList<Tweet> tweets, ArrayList<User> users, ArrayList<Subscription> subscriptions) throws CloneNotSupportedException, Exception {

        //Generate arraylists from database?  Not sure if should be parameters.
        this.tweets = tweets;
        this.users = users;
        this.subs = subscriptions;

        //first display the newsfeed.  GUI methods to launch window?
        //loop through the tweets to display the current tweets to the guest user
        displayPublicTweets();
        //News Feed Menu options
        System.out.print("Type log, signup, post, search, profile, or close: ");
        Scanner in = new Scanner(System.in);
        String input = in.next().toLowerCase();
        while (!input.equals("close")) {
            if (input.equals("signup")) { //sign up for an account
                if (currentUser == null) {
                    System.out.print("Enter your first name: ");
                    String fn = in.next();
                    System.out.print("Enter your last name: ");
                    String ln = in.next();
                    System.out.print("Enter your handle: ");
                    String handle = in.next();
                    
                    // Populate user list
                    Database.getUser(users);
                    
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
                    User newUser = new User(0, fn, ln, handle, password, isPublic);
                    Database.addUser(newUser); // Updated to database
                    Database.getUser(users); // Update users list
                    //userAccountCounter++;
                    System.out.println("You have created an account!  Now go log in!");
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
                            System.out.println("Welcome back " + handle + ".");
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
                    displayPublicTweets();
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
                    //Regex idea found at http://stackoverflow.com/questions/600733/using-java-to-find-substring-of-a-bigger-string-using-regular-expression
                    Pattern pattern = Pattern.compile("@\\d+\\W");
                    Matcher m = pattern.matcher(phrase);
                    while (m.find()) {
                        String fullHandle = m.group(); //@name.
                        int start = phrase.indexOf(fullHandle);
                        int fullHandleLength = fullHandle.length();
                        int end = start + fullHandleLength - 1;
                        String userHandle = fullHandle.substring(1, fullHandleLength - 1);
                        for (User user : users) {
                            if (user.getHandle().equals(userHandle)) {
                                phrase = phrase.substring(0, start + 1) + user.getUserId() + phrase.substring(end);
                            }
                        }
                    }
                    //TODO: Remove if db generated
                    Tweet newTweet = new Tweet(0, currentUser.getUserId(), phrase, isPublic, SimpleDateFormatter.getTimestamp());
                    //tweetAccountCounter++;
                    Database.addTweet(newTweet);
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
                        if ((currentUser != null) && tempTweet.getPhrase().contains("@" + currentUser.getHandle())) { //if the tweet is a message to a current user
                            System.out.println(handleLookup(tempTweet.getUserId()) + " " + tempTweet.getPhrase() + " " + tweet.getTimestamp());
                            isFound = true;
                        } else if (tempTweet.getIsPublic()) { //if the tweet is public
                            System.out.println(handleLookup(tempTweet.getUserId()) + " " + tempTweet.getPhrase() + " " + tweet.getTimestamp());
                            isFound = true;
                        } else if ((currentUser != null) && (isSubscribed(tempTweet.getUserId()))) { //if user subscribes to another user.
                            System.out.println(handleLookup(tempTweet.getUserId()) + " " + tempTweet.getPhrase() + " " + tweet.getTimestamp());
                            isFound = true;
                        } else if ((currentUser != null) && (tempTweet.getUserId() == currentUser.getUserId())) { //if the user has posted a tweet.
                            System.out.println(handleLookup(tempTweet.getUserId()) + " " + tempTweet.getPhrase() + " " + tweet.getTimestamp());
                            isFound = true;
                        }
                    }
                }
                if (!isFound) {
                    System.out.println("No results.");
                }

            } else if (input.equals("profile")) { //Search for a Profile
                System.out.print("Type the handle for the profile you wish to see: ");
                String handle = in.next();
                User userProfile = null;
                for (User user : users) {
                    if (user.getHandle().equals(handle)) {
                        userProfile = user;
                    }
                }
                if (userProfile == null) {
                    System.out.println("User does not exist.");
                } else if (currentUser != null && userProfile.getUserId() == currentUser.getUserId()) { //User is looking at own profile
                    System.out.println("First Name: " + userProfile.getFirstName());
                    System.out.println("Last Name: " + userProfile.getLastName());
                    System.out.println("Handle: " + userProfile.getHandle());
                    System.out.println("Password: " + userProfile.getPassword());
                    System.out.println("Public profile: " + userProfile.getIsPublic());
                    System.out.print("Would you like to update your information? Type y or n: ");
                    String ans = in.next().toLowerCase();
                    if (ans.equals("y")) { //update profile
                        System.out.print("Enter your first name: ");
                        String newFn = in.next();
                        System.out.print("Enter your last name: ");
                        String newLn = in.next();
                        System.out.print("Enter your handle: ");
                        String newHandle = in.next();
                        //Check to see if handle is unique
                        boolean isTaken = false;
                        for (User user : users) {
                            if (user.getHandle().equals(newHandle)) {
                                isTaken = true;
                            }
                        }
                        while (isTaken) {
                            System.out.print("Handle is taken. Enter your handle: ");
                            newHandle = in.next();
                            for (User user : users) {
                                if (user.getHandle().equals(newHandle)) {
                                    isTaken = true;
                                } else {
                                    isTaken = false;
                                }
                            }
                        }
                        System.out.print("Enter your password: ");
                        String newPassword = in.next();
                        System.out.print("Type 'true' if your profile is public, otherwise type false: ");
                        boolean newIsPublic = in.nextBoolean();
                        //Update the user's profile
                        //TODO: Add DB method
                        for (User user : users) {
                            if (user.getUserId() == currentUser.getUserId()) {
                                user.setFirstName(newFn);
                                user.setLastName(newLn);
                                user.setHandle(newHandle);
                                user.setPassword(newPassword);
                                user.setIsPublic(newIsPublic);
                                currentUser = user;
                            }
                        }
                    }
                } else { //User is looking at another user's profile
                    if (userProfile.getIsPublic() || (currentUser != null && isSubscribed(userProfile.getUserId()))) {
                        System.out.println("First Name: " + userProfile.getFirstName());
                        System.out.println("Last Name: " + userProfile.getLastName());
                        System.out.println("Handle: " + userProfile.getHandle());
                        if (currentUser != null) {
                            System.out.print("Would you like to unsubscribe to this user? Type y or n: ");
                            String ans = in.next().toLowerCase();
                            if (ans.equals("y") && currentUser != null) {
                                //TODO: Will need a vastly different update for the db
                                for (Subscription sub : subs) {
                                    if (sub.getSubscriberId() == currentUser.getUserId() && sub.getSubscribeeId() == userProfile.getUserId()) {
                                        subs.remove(sub);
                                    }
                                }
                                subCounter--;
                                System.out.println("Your are now unsubscribed to " + userProfile.getHandle() + ".");
                            }
                        }
                    } else { //That user's profile is not public
                        System.out.println("Handle: " + userProfile.getHandle());
                        System.out.print("Would you like to subscribe to this user? Type y or n: ");
                        String ans = in.next().toLowerCase();
                        if (ans.equals("y") && currentUser != null) {
                            Subscription newSub = new Subscription(subCounter, currentUser.getUserId(), userProfile.getUserId());
                            subCounter++;
                            System.out.println("Your are now subscribed to " + userProfile.getHandle() + ".");
                        } else if (ans.equals("y") && currentUser == null) {
                            System.out.println("Too bad. You're not logged in.");
                        }
                    }
                }
                if (currentUser != null) {
                    displayPrivateTweets();
                } else {
                    displayPublicTweets();
                }

            } else {
                System.out.println("Incorrect input.  Try again.");
            }
            System.out.print("Type log, signup, post, search, profile, or close: ");
            input = in.next().toLowerCase();
        }
    }

    /**
     * Prints the public tweets.
     */
    private void displayPublicTweets() throws CloneNotSupportedException {
        System.out.println("Your public tweets:");
        for (Tweet tweet : tweets) {
            if (tweet.getIsPublic()) {
                if (tweet.getPhrase().contains("@")) {
                    Tweet tempTweet = displayMessage(tweet);
                    System.out.println(handleLookup(tweet.getUserId()) + " " + tempTweet.getPhrase() + " " + tweet.getTimestamp());
                } else {
                    int tweeterId = tweet.getUserId();
                    String handle = "";
                    System.out.println(handleLookup(tweet.getUserId()) + " " + tweet.getPhrase() + " " + tweet.getTimestamp());
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
        System.out.println("Your subscribed only tweets:");
        if (currentUser != null) {
            int userId = currentUser.getUserId();
            for (Tweet tweet : tweets) {
                if (tweet.getUserId() == currentUser.getUserId()) { //grab tweets this own user posts
                    Tweet tempTweet = displayMessage(tweet);
                    System.out.println(handleLookup(tweet.getUserId()) + " " + tempTweet.getPhrase() + " " + tweet.getTimestamp());
                } else if (tweet.getPhrase().contains("@" + currentUser.getUserId())) {//look for messages to the user, regardless of public/private
                    Tweet tempTweet = displayMessage(tweet);
                    System.out.println(handleLookup(tweet.getUserId()) + " " + tempTweet.getPhrase() + " " + tweet.getTimestamp());
                } else if (!tweet.getIsPublic() && isSubscribed(tweet.getUserId())) { //look for tweets that another user has post to whom this user subscribes.
                    System.out.println(handleLookup(tweet.getUserId()) + " " + tweet.getPhrase() + " " + tweet.getTimestamp());
                }
            }
        }
    }

    /**
     * Searches through the available users given a userId.
     *
     * @param userId A user's userId number.
     * @return Their handle identifier.
     */
    private String handleLookup(int userId) {
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
        //Regex idea found at http://stackoverflow.com/questions/600733/using-java-to-find-substring-of-a-bigger-string-using-regular-expression
        Pattern pattern = Pattern.compile("@\\d+\\W");
        Matcher m = pattern.matcher(phrase);
        while (m.find()) {
            String fullHandle = m.group();
            int start = phrase.indexOf(fullHandle);
            int fullHandleLength = fullHandle.length();
            int end = start + fullHandleLength - 1;
            String uid = fullHandle.substring(1, fullHandleLength - 1);
            int userId = Integer.parseInt(uid);
            for (User user : users) {
                if (user.getUserId() == userId) {
                    phrase = phrase.substring(0, start + 1) + user.getHandle() + phrase.substring(end);
                }
            }
        }
        tempTweet.setPhrase(phrase);
        return tempTweet;
    }
}
