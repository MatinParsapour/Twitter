package repository.impl;

import base.repository.BaseRepositoryImpl;
import domain.Comment;
import domain.Like;
import domain.Tweet;
import domain.User;
import repository.TweetRepository;
import util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class TweetRepositoryImpl extends BaseRepositoryImpl<Tweet,Long> implements TweetRepository {

    public TweetRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Tweet> getEntity() {
        return Tweet.class;
    }

    @Override
    public Tweet findTweetByUserLikes(Tweet tweeted,String userName) {
        try{
            EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
            Tweet tweet = entityManager.createQuery("SELECT t " +
                    "FROM Tweet t " +
                    "JOIN t.likes l"+
                    " WHERE l.userName = :username and t.id = :id",Tweet.class)
                    .setParameter("username",userName).setParameter("id",tweeted.getId()).getSingleResult();
            return tweet;
        }catch (NoResultException exception){
            return null;
        }
    }

    @Override
    public Tweet findTweetByUserDisLikes(Tweet tweeted,String userName) {
        try{
            EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
            Tweet tweet = entityManager.createQuery("SELECT t " +
                    "FROM Tweet t " +
                    "JOIN t.disLikes d"+
                    " WHERE d.userName = :username and t.id = :id",Tweet.class)
                    .setParameter("username",userName).setParameter("id",tweeted.getId()).getSingleResult();
            return tweet;
        }catch (NoResultException exception){
            return null;
        }
    }


    @Override
    public List<Tweet> findTweetByUserComments(Tweet tweeted,String userName) {
        try{
            EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
            List<Tweet> tweet = entityManager.createQuery("SELECT t " +
                    "FROM Tweet t " +
                    "JOIN t.commentList l"+
                    " WHERE l.user = :username ", Tweet.class).
                    setParameter("username",userName).getResultList();
            return tweet;
        }catch (NoResultException exception){
            return null;
        }
    }
}
