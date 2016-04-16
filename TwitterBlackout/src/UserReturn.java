
import java.util.ArrayList;

/**
 *
 * @author mdavisso
 */
public class UserReturn {

    public static ArrayList mailbox(String lpassword, String lemail, String lfname, String llname, String lhandle) {
        ArrayList<String> mb = new ArrayList();
        mb.add(lpassword);
        mb.add(lemail);
        mb.add(lfname);
        mb.add(llname);
        mb.add(lhandle);
        return mb;
    }

    public static ArrayList mailbox2(String opassword, String ohandle) {
        ArrayList<String> mb2 = new ArrayList();
        mb2.add(ohandle);
        mb2.add(opassword);
        return mb2;
    }

    public static String refresher() {
//* will need data base consultation for this part
/* get info from data table and pass it into list, also user data.

         */
        return j;
    }
}
