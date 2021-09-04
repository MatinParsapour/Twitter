package repository;

import base.repository.BaseRepository;
import domain.User;

public interface UserRepository extends BaseRepository<User,Long> {

    User findUserByUserName(String userName);

    User findUserByUserNameAndPassword(String userName, String password);
}
