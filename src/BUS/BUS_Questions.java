package BUS;

import DAO.DAO_Questions;
import DTO.DTO_Questions;
import java.util.ArrayList;

public class BUS_Questions {
    public DAO.DAO_Questions questionsDAO = new DAO_Questions();

    public static BUS_Questions getInstance() {
        return new BUS_Questions();
    }

    public int insert(DTO.DTO_Questions cur) {
        return questionsDAO.insert(cur);
    }

    public int update(DTO.DTO_Questions cur) {
        return questionsDAO.update(cur);
    }

    public int update(DTO_Questions question, int id) {
        return questionsDAO.update(question, id);
    }

    public int delete(int cur) {
        return questionsDAO.delete(cur);
    }

    public ArrayList<DTO_Questions> getAllData() {
        return questionsDAO.getAllData();
    }
    
    public ArrayList<DTO_Questions> getAllData(String quesID) {
        quesID = quesID.replace("[", "").replace("]", "");
        return questionsDAO.getAllData(quesID);
    }

    public int getLargestID() {
        return questionsDAO.getLargestID();
    }

    public DTO.DTO_Questions selectById(String id) {
        return questionsDAO.selectById(id);
    }

    /**
     * Search for questions based on their content
     * @param keyword The search keyword
     * @return List of questions matching the search criteria
     */
    public ArrayList<DTO_Questions> searchQuestions(String keyword) {
        DAO_Questions dao = new DAO_Questions();
        return dao.searchQuestions(keyword);
    }

    /**
     * Search questions with advanced filters
     * @param keyword The search keyword for question content
     * @param topicId The topic ID to filter by (-1 for all topics)
     * @param difficulty The difficulty level to filter by (empty string for all)
     * @return List of questions matching all the specified criteria
     */
    public ArrayList<DTO_Questions> searchQuestionsFiltered(String keyword, int topicId, String difficulty) {
        DAO_Questions dao = new DAO_Questions();
        return dao.searchQuestionsFiltered(keyword, topicId, difficulty);
    }

    public int updateStatus(int questionId, int status) {
        return questionsDAO.updateStatus(questionId, status);
    }

    /**
     * Soft delete a question by setting its status to 0 (inactive)
     * @param questionId The ID of the question to delete
     * @return The number of rows affected (1 if successful, 0 if failed)
     */
    public int softDelete(int questionId) {
        return updateStatus(questionId, 0);
    }

}
