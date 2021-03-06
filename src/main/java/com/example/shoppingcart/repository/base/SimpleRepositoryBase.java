package com.example.shoppingcart.repository.base;

import com.example.shoppingcart.domain.base.BaseEntity;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Arman
 * Date: 4/3/21
 * Time: 1:19 PM
 **/
public abstract class SimpleRepositoryBase<T extends BaseEntity> implements SimpleRepository<T> {
    private Set<T> entities = Collections.newSetFromMap(new ConcurrentHashMap<>());

    protected Set<T> getEntities() {
        return entities;
    }

    @Override
    public <S extends T> S add(S entity) {
        checkNotNull(entity);
        entity.setId(getSequenceGenerator().getNext());
        entities.add(entity);
        return entity;
    }

    @Override
    public <S extends T> Collection<S> saveAll(Collection<S> entities) {
        checkNotNull(entities);
        entities.forEach(s -> s.setId(getSequenceGenerator().getNext()));
        this.entities.addAll(entities);
        return entities;
    }

    @Override
    public Collection<T> findAll() {
        return new ArrayList<>(entities);
    }

    @Override
    public Optional<T> findById(long id) {
        return entities.stream().filter(t -> t.getId().equals(id)).findFirst();
    }

    @Override
    public void delete(T entity) {
        checkNotNull(entities);
        entities.remove(entity);
    }

    private void checkNotNull(Object obj) {
        if (obj == null) throw new IllegalArgumentException("null argument passed");
    }

    public void deleteAll(Collection<? extends T> entities) {
        checkNotNull(entities);
        this.entities.removeAll(entities);
    }

}
