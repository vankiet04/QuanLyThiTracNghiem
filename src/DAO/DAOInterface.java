
package DAO;

import java.util.ArrayList;


public interface DAOInterface<T> {
    
    public int insert(T t);
    
    public int update(T t);
    
    public int delete(int t);
    
    public ArrayList<T> getAllData();
    
    public T selectById(String t);
    
    int getAutoIncrement();
    
}
