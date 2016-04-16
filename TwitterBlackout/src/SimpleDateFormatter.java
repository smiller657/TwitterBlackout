
import java.time.*;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author Samantha
 */
class SimpleDateFormatter {
    
  public static String getTimestamp() {
      LocalDateTime tStamp = LocalDateTime.now();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
      String timestamp = tStamp.format(formatter);
      return timestamp;
  }  
}
