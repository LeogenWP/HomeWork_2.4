package org.leogenwp.repository;

import java.util.List;

public interface GenericRepository <T,ID> {
    List<T> getall();
    T save(T t);
    T getById(ID id);
    T update(T t);
    void deleteById(ID id);
}
