package service;

import base.service.BaseService;
import domain.User;

public interface UserService extends BaseService<User,Long> {

    void signUp();

    void howToLogIn();

    void logInUsingEmail();

    void logInUsingUserName();
}
