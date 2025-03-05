package DAO;

import ConnectDB.JDBCUtil;
import DTO.DTO_Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


public class DAO_Result implements DAOInterface<DTO.DTO_Result>{

    @Override
    public int insert(DTO_Result kq) {
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
    
    
        public boolean hasSubmitted(int userID, String exCode) {
        try {
            Connection con = JDBCUtil.getConnectDB();
            String sql = "SELECT COUNT(*) FROM result WHERE userID = ? AND exCode = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, userID);
            pst.setString(2, exCode);
            ResultSet rs = pst.executeQuery();
            boolean exists = false;
            if (rs.next()) {
                exists = rs.getInt(1) > 0; // Nếu có ít nhất 1 bản ghi trong result
            }
            rs.close();
            pst.close();
            con.close();
            return exists;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // đếm lượt làm đa tham gia của người dùng
    public int CountResult(String testCode){
        int count = 0;
        try {
            Connection con = JDBCUtil.getConnectDB();
            String sql = "SELECT COUNT(*) FROM result WHERE result.exCode IN (SELECT e.exCode FROM exams e WHERE e.testCode = ?)";
    
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, testCode);
    
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
    
            rs.close();
            pst.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
    
    // đếm lượt làm còn lại của người dùng đối với đề thi
    public int CountResult(String testCode, int userID){
        int count = 0;
        try {
            Connection con = JDBCUtil.getConnectDB();
                String sql = "SELECT COUNT(*) FROM result WHERE userID = ?"
                        + " AND result.exCode"
                        + " IN (SELECT e.exCode FROM exams e WHERE e.testCode = ?)";
    
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, userID);
            pst.setString(2, testCode);
    
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
    
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

    public ArrayList<DTO_Result> getAllData(String testCode) {
        ArrayList<DTO_Result> list= new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM result r"
                    + " JOIN exams e ON r.exCode = e.exCode"
                    + " WHERE e.testCode =? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, testCode);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                DTO_Result res = new DTO_Result();
                res.setRsNum(rs.getInt("rs_num"));
                res.setUserID(rs.getInt("userID"));
                res.setExCode(rs.getString("exCode"));
                res.setRsAnswer(rs.getString("rs_anwsers"));
                res.setRsMask(rs.getDouble("rs_mark"));
                res.setRsDate(rs.getString("rs_date"));
                list.add(res);
            }
            JDBCUtil.close(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public ArrayList<DTO_Result> getAllData(int userID, String testCode) {
        ArrayList<DTO_Result> list= new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM result r"
                    + " JOIN exams e ON r.exCode = e.exCode"
                    + " WHERE r.userID=? AND e.testCode =? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, userID);
            pst.setString(2, testCode);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                DTO_Result res = new DTO_Result();
                res.setRsNum(rs.getInt("rs_num"));
                res.setUserID(userID);
                res.setExCode(rs.getString("exCode"));
                res.setRsAnswer(rs.getString("rs_anwsers"));
                res.setRsMask(rs.getDouble("rs_mark"));
                res.setRsDate(rs.getString("rs_date"));
                list.add(res);
            }
            JDBCUtil.close(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
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
