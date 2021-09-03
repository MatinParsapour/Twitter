package base.repository;

import base.entity.BaseEntity;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public interface BaseRepository<E extends BaseEntity<ID>,ID extends Serializable> {

    E createOrUpdate(E e);

    List<E> findAll();

    E findById(ID id);

    void delete(E e);

    EntityManager getEntityManager();
}
