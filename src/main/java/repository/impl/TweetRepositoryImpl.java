package repository.impl;

import base.repository.BaseRepositoryImpl;
import domain.Like;
import domain.Tweet;
import domain.User;
import repository.TweetRepository;
import util.CriteriaCustom;
import util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class TweetRepositoryImpl extends BaseRepositoryImpl<Tweet,Long> implements TweetRepository {

    public TweetRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Tweet> getEntity() {
        return Tweet.class;
    }

    @Override
    public Tweet findTweetByUserLikes(String userName) {
        try{
            EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
            Tweet tweet = entityManager.createQuery("SELECT t " +
                    "FROM Tweet t " +
                    "JOIN t.likes l"+
                    " WHERE l.userName = :username ",Tweet.class).setParameter("username",userName).getSingleResult();
            return tweet;
        }catch (NoResultException exception){
            return null;
        }
    }

    @Override
    public Tweet findTweetByUserDisLikes(String userName) {
        try{
            EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
            Tweet tweet = entityManager.createQuery("SELECT t " +
                    "FROM Tweet t " +
                    "JOIN t.disLikes d"+
                    " WHERE d.userName = :username ",Tweet.class)
                    .setParameter("username",userName).getSingleResult();
            return tweet;
        }catch (NoResultException exception){
            return null;
        }
    }

    @Override
    public void deleteUserDisLikes(Tweet tweet,String userName) {
        CriteriaDelete<Tweet> criteriaDelete = CriteriaCustom.getCriteriaBuilderCutom().createCriteriaDelete(Tweet.class);
        Root<Tweet> tweetRoot = criteriaDelete.from(Tweet.class);
        tweetRoot.join("Tweet.likes");
        criteriaDelete.where(CriteriaCustom.getCriteriaBuilderCutom().and(
                CriteriaCustom.getCriteriaBuilderCutom().equal
                        (tweetRoot.get("userName"),userName),
                CriteriaCustom.getCriteriaBuilderCutom().equal
                        (tweetRoot.get("Tweet_id"),tweet.getId())));
        HibernateUtil.getEntityManagerFactory().createEntityManager().createQuery(criteriaDelete).executeUpdate();
    }

    @Override
    public void deleteUserLikes(Tweet tweet,String userName) {
        CriteriaDelete<Tweet> criteriaDelete = CriteriaCustom.getCriteriaBuilderCutom().createCriteriaDelete(Tweet.class);
        Root<Tweet> tweetRoot = criteriaDelete.from(Tweet.class);
        tweetRoot.join("Tweet.disLikes");
        criteriaDelete.where(CriteriaCustom.getCriteriaBuilderCutom().and(
                CriteriaCustom.getCriteriaBuilderCutom().equal
                        (tweetRoot.get("userName"),userName),
                CriteriaCustom.getCriteriaBuilderCutom().equal
                        (tweetRoot.get("Tweet_id"),tweet.getId())));
        HibernateUtil.getEntityManagerFactory().createEntityManager().createQuery(criteriaDelete).executeUpdate();
    }
}
