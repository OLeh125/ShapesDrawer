package ua.edu.task.dao;


import java.util.List;

public interface CrudDao<T> {
      T find(String sname);
    void save(T model);
    void update(T model);
    void delete(String id);
    List<T> findAll();
}
