
package DAO;

import ConnectDB.JDBCUtil;
import DTO.DTO_User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class DAO_User implements DAOInterface<DTO.DTO_User>{
    public static DAO_User getInstance(){
        return new DAO_User();
    }
    
    public int Login(String user, String pass){
        int res=0;
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM users WHERE userName=? AND userPassword=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, user);
            pst.setString(2, pass);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                res = 1;
            }
            JDBCUtil.close(con);

        } catch (Exception e) {
        }
        
        return res;
    }
    
    public DTO_User GetInfo(String user, String pass){
        DTO_User res = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM users WHERE userName=? AND userPassword=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, user);
            pst.setString(2, pass);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("userID");
                String email = rs.getString("userEmail");
                String fullName = rs.getString("userFullName");
                boolean isAdmin = rs.getBoolean("isAdmin");
                res = new DTO_User(id, user, pass, email, fullName, isAdmin);
            }
            JDBCUtil.close(con);
        } catch (Exception e) {
        }
        return res;
    }
    
    @Override
    public int insert(DTO_User t) {
        int x =0;
        try{
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "INSERT INTO `users`(userName, userEmail, userPassword, userFullName, isAdmin) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getUserName());
            pst.setString(2, t.getEmail());
            pst.setString(3, t.getPass());
            pst.setString(4, t.getFullName());
            pst.setInt(5, 0);
            
            int res = pst.executeUpdate();
            if (res >0)
                x=1;
            
            JDBCUtil.close(con);
        }catch (Exception e){
            e.printStackTrace();
        }
        return x;
    }

    @Override
    public int update(DTO_User t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(int t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<DTO_User> getAllData() {
        ArrayList<DTO_User> list = new ArrayList<>();
        try {
        Connection con = JDBCUtil.getConnectDB();
        String sql = "SELECT * FROM users";
        PreparedStatement pst = con.prepareStatement(sql);
        
        ResultSet rs = pst.executeQuery();
        
        while (rs.next()) {
                int id = rs.getInt("userID");
                String email = rs.getString("userEmail");
                String fullName = rs.getString("userFullName");
                String userName = rs.getString("userName");
                String pass = rs.getString("userPassword");
                boolean isAdmin = rs.getBoolean("isAdmin");  
                list.add(new DTO_User(id, userName, pass, email, fullName, isAdmin));
        }
        // Đóng kết nối
        JDBCUtil.close(con);
    } catch (Exception e) {
        e.printStackTrace(); // Hiển thị lỗi chi tiết
    }
    return list;
    }

    @Override
    public DTO_User selectById(String t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getAutoIncrement() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
