package DAO;

import ConnectDB.JDBCUtil;
import DTO.DTO_Log;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class DAO_Log implements DAOInterface<DTO.DTO_Log>{

    
    // lưu log theo từng dòng, ta có logExCode và logUserID giống nhau
    // logContent đổi theo kết quả người dùng và logDate là timeline
    @Override
    public int insert(DTO_Log log) {
        try {
            Connection con = JDBCUtil.getConnectDB();
            String sql = "INSERT INTO logs (logContent, logUserID, logExCode, logDate) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, log.getLogContent());
            pst.setInt(2, log.getLogUserID());
            pst.setString(3, log.getLogExCode());
            pst.setString(4, log.getLogDate());
            int result = pst.executeUpdate();
            JDBCUtil.close(con);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Lấy logContent để tiếp tục kéo dài chuỗi
    public String LayLogContentMoiNhat(int userID, String exCode) {
        String res = "";
        try {
            Connection con = JDBCUtil.getConnectDB();

            String sql = "SELECT logContent FROM logs WHERE logUserID = ? AND logExCode = ? ORDER BY logID DESC LIMIT 1";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, userID);
            pst.setString(2, exCode);
            ResultSet rs = (ResultSet) pst.executeQuery();
            if (rs.next()) {
                res = rs.getString("logContent");
            }
        }catch (Exception e) {
        }

        return res;
    }


    public ArrayList<DTO_Log> LayLogCuaNguoiThiTheoThoiGian(int userID, String exCode, int examDurationMinutes) {
        ArrayList<DTO_Log> res = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnectDB();
            
            // Query for the earliest log in the exam period
            String sqlFirst = "SELECT * FROM logs WHERE logUserID = ? AND logExCode = ? "
                            +  "AND logDate >= (NOW() - INTERVAL ? MINUTE)"
                            + " ORDER BY logDate ASC LIMIT 1";
            PreparedStatement pstFirst = con.prepareStatement(sqlFirst);
            pstFirst.setInt(1, userID);
            pstFirst.setString(2, exCode);
            pstFirst.setInt(3, examDurationMinutes);
            ResultSet rsFirst = pstFirst.executeQuery();
            if (rsFirst.next()) {
                DTO_Log firstLog = new DTO_Log();
                firstLog.setLogID(rsFirst.getInt("logID"));
                firstLog.setLogUserID(rsFirst.getInt("logUserID"));
                firstLog.setLogExCode(rsFirst.getString("logExCode"));
                firstLog.setLogDate(rsFirst.getString("logDate"));
                firstLog.setLogContent(rsFirst.getString("logContent"));
                res.add(firstLog);
            }
            rsFirst.close();
            pstFirst.close();
            
            // Query for the latest log in the exam period
            String sqlLast = "SELECT * FROM logs WHERE logUserID = ? AND logExCode = ? "
                            +  "AND logDate >= (NOW() - INTERVAL ? MINUTE)"
                            + " ORDER BY logDate DESC LIMIT 1";
            PreparedStatement pstLast = con.prepareStatement(sqlLast);
            pstLast.setInt(1, userID);
            pstLast.setString(2, exCode);
            pstLast.setInt(3, examDurationMinutes);
            ResultSet rsLast = pstLast.executeQuery();
            if (rsLast.next()) {
                DTO_Log lastLog = new DTO_Log();
                lastLog.setLogID(rsLast.getInt("logID"));
                lastLog.setLogUserID(rsLast.getInt("logUserID"));
                lastLog.setLogExCode(rsLast.getString("logExCode"));
                lastLog.setLogDate(rsLast.getString("logDate"));
                lastLog.setLogContent(rsLast.getString("logContent"));
                // Avoid duplicate if first and last log are the same
                if (res.isEmpty() || res.get(0).getLogID() != lastLog.getLogID()) {
                    res.add(lastLog);
                }
            }
            rsLast.close();
            pstLast.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    
        public void updateLogToConfirm(int userID, String exCode) {
            try {
                Connection con = JDBCUtil.getConnectDB();
                String sql = "UPDATE logs SET logContent = 'confirm' WHERE logUserID = ? AND logExCode = ? ORDER BY logID DESC LIMIT 1";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, userID);
                pst.setString(2, exCode);
                pst.executeUpdate();
                pst.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        public boolean isLatestLogConfirm(int userID, String exCode) {
        try {
            Connection con = JDBCUtil.getConnectDB();
            String sql = "SELECT logContent FROM logs WHERE logUserID = ? AND logExCode = ? ORDER BY logID DESC LIMIT 1";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, userID);
            pst.setString(2, exCode);
            ResultSet rs = pst.executeQuery();
            boolean isConfirm = false;
            if (rs.next()) {
                isConfirm = "confirm".equals(rs.getString("logContent"));
            }
            rs.close();
            pst.close();
            con.close();
            return isConfirm;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
        

    public boolean hasSubmitted(int userID, String exCode) {
        try {
            Connection con = JDBCUtil.getConnectDB();
            String sql = "SELECT COUNT(*) FROM result WHERE userID = ? AND exCode = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, userID);
            pst.setString(2, exCode);
            ResultSet rs = pst.executeQuery();
            boolean exists = false;
            if (rs.next()) {
                exists = rs.getInt(1) > 0; // Nếu có ít nhất 1 bản ghi trong result
            }
            rs.close();
            pst.close();
            con.close();
            return exists;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    
    
        public DTO_Log layLogMoiNhat(int userID, String exCode) {
            DTO_Log logMoiNhat = null;
            try {
                Connection con = JDBCUtil.getConnectDB();
                String sql = "SELECT * FROM logs WHERE logUserID = ? AND logExCode = ? ORDER BY logID DESC LIMIT 1";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, userID);
                pst.setString(2, exCode);

                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    logMoiNhat = new DTO_Log();
                    logMoiNhat.setLogID(rs.getInt("logID"));
                    logMoiNhat.setLogUserID(rs.getInt("logUserID"));
                    logMoiNhat.setLogExCode(rs.getString("logExCode"));
                    logMoiNhat.setLogDate(rs.getString("logDate"));
                    logMoiNhat.setLogContent(rs.getString("logContent"));
                }

                rs.close();
                pst.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return logMoiNhat;
        }
    @Override
    public int update(DTO_Log t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(int t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<DTO_Log> getAllData() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public DTO_Log selectById(String t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getAutoIncrement() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
