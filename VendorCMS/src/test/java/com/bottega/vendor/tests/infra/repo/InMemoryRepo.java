package com.bottega.vendor.tests.infra.repo;

import com.bottega.vendor.infra.repo.BaseEntity;
import com.bottega.vendor.shared.AggregateId;
import org.springframework.data.repository.CrudRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryRepo<E extends BaseEntity, ID extends AggregateId> implements CrudRepository<E, ID> {

    protected Map<AggregateId, BaseEntity> database = new HashMap<>();

    @Override
    public <S extends E> S save(S entity) {
        AggregateId id = entity.getId();
        database.put(id, entity);
        return entity;
    }

    @Override
    public <S extends E> Iterable<S> saveAll(Iterable<S> entities) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public Optional<E> findById(ID id) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public boolean existsById(ID id) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public Iterable<E> findAll() {
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
