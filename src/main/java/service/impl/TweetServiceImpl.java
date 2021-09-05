package service.impl;

import base.service.BaseServiceImpl;
import com.github.javafaker.App;
import domain.Comment;
import domain.Tweet;
import domain.User;
import repository.TweetRepository;
import service.TweetService;
import util.ApplicationContext;

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
        if(user.getTweets().size() != 0){
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
}
