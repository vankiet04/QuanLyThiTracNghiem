package DAO;

import DTO.DTO_Answer;
import ConnectDB.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO_Answers implements DAOInterface<DTO_Answer> {
    private static DAO_Answers instance;

    public static DAO_Answers getInstance() {
        if (instance == null) {
            instance = new DAO_Answers();
        }
        return instance;
    }

    @Override
    public int insert(DTO_Answer t) {
        try {
            Connection con = JDBCUtil.getConnectDB();
            String sql = "INSERT INTO answers(qID, awContent, awPictures, isRight, awStatus) VALUES(?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t.getQuestionId());
            pst.setString(2, t.getContent());
            pst.setString(3, t.getImage());
            pst.setInt(4, t.isRight());
            pst.setInt(5, t.getStatus());
            int result = pst.executeUpdate();
            JDBCUtil.close(con);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(DTO_Answer t) {
        return 0;
    }

    @Override
    public DTO_Answer selectById(String idStr) {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
                                                                       // Tools | Templates.
    }

    @Override
    public ArrayList<DTO_Answer> getAllData() {
        ArrayList<DTO_Answer> list = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnectDB();
            String sql = "SELECT awID, qID, awContent, awPictures, isRight, awStatus FROM answers";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                DTO_Answer answer = new DTO_Answer();
                answer.setQuestionId(rs.getInt("qID"));
                answer.setContent(rs.getString("awContent"));
                answer.setImage(rs.getString("awPictures"));
                answer.setRight(rs.getInt("isRight"));
                answer.setStatus(rs.getInt("awStatus"));
                list.add(answer);
            }
            JDBCUtil.close(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<DTO_Answer> getAllData(int qID) {
        ArrayList<DTO_Answer> list = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM answers WHERE qID = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, qID);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                DTO_Answer answer = new DTO_Answer();
                answer.setQuestionId(rs.getInt("qID"));
                answer.setContent(rs.getString("awContent"));
                answer.setImage(rs.getString("awPictures"));
                answer.setRight(rs.getInt("isRight"));
                answer.setStatus(rs.getInt("awStatus"));
                list.add(answer);
            }
            JDBCUtil.close(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    @Override
    public int delete(int t) {
        try {
            Connection con = JDBCUtil.getConnectDB();
            String sql = "DELETE FROM answers WHERE awID=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t);
            int result = pst.executeUpdate();
            JDBCUtil.close(con);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int getAutoIncrement() {
        try {
            Connection con = JDBCUtil.getConnectDB();
            String sql = "SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = 'answers'";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int nextId = rs.getInt(1);
                JDBCUtil.close(con);
                return nextId;
            }
            JDBCUtil.close(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    // New method to update answers for a question:
    public int updateAns(ArrayList<DTO_Answer> answers, int questionId) throws Exception {
        Connection con = JDBCUtil.getConnectDB();
        // Delete existing answers for the question
        String deleteSql = "DELETE FROM answers WHERE qID=?";
        PreparedStatement deletePst = con.prepareStatement(deleteSql);
        deletePst.setInt(1, questionId);
        deletePst.executeUpdate();

        // Insert new answers
        String insertSql = "INSERT INTO answers(qID, awContent, awPictures, isRight, awStatus) VALUES(?,?,?,?,?)";
        PreparedStatement insertPst = con.prepareStatement(insertSql);
        int count = 0;
        for (DTO_Answer ans : answers) {
            insertPst.setInt(1, questionId);
            insertPst.setString(2, ans.getContent());
            insertPst.setString(3, ans.getImage());
            insertPst.setInt(4, ans.isRight());
            insertPst.setInt(5, ans.getStatus());
            count += insertPst.executeUpdate();
        }
        JDBCUtil.close(con);
        return count;
    }
}