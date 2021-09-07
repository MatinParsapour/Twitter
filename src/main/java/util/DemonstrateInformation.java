package util;

import domain.Tweet;
import domain.User;

import java.util.List;

public class DemonstrateInformation {
    public void userInformation(User user){
        int firstNameSize = user.getFirstName().length();
        int lastNameSize;
        int phoneNumberSize;
        int bioSize;
        if(user.getLastName() != null){
            lastNameSize = user.getLastName().length();
        }else{
            lastNameSize = 10;
        }if(user.getPhoneNumber() != null){
            phoneNumberSize = user.getPhoneNumber().length();
        }else{
            phoneNumberSize = 12;
        }if(user.getBio() != null){
            bioSize = user.getBio().length();
        }else{
            bioSize = 5;
        }
        int userNameSize = user.getUserName().length();
        int passwordSize = user.getPassword().length();
        int emailSize = user.getEmail().length();
        int birthdaySize = 10;

        int cover = firstNameSize + lastNameSize + userNameSize + passwordSize + emailSize + phoneNumberSize +
                bioSize + birthdaySize + 48;

        printUserHeader(firstNameSize, lastNameSize, phoneNumberSize, bioSize, userNameSize, passwordSize, emailSize, birthdaySize, cover);

        printUserInformation(user, firstNameSize, lastNameSize, phoneNumberSize, bioSize, userNameSize, passwordSize, emailSize, birthdaySize, cover);
    }

    private void printUserInformation(User user, int firstNameSize,
                                      int lastNameSize, int phoneNumberSize,
                                      int bioSize, int userNameSize,
                                      int passwordSize, int emailSize,
                                      int birthdaySize, int cover) {
        System.out.format("| %" +(-(firstNameSize + 9)) + "s", user.getFirstName());
        if(user.getLastName() != null){
            System.out.format("|%" +(-(lastNameSize + 8)) + "s", user.getLastName());
        }else{
            System.out.format("|%" +(-(lastNameSize + 8)) + "s","");
        }
        System.out.format("|%" +(-(userNameSize + 8)) + "s", user.getUserName());
        System.out.format("|%" +(-(passwordSize + 3)) + "s", user.getPassword());
        System.out.format("|%" +(-(emailSize + 3)) + "s", user.getEmail());
        if(user.getPhoneNumber() != null){
            System.out.format("|%" +(-(phoneNumberSize + 3)) + "s", user.getPhoneNumber());
        }else{
            System.out.format("|%" +(-(phoneNumberSize + 3)) + "s","");
        }
        if(user.getBio() != null){
            System.out.format("|%" +(-(bioSize + 3)) + "s", user.getBio());
        }else{
            System.out.format("|%" +(-(bioSize + 3)) + "s","");
        }
        System.out.format("|%" +(-(birthdaySize + 3)) + "s |\n", user.getBirthday());


        System.out.print("+");
        for(int header = 0; header <= cover; header++){
            System.out.print("-");
        }
        System.out.println("+");
    }

    private void printUserHeader(int firstNameSize, int lastNameSize,
                                 int phoneNumberSize, int bioSize,
                                 int userNameSize, int passwordSize,
                                 int emailSize, int birthdaySize,
                                 int cover) {
        System.out.print("+");
        for(int header = 0; header <= cover; header++){
            System.out.print("-");
        }
        System.out.println("+");

        System.out.format("| %" +(-(firstNameSize + 9)) + "s","firstname");
        System.out.format("|%" +(-(lastNameSize + 8)) + "s","lastname");
        System.out.format("|%" +(-(userNameSize + 8)) + "s","username");
        System.out.format("|%" +(-(passwordSize + 3)) + "s","password");
        System.out.format("|%" +(-(emailSize + 3)) + "s","email");
        System.out.format("|%" +(-(phoneNumberSize + 3)) + "s","phonenumber");
        System.out.format("|%" +(-(bioSize + 3)) + "s","bio");
        System.out.format("|%" +(-(birthdaySize + 3)) + "s |\n","birthday");

        System.out.print("+");
        for(int header = 0; header <= cover; header++){
            System.out.print("-");
        }
        System.out.println("+");
    }

