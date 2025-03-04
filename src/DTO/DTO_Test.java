package DTO;

import java.time.LocalDateTime;
import java.util.Date;

public class DTO_Test {
    private int testID;
    private String testCode;
    private String testTitle;
    private int tpID;
    private int testTime;
    private int numEasy;
    private int numMedium;
    private int numDiff;
    private int testLimit;
    private LocalDateTime testDate;
    private int testStatus;

    public DTO_Test(int testID, String testCode, String testTitle, int tpID, int testTime, int numEasy, int numMedium,
            int numDiff, int testLimit, LocalDateTime testDate, int testStatus) {
        this.testID = testID;
        this.testCode = testCode;
        this.testTitle = testTitle;
        this.tpID = tpID;
        this.testTime = testTime;
        this.numEasy = numEasy;
        this.numMedium = numMedium;
        this.numDiff = numDiff;
        this.testLimit = testLimit;
        this.testDate = testDate;
        this.testStatus = testStatus;
    }
    public DTO_Test() {
        this.testID = 0;
        this.testCode = "";
        this.testTitle = "";
        this.tpID = 0;
        this.testTime = 0;
        this.numEasy = 0;
        this.numMedium = 0;
        this.numDiff = 0;
        this.testLimit = 0;
        this.testDate = null;
        this.testStatus = 0;
    }

    public int getTestID() {
        return testID;
    }

    public void setTestID(int testID) {
        this.testID = testID;
    }

    public String getTestCode() {
        return testCode;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }

    public String getTestTitle() {
        return testTitle;
    }

    public void setTestTitle(String testTitle) {
        this.testTitle = testTitle;
    }

    public int getTpID() {
        return tpID;
    }

    public void setTpID(int tpID) {
        this.tpID = tpID;
    }

    public int getTestTime() {
        return testTime;
    }

    public void setTestTime(int testTime) {
        this.testTime = testTime;
    }

    public int getNumEasy() {
        return numEasy;
    }

    public void setNumEasy(int numEasy) {
        this.numEasy = numEasy;
    }

    public int getNumMedium() {
        return numMedium;
    }

    public void setNumMedium(int numMedium) {
        this.numMedium = numMedium;
    }

    public int getNumDiff() {
        return numDiff;
    }

    public void setNumDiff(int numDiff) {
        this.numDiff = numDiff;
    }

    public int getTestLimit() {
        return testLimit;
    }

    public void setTestLimit(int testLimit) {
        this.testLimit = testLimit;
    }

    public LocalDateTime getTestDate() {
        return testDate;
    }

    public void setTestDate(LocalDateTime testDate) {
        this.testDate = testDate;
    }

    public int getTestStatus() {
        return testStatus;
    }

    public void setTestStatus(int testStatus) {
        this.testStatus = testStatus;
    }

    
    public int getNumQuest(){
        return this.numEasy + this.numDiff + this.numMedium;
    }
    //system out
    @Override
    public String toString() {
        return "DTO_Test{" + "testID=" + testID + ", testCode=" + testCode + ", testTitle=" + testTitle + ", tpID=" + tpID + ", testTime=" + testTime + ", numEasy=" + numEasy + ", numMedium=" + numMedium + ", numDiff=" + numDiff + ", testLimit=" + testLimit + ", testDate=" + testDate + ", testStatus=" + testStatus + '}';
    }
}