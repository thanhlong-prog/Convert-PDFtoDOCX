package Utils;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import Model.DAO.ConvertJobDAO;

public class Utils {
    private static final Logger logger = Logger.getLogger(ConvertJobDAO.class.getName());

    public static boolean deleteFile(String path) {
        try {
            File f = new File(path);
            if (f.exists()) {
                return f.delete();
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error in deleteFile method", e);
        }
        return false;
    }
}
