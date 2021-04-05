package com.example.shoppingcart.repository.base;

import java.util.Collection;

/**
 * @author Arman
 * Date: 4/3/21
 * Time: 12:51 PM
 **/
public interface SimpleRepository<T> {
    /**
     * Saves a given entity.
     *
     * @param entity must not be {@literal null}.
     * @return the saved entity will never be {@literal null}.
     */
    <S extends T> S add(S entity);

    /**
     * Saves all given entities.
     *
     * @param entities must not be {@literal null}.
     * @return the saved entities will never be {@literal null}.
     * @throws IllegalArgumentException in case the given entity is {@literal null}.
     */
    <S extends T> Collection<S> saveAll(Collection<S> entities);

    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    Collection<T> findAll();

    /**
     * Deletes a given entity.
     *
     * @param entity must not be {@literal null}.
     * @throws IllegalArgumentException in case the given entity is {@literal null}.
     */
    void delete(T entity);

}
