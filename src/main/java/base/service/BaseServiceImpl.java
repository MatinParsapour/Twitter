package base.service;

import base.entity.BaseEntity;
import base.repository.BaseRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public class BaseServiceImpl<E extends BaseEntity<ID>,ID extends Serializable,R extends BaseRepository<E,ID>> implements BaseService<E,ID> {
    protected final R repository;

    public BaseServiceImpl(R repository){
        this.repository = repository;
    }
    @Override
    public E createOrUpdate(E e) {
        repository.getEntityManager().getTransaction().begin();
        repository.createOrUpdate(e);
        repository.getEntityManager().getTransaction().commit();
        return e;
    }

    @Override
    public List<E> findAll() {
        return repository.findAll();
    }

    @Override
    public E findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public void delete(E e) {
        repository.getEntityManager().getTransaction().begin();
        repository.delete(e);
        repository.getEntityManager().getTransaction().commit();
    }
}
