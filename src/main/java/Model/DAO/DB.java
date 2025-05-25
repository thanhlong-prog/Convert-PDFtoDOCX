package Model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/convert_pdf2docx", "root", "");
    }
}
