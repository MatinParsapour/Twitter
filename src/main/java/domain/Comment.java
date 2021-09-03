package domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;

@Embeddable
@Table(name = Comment.COMMENT_TABLE)
public class Comment {
    public static final String COMMENT_TABLE = "comment_table";
    private static final String USER_NAME = "user_name";
    private static final String COMMENT = "comment";

    @Column(name = USER_NAME)
    private String user;

    @Column(name = COMMENT,length = 280)
    private String comment;
}
