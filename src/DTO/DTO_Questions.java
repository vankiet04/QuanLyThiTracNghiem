package DTO;

public class DTO_Questions {
    private int id = -1;
    private String qContent;
    private String qPictures;
    private int qTopicID;
    private String qLevel;
    private int qStatus = 1; // Default status is active

    public DTO_Questions() {
    }

    public DTO_Questions(String qContent, String qPictures, int qTopicID, String qLevel) {
        this.qContent = qContent;
        this.qPictures = qPictures;
        this.qTopicID = qTopicID;
        this.qLevel = qLevel;
    }

    public DTO_Questions(int id, String qContent, String qPictures, int qTopicID, String qLevel) {
        this.id = id;
        this.qContent = qContent;
        this.qPictures = qPictures;
        this.qTopicID = qTopicID;
        this.qLevel = qLevel;
    }

    public String getqContent() {
        return qContent;
    }

    public void setqContent(String qContent) {
        this.qContent = qContent;
    }

    public String getqPictures() {
        return qPictures;
    }

    public void setqPictures(String qPictures) {
        this.qPictures = qPictures;
    }

    public int getqTopicID() {
        return qTopicID;
    }

    public void setqTopicID(int qTopicID) {
        this.qTopicID = qTopicID;
    }

    public String getqLevel() {
        return qLevel;
    }

    public void setqLevel(String qLevel) {
        this.qLevel = qLevel;
    }

    // Add the missing setter for qID
    public void setqID(int id) {
        this.id = id;
    }

    // Add getter and setter for qStatus
    public int getqStatus() {
        return qStatus;
    }

    public void setqStatus(int qStatus) {
        this.qStatus = qStatus;
    }

    public int getqID() {
        return id;
    }
}