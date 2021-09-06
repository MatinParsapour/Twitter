package repository;

import base.repository.BaseRepository;
import domain.Like;
import domain.Tweet;

import java.util.List;

public interface TweetRepository extends BaseRepository<Tweet,Long> {

    Tweet findTweetByUserLikes(Tweet tweet,String userName);

    Tweet findTweetByUserDisLikes(Tweet tweet,String userName);

    List<Tweet> findTweetByUserComments(Tweet tweet,String userName);
}
