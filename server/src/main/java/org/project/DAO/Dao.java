package org.project.DAO;

import java.util.List;

public interface Dao <T> {
    T save(T object);
    T getById(Long id);
    void delete(Long id);
    List<T> getAll();
}
