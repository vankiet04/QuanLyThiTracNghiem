package BUS;

import DAO.DAOInterface;
import DAO.DAO_Answers;
import DTO.DTO_Answer;
import java.util.ArrayList;

public class BUS_Answers implements DAOInterface<DTO_Answer> {
    private ArrayList<DTO_Answer> listAnswers;
    private DAO_Answers daoAnswers;

    public BUS_Answers() {
        this.listAnswers = new ArrayList<>();
        this.daoAnswers = new DAO_Answers();
        // this.listAnswers = daoAnswers.getAllData();
    }

    @Override
    public int insert(DTO_Answer answer) {
        if (validate(answer)) {
            int result = daoAnswers.insert(answer);
            if (result > 0) {
                this.listAnswers.add(answer);
            }
            return result;
        }
        return 0;
    }

    @Override
    public int update(DTO_Answer answer) {
        if (validate(answer)) {
            int result = daoAnswers.update(answer);
            if (result > 0) {
                this.listAnswers = daoAnswers.getAllData();
            }
            return result;
        }
        return 0;
    }

    @Override
    public DTO_Answer selectById(String id) {
        return daoAnswers.selectById(id);
    }

    private boolean validate(DTO_Answer answer) {
        if (answer.getContent().isEmpty()) {
            return false;
        }
        return true;
    }

    public ArrayList<DTO_Answer> getAnswersByQuestionId(int questionId) {
        ArrayList<DTO_Answer> result = new ArrayList<>();
        for (DTO_Answer answer : listAnswers) {
            if (answer.getQuestionId() == questionId) {
                result.add(answer);
            }
        }
        return result;
    }

    @Override
    public int delete(int t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public ArrayList<DTO_Answer> getAllData() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllData'");
    }

    @Override
    public int getAutoIncrement() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAutoIncrement'");
    }
}