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
            String sql = "UPDATE exams SET testCode = ?, exOrder = ?, ex_quesIDs = ? WHERE exCode = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, exam.getTestCode());
            pst.setString(2, exam.getExOrder());
            pst.setString(3, exam.getEx_quesIDs());
            pst.setString(4, exam.getExCode());
            int result = pst.executeUpdate();
            JDBCUtil.close(con);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public int delete(int exCode) {
        try {
            Connection con = JDBCUtil.getConnectDB();
            String sql = "UPDATE exams SET status = 0 WHERE exCode = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, exCode);
            int result = pst.executeUpdate();
            JDBCUtil.close(con);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public DTO_Exam selectById(String code) {
        try {
            Connection con = JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM exams WHERE exCode = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, code);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String testCode = rs.getString("testCode");
                String exOrder = rs.getString("exOrder");
                String exCode = rs.getString("exCode");
                String ex_quesIDs = rs.getString("ex_quesIDs");
                DTO_Exam exam = new DTO_Exam(testCode, exOrder, exCode, ex_quesIDs);
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
                String testCode = rs.getString("testCode");
                String exOrder = rs.getString("exOrder");
                String exCode = rs.getString("exCode");
                String ex_quesIDs = rs.getString("ex_quesIDs");
                DTO_Exam exam = new DTO_Exam(testCode, exOrder, exCode, ex_quesIDs);
                list.add(exam);
            }
            JDBCUtil.close(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    
    // lấy  tất cả bài thi theo mã bài testCode
    public ArrayList<DTO_Exam> getAllData(String code) {
        ArrayList<DTO_Exam> list = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM exams WHERE testCode = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, code);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String testCode = rs.getString("testCode");
                String exOrder = rs.getString("exOrder");
                String exCode = rs.getString("exCode");
                String ex_quesIDs = rs.getString("ex_quesIDs");
                DTO_Exam exam = new DTO_Exam(testCode, exOrder, exCode, ex_quesIDs);
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
    // lấy  tất cả bài thi theo mã bài testCode
    public ArrayList<DTO_Exam> searchData(String key) {
        ArrayList<DTO_Exam> list = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM exams WHERE exCode LIKE ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, "%" + key + "%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String testCode = rs.getString("testCode");
                String exOrder = rs.getString("exOrder");
                String exCode = rs.getString("exCode");
                String ex_quesIDs = rs.getString("ex_quesIDs");
                DTO_Exam exam = new DTO_Exam(testCode, exOrder, exCode, ex_quesIDs);
                list.add(exam);
            }
            JDBCUtil.close(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public int deleteByCode(String exCode) {
        try {
            Connection con = JDBCUtil.getConnectDB();
            // Change from UPDATE to DELETE statement
            String sql = "DELETE FROM exams WHERE exCode = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, exCode);
            int result = pst.executeUpdate();
            JDBCUtil.close(con);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}