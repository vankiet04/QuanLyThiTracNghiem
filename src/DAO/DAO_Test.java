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
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public int delete(int t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public DTO_Test selectById(String t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectById'");
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
            String sql = "SELECT * FROM test";
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
    
}
