package BUS;

import DAO.DAO_Topic;
import DTO.DTO_Topic;

import java.util.ArrayList;

public class BUS_Topic {

    private DAO_Topic topicDAO = DAO_Topic.getInstance();
    private ArrayList<DTO_Topic> list;

    public BUS_Topic() {
        list = topicDAO.getAllData();
    }

    public ArrayList<DTO_Topic> getAllData() {
        return list;
    }

    public DTO_Topic getInfo(int id) {
        for (DTO_Topic topic : list) {
            if (topic.getTpID() == id) {
                return topic;
            }
        }
        return null;
    }


    public int insert(DTO_Topic cur) {
        for (DTO_Topic topic : list) {
            if (topic.getTpTitle().equalsIgnoreCase(cur.getTpTitle())) {
                return 0; 
            }
        }
        int result = topicDAO.insert(cur);
        if (result > 0) {
            list.add(cur); 
        }
        return result;
    }

    // Update an existing topic in the system
    public int update(DTO_Topic cur) {
        // Check for duplicate titles in other topics
        for (DTO_Topic topic : list) {
            if (topic.getTpTitle().equalsIgnoreCase(cur.getTpTitle()) && topic.getTpID() != cur.getTpID()) {
                return 0; // Duplicate title found with a different ID
            }
        }
        int result = topicDAO.update(cur);
        if (result > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getTpID() == cur.getTpID()) {
                    list.set(i, cur); // Update the local list
                    break;
                }
            }
        }
        return result;
    }

    // Delete a topic by its ID
    public int delete(int id) {
        int result = topicDAO.delete(id);
        if (result > 0) {
            list.removeIf(topic -> topic.getTpID() == id); // Remove from the local list
        }
        return result;
    }

    // Refresh the local data from the database
    public void refreshData() {
        list = topicDAO.getAllData();
    }

    public String getTopicName(int id) {
        for (DTO_Topic topic : list) {
            if (topic.getTpID() == id) {
                return topic.getTpTitle();
            }
        }
        return null;
    }
}