    public void tweetsInformation(User user){
        int tweetSize = 0;
        int userNameSize = 0;
        int commentSize = 0;
        for(int tweetSizes = 0 ; tweetSizes < user.getTweets().size(); tweetSizes++){
            if(user.getTweets().get(tweetSizes).getTweet().length() > tweetSize){
                tweetSize = user.getTweets().get(tweetSizes).getTweet().length();
            }
        }
        int likesSize = 10;
        int disLikesSize = 10;
        printUserTweets(user, tweetSize, userNameSize, commentSize, likesSize, disLikesSize);
    }

    private void printUserTweets(User user, int tweetSize,
                                 int userNameSize, int commentSize,
                                 int likesSize, int disLikesSize) {
        for(int tweet = 0; tweet < user.getTweets().size() ; tweet++){
            int cover = tweetSize + likesSize + disLikesSize + 9;
            printTweetHeader(tweetSize, likesSize, disLikesSize, cover);
            printUserTweetInformation(user, tweetSize, likesSize, disLikesSize, tweet, cover);
            if(user.getTweets().get(tweet).getCommentList().size() != 0){
                for(int userNameSizes = 0; userNameSizes < user.getTweets().get(tweet).getCommentList().get(tweet).getUser().length(); userNameSizes++){
                    if(user.getTweets().get(tweet).getCommentList().get(tweet).getUser().length() > tweetSize){
                        userNameSize = user.getTweets().get(tweet).getCommentList().get(tweet).getUser().length();
                    }
                }for(int commentSizes = 0; commentSizes < user.getTweets().get(tweet).getCommentList().get(tweet).getComment().length(); commentSizes++){
                    if(user.getTweets().get(tweet).getCommentList().get(tweet).getComment().length() > tweetSize){
                        commentSize = user.getTweets().get(tweet).getCommentList().get(tweet).getComment().length();
                    }
                }
                for(int comment = 0; comment < user.getTweets().get(tweet).getCommentList().size() ; comment++){
                    System.out.format(" %" + (-userNameSize) + "s : ", user.getTweets().get(tweet).getCommentList().get(comment).getUser());
                    System.out.format("        %" + (-commentSize) + "s\n", user.getTweets().get(tweet).getCommentList().get(comment).getComment());
                }
            }
            int nextMove = ApplicationContext.getTweetService().eachTweet(user.getTweets().get(tweet));
            if(nextMove == 4 || nextMove == 2){
                break;
            }else if(nextMove == 1){
                --tweet;
            }
        }
    }

    private void printUserTweetInformation(User user, int tweetSize,
                                           int likesSize, int disLikesSize,
                                           int tweet, int cover) {
        System.out.format("| %" + (-(tweetSize + 7)) + "s", user.getTweets().get(tweet).getTweet());
        System.out.format("|%" + (-likesSize) + "s", user.getTweets().get(tweet).getLikes().size());
        System.out.format("|%" + (-disLikesSize) + "s|\n", user.getTweets().get(tweet).getDisLikes().size());
        System.out.print("+");
        for(int header = 0; header <= cover; header++){
            System.out.print("-");
        }
        System.out.println("+");
    }

    private void printTweetHeader(int tweetSize, int likesSize,
                                  int disLikesSize, int cover) {
        System.out.print("+");
        for(int header = 0; header <= cover; header++){
            System.out.print("-");
        }
        System.out.println("+");
        System.out.format("| %" + (-(tweetSize + 7)) + "s", "tweet");
        System.out.format("|%" + (-likesSize) + "s","likes");
        System.out.format("|%" + (-disLikesSize) + "s|\n","dislikes");
        System.out.print("+");
        for(int header = 0; header <= cover; header++){
            System.out.print("-");
        }
        System.out.println("+");
    }

    public void allTweets(List<Tweet> tweetList,User user){
        int tweetSize = 0;
        int userNameSize = 0;
        int commentSize = 0;
        for(int tweetSizes = 0 ; tweetSizes < tweetList.size(); tweetSizes++){
            if(tweetList.get(tweetSizes).getTweet().length() > tweetSize){
                tweetSize = tweetList.get(tweetSizes).getTweet().length();
            }
        }
        int likesSize = 10;
        int disLikesSize = 10;
        printAllTweets(tweetList,tweetSize,likesSize,disLikesSize,userNameSize,commentSize,user);
    }

    private void printAllTweets(List<Tweet> tweets, int tweetSize,
                                int likesSize, int disLikesSize,
                                int userNameSize, int commentSize,
                                User user){
        for(int tweet = 0; tweet < tweets.size() ; tweet++){
            int cover = tweetSize + likesSize + disLikesSize + 9;
            ApplicationContext.getUserService().showUser(tweets.get(tweet));
            printTweetHeader(tweetSize, likesSize, disLikesSize, cover);
            printAllTweetsInformation(tweets, tweetSize, likesSize, disLikesSize, tweet, cover);
            if(tweets.get(tweet).getCommentList().size() != 0){
                for(int userNameSizes = 0; userNameSizes < tweets.get(tweet).getCommentList().size(); userNameSizes++){
                    if(tweets.get(tweet).getCommentList().get(userNameSizes).getUser().length() > tweetSize){
                        userNameSize = tweets.get(tweet).getCommentList().get(userNameSizes).getUser().length();
                    }
                }for(int commentSizes = 0; commentSizes < tweets.get(tweet).getCommentList().size(); commentSizes++){
                    if(tweets.get(tweet).getCommentList().get(commentSizes).getComment().length() > tweetSize){
                        commentSize = tweets.get(tweet).getCommentList().get(commentSizes).getComment().length();
                    }
                }
                for(int comment = 0; comment < tweets.get(tweet).getCommentList().size() ; comment++){
                    System.out.format(Color.ANSI_BLUE + " %" + (-(userNameSize + 5)) + "s : \n", tweets.get(tweet).getCommentList().get(comment).getUser() + Color.ANSI_RESET);
                    System.out.format(Color.ANSI_YELLOW + "        %" + (-(commentSize + 5)) + "s\n", tweets.get(tweet).getCommentList().get(comment).getComment() + Color.ANSI_RESET);
                }
            }
            int nextMove = ApplicationContext.getTweetService().toDoWithTweets(tweets.get(tweet),user,tweet);
            if(nextMove == 5){
                break;
            }
            if(nextMove == 1 || nextMove == 2 || nextMove == 3){
                --tweet;
            }
        }
    }
    private void printAllTweetsInformation(List<Tweet> tweets, int tweetSize,
                                           int likesSize , int disLikesSize,
                                           int tweet, int cover){
        System.out.format("| %" + (-(tweetSize + 7)) + "s", tweets.get(tweet).getTweet());
        System.out.format("|%" + (-(likesSize)) + "s", tweets.get(tweet).getLikes().size());
        System.out.format("|%" + (-disLikesSize) + "s|\n", tweets.get(tweet).getDisLikes().size());
        System.out.print("+");
        for(int header = 0; header <= cover; header++){
            System.out.print("-");
        }
        System.out.println("+");
    }

    public void printUserInSearch(User user){
        int firstNameSize = user.getFirstName().length();
        int lastNameSize;
        int phoneNumberSize;
        int bioSize;
        if(user.getLastName() != null){
            lastNameSize = user.getLastName().length();
        }else{
            lastNameSize = 10;
        }if(user.getPhoneNumber() != null){
            phoneNumberSize = user.getPhoneNumber().length();
        }else{
            phoneNumberSize = 12;
        }if(user.getBio() != null){
            bioSize = user.getBio().length();
        }else{
            bioSize = 5;
        }
        int userNameSize = user.getUserName().length();
        int emailSize = user.getEmail().length();

        int cover = firstNameSize + lastNameSize + userNameSize + emailSize + phoneNumberSize +
                bioSize + 39;

        userHeaderInSearch(firstNameSize,lastNameSize,userNameSize,emailSize,phoneNumberSize,bioSize,cover);

        userInformationInSearch(firstNameSize,lastNameSize,userNameSize,emailSize,phoneNumberSize,bioSize,cover,user);

    }

    private void userHeaderInSearch(int firstNameSize, int lastNameSize,
                                    int userNameSize, int emailSize,
                                    int phoneNumberSize, int bioSize,
                                    int cover){
        System.out.print("+");
        for(int header = 0; header <= cover; header++){
            System.out.print("-");
        }
        System.out.println("+");

        System.out.format("| %" +(-(firstNameSize + 9)) + "s","firstname");
        System.out.format("|%" +(-(lastNameSize + 8)) + "s","lastname");
        System.out.format("|%" +(-(userNameSize + 8)) + "s","username");
        System.out.format("|%" +(-(emailSize + 3)) + "s","email");
        System.out.format("|%" +(-(phoneNumberSize + 3)) + "s","phonenumber");
        System.out.format("|%" +(-(bioSize + 3)) + "s|\n","bio");

        System.out.print("+");
        for(int header = 0; header <= cover; header++){
            System.out.print("-");
        }
        System.out.println("+");
    }
    private void userInformationInSearch(int firstNameSize, int lastNameSize,
                                         int userNameSize, int emailSize,
                                         int phoneNumberSize, int bioSize,
                                         int cover, User user){
        System.out.format("| %" +(-(firstNameSize + 9)) + "s", user.getFirstName());
        if(user.getLastName() != null){
            System.out.format("|%" +(-(lastNameSize + 8)) + "s", user.getLastName());
        }else{
            System.out.format("|%" +(-(lastNameSize + 8)) + "s","");
        }
        System.out.format("|%" +(-(userNameSize + 8)) + "s", user.getUserName());
        System.out.format("|%" +(-(emailSize + 3)) + "s", user.getEmail());
        if(user.getPhoneNumber() != null){
            System.out.format("|%" +(-(phoneNumberSize + 3)) + "s", user.getPhoneNumber());
        }else{
            System.out.format("|%" +(-(phoneNumberSize + 3)) + "s","");
        }
        if(user.getBio() != null){
            System.out.format("|%" +(-(bioSize + 3)) + "s|\n", user.getBio());
        }else{
            System.out.format("|%" +(-(bioSize + 3)) + "s|\n","");
        }


        System.out.print("+");
        for(int header = 0; header <= cover; header++){
            System.out.print("-");
        }
        System.out.println("+");
    }

    public void printNominatedForDelete(List<Tweet> tweets,User user,int tweet){
        for(int counter = 0 ; counter <tweets.get(tweet).getCommentList().size() ; counter++){
            if(tweets.get(tweet).getCommentList().get(counter).getUser().equals(user.getUserName())){
                System.out.println(tweets.get(tweet).getCommentList().get(counter).getComment());
                int nextMove = ApplicationContext.getTweetService().deletableComments(tweets.get(tweet),tweets.get(tweet).getCommentList().get(counter));
                if(nextMove == 3){
                    break;
                }
            }
        }
    }

    public void printNominatedForEdit(List<Tweet> tweets, User user, int tweet){
        for(int counter = 0 ; counter <tweets.get(tweet).getCommentList().size() ; counter++){
            if(tweets.get(tweet).getCommentList().get(counter).getUser().equals(user.getUserName())){
                System.out.println(tweets.get(tweet).getCommentList().get(counter).getComment());
                int nextMove = ApplicationContext.getTweetService().editableComments(tweets.get(tweet),tweets.get(tweet).getCommentList().get(counter));
                if(nextMove == 3){
                    break;
                }
            }
        }
    }
}
