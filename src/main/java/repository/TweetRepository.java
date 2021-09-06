package repository;

import base.repository.BaseRepository;
import domain.Like;
import domain.Tweet;

public interface TweetRepository extends BaseRepository<Tweet,Long> {

    Tweet findTweetByUserLikes(String username);

    Tweet findTweetByUserDisLikes(String userName);

    void deleteUserDisLikes(Tweet tweet, String userName);

    void deleteUserLikes(Tweet tweet ,String userName);
}
