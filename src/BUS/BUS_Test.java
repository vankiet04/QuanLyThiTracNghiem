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
    }

    @Override
    public int insert(DTO_Test t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public int update(DTO_Test t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public int delete(int t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public ArrayList<DTO_Test> getAllData() {
        // TODO Auto-generated method stub
        return daoTest.getAllData();
    }

    @Override
    public DTO_Test selectById(String t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectById'");
    }

    @Override
    public int getAutoIncrement() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAutoIncrement'");
    }
    
}
