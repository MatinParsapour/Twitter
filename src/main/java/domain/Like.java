package domain;

import javax.persistence.Embeddable;

@Embeddable
public class Like {

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
