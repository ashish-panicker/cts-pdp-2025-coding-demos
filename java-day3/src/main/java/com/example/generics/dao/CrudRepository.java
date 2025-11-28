package com.example.generics.dao;

public interface CrudRepository<T, E> {

    T save(T entity);

    T findById(E id);
}
