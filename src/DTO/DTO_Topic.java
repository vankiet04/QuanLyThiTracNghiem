package DTO;

public class DTO_Topic {
    private int tpID;         // Topic ID
    private String tpTitle;   // Topic Title
    private int tpParent;     // Parent Topic ID
    private int tpStatus;     // Status (e.g., active/inactive)

    // Default Constructor
    public DTO_Topic() {}

    // Parameterized Constructor
    public DTO_Topic(int tpID, String tpTitle, int tpParent, int tpStatus) {
        this.tpID = tpID;
        this.tpTitle = tpTitle;
        this.tpParent = tpParent;
        this.tpStatus = tpStatus;
    }

    // Getters and Setters
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
        return "DTO_Topic{" +
                "tpID=" + tpID +
                ", tpTitle='" + tpTitle + '\'' +
                ", tpParent=" + tpParent +
                ", tpStatus=" + tpStatus +
                '}';
    }
}
