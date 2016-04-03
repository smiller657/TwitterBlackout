/**
 * The User class establishes the attributes for a user.
 * @author Samantha
 */
public class User {
    private int userId;
    private String firstName;
    private String lastName;
    private String handle;
    private String password;
    private boolean isPublic;
    
    /**
     * Constructor for a User of Twitter Blackout.
     * @param userId The unique identifier for a user.
     * @param firstName The first name of a user.
     * @param lastName The last name of a user.
     * @param handle The handle, or online identifier, of a user.
     * @param password The password of a user.
     * @param isPublic True if the user wants to display their profile to the public.  False displays to subscribed only.
     */
    public User(int userId, String firstName, String lastName, String handle, String password, boolean isPublic) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.handle = handle;
        this.password = password;
        this.isPublic = isPublic;
    }
    /**
     * Getter for the user's unique id number.
     * @return The userId for the user.
     */
    public int getUserId() {
        return userId;
    }
    /**
     * Getter for a user's first name.
     * @return The string of a user's first name.
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * Getter for a user's last name.
     * @return The string of a user's last name.
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * Getter for a user's handle.
     * @return The string of a user's handle.
     */
    public String getHandle() {
        return handle;
    }
    /**
     * Getter for a user's password.
     * @return The string of a user's password.
     */
    public String getPassword() {
        return password;
    }
    /**
     * Getter whether the user wants their profile to be public or not.
     * @return True if the profile is public.
     */
    public boolean getIsPublic() {
        return isPublic;
    }
    /**
     * Sets a unique id number for a user.
     * @param userId A userId for a user.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }
    /**
     * Setter for the user's first name.
     * @param firstName The first name of the user.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     * Setter for the user's last name.
     * @param lastName The last name of the user.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * Setter for the user's handle.
     * @param handle The unique handle a user that appears in the News Feed.
     */
    public void setHandle(String handle) {
        this.handle = handle;
    }
    /**
     * Setter for the user's password.
     * @param password A code used for the user to log in.
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * Setter for whether the user's profile is public or not.
     * @param isPublic  True if the user wants their profile viewable to the public.
     */
    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }
}
