package DTO;

public class DTO_Exam {
    private int exID;
    private String testCode;
    private String exOrder;
    private String exCode;
    private String ex_quesIDs;

    public DTO_Exam(int exID, String testCode, String exOrder, String exCode, String ex_quesIDs) {
        this.exID = exID;
        this.testCode = testCode;
        this.exOrder = exOrder;
        this.exCode = exCode;
        this.ex_quesIDs = ex_quesIDs;
    }
    
    public DTO_Exam(String testCode, String exOrder, String exCode, String ex_quesIDs) {
        this.testCode = testCode;
        this.exOrder = exOrder;
        this.exCode = exCode;
        this.ex_quesIDs = ex_quesIDs;
    }
    
    

    public DTO_Exam() {
        this.exID = 0;
        this.testCode = "";
        this.exOrder = "";
        this.exCode = "";
        this.ex_quesIDs = "";
    }

    public int getExID() {
        return exID;
    }

    public void setExID(int exID) {
        this.exID = exID;
    }

    public String getTestCode() {
        return testCode;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }

    public String getExOrder() {
        return exOrder;
    }

    public void setExOrder(String exOrder) {
        this.exOrder = exOrder;
    }

    public String getExCode() {
        return exCode;
    }

    public void setExCode(String exCode) {
        this.exCode = exCode;
    }

    public String getEx_quesIDs() {
        return ex_quesIDs;
    }

    public void setEx_quesIDs(String ex_quesIDs) {
        this.ex_quesIDs = ex_quesIDs;
    }

    @Override
    public String toString() {
        return "DTO_Exam{" +
                "exID=" + exID +
                ", testCode='" + testCode + '\'' +
                ", exOrder='" + exOrder + '\'' +
                ", exCode='" + exCode + '\'' +
                ", ex_quesIDs='" + ex_quesIDs + '\'' +
                '}';
    }
}