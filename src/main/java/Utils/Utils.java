package Utils;

import java.io.File;

public class Utils {
     public static boolean deleteFile(String path) {
        try {
            File f = new File(path);
            if (f.exists()) {
                return f.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
