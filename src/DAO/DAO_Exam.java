package DAO;

import DTO.DTO_Exam;
import ConnectDB.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO_Exam implements DAOInterface<DTO_Exam> {

    @Override
    public int insert(DTO_Exam exam) {
        try {
            Connection con = JDBCUtil.getConnectDB();
            String sql = "INSERT INTO exams (testCode, exOrder, exCode, ex_quesIDs) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, exam.getTestCode());
            pst.setString(2, exam.getExOrder());
            pst.setString(3, exam.getExCode());
            pst.setString(4, exam.getEx_quesIDs());
            int result = pst.executeUpdate();
            JDBCUtil.close(con);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(DTO_Exam exam) {
        try {
            Connection con = JDBCUtil.getConnectDB();
            String sql = "UPDATE exams SET testCode = ?, exOrder = ?, exCode = ?, ex_quesIDs = ? WHERE exID = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, exam.getTestCode());
            pst.setString(2, exam.getExOrder());
            pst.setString(3, exam.getExCode());
            pst.setString(4, exam.getEx_quesIDs());
            pst.setInt(5, exam.getExID());
            int result = pst.executeUpdate();
            JDBCUtil.close(con);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(int exID) {
        try {
            Connection con = JDBCUtil.getConnectDB();
            String sql = "DELETE FROM exams WHERE exID = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, exID);
            int result = pst.executeUpdate();
            JDBCUtil.close(con);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public DTO_Exam selectById(String exID) {
        try {
            Connection con = JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM exams WHERE exID = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(exID));
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("exID");
                String testCode = rs.getString("testCode");
                String exOrder = rs.getString("exOrder");
                String exCode = rs.getString("exCode");
                String ex_quesIDs = rs.getString("ex_quesIDs");
                DTO_Exam exam = new DTO_Exam(id, testCode, exOrder, exCode, ex_quesIDs);
                JDBCUtil.close(con);
                return exam;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<DTO_Exam> getAllData() {
        ArrayList<DTO_Exam> list = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM exams";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int exID = rs.getInt("exID");
                String testCode = rs.getString("testCode");
                String exOrder = rs.getString("exOrder");
                String exCode = rs.getString("exCode");
                String ex_quesIDs = rs.getString("ex_quesIDs");
                DTO_Exam exam = new DTO_Exam(exID, testCode, exOrder, exCode, ex_quesIDs);
                list.add(exam);
            }
            JDBCUtil.close(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int getAutoIncrement() {
        try {
            Connection con = JDBCUtil.getConnectDB();
            String sql = "SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA = 'quanlytracnghiem' AND TABLE_NAME = 'exams'";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int autoIncrement = rs.getInt("AUTO_INCREMENT");
                JDBCUtil.close(con);
                return autoIncrement;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}