package controllers;

import models.ArticleComment;
import models.Comment;
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
public class CommentDao {
    public static void create(Comment entitie) {
        String sql = "insert into comment (id,body,author)"+
                " values(:id,:body,:author)";
        try (Connection con = DataBaseServices.getInstancia().getConexion()) {
            con.createQuery(sql)
                    .addParameter("id", entitie.getId())
                    .addParameter("body", entitie.getBody())
                    .addParameter("author", entitie.getAuthor().getId())
                    .executeUpdate();
            ArticleCommentDao.create(new ArticleComment(entitie.getArticle().getId(),entitie.getId()));
        }
    }

    public static void edit(Comment entitie) {
        String sql = "UPDATE comment SET id=:id,body=:body,author=:author" +
                " where id=:id";

        try (Connection con = DataBaseServices.getInstancia().getConexion()) {
            con.createQuery(sql)
                    .addParameter("id", entitie.getId())
                    .addParameter("body", entitie.getBody())
                    .addParameter("author", entitie.getAuthor().getId())
                    .executeUpdate();
            /* Parese raro un destroy y despues un create
             lo que sucede es que el destroy solo usa el Id del comentario
             deje el metodo que resiva el entidad entera para no romper la nomenclatura
             que se a usado.*/
            ArticleCommentDao.destroy(new ArticleComment(entitie.getArticle().getId(),entitie.getId()));
            ArticleCommentDao.create(new ArticleComment(entitie.getArticle().getId(),entitie.getId()));

        }
    }

    public static void destroy(Comment entitie) {
        String sql = "DELETE FROM comment where id=:id";
        try (Connection con = DataBaseServices.getInstancia().getConexion()) {
            con.createQuery(sql)
                    .addParameter("id", entitie.getId())
                    .executeUpdate();
        }
    }

    public static List<Comment> findEntities() {
        List<Comment> lista = new ArrayList<>();
        java.sql.Connection con = null; //objeto conexion.
        try {

            String query = "select * from comment";
            con = DataBaseServices.getInstancia().getJDBCConexion(); //referencia a la conexion.
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                Comment comment = new Comment();
                comment.setId(rs.getInt("id"));
                comment.setBody(rs.getString("body"));
                comment.setAuthor(UserDao.find(rs.getInt("author")));
                comment.setArticle(ArticleCommentDao.findByComment(comment.getId()));
                lista.add(comment);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CommentDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(CommentDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return lista;
    }

    public static Comment find(Integer id) {
        List<Comment> lista = new ArrayList<>();
        java.sql.Connection con = null; //objeto conexion.
        try {

            String query = "select * from comment where id="+id+";";
            con = DataBaseServices.getInstancia().getJDBCConexion(); //referencia a la conexion.
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                Comment comment = new Comment();
                comment.setId(rs.getInt("id"));
                comment.setBody(rs.getString("body"));
                comment.setAuthor(UserDao.find(rs.getInt("author")));
                comment.setArticle(ArticleCommentDao.findByComment(comment.getId()));
                return comment;
            }

        } catch (SQLException ex) {
            Logger.getLogger(CommentDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(CommentDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}
