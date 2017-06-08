package controllers;

import models.User;
import org.sql2o.Connection;
import services.DataBaseServices;

import java.util.List;

/**
 * Created by Jhon on 7/6/2017.
 */
public class UserDao{

    public static void create(User entitie) {
        String sql = "insert into user (id,userName,name,password,isAdmin,isAuthor)" +
                " values(:id,:userName,:name,:password,:isAdmin,:isAuthor)";
        try (Connection con = DataBaseServices.getInstancia().getConexion()) {
            con.createQuery(sql)
                    .addParameter("id", entitie.getId())
                    .addParameter("userName", entitie.getUserName())
                    .addParameter("name", entitie.getName())
                    .addParameter("password", entitie.getPassword())
                    .addParameter("isAdmin", entitie.getAdmin())
                    .addParameter("isAuthor", entitie.getAuthor())
                    .executeUpdate();
        }
    }

    public static void edit(User entitie) {
        String sql = "UPDATE user SET userName=:userName,name=:name," +
                " password=:password,isAdmin=:isAdmin,isAuthor=:isAuthor" +
                " where id=:id";

        try (Connection con = DataBaseServices.getInstancia().getConexion()) {
            con.createQuery(sql)
                    .addParameter("id", entitie.getId())
                    .addParameter("userName", entitie.getUserName())
                    .addParameter("name", entitie.getName())
                    .addParameter("password", entitie.getPassword())
                    .addParameter("isAdmin", entitie.getAdmin())
                    .addParameter("isAuthor", entitie.getAuthor())
                    .executeUpdate();
        }
    }

    public static void destroy(User entitie) {
        String sql = "DELETE FROM user where id=:id";
        try (Connection con = DataBaseServices.getInstancia().getConexion()) {
            con.createQuery(sql)
                    .addParameter("id", entitie.getId())
                    .executeUpdate();
        }
    }

    public static List<User> findEntities() {
        String sql = "select * from user";
        try (Connection con = DataBaseServices.getInstancia().getConexion()) {
            return con.createQuery(sql).executeAndFetch(User.class);
        }
    }

    public static User find(Integer id) {
        String sql = "select * from user where id=:id";
        try (Connection con = DataBaseServices.getInstancia().getConexion()) {
            List<User> user = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetch(User.class);
            if (!user.isEmpty()){
                return user.get(0);
            }else{
                return null;
            }
        }
    }
}
