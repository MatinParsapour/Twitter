package base.repository;

import base.entity.BaseEntity;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public abstract class BaseRepositoryImpl<E extends BaseEntity<ID>,ID extends Serializable> implements BaseRepository<E,ID> {
    protected final EntityManager entityManager;

    public BaseRepositoryImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public abstract Class<E> getEntity();

    @Override
    public E createOrUpdate(E e) {
        if(e.getId() == null){
            entityManager.persist(e);
            return e;
        }else{
            return entityManager.merge(e);
        }
    }

    @Override
    public List<E> findAll() {
        return entityManager.createQuery(
                "FROM " + getEntity().getSimpleName(),getEntity())
                .getResultList();
    }

    @Override
    public E findById(ID id) {
        return entityManager.find(getEntity(),id);
    }

    @Override
    public void delete(E e) {
        entityManager.remove(e);
    }

    @Override
    public EntityManager getEntityManager() {
        return this.entityManager;
    }
}
