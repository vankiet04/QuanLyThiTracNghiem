
package DTO;

import java.util.Date;


public class DTO_Log {
    private int logID;
    private int logUserID;
    private int logExId;
    private Date logDate;

    public DTO_Log() {
    }

    public DTO_Log(int logID, int logUserID, int logExId, Date logDate) {
        this.logID = logID;
        this.logUserID = logUserID;
        this.logExId = logExId;
        this.logDate = logDate;
    }

    public int getLogID() {
        return logID;
    }

    public int getLogUserID() {
        return logUserID;
    }

    public int getLogExId() {
        return logExId;
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

    public void setLogExId(int logExId) {
        this.logExId = logExId;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }
    
    
}
