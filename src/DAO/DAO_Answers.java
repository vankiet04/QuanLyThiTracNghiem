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
    public DTO_Answer selectById(String id) {
        return null;
    }

    @Override
    public int delete(int t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public ArrayList<DTO_Answer> getAllData() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllData'");
    }

    @Override
    public int getAutoIncrement() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAutoIncrement'");
    }
}