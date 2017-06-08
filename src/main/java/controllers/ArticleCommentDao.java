package controllers;

import models.*;
import org.sql2o.Connection;
import services.DataBaseServices;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jhon on 7/6/2017.
 */
public class ArticleCommentDao {
    public static void create(ArticleComment entitie) {
        String sql = "insert into article_comment (idArticle,idComment)" +
                " values(:idArticle,:idComment)";
        try (Connection con = DataBaseServices.getInstancia().getConexion()) {
            con.createQuery(sql)
                    .addParameter("idArticle", entitie.getIdArticle())
                    .addParameter("idComment", entitie.getIdComment())
                    .executeUpdate();
        }
    }


    public static void destroy(ArticleComment entitie) {
        String sql = "DELETE FROM ArticleComment where idComment:=idComment";
        try (Connection con = DataBaseServices.getInstancia().getConexion()) {
            con.createQuery(sql)
                    .addParameter("idComment", entitie.getIdComment())
                    .executeUpdate();
        }
    }

    public static List<ArticleComment> findEntities() {
        String sql = "select * from article_comment";
        try (Connection con = DataBaseServices.getInstancia().getConexion()) {
            return con.createQuery(sql).executeAndFetch(ArticleComment.class);
        }
    }

    public static ArrayList<Comment> findByArticle(Integer idArticle) {
        String sql = "select * from article_comment where idArticle=:idArticle";
        try (Connection con = DataBaseServices.getInstancia().getConexion()) {
            List<ArticleComment> articleComments = con.createQuery(sql)
                    .addParameter("idArticle", idArticle)
                    .executeAndFetch(ArticleComment.class);
            ArrayList<Comment> commentsList = new ArrayList<>();
            for (ArticleComment articleComment:articleComments) {
                System.out.println(1);
                commentsList.add(CommentDao.find(articleComment.getIdComment()));
            }
            return commentsList;
        }
    }

    public static Article findByComment(Integer idComment) {
        String sql = "select * from article_comment where idComment=:idComment";
        try (Connection con = DataBaseServices.getInstancia().getConexion()) {
            List<ArticleComment> art_coment = con.createQuery(sql)
                    .addParameter("idComment", idComment)
                    .executeAndFetch(ArticleComment.class);
            if (!art_coment.isEmpty()){
                return ArticleDao.find(art_coment.get(0).getIdArticle());
            }else{
                return null;
            }
        }
    }
}
