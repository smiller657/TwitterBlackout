
/**
 * This abstract class contains similar fields/methods between the ProfileSignUp page, ProfileOwn, and ProfileOther.
 * @author Samantha
 */
public abstract class ProfileAbs {
    
    public String getFirstName(User user) {
        return user.getFirstName();
    }
    
    public String getLastName(User user) {
        return user.getLastName();
    }
    
    public String getHandle(User user) {
        return user.getHandle();
    }
    
    public boolean getIsPublic(User user) {
        return user.getIsPublic();
    }
}
