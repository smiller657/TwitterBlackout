
import java.util.HashMap;


/**
 * Creates a collection of subscriptions to map a user to their subscribed users.
 * @author Samantha
 */
public class Subscriptions {
    //May need to be an ArrayList?
    private HashMap subscriptions;
    /**
     * Adds a user-to-user subscription to the collection of subscriptions.
     * @param subscription A subscription when a user follows another user.
     */
    public void addSubscription(Subscription subscription) {
        // database insert method
        // will this require a subscription constructor?
        subscriptions.put(subscription.getSubscriptionId(), subscription);
    }
    /**
     * Removes a subscription from the collection of subscriptions.
     * @param subscription A subscription to remove from the collection.
     */
    public void deletesSubscription(Subscription subscription) {
        //database delete method
        subscriptions.remove(subscription.getSubscriptionId(), subscription);
    }
}
