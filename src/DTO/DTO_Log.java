
package DTO;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


public class DTO_Log {
    private int logID;
    private int logUserID;
    private String logExCode;
    private String logDate;
    private String logContent;

    public DTO_Log() {
    }

    public DTO_Log(int logID, int logUserID, String logExCode, String logDate, String logContent) {
        this.logID = logID;
        this.logUserID = logUserID;
        this.logExCode = logExCode;
        this.logDate = logDate;
        this.logContent = logContent;
    }

    public DTO_Log(int logUserID, String logExCode, String logDate, String logContent) {
        this.logUserID = logUserID;
        this.logExCode = logExCode;
        this.logDate = logDate;
        this.logContent = logContent;
    }

    public int getLogID() {
        return logID;
    }

    public int getLogUserID() {
        return logUserID;
    }

    public String getLogExCode() {
        return logExCode;
    }

    public String getLogDate() {
        return logDate;
    }

    public String getLogContent() {
        return logContent;
    }

    public void setLogID(int logID) {
        this.logID = logID;
    }

    public void setLogUserID(int logUserID) {
        this.logUserID = logUserID;
    }

    public void setLogExCode(String logExCode) {
        this.logExCode = logExCode;
    }

    public void setLogDate(String logDate) {
        this.logDate = logDate;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent;
    }


    
    
}
