package com.dirijable.project1.dao;

import com.dirijable.project1.model.Book;

import java.util.List;

public interface Dao<T> {
    public List<T> findAll();

    public T findById(int id);

    public void update(int id, T toUpdate);

    public void delete(int id);

    public void save(T toSave);
}
