package repository.impl;

import base.repository.BaseRepositoryImpl;
import domain.Tweet;
import repository.TweetRepository;

import javax.persistence.EntityManager;

public class TweetRepositoryImpl extends BaseRepositoryImpl<Tweet,Long> implements TweetRepository {

    public TweetRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Tweet> getEntity() {
        return Tweet.class;
    }
}
