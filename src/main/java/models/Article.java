package models;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by Jhon on 7/6/2017.
 */
public class Article {
    private Integer id;
    private String title;
    private User author;
    private String body;
    private Date releaseDate;
    private ArrayList<Comment> commentList;
    private ArrayList<Tag> tagList;

    public Article() {
    }

    public Article(Integer id, String title, User author, String body, Date releaseDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.body = body;
        this.releaseDate = releaseDate;
    }

    public Article(Integer id, String title, User author, String body, Date releaseDate, ArrayList<Comment> commentList, ArrayList<Tag> tagList) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.body = body;
        this.releaseDate = releaseDate;
        this.commentList = commentList;
        this.tagList = tagList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public ArrayList<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(ArrayList<Comment> commentList) {
        this.commentList = commentList;
    }

    public ArrayList<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(ArrayList<Tag> tagList) {
        this.tagList = tagList;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", body='" + body + '\'' +
                ", releaseDate=" + releaseDate +
                ", commentList=" + (commentList==null?" ":commentList) +
                ", tagList=" + (tagList==null?" ":tagList)  +
                '}';
    }
}
