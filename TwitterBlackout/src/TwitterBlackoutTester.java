

/**
 * Establishes a work space to test out various parts of the code.
 * @author Samantha
 */
public class TwitterBlackoutTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        User sm = new User(0, "Sam", "Miller", "swordgirl", "password", true);
        /* Tweet sm1 = new Tweet(0, 0, "sam's tweet 1", true, "160101");
        Tweet sm2 = new Tweet(1, 0, "sam's tweet 2", true, "160102");
        Tweets tweets = new Tweets();
        tweets.addTweet(sm1);
        tweets.addTweet(sm2); */
        
        // test connection
        /* try {
            Connect c = new Connect();
            System.out.println("Connection established");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Connection failed");
        } */
        
        // test insert into user table
        int uId = sm.getUserId();
        String fN = sm.getFirstName();
        String lN = sm.getLastName();
        String h = sm.getHandle();
        String pw = sm.getPassword();
        boolean iiP = sm.getIsPublic();
        int iP;
        if (iiP) 
            iP = 1;
        else
            iP = 0;
            
        Database.addUser(uId, fN, lN, h, pw, iP);
        
        
    }
    
}
