package DAO;

import DTO.DTO_Questions;

import java.sql.Connection;
import java.util.ArrayList;

import ConnectDB.JDBCUtil;

public class DAO_Questions implements DAOInterface<DTO.DTO_Questions> {
    public static DAO_Questions getInstance() {
        return new DAO_Questions();
    }

    @Override
    public int insert(DTO_Questions t) {
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "INSERT INTO questions(qContent, qPictures, qTopicID, qLevel) VALUES(?,?,?,?)";
            java.sql.PreparedStatement pst = con.prepareStatement(sql);
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

    @Override
    public int update(DTO_Questions t) {
        return 0;
    }

    @Override
    public int delete(int t) {
        return 0;
    }

    @Override
    public ArrayList<DTO_Questions> getAllData() {
        try{
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT * FROM questions";
            java.sql.PreparedStatement pst = con.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery();
            ArrayList<DTO_Questions> list = new ArrayList<>();
            while(rs.next()){
                DTO_Questions q = new DTO_Questions();
                q.setqID(rs.getInt("qID"));
                q.setqContent(rs.getString("qContent"));
                q.setqPictures(rs.getString("qPictures"));
                q.setqTopicID(rs.getInt("qTopicID"));
                q.setqLevel(rs.getString("qLevel"));
                list.add(q);
            }
            return list;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }   

    @Override
    public DTO_Questions selectById(String t) {
        return null;
    }

    @Override
    public int getAutoIncrement() {
        return 0;
    }

    public int getLargestID() {
        try {
            Connection con = (Connection) JDBCUtil.getConnectDB();
            String sql = "SELECT MAX(qID) FROM questions";
            java.sql.PreparedStatement pst = con.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
