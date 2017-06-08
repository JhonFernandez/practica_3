package models;

/**
 * Created by Jhon on 7/6/2017.
 */
public class Comment {
    private Integer id;
    private Article article;
    private String body;
    private User author;

    public Comment() {
    }

    public Comment(Integer id, Article article, String body, User author) {
        this.id = id;
        this.article = article;
        this.body = body;
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", article=" + article.getId() +
                ", body='" + body + '\'' +
                ", author=" + author.getId() +
                '}';
    }
}
