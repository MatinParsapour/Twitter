package util;

import repository.TweetRepository;
import repository.UserRepository;
import repository.impl.TweetRepositoryImpl;
import repository.impl.UserRepositoryImpl;
import service.TweetService;
import service.UserService;
import service.impl.TweetServiceImpl;
import service.impl.UserServiceImpl;

import javax.persistence.EntityManager;

public class ApplicationContext {
    private static final EntityManager entityManager;

    private static final TweetRepository tweetRepository;

    private static final TweetService tweetService;

    private static final UserRepository userRepository;

    private static final UserService userService;

    private static final DemonstrateMenus demonstrateMenus;

    private static final DemonstrateInformation demonstrateInformation;

    static {
        entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        tweetRepository = new TweetRepositoryImpl(entityManager);
        tweetService = new TweetServiceImpl(tweetRepository);
        userRepository = new UserRepositoryImpl(entityManager);
        userService = new UserServiceImpl(userRepository);
        demonstrateMenus = new DemonstrateMenus();
        demonstrateInformation = new DemonstrateInformation();
    }

    public static TweetService getTweetService(){
        return tweetService;
    }

    public static UserService getUserService(){
        return userService;
    }

    public static DemonstrateMenus getDemonstrateMenus(){
        return demonstrateMenus;
    }

    public static DemonstrateInformation getDemonstrateInformation(){return demonstrateInformation;}
}
