
package DTO;


public class DTO_Result {
    private int rsNum;
    private int userID;
    private String exCode;
    private String rsAnswer;
    private String rsDate;
    private double rsMask;

    public DTO_Result() {
    }
    public DTO_Result(int userID, String exCode, String rsAnswer, String rsDate) {
        this.userID = userID;
        this.exCode = exCode;
        this.rsAnswer = rsAnswer;
        this.rsDate = rsDate;
    }

    public DTO_Result(int userID, String exCode, String rsAnswer, String rsDate, double rsMask) {
        this.userID = userID;
        this.exCode = exCode;
        this.rsAnswer = rsAnswer;
        this.rsDate = rsDate;
        this.rsMask = rsMask;
    }

    public int getRsNum() {
        return rsNum;
    }

    public int getUserID() {
        return userID;
    }

    public String getExCode() {
        return exCode;
    }

    public String getRsAnswer() {
        return rsAnswer;
    }

    public String getRsDate() {
        return rsDate;
    }

    public double getRsMask() {
        return rsMask;
    }

    public void setRsNum(int rsNum) {
        this.rsNum = rsNum;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setExCode(String exCode) {
        this.exCode = exCode;
    }

    public void setRsAnswer(String rsAnswer) {
        this.rsAnswer = rsAnswer;
    }

    public void setRsDate(String rsDate) {
        this.rsDate = rsDate;
    }

    public void setRsMask(double rsMask) {
        this.rsMask = rsMask;
    }
    
    
    
    
    
}
