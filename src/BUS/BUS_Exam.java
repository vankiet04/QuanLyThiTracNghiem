package BUS;

import DAO.DAOInterface;
import DAO.DAO_Exam;
import DTO.DTO_Exam;

import java.util.ArrayList;

public class BUS_Exam implements DAOInterface<DTO_Exam> {
    private ArrayList<DTO_Exam> listExam;
    private DAO_Exam daoExam;

    public BUS_Exam() {
        this.daoExam = new DAO_Exam();
        this.listExam = daoExam.getAllData();
    }

    @Override
    public int insert(DTO_Exam exam) {
        int result = daoExam.insert(exam);
        if (result > 0) {
            listExam.add(exam);
        }
        return result;
    }

    @Override
    public int update(DTO_Exam exam) {
        int result = daoExam.update(exam);
        if (result > 0) {
            listExam = daoExam.getAllData();
        }
        return result;
    }

    @Override
    public int delete(int exID) {
        int result = daoExam.delete(exID);
        if (result > 0) {
            listExam = daoExam.getAllData();
        }
        return result;
    }

    @Override
    public DTO_Exam selectById(String exCode) {
        return daoExam.selectById(exCode);
    }

    @Override
    public ArrayList<DTO_Exam> getAllData() {
        return listExam;
    }

    public ArrayList<DTO_Exam> getAllWithTestCode(String testcode) {
        return daoExam.getAllData(testcode);
    }

    
    
    @Override
    public int getAutoIncrement() {
        return daoExam.getAutoIncrement();
    }
    public     ArrayList<DTO_Exam> searchData(String key){
        return daoExam.searchData(key);
    }

    public int deleteByCode(String exCode) {
        return daoExam.deleteByCode(exCode);
    }
}