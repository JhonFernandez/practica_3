package controllers;

import models.Article;
import models.ArticleTag;
import models.Tag;
import org.sql2o.Connection;
import services.DataBaseServices;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jhon on 7/6/2017.
 */
public class ArticleTagDao {

    public static void create(ArticleTag entitie) {
        String sql = "insert into article_tag (idArticle,idTag)" +
                " values(:idArticle,:idTag)";
        try (Connection con = DataBaseServices.getInstancia().getConexion()) {
            con.createQuery(sql)
                    .addParameter("idArticle", entitie.getIdArticle())
                    .addParameter("idTag", entitie.getIdTag())
                    .executeUpdate();
        }
    }


    public static void destroy(ArticleTag entitie) {
        String sql = "DELETE FROM ArticleTag where idArticle=:idArticle AND idTag:=idTag";
        try (Connection con = DataBaseServices.getInstancia().getConexion()) {
            con.createQuery(sql)
                    .addParameter("idArticle", entitie.getIdArticle())
                    .addParameter("idTag", entitie.getIdTag())
                    .executeUpdate();
        }
    }

    public static List<ArticleTag> findEntities() {
        String sql = "select * from article_tag";
        try (Connection con = DataBaseServices.getInstancia().getConexion()) {
            return con.createQuery(sql).executeAndFetch(ArticleTag.class);
        }
    }

    public static ArrayList<Tag> findByArticle(Integer idArticle) {
        String sql = "select * from article_tag where idArticle=:idArticle";
        try (Connection con = DataBaseServices.getInstancia().getConexion()) {
            List<ArticleTag> articleTags= con.createQuery(sql)
                    .addParameter("idArticle", idArticle)
                    .executeAndFetch(ArticleTag.class);
            ArrayList<Tag> tagsList = new ArrayList<>();
            for (ArticleTag articleTag:articleTags) {
                tagsList.add(TagDao.find(articleTag.getIdTag()));
            }
            return tagsList;
        }
    }

    public static ArrayList<Article> findByTag(Integer idTag) {
        String sql = "select * from article_tag where idTag=:idTag";
        try (Connection con = DataBaseServices.getInstancia().getConexion()) {
            List<ArticleTag> articleTags= con.createQuery(sql)
                    .addParameter("idTag", idTag)
                    .executeAndFetch(ArticleTag.class);
            ArrayList<Article> articleList = new ArrayList<>();
            for (ArticleTag articleTag:articleTags) {
                articleList.add(ArticleDao.find(articleTag.getIdArticle()));
            }
            return articleList;
        }
    }
}

