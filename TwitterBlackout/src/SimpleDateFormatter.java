
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * Formats the date for timestamping a Tweet.
 *
 * @author Mike
 */
class SimpleDateFormatter {
    
  public static String getTimestamp() {
      LocalDateTime tStamp = LocalDateTime.now();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
      String timestamp = tStamp.format(formatter);
      return timestamp;
  }  
}
