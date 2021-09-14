package repository.impl;

import domain.Tweet;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import repository.TweetRepository;
import util.HibernateUtil;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

class TweetRepositoryImplTest {

    private static Tweet tweet1;
    private static Tweet tweet2;
    private static Tweet tweet3;
    private static TweetRepository tweetRepository;

    @BeforeAll
    static void initialization() {
        EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        tweetRepository = new TweetRepositoryImpl(entityManager);
        tweet1 = tweetRepository.findById(19L);
        tweet2 = tweetRepository.findById(29L);
        tweet3 = new Tweet();
    }

    @Test
    void validUserNameForFindingTweetByLikes() {
        //tweet = tweet1 , username = sasan -> Expectation : equals
        assertEquals(tweet1.getId(), tweetRepository.findTweetByUserLikes(tweet1, "sasan").getId());
        //tweet = tweet2 , username = alireza -> Expectation : not equals
        assertNotEquals(tweet1.getId(), tweetRepository.findTweetByUserLikes(tweet2, "alireza").getId());
        //tweet = tweet2 , username = matin -> Expectation : null
        assertNull(tweetRepository.findTweetByUserLikes(tweet2, "matin"));
        //tweet = tweet2 , username = alireza -> Expectation : not null
        assertNotNull(tweetRepository.findTweetByUserLikes(tweet2, "alireza"));
        //tweet = tweet3 , username = sasan -> Expectation : null
        assertNull(tweetRepository.findTweetByUserLikes(tweet3, "sasan"));
        //tweet = tweet2 , username = mohammad -> Expectation : null
        assertNull(tweetRepository.findTweetByUserLikes(tweet2, "mohammad"));
    }

    @Test
    void validUserNameForFindingTweetByDisLikes() {
        //tweet = tweet1 , username = sasan -> Expectation : equals
        assertEquals(tweet1.getId(), tweetRepository.findTweetByUserDisLikes(tweet1, "sasan").getId());
        //tweet = tweet2 , username = sasan -> Expectation : not equals
        assertNotEquals(tweet1.getId(), tweetRepository.findTweetByUserDisLikes(tweet2, "sasan").getId());
        //tweet = tweet1 , username = Amirmohammad -> Expectation : null
        assertNull(tweetRepository.findTweetByUserDisLikes(tweet1, "Amirmohammad"));
        //tweet = tweet1 , username = matin -> Expectation : not null
        assertNotNull(tweetRepository.findTweetByUserDisLikes(tweet1, "matin"));
        //tweet = tweet3 , username = matin -> Expectation : null
        assertNull(tweetRepository.findTweetByUserDisLikes(tweet3, "matin"));
        //tweet = tweet1 , username = f -> Expectation : null
        assertNull(tweetRepository.findTweetByUserDisLikes(tweet1, "f"));
    }

    @Test
    void findTweetByUserComments() {
        //tweet = tweet1 , username = sasan -> Expectation : equals
        assertEquals(tweet1.getId(), tweetRepository.findTweetByUserComments(tweet1, "sasan").get(0).getId());
        //tweet = tweet2 , username = matin -> Expectation : not equals
        assertNotEquals(tweet1.getId(),tweetRepository.findTweetByUserComments(tweet2,"matin").get(0).getId());
    }
}