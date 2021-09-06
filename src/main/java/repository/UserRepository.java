package repository;

import base.repository.BaseRepository;
import domain.Tweet;
import domain.User;

public interface UserRepository extends BaseRepository<User,Long> {

    User findUserByUserName(String userName);

    User findUserByUserNameAndPassword(String userName, String password);

    User findUserByEmailAndPassword(String email, String password);

    User findUserByUserNameForSearch(String userName);

    User findUserByTweet(Tweet tweet);
}
