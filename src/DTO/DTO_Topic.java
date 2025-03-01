package DTO;

public class DTO_Topic {

    private int tpID;
    private String tpTitle;
    private int tpParent;  // Changed default to 0 for "None"
    private int tpStatus;

    public DTO_Topic() {
        this.tpParent = 0; // Default value for no parent
    }

    public DTO_Topic(int tpID, String tpTitle, int tpParent, int tpStatus) {
        this.tpID = tpID;
        this.tpTitle = tpTitle;
        this.tpParent = tpParent;
        this.tpStatus = tpStatus;
    }

    public int getTpID() {
        return tpID;
    }

    public void setTpID(int tpID) {
        this.tpID = tpID;
    }

    public String getTpTitle() {
        return tpTitle;
    }

    public void setTpTitle(String tpTitle) {
        this.tpTitle = tpTitle;
    }

    public int getTpParent() {
        return tpParent;
    }

    public void setTpParent(int tpParent) {
        this.tpParent = tpParent;
    }

    public int getTpStatus() {
        return tpStatus;
    }

    public void setTpStatus(int tpStatus) {
        this.tpStatus = tpStatus;
    }

    @Override
    public String toString() {
        return this.tpTitle;
    }
}
