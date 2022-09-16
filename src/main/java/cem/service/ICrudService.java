package cem.service;

import java.util.Optional;
import java.util.Set;

public interface ICrudService<T,ID> { // generelt CrudService interface der matcher de metoder der findes i CrudRepo

    Optional<T> findById(ID id);

    Set<T> findAll();

    T save(T object);

    void deleteById(ID id);

    void delete(T entity);

    boolean existsById(ID id);

}
