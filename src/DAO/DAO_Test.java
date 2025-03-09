/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import ConnectDB.JDBCUtil;
import DTO.DTO_Answer;
import DTO.DTO_Test;
import DTO.DTO_User;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KIET
 */
public class DAO_Test implements DAOInterface<DTO_Test> {

    @Override
public int insert(DTO_Test t) {
    try {
        Connection con = JDBCUtil.getConnectDB();
        String sql = "INSERT INTO test (testCode, testTitle, tpID, testTime, num_easy, num_medium, num_diff, testLimit, testDate, testStatus) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, t.getTestCode());
        pst.setString(2, t.getTestTitle());
        pst.setInt(3, t.getTpID());
        pst.setInt(4, t.getTestTime());
        pst.setInt(5, t.getNumEasy());
        pst.setInt(6, t.getNumMedium());
        pst.setInt(7, t.getNumDiff());
        pst.setInt(8, t.getTestLimit());
        pst.setTimestamp(9, java.sql.Timestamp.valueOf(t.getTestDate()));
        pst.setInt(10, t.getTestStatus());
        int result = pst.executeUpdate();
        JDBCUtil.close(con);
        return result;
    } catch (Exception e) {
        e.printStackTrace();
        return 0;
    }
}

    @Override
    public int update(DTO_Test t) {
        // TODO Auto-generated method stub
        try {
            Connection con = JDBCUtil.getConnectDB();
            String sql = "UPDATE test SET testCode=?, testTitle=?, tpID=?, testTime=?, num_easy=?, num_medium=?, num_diff=?, testLimit=?, testDate=?, testStatus=? WHERE testID=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getTestCode());
            pst.setString(2, t.getTestTitle());
            pst.setInt(3, t.getTpID());
            pst.setInt(4, t.getTestTime());
            pst.setInt(5, t.getNumEasy());
            pst.setInt(6, t.getNumMedium());
            pst.setInt(7, t.getNumDiff());
            pst.setInt(8, t.getTestLimit());
            pst.setTimestamp(9, java.sql.Timestamp.valueOf(t.getTestDate()));
            pst.setInt(10, t.getTestStatus());
            pst.setInt(11, t.getTestID());
            
            int result = pst.executeUpdate();
            JDBCUtil.close(con);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(int testID) {
        try {
            Connection con = JDBCUtil.getConnectDB();
            // Instead of deleting, update the status to 0
            String sql = "UPDATE test SET testStatus = 0 WHERE testID = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, testID);
            
            int result = pst.executeUpdate();
            JDBCUtil.close(con);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    
    // lấy bài thi theo testCode có trạng thái == 1
    @Override
    public DTO_Test selectById(String t) {
        DTO_Test res = null;
        try {
            Connection con = JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM test WHERE testCode=? AND testStatus=1";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                int testID = rs.getInt("testID");
                String testCode = rs.getString("testCode");
                String testTitle = rs.getString("testTitle");
                int tpID = rs.getInt("tpID");
                int testTime = rs.getInt("testTime");
                int numEasy = rs.getInt("num_easy");
                int numMedium = rs.getInt("num_medium");
                int numDiff = rs.getInt("num_diff");
                int testLimit = rs.getInt("testLimit");
                java.sql.Timestamp testDate = rs.getTimestamp("testDate");
                int testStatus = rs.getInt("testStatus");
                res = new DTO_Test(testID, testCode, testTitle, tpID, testTime, numEasy, numMedium, numDiff, testLimit, testDate.toLocalDateTime(), testStatus);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    @Override
    public int getAutoIncrement() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAutoIncrement'");
    }

    @Override
    public ArrayList<DTO_Test> getAllData() {
        ArrayList<DTO_Test> list = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM test WHERE testStatus = 1";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int testID = rs.getInt("testID");
                String testCode = rs.getString("testCode");
                String testTitle = rs.getString("testTitle");
                int tpID = rs.getInt("tpID");
                int testTime = rs.getInt("testTime");
                int numEasy = rs.getInt("num_easy");
                int numMedium = rs.getInt("num_medium");
                int numDiff = rs.getInt("num_diff");
                int testLimit = rs.getInt("testLimit");
                java.sql.Timestamp testDate = rs.getTimestamp("testDate");
                int testStatus = rs.getInt("testStatus");
                DTO_Test test = new DTO_Test(testID, testCode, testTitle, tpID, testTime, numEasy, numMedium, numDiff, testLimit, testDate.toLocalDateTime(), testStatus);
                list.add(test);
                System.out.println(test);
            }
            
            JDBCUtil.close(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    

    // tìm theo tiêu đề + mã bài
    public ArrayList<DTO_Test> searchData(String searchText) {
        ArrayList<DTO_Test> list = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM test WHERE (testTitle LIKE ? OR testCode LIKE ?) AND testStatus = 1";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, "%" + searchText + "%");
            pst.setString(2, "%" + searchText + "%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int testID = rs.getInt("testID");
                String testCode = rs.getString("testCode");
                String testTitle = rs.getString("testTitle");
                int tpID = rs.getInt("tpID");
                int testTime = rs.getInt("testTime");
                int numEasy = rs.getInt("num_easy");
                int numMedium = rs.getInt("num_medium");
                int numDiff = rs.getInt("num_diff");
                int testLimit = rs.getInt("testLimit");
                java.sql.Timestamp testDate = rs.getTimestamp("testDate");
                int testStatus = rs.getInt("testStatus");
                DTO_Test test = new DTO_Test(testID, testCode, testTitle, tpID, testTime, numEasy, numMedium, numDiff, testLimit, testDate.toLocalDateTime(), testStatus);
                list.add(test);
            }
            
            JDBCUtil.close(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // lấy số lượng câu hỏi của bài thi 
    public int getSoLuongCauHoi(String testCode) {
        int res = 0;
        try {
            Connection con = JDBCUtil.getConnectDB();
            String sql = "SELECT SUM(num_easy + num_medium + num_diff) AS total FROM test WHERE testCode = ? AND testStatus = 1";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, testCode);
            ResultSet rs = pst.executeQuery();
            if (rs.next())
                res = rs.getInt("total");
            JDBCUtil.close(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }



}
