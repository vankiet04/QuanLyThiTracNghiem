package DTO;

public class DTO_Questions {
    private int qID;
    private String qContent;
    private String qPictures;
    private int qTopicID;
    private String qLevel;

    public DTO_Questions() {
    }

    public DTO_Questions(int qID, String qContent, String qPictures, int qTopicID, String qLevel) {
        this.qID = qID;
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

    public int getqID() {
        return qID;
    }

    public void setqID(int qID) {
        this.qID = qID;
    }

    @Override
    public String toString() {
        return "DTO_Questions{" + "qID=" + qID + ", qContent=" + qContent + ", qPictures=" + qPictures + ", qTopicID=" + qTopicID + ", qLevel=" + qLevel + '}';
    }
}