package domain;

import base.entity.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = Tweet.TWEET_TABLE)
public class Tweet extends BaseEntity<Long> {
    public static final String TWEET_TABLE = "tweet_table";
    private static final String TWEET = "tweet";
    private static final String DIS_LIKES = "dis_likes";
    private static final String LIKES = "likes";

    @Column(name = TWEET,length = 280)
    private String tweet;

    @ElementCollection
    private List<Like> likes;

    @ElementCollection
    private List<DisLike> disLikes = new ArrayList<>();

    @ElementCollection
    private List<Comment> commentList = new ArrayList<>();

    public Tweet() {
    }

    public Tweet(String tweet) {
        this.tweet = tweet;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public List<DisLike> getDisLikes() {
        return disLikes;
    }

    public void setDisLikes(List<DisLike> disLikes) {
        this.disLikes = disLikes;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
}
