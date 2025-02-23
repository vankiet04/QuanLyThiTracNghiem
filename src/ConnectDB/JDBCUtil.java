
package ConnectDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class JDBCUtil {
    public static Connection getConnectDB(){
        Connection res = null;
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            String url = "jdbc:mySQL://localhost:3306/tracnghiem";
            String name = "root";
            String pass = "";

            res = DriverManager.getConnection(url, name, pass);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Lỗi connect csdl", "Lỗi",
            JOptionPane.ERROR_MESSAGE);
        }
        return res;
        
    }
        public static void close(Connection c) {
        try {
            if (c != null) 
                c.close();
        } catch (SQLException e) {
        }
    }
}
