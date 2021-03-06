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

    @Column(name = TWEET,length = 280)
    private String tweet;

    @ElementCollection
    private List<Like> likes = new ArrayList<>();

    @ElementCollection
    private List<DisLike> disLikes = new ArrayList<>();

    @ElementCollection
    private List<Comment> commentList = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
