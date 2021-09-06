package service.impl;

import base.service.BaseServiceImpl;
import com.github.javafaker.App;
import domain.*;
import repository.TweetRepository;
import service.TweetService;
import util.ApplicationContext;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TweetServiceImpl extends BaseServiceImpl<Tweet,Long, TweetRepository> implements TweetService {
    public TweetServiceImpl(TweetRepository repository) {
        super(repository);
    }

    @Override
    public void userTweets(User user) {
        while(true){
            try{
                ApplicationContext.getDemonstrateMenus().tweetsMenu();
                int choice = new Scanner(System.in).nextInt();
                if(choice == 1){
                    seeUserTweets(user);
                }else if(choice == 2){
                    addTweet(user);
                }else if(choice == 3){
                    break;
                }else{
                    System.out.println("Choose between menu options");
                }
            }catch (InputMismatchException exception){
                System.out.println("Invalid entry");
            }
        }
    }

    @Override
    public void seeUserTweets(User user) {
        if(user.getTweets() != null){
            ApplicationContext.getDemonstrateInformation().tweetsInformation(user);
        }
        else{
            System.out.println("You don't have any tweet");
        }
    }

    @Override
    public void addTweet(User user) {
        while(true){
            try{
                List<Comment> commentList = new ArrayList<>();
                Tweet tweeting = new Tweet();
                System.out.print("Tweet : ");
                String tweet = new Scanner(System.in).nextLine();
                tweeting.setTweet(tweet);
                System.out.println("1.Acceptable    2.Unacceptable");
                int choice = new Scanner(System.in).nextInt();
                if(choice == 1){
                    tweeting.setCommentList(commentList);
                    createOrUpdate(tweeting);
                    user.getTweets().add(tweeting);
                    ApplicationContext.getUserService().createOrUpdate(user);
                    System.out.println("Your tweet successfully created");
                    break;
                }
            }catch (InputMismatchException exception){
                System.out.println("Invalid entry");
            }
        }
    }

    @Override
    public Integer eachTweet(Tweet tweet) {
        while(true){
            try{
                ApplicationContext.getDemonstrateMenus().eachTweetMenu();
                int choice = new Scanner(System.in).nextInt();
                if(choice == 1){
                    editTweet(tweet);
                    return 1;
                }else if(choice == 2){
                    deleteTweet(tweet);
                    return 2;
                }else if(choice == 3){
                    return 3;
                }else if(choice == 4){
                    return 4;
                }else{
                    System.out.println("Choose between menu options");
                }
            }catch (InputMismatchException exception){
                System.out.println("Invalid entry");
            }
        }
    }

    @Override
    public void tweets(User user) {
        List<Tweet> tweets = findAll();
        if(tweets.size() != 0){
            ApplicationContext.getDemonstrateInformation().allTweets(tweets,user);
        }else {
            System.out.println("There's no tweets yet");
        }
    }

    @Override
    public Integer toDoWithTweets(Tweet tweet,User user) {
        while(true){
            try{
                ApplicationContext.getDemonstrateMenus().allTweetsMenu();
                int choice = new Scanner(System.in).nextInt();
                if(choice == 1){
                    addComment(tweet,user);
                    return 1;
                }else if(choice == 2){
                    like(tweet,user);
                    return 2;
                }else if(choice == 3){
                    disLike(tweet,user);
                    return 3;
                }else if(choice == 4){
                    return 4;
                }else if(choice == 5){
                    return 5;
                }else{
                    System.out.println("Choose between menu options");
                }
            }catch (InputMismatchException exception){
                System.out.println("Invalid entry");
            }
        }
    }

    @Override
    public void deleteDisLike(Tweet tweet,String userName) {
        HibernateUtil.getEntityManagerFactory().createEntityManager().getTransaction().begin();
        repository.deleteUserDisLikes(tweet,userName);
        HibernateUtil.getEntityManagerFactory().createEntityManager().getTransaction().commit();
    }

    @Override
    public void deleteLike(Tweet tweet,String userName) {
        HibernateUtil.getEntityManagerFactory().createEntityManager().getTransaction().begin();
        repository.deleteUserLikes(tweet,userName);
        HibernateUtil.getEntityManagerFactory().createEntityManager().getTransaction().commit();
    }

    private void editTweet(Tweet tweet){
        while(true){
            try{
                System.out.print("New tweet : ");
                String newTweet = new Scanner(System.in).nextLine();
                System.out.println("1.Acceptable      2.Unacceptable");
                int choice = new Scanner(System.in).nextInt();
                if(choice == 1){
                    tweet.setTweet(newTweet);
                    createOrUpdate(tweet);
                    System.out.println("Your tweet successfully edited");
                    break;
                }
            }catch (InputMismatchException exception){
                System.out.println("Invalid entry");
            }
        }
    }

    private void deleteTweet(Tweet tweet){
        while(true){
            try{
                System.out.println("Are you sure");
                System.out.println("1.Yes    2.No");
                int choice = new Scanner(System.in).nextInt();
                if(choice == 1){
                    delete(tweet);
                    System.out.println("Your tweet successfully deleted");
                }
                break;
            }catch (InputMismatchException exception){
                System.out.println("Invalid entry");
            }
        }
    }

    private void addComment(Tweet tweet,User user){
        while(true){
            try{
                Comment commenting = new Comment();
                System.out.print("Comment : ");
                String comment = new Scanner(System.in).nextLine();
                System.out.println("1.Acceptable         2.Unacceptable");
                int choice = new Scanner(System.in).nextInt();
                if(choice == 1){
                    commenting.setUser(user.getUserName());
                    commenting.setComment(comment);
                    tweet.getCommentList().add(commenting);
                    createOrUpdate(tweet);
                    System.out.println("You comment added");
                    break;
                }
            }catch (InputMismatchException exception){
                System.out.println("Invalid entry");
            }
        }
    }

    private void like(Tweet tweet,User user){
        Tweet userLikes = repository.findTweetByUserLikes(user.getUserName());
        if(userLikes == null){
            for(int deleteDisLike = 0 ; deleteDisLike < tweet.getDisLikes().size(); deleteDisLike++){
                if(tweet.getDisLikes().get(deleteDisLike).getUserName().equals(user.getUserName())){
                    tweet.getDisLikes().remove(deleteDisLike);
                    createOrUpdate(tweet);
                    break;
                }
            }
            Like like = new Like();
            like.setUserName(user.getUserName());
            tweet.getLikes().add(like);
            createOrUpdate(tweet);
        }else{
            System.out.println("You can't like a tweet more than once");
        }
    }

    private void disLike(Tweet tweet, User user){
        Tweet userDisLikes = repository.findTweetByUserDisLikes(user.getUserName());
        if(userDisLikes == null){
            for(int deleteLike = 0 ; deleteLike < tweet.getLikes().size(); deleteLike++){
                if(tweet.getLikes().get(deleteLike).getUserName().equals(user.getUserName())){
                    tweet.getLikes().remove(deleteLike);
                    createOrUpdate(tweet);
                    break;
                }
            }
            DisLike disLike = new DisLike();
            disLike.setUserName(user.getUserName());
            tweet.getDisLikes().add(disLike);
            createOrUpdate(tweet);
        }else{
            System.out.println("You can't dislike a tweet more than once");
        }
    }
}
