
package DTO;

import java.util.Date;
import java.util.List;


public class DTO_Log {
    private int logID;
    private int logUserID;
    private int logExCode;
    private Date logDate;
    private List<String> logContent;

    public void setLogContent(List<String> logContent) {
        this.logContent = logContent;
    }

    public List<String> getLogContent() {
        return logContent;
    }

    public DTO_Log() {
    }

    public DTO_Log(int logID, int logUserID, int logExCode, Date logDate, List<String> logContent) {
        this.logID = logID;
        this.logUserID = logUserID;
        this.logExCode = logExCode;
        this.logDate = logDate;
        this.logContent = logContent;
    }

    public DTO_Log(int logID, int logUserID, int logExId, Date logDate) {
        this.logID = logID;
        this.logUserID = logUserID;
        this.logExCode = logExId;
        this.logDate = logDate;
    }

    public int getLogID() {
        return logID;
    }

    public int getLogUserID() {
        return logUserID;
    }

    public int getLogExCode() {
        return logExCode;
    }

    public Date getLogDate() {
        return logDate;
    }

    public void setLogID(int logID) {
        this.logID = logID;
    }

    public void setLogUserID(int logUserID) {
        this.logUserID = logUserID;
    }

    public void setLogExCode(int logExId) {
        this.logExCode = logExId;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }
    
    
}
