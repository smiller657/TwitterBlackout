
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * Formats the date for timestamping a Tweet.
 *
 * @author mrey4_000
 */
class SimpleDateFormatter {

    /**
     * Instantiates a timestamp based on a pattern.
     * @return The timestamp when called
     */
    public static String getTimestamp() {
        LocalTime tStamp = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String timestamp = tStamp.format(formatter);
        return timestamp;
    }
}
