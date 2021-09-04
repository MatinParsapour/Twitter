package domain;

import base.entity.BaseEntity;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = User.TABLE_NAME)
public class User extends BaseEntity<Long> {
    public static final String TABLE_NAME = "user_table";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String USER_NAME = "user_name";
    private static final String PASSWORD = "password";
    private static final String BIRTHDAY = "birthday";
    private static final String EMAIL = "email";
    private static final String PHONE_NUMBER = "phone_number";
    private static final String BIO = "bio";

    @Column(name = FIRST_NAME)
    private String firstName;

    @Column(name = LAST_NAME)
    private String lastName;

    @Column(name = USER_NAME,unique = true)
    private String userName;

    @Column(name = PASSWORD)
    private String password;

    @Column(name = BIRTHDAY)
    private LocalDate birthday;

    @Column(name = EMAIL)
    private String email;

    @Column(name = PHONE_NUMBER)
    private String phoneNumber;

    @Column(name = BIO,length = 160)
    private String bio;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Tweet> tweets;

    public User() {
    }

    public User(String firstName, String userName, String password) {
        this.firstName = firstName;
        this.userName = userName;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweetList) {
        this.tweets = tweetList;
    }
}
