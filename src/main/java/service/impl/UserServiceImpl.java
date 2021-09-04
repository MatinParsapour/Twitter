package service.impl;

import base.service.BaseServiceImpl;
import domain.User;
import repository.UserRepository;
import service.UserService;

public class UserServiceImpl extends BaseServiceImpl<User,Long, UserRepository> implements UserService {
    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }
}
