package models;

/**
 * Created by Jhon on 7/6/2017.
 */
public class ArticleTag {
    private Integer idArticle;
    private Integer idTag;

    public ArticleTag() {
    }

    public ArticleTag(Integer idArticle, Integer idTag) {
        this.idArticle = idArticle;
        this.idTag = idTag;
    }

    public Integer getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(Integer idArticle) {
        this.idArticle = idArticle;
    }

    public Integer getIdTag() {
        return idTag;
    }

    public void setIdTag(Integer idTag) {
        this.idTag = idTag;
    }

    @Override
    public String toString() {
        return "ArticleTag{" +
                "idArticle=" + idArticle +
                ", idTag=" + idTag +
                '}';
    }
}
