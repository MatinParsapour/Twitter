package repository;

import base.repository.BaseRepository;
import domain.Like;
import domain.Tweet;

import java.util.List;

public interface TweetRepository extends BaseRepository<Tweet,Long> {

    Tweet findTweetByUserLikes(String userName);

    Tweet findTweetByUserDisLikes(String userName);

    List<Tweet> findTweetByUserComments(Tweet tweet,String userName);
}
