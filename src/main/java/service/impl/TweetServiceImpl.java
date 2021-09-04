package service.impl;

import base.service.BaseServiceImpl;
import domain.Tweet;
import repository.TweetRepository;
import service.TweetService;

public class TweetServiceImpl extends BaseServiceImpl<Tweet,Long, TweetRepository> implements TweetService {
    public TweetServiceImpl(TweetRepository repository) {
        super(repository);
    }
}
