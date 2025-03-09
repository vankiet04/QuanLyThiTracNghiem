/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import java.util.ArrayList;

import DAO.DAOInterface;
import DAO.DAO_Test;
import DTO.DTO_Test;

/**
 *
 * @author KIET
 */
public class BUS_Test implements DAOInterface<DTO_Test> {
    private ArrayList<DTO_Test> listTest;
    private DAO.DAO_Test daoTest;
    public BUS_Test() {
        this.daoTest = new DAO_Test();
        this.listTest = daoTest.getAllData();
    }

    @Override
    public int insert(DTO_Test t) {
        int result = daoTest.insert(t);
        if (result > 0) {
            if (listTest == null) {
                listTest = new ArrayList<>();
            }
            listTest.add(t);
        }
        return result;
    }

    @Override
    public int update(DTO_Test t) {
        //code upadte
        int result = daoTest.update(t);
        if (result > 0) {
            // Update the test in the local list
            for (int i = 0; i < listTest.size(); i++) {
                if (listTest.get(i).getTestID() == t.getTestID()) {
                    listTest.set(i, t);
                    break;
                }
            }
        }
        return result;

        // TODO Auto-generated method stub
       
    }

    @Override
    public int delete(int testID) {
        // Retrieve the test to update its status
        DTO_Test testToDelete = null;
        for (DTO_Test test : listTest) {
            if (test.getTestID() == testID) {
                testToDelete = test;
                break;
            }
        }
        
        if (testToDelete != null) {
            // Set status to 0 (inactive)
            testToDelete.setTestStatus(0);
            
            // Update in database
            int result = daoTest.update(testToDelete);
            
            if (result > 0) {
                // Remove from active list or update status
                for (int i = 0; i < listTest.size(); i++) {
                    if (listTest.get(i).getTestID() == testID) {
                        listTest.remove(i);
                        break;
                    }
                }
            }
            return result;
        }
        
        return 0; // Test not found
    }
    @Override
    public ArrayList<DTO_Test> getAllData() {
        // TODO Auto-generated method stub
        return daoTest.getAllData();
    }

    @Override
    public DTO_Test selectById(String t) {
        return daoTest.selectById(t);
    }

    public ArrayList<DTO_Test> searchData(String searchText) {
        return daoTest.searchData(searchText);
    }

    public int getSoLuongCauHoi(String testCode) {
        return daoTest.getSoLuongCauHoi(testCode);
    }

    @Override
    public int getAutoIncrement() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAutoIncrement'");
    }
    // ArrayList<DTO_Test> testsWithSameCode = testBUS.getAllByTestCode(testCode);
    public ArrayList<DTO_Test> getAllByTestCode(String testCode) {
        ArrayList<DTO_Test> testsWithSameCode = new ArrayList<>();
        for (DTO_Test test : listTest) {
            if (test.getTestCode().equals(testCode)) {
                testsWithSameCode.add(test);
            }
        }
        return testsWithSameCode;
    }


    
}
