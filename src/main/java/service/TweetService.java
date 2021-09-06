package service;

import base.service.BaseService;
import domain.DisLike;
import domain.Like;
import domain.Tweet;
import domain.User;

import java.util.Dictionary;

public interface TweetService extends BaseService<Tweet,Long> {

    void userTweets(User user);

    void seeUserTweets(User user);

    void addTweet(User user);

    Integer eachTweet(Tweet tweet);

    void tweets(User user);

    Integer toDoWithTweets(Tweet tweet, User user);

    void deleteDisLike(Tweet tweet,String userName);

    void deleteLike(Tweet tweet,String userName);
}
