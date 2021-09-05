package service;

import base.service.BaseService;
import domain.Tweet;
import domain.User;

public interface TweetService extends BaseService<Tweet,Long> {

    void userTweets(User user);

    void seeUserTweets(User user);

    void addTweet(User user);

    Integer eachTweet(Tweet tweet);
}
