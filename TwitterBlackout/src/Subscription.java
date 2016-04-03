
/**
 * Creates an instance of to map a user to whom they subscribe.
 * A subscription belongs in a collection of subscriptions to display tweets.
 * 
 * @author Samantha
 */
public class Subscription {
    private int subscriptionId;
    private int subscriberId;
    private int subscribeeId;
    
    public Subscription(int subscriptionId, int subscriberId, int subscribeeId) {
        this.subscriptionId = subscriptionId;
        this.subscriberId = subscriberId;
        this.subscribeeId = subscribeeId;
    }
    /**
     * Getter for a subscription's id number set by the database.
     * @return The subscriptionId number.
     */
    public int getSubscriptionId() {
        return subscriptionId;
    }
    /**
     * Getter for a user as a subscriber.
     * @return The userId who is subscribing to another user.
     */
    public int getSubscriberId() {
        return subscriberId;
    }
    /**
     * Getter for the userId of a user to whom this user subscribes.
     * @return The userId to whom this user subscribes.
     */
    public int getSubscribeeId() {
        return subscribeeId;
    }
    /**
     * Sets the subscriptionId for a Subscription object.
     * @param subscriptionId A unique identifier set by the database.
     */
    public void setSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
    }
    /**
     * Sets the userId for a subscriber.
     * @param subscriberId The userId for a subscriber.
     */
    public void setSubscriberId(int subscriberId) {
        this.subscriberId = subscriberId;
    }
    /**
     * Sets the userid to whom a user subscribes.
     * @param subscribeeId The userId to whom this user subscribes.
     */
    public void subScribeeId(int subscribeeId) {
        this.subscribeeId = subscribeeId;
    }
}
