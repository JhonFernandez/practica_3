package controllers;

import models.Tag;
import org.sql2o.Connection;
import services.DataBaseServices;

import java.util.List;

/**
 * Created by Jhon on 7/6/2017.
 */
public class TagDao {


    public static void create(Tag entitie) {
        String sql = "insert into tag (id, name) values(:id,:name)";
        try (Connection con = DataBaseServices.getInstancia().getConexion()) {
            con.createQuery(sql)
                    .addParameter("id", entitie.getId())
                    .addParameter("name", entitie.getName())
                    .executeUpdate();
        }
    }

    public static void edit(Tag entitie) {
        String sql = "UPDATE tag SET name =:name where id=:id";

        try (Connection con = DataBaseServices.getInstancia().getConexion()) {
            con.createQuery(sql)
                    .addParameter("id", entitie.getId())
                    .addParameter("name", entitie.getName())
                        .executeUpdate();
        }
    }

    public static void destroy(Tag entitie) {
        String sql = "DELETE FROM tag where id=:id";
        try (Connection con = DataBaseServices.getInstancia().getConexion()) {
            con.createQuery(sql)
                    .addParameter("id", entitie.getId())
                    .executeUpdate();
        }
    }

    public static List<Tag> findEntities() {
        String sql = "select * from tag";
        try (Connection con = DataBaseServices.getInstancia().getConexion()) {
            return con.createQuery(sql).executeAndFetch(Tag.class);
        }
    }

    public static Tag find(Integer id) {
        String sql = "select * from tag where id=:id";
        try (Connection con = DataBaseServices.getInstancia().getConexion()) {
            List<Tag> tags = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetch(Tag.class);
            if (!tags.isEmpty()){
                return tags.get(0);
            }else{
                return null;
            }
        }
    }
}
