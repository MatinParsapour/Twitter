package service;

import base.service.BaseService;
import domain.Tweet;
import domain.User;

public interface UserService extends BaseService<User,Long> {

    void signUp();

    void howToLogIn();

    void logInUsingEmail();

    void logInUsingUserName();

    void mainMenu(User user);

    void profile(User user);

    void changeProfile(User user);

    void deleteField(User user);

    void showUser(Tweet tweet);
}
