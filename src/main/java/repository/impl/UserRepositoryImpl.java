package repository.impl;

import base.repository.BaseRepositoryImpl;
import domain.User;
import repository.UserRepository;
import util.CriteriaCustom;
import util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class UserRepositoryImpl extends BaseRepositoryImpl<User,Long> implements UserRepository {
    public UserRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<User> getEntity() {
        return User.class;
    }

    @Override
    public User findUserByUserName(String userName) {
        try{
            CriteriaQuery<User> criteriaQuery = CriteriaCustom.getCriteriaBuilderCutom().createQuery(User.class);
            Root<User> userRoot = criteriaQuery.from(User.class);
            criteriaQuery.select(userRoot).where(CriteriaCustom.getCriteriaBuilderCutom().equal(userRoot.get("userName"), userName));
            User user = entityManager.createQuery(criteriaQuery).getSingleResult();
            return user;
        }catch (NoResultException exception){
            return null;
        }
    }

    @Override
    public User findUserByUserNameAndPassword(String userName, String password) {
        try{
            CriteriaQuery<User> criteriaQuery = CriteriaCustom.getCriteriaBuilderCutom().createQuery(User.class);
            Root<User> userRoot = criteriaQuery.from(User.class);
            criteriaQuery.select(userRoot)
                    .where(CriteriaCustom.getCriteriaBuilderCutom().and(
                            CriteriaCustom.getCriteriaBuilderCutom().equal(
                                    userRoot.get("userName"), userName),
                            CriteriaCustom.getCriteriaBuilderCutom().equal(
                                    userRoot.get("password"),password)));
            User user = entityManager.createQuery(criteriaQuery).getSingleResult();
            return user;
        }catch (NoResultException exception){
            return null;
        }
    }
}
