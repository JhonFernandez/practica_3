package models;

/**
 * Created by Jhon on 7/6/2017.
 */
public class ArticleComment {
    private Integer idArticle;
    private Integer idComment;

    public ArticleComment() {
    }

    public ArticleComment(Integer idArticle, Integer idComment) {
        this.idArticle = idArticle;
        this.idComment = idComment;
    }

    public Integer getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(Integer idArticle) {
        this.idArticle = idArticle;
    }

    public Integer getIdComment() {
        return idComment;
    }

    public void setIdComment(Integer idComment) {
        this.idComment = idComment;
    }

    @Override
    public String toString() {
        return "ArticleComment{" +
                "idArticle=" + idArticle +
                ", idComment=" + idComment +
                '}';
    }
}
