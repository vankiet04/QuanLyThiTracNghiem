package DAO;

import ConnectDB.JDBCUtil;
import DTO.DTO_Questions;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO_Questions implements DAOInterface<DTO.DTO_Questions> {

    public static DAO_Questions getInstance() {
        return new DAO_Questions();
    }

    @Override
    public int insert(DTO_Questions t) {
        try {
            Connection con = JDBCUtil.getConnectDB();
            String sql = "INSERT INTO questions(qContent, qPictures, qTopicID, qLevel) VALUES(?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getqContent());
            pst.setString(2, t.getqPictures());
            pst.setInt(3, t.getqTopicID());
            pst.setString(4, t.getqLevel());
            pst.executeUpdate();
            JDBCUtil.close(con);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int update(DTO_Questions t, int id) {
        try {
            Connection con = JDBCUtil.getConnectDB();
            String sql = "UPDATE questions SET qContent=?, qPictures=?, qTopicID=?, qLevel=? WHERE qID=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getqContent());
            pst.setString(2, t.getqPictures());
            pst.setInt(3, t.getqTopicID());
            pst.setString(4, t.getqLevel());
            pst.setInt(5, id);
            int result = pst.executeUpdate();
            JDBCUtil.close(con);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(int id) {
        try {
            Connection con = JDBCUtil.getConnectDB();
            String sql = "DELETE FROM questions WHERE qID=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            int result = pst.executeUpdate();
            JDBCUtil.close(con);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public ArrayList<DTO_Questions> getAllData() {
        ArrayList<DTO_Questions> list = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnectDB();
            String sql = "SELECT qID, qContent, qPictures, qTopicID, qLevel FROM questions";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                DTO_Questions obj = new DTO_Questions(
                        rs.getInt("qID"),
                        rs.getString("qContent"),
                        rs.getString("qPictures"),
                        rs.getInt("qTopicID"),
                        rs.getString("qLevel")
                );
                list.add(obj);
            }
            JDBCUtil.close(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    // lấy câu hỏi theo danh sách ID từ exam
    public ArrayList<DTO_Questions> getAllData(String quesID) {
        ArrayList<DTO_Questions> list = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnectDB();
            String sql = "SELECT qID, qContent, qPictures, qTopicID, qLevel FROM questions";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                DTO_Questions obj = new DTO_Questions(
                        rs.getInt("qID"),
                        rs.getString("qContent"),
                        rs.getString("qPictures"),
                        rs.getInt("qTopicID"),
                        rs.getString("qLevel")
                );
                list.add(obj);
            }
            JDBCUtil.close(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public DTO_Questions selectById(String idStr) {
        try {
            int id = Integer.parseInt(idStr);
            Connection con = JDBCUtil.getConnectDB();
            String sql = "SELECT qContent, qPictures, qTopicID, qLevel FROM questions WHERE qID=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                DTO_Questions obj = new DTO_Questions(
                        rs.getString("qContent"),
                        rs.getString("qPictures"),
                        rs.getInt("qTopicID"),
                        rs.getString("qLevel")
                );
                JDBCUtil.close(con);
                return obj;
            }
            JDBCUtil.close(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getAutoIncrement() {
        try {
            Connection con = JDBCUtil.getConnectDB();
            String sql = "SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = 'questions'";
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

    public int getLargestID() {
        try {
            Connection con = JDBCUtil.getConnectDB();
            String sql = "SELECT MAX(qID) FROM questions";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int update(DTO_Questions t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    /**
     * Search questions by content
     * @param keyword The search keyword
     * @return List of questions matching the search criteria
     */
    public ArrayList<DTO_Questions> searchQuestions(String keyword) {
        ArrayList<DTO_Questions> result = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnectDB();
            // Modified query to handle if qStatus column doesn't exist or is NULL
            String sql = "SELECT qID, qContent, qPictures, qTopicID, qLevel, IFNULL(qStatus, 1) as qStatus FROM questions WHERE qContent LIKE ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, "%" + keyword + "%");
            
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                DTO_Questions question = new DTO_Questions();
                question.setqID(rs.getInt("qID"));
                question.setqContent(rs.getString("qContent"));
                question.setqPictures(rs.getString("qPictures"));
                question.setqLevel(rs.getString("qLevel"));
                question.setqTopicID(rs.getInt("qTopicID"));
                
                // Set status if available, otherwise default to 1 (active)
                try {
                    question.setqStatus(rs.getInt("qStatus"));
                } catch (Exception e) {
                    question.setqStatus(1); // Default to active if status column doesn't exist
                }
                
                result.add(question);
            }
            
            JDBCUtil.close(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
