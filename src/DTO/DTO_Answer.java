package DTO;

public class DTO_Answer {
    private int questionId;
    private String content;
    private String image;
    private int isRight;
    private int status = 1;

    // get and set
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int isRight() {
        return isRight;
    }

    public void setRight(int isRight) {
        this.isRight = isRight;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    // get set questionid
    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
}
