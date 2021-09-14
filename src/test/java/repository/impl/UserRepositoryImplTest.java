package repository.impl;

import domain.Tweet;
import domain.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repository.TweetRepository;
import repository.UserRepository;
import util.HibernateUtil;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryImplTest {
    private static User user1;
    private static User user2;
    private static Tweet tweet1;
    private static Tweet tweet2;
    private static UserRepository userRepository;

    @BeforeAll
    static void init(){
        EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        userRepository = new UserRepositoryImpl(entityManager);
        user1 = new User();
        user1.setId(28L);
        user1.setUserName("alireza");
        user2 = new User();
        user2.setId(18L);
        TweetRepository tweetRepository = new TweetRepositoryImpl(entityManager);
        tweet1 = tweetRepository.findById(19L);
        tweet2 = new Tweet();
    }

    @Test
    void validUserName() {
        //username = alireza -> Expectation : equals
        assertEquals(user1.getId(),userRepository.findUserByUserName("alireza").getId());
        //username = mohammad -> Expectation : not equals
        assertNotEquals(user1.getId(),userRepository.findUserByUserName("sasan").getId());
        //username = baqer -> Expectation : null
        assertNull(userRepository.findUserByUserName("baqer"));
        //username = matin -> Expectation : not null
        assertNotNull(userRepository.findUserByUserName("matin"));
    }

    @Test
    void validUserNameAndPassword() {
        //username = alireza , password = 1234567890 -> Expectation : equals
        assertEquals(user1.getId(),userRepository.findUserByUserNameAndPassword("alireza","1234567890").getId());
        //username = mohammad , password = 1234567890 -> Expectation : not equals
        assertNotEquals(user1.getId(),userRepository.findUserByUserNameAndPassword("mohammad","1234567890"));
        //username = alireza , password = 0987654321 -> Expectation : null
        assertNull(userRepository.findUserByUserNameAndPassword("alireza","0987654321"));
        //username = mohammad , password = 1234567890 -> Expectation : not null
        assertNotNull(userRepository.findUserByUserNameAndPassword("sasan","1234567890"));
    }

    @Test
    void validEmailAndPassword() {
        //Email = alireza@gmail.com , password = 1234567890 -> Expectation : equals
        assertEquals(user1.getId(),userRepository.findUserByEmailAndPassword("alireza@gmail.com","1234567890").getId());
        //Email = sasan@gmail.com , password = 1234567890 -> Expectation : not equals
        assertNotEquals(user1.getId(), userRepository.findUserByEmailAndPassword("sasan@gmail.com","1234567890").getId());
        //Email = mohammad@gmail.com , password = 1234567890 -> Expectation : null
        assertNull(userRepository.findUserByEmailAndPassword("mohammad@gmail.com","1234567890"));
        //Email = sasan@gmail.com , password = 1234567890 -> Expectation = not null
        assertNotNull(userRepository.findUserByEmailAndPassword("sasan@gmail.com","1234567890"));
    }

    @Test
    void validUserNameForSearch() {
        //username = alireza -> Expectation : equals
        assertEquals(user1.getUserName(),userRepository.findUserByUserNameForSearch("alireza").getUserName());
        //username = alireza -> Expectation : null (find by id when in original method we didn't get id)
        assertNull(userRepository.findUserByUserNameForSearch("alireza").getId());
        //username = mohammad -> Expectation : null
        assertNull(userRepository.findUserByUserNameForSearch("mohammad"));
        //username = matin -> Expectation : not null
        assertNotNull(userRepository.findUserByUserNameForSearch("matin"));
    }

    @Test
    void validTweetToFindUser() {
        //tweet = tweet1 -> Expectation : equals
        assertEquals(user2.getId(),userRepository.findUserByTweet(tweet1).getId());
        //tweet = tweet1 -> Expectation : not equals
        assertNotEquals(user1.getId(),userRepository.findUserByTweet(tweet1).getId());
        //tweet = tweet2 -> Expectation : null
        assertNull(userRepository.findUserByTweet(tweet2));
        //tweet = tweet1 -> Expectation : nul null
        assertNotNull(userRepository.findUserByTweet(tweet1));
    }
}