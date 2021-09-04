package domain;

import base.entity.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = Tweet.TWEET_TABLE)
public class Tweet extends BaseEntity<Long> {
    public static final String TWEET_TABLE = "tweet_table";
    private static final String TWEET = "tweet";
    private static final String DIS_LIKES = "dis_likes";
    private static final String LIKES = "likes";

    @Column(name = TWEET)
    private String tweet;

    @Column(name = LIKES)
    private Integer likes;

    @Column(name = DIS_LIKES)
    private Integer disLikes;

    @ElementCollection
    private List<Comment> commentList;

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

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getDisLikes() {
        return disLikes;
    }

    public void setDisLikes(Integer disLikes) {
        this.disLikes = disLikes;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
}
