package BUS;

import DAO.DAO_Topics;
import DTO.DTO_Topic;
import java.util.ArrayList;

public class BUS_Topics {
    private DAO_Topics topicsDAO;
    
    public BUS_Topics() {
        this.topicsDAO = new DAO_Topics();
    }
    
    public ArrayList<DTO_Topic> getAllTopics() {
        return topicsDAO.getAllData();
    }
}
