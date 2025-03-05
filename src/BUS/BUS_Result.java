
package BUS;

import DAO.DAO_Result;
import DTO.DTO_Result;
import java.util.ArrayList;
import java.util.List;


public class BUS_Result {
    public DAO_Result resDAO = new DAO_Result();
    private ArrayList<DTO_Result> listRes= new ArrayList<>();

    public BUS_Result() {
    }
       
    public int insert(DTO.DTO_Result kq, int numQuest){
        int count;
        if (kq.getRsAnswer() == null || kq.getRsAnswer().isEmpty())
            count =0;
    
        List<Integer> listAwID = new ArrayList<>();
        
        String[] pairs = kq.getRsAnswer().split(", ");
        for (String pair : pairs) {
            String[] parts = pair.split(":");
            int awID = Integer.parseInt(parts[1]);
            listAwID.add(awID);
        }
    
        if (listAwID.isEmpty())
            count =0;
        
        count = resDAO.demCauDung(listAwID);
        kq.setRsMask(((double) count / numQuest) * 10);
        return resDAO.insert(kq);
        
    }

    public ArrayList<DTO_Result> getAllData(int UserID, String testCode){
        listRes = resDAO.getAllData(UserID, testCode);
        return listRes; 
   }
    
    public ArrayList<DTO_Result> getAllData(String testCode){
        listRes = resDAO.getAllData(testCode);
        return listRes; 
   }
    
    public int CountResult(String testCode) {
        return resDAO.CountResult(testCode);
    }
    public int CountResult(String testCode, int userID) {
        return resDAO.CountResult(testCode, userID);
    }
}
