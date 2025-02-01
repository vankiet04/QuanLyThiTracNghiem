package BUS;

import DAO.DAO_Questions;

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

    public int delete(int cur) {
        return questionsDAO.delete(cur);
    }

    public java.util.ArrayList<DTO.DTO_Questions> getAllData() {
        return questionsDAO.getAllData();
    }

    public int getLargestID() {
        return questionsDAO.getLargestID();
    }

}
