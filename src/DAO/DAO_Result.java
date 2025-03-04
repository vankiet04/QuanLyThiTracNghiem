package DAO;

import ConnectDB.JDBCUtil;
import DTO.DTO_Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


public class DAO_Result implements DAOInterface<DTO.DTO_Result>{

    @Override
public int insert(DTO_Result kq) {
    System.out.println("DEBUG rs_anwsers: " + kq.getRsAnswer());

    try {
        Connection con = JDBCUtil.getConnectDB();
        String sql = "INSERT INTO result (userID, exCode, rs_anwsers, rs_mark, rs_date) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(sql);
       

        pst.setInt(1, kq.getUserID());
        pst.setString(2, kq.getExCode());
        pst.setString(3, kq.getRsAnswer());
        pst.setDouble(4, kq.getRsMask());
        pst.setString(5, kq.getRsDate());     

        int result = pst.executeUpdate();
        JDBCUtil.close(con);
        return result;
    } catch (Exception e) {
        e.printStackTrace();
    }
    return 0;
}

    
    public int demCauDung(List<Integer> listAwID) {
        int count = 0;
        try {
            Connection con = JDBCUtil.getConnectDB();
            String placeholders = String.join(",", Collections.nCopies(listAwID.size(), "?"));
            String sql = "SELECT COUNT(*) FROM answers WHERE isRight = 1 AND awID IN (" + placeholders + ")";
            
            PreparedStatement pst = con.prepareStatement(sql);
            
            int index = 1;
            for (int awID : listAwID) 
                pst.setInt(index++, awID);
    
            // Use standard java.sql.ResultSet without casting
            java.sql.ResultSet rs = pst.executeQuery();
            if (rs.next()) 
                count = rs.getInt(1); 
            rs.close();
            pst.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
    
    



    
    @Override
    public int update(DTO_Result t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(int t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<DTO_Result> getAllData() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public DTO_Result selectById(String t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getAutoIncrement() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
