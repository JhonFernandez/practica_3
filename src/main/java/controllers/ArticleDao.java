package controllers;

import models.Article;
import org.sql2o.Connection;
import services.DataBaseServices;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Jhon on 7/6/2017.
 */
public class ArticleDao {
    public static void create(Article entitie) {
        String sql = "insert into article (id,title,author,body ,releaseDate)" +
                " values(:id,:title,:author,:body,:releaseDate)";
        try (Connection con = DataBaseServices.getInstancia().getConexion()) {
            con.createQuery(sql)
                    .addParameter("id", entitie.getId())
                    .addParameter("title", entitie.getTitle())
                    .addParameter("author", entitie.getAuthor().getId())
                    .addParameter("body", entitie.getBody())
                    .addParameter("releaseDate", entitie.getReleaseDate())
                    .executeUpdate();
        }
    }

    public static void edit(Article entitie) {
        String sql = "UPDATE article SET title=:title,author=:author," +
                " body=:body,releaseDate=:releaseDate" +
                " where id=:id";

        try (Connection con = DataBaseServices.getInstancia().getConexion()) {
            con.createQuery(sql)
                    .addParameter("id", entitie.getId())
                    .addParameter("title", entitie.getTitle())
                    .addParameter("author", entitie.getAuthor().getId())
                    .addParameter("body", entitie.getBody())
                    .addParameter("releaseDate", entitie.getReleaseDate())
                    .executeUpdate();
        }
    }

    public static void destroy(Article entitie) {
        String sql = "DELETE FROM article where id=:id";
        try (Connection con = DataBaseServices.getInstancia().getConexion()) {
            con.createQuery(sql)
                    .addParameter("id", entitie.getId())
                    .executeUpdate();
        }
    }

    public static List<Article> findEntities() {
        List<Article> lista = new ArrayList<>();
        java.sql.Connection con = null; //objeto conexion.
        try {

            String query = "select * from article";
            con = DataBaseServices.getInstancia().getJDBCConexion(); //referencia a la conexion.
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                Article art = new Article();
                art.setId(rs.getInt("id"));
                art.setBody(rs.getString("body"));
                art.setReleaseDate(rs.getDate("releaseDate"));
                art.setTitle(rs.getString("title"));
                art.setAuthor(UserDao.find(rs.getInt("author")));
                art.setTagList(ArticleTagDao.findByArticle(art.getId()));
                art.setCommentList(ArticleCommentDao.findByArticle(art.getId()));
                lista.add(art);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ArticleDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ArticleDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return lista;
    }

    public static Article find(Integer id) {
        Article art = new Article();
        java.sql.Connection con = null; //objeto conexion.
        try {

            String query = "select * from article where id ="+id+";";
            con = DataBaseServices.getInstancia().getJDBCConexion(); //referencia a la conexion.
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){

                art.setId(rs.getInt("id"));
                art.setBody(rs.getString("body"));
                art.setReleaseDate(rs.getDate("releaseDate"));
                art.setTitle(rs.getString("title"));
                art.setAuthor(UserDao.find(rs.getInt("author")));


                art.setTagList(ArticleTagDao.findByArticle(art.getId()));


                return art;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ArticleDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ArticleDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return art;

    }
}
