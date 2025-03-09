package DAO;

import DTO.DTO_Topic;
import ConnectDB.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO_Topic {

    private static DAO_Topic instance;

    // Singleton pattern for DAO_Topic
    public static DAO_Topic getInstance() {
        if (instance == null) {
            instance = new DAO_Topic();
        }
        return instance;
    }

    // Insert a new topic into the database
    public int insert(DTO_Topic t) {
        try (Connection con = JDBCUtil.getConnectDB()) {
            String sql = "INSERT INTO topics(tpTitle, tpParent, tpStatus) VALUES(?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getTpTitle());
            pst.setInt(2, t.getTpParent());
            pst.setInt(3, t.getTpStatus());
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Update an existing topic in the database
    public int update(DTO_Topic t) {
        try (Connection con = JDBCUtil.getConnectDB()) {
            String sql = "UPDATE topics SET tpTitle = ?, tpParent = ?, tpStatus = ? WHERE tpID = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getTpTitle());
            pst.setInt(2, t.getTpParent());
            pst.setInt(3, t.getTpStatus());
            pst.setInt(4, t.getTpID());
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Select a topic by its ID
    public DTO_Topic selectById(String id) {
        try (Connection con = JDBCUtil.getConnectDB()) {
            String sql = "SELECT * FROM topics WHERE tpID = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new DTO_Topic(
                    rs.getInt("tpID"),
                    rs.getString("tpTitle"),
                    rs.getInt("tpParent"),
                    rs.getInt("tpStatus")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Delete a topic by its ID
    public int delete(int id) {
        try (Connection con = JDBCUtil.getConnectDB()) {
            String sql = "DELETE FROM topics WHERE tpID = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Retrieve all topics from the database
    public ArrayList<DTO_Topic> getAllData() {
        ArrayList<DTO_Topic> list = new ArrayList<>();
        try (Connection con = JDBCUtil.getConnectDB()) {
            String sql = "SELECT * FROM topics";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new DTO_Topic(
                    rs.getInt("tpID"),
                    rs.getString("tpTitle"),
                    rs.getInt("tpParent"),
                    rs.getInt("tpStatus")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // getAllTopic đầu với testCode so với bảng test để lấy các tpID, sau đó kết nối với bảng topics để lấy thông tin topic
    public ArrayList<DTO_Topic> getAllTopic(String testCode) {
        ArrayList<DTO_Topic> list = new ArrayList<>();
        try (Connection con = JDBCUtil.getConnectDB()) {
            String sql = "SELECT * FROM topics WHERE tpID IN (SELECT tpID FROM test WHERE testCode = ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, testCode);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new DTO_Topic(
                        rs.getInt("tpID"),
                        rs.getString("tpTitle"),
                        rs.getInt("tpParent"),
                        rs.getInt("tpStatus")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public String getNameById (int id) {
        try (Connection con = JDBCUtil.getConnectDB()) {
            String sql = "SELECT tpTitle FROM topics WHERE tpID = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getString("tpTitle");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
