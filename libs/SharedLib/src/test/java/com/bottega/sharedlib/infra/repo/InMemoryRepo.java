package com.bottega.sharedlib.infra.repo;

import com.bottega.sharedlib.repo.AggregateId;
import com.bottega.sharedlib.repo.BaseEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryRepo<E extends BaseEntity, ID extends AggregateId> implements CrudRepository<E, ID> {

    protected Map<AggregateId, E> database = new HashMap<>();

    @Override
    public <S extends E> S save(S entity) {
        AggregateId id = entity.getId();
        database.put(id, entity);
        return entity;
    }

    @Override
    public <S extends E> Iterable<S> saveAll(Iterable<S> entities) {
        entities.forEach(this::save);
        return entities;
    }

    @Override
    public Optional<E> findById(ID id) {
        //TODO implement
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public boolean existsById(ID id) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public Iterable<E> findAll() {
        //TODO implement
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public Iterable<E> findAllById(Iterable<ID> ids) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public long count() {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public void deleteById(ID id) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public void delete(E entity) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public void deleteAllById(Iterable<? extends ID> ids) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public void deleteAll(Iterable<? extends E> entities) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public void deleteAll() {
        throw new RuntimeException("Not Implemented");
    }

}
