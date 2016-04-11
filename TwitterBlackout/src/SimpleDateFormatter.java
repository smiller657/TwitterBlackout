
import java.time.*;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author Samantha
 */
class SimpleDateFormatter {
    
  public static String getTimestamp() {
      LocalTime tStamp = LocalTime.now();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
      String timestamp = tStamp.format(formatter);
      return timestamp;
  }  
}
