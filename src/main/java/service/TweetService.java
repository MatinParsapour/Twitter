package service;

import base.service.BaseService;
import domain.*;

import java.util.Dictionary;

public interface TweetService extends BaseService<Tweet,Long> {

    void userTweets(User user);

    void seeUserTweets(User user);

    void addTweet(User user);

    Integer eachTweet(Tweet tweet);

    void tweets(User user);

    Integer toDoWithTweets(Tweet tweet, User user, int counter);

    Integer deletableComments(Tweet tweet, Comment comment);
}
