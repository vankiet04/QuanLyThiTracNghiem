package DAO;

import ConnectDB.JDBCUtil;
import DTO.DTO_Topic;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO_Topics implements DAOInterface<DTO_Topic> {
    
    public static DAO_Topics getInstance() {
        return new DAO_Topics();
    }
    
    @Override
    public int insert(DTO_Topic t) {
        // Implementation not needed for this task
        return 0;
    }
    
    @Override
    public int update(DTO_Topic t) {
        // Implementation not needed for this task
        return 0;
    }
    
    @Override
    public int delete(int id) {
        // Implementation not needed for this task
        return 0;
    }
    
    @Override
    public ArrayList<DTO_Topic> getAllData() {
        ArrayList<DTO_Topic> list = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnectDB();
            String sql = "SELECT tpID, tpTitle, tpParent, tpStatus FROM topics WHERE tpStatus = 1";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                DTO_Topic topic = new DTO_Topic(
                    rs.getInt("tpID"),
                    rs.getString("tpTitle"),
                    rs.getInt("tpParent"),
                    rs.getInt("tpStatus")
                );
                list.add(topic);
            }
            JDBCUtil.close(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    @Override
    public DTO_Topic selectById(String id) {
        // Implementation not needed for this task
        return null;
    }
    
    @Override
    public int getAutoIncrement() {
        // Implementation not needed for this task
        return 0;
    }
}
