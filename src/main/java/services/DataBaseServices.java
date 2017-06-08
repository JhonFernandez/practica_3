package services;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase con patrón Singleton
 * Created by vacax on 27/05/16.
 */
public class DataBaseServices {

    private static DataBaseServices instancia;
    private String URL = "jdbc:h2:tcp://localhost/~/tarea3"; //Modo Server...

    /**
     *Implementando el patron Singleton
     */
    private DataBaseServices(){
        registrarDriver();
        createDataBase();
    }

    /**
     * Retornando la instancia.
     * @return
     */
    public static DataBaseServices getInstancia(){
        if(instancia==null){
             instancia = new DataBaseServices();
        }
        return instancia;
    }

    /**
     * Metodo para el registro de driver de conexión.
     */
    private void registrarDriver() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Error en registrarDriver: " );
            System.err.println(ex.toString());
        }
    }

    public Connection getConexion() {
        Connection con = null;
        con = new Sql2o(URL, "sa", "").open();
        return con;
    }

    public java.sql.Connection getJDBCConexion() {
        java.sql.Connection con = null;
        try {
            con = DriverManager.getConnection(URL, "sa", "");
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public void testConexion() {
        getConexion().close();
        System.out.println("Conexión realizado con exito...");
    }

    private void createDataBase(){
        String sqlCreateTags="CREATE TABLE IF NOT EXISTS tag" +
                "( id INT PRIMARY KEY,\n" +
                " name VARCHAR(100) NOT NULL UNIQUE);";

        String sqlCreateUsers= "CREATE TABLE IF NOT EXISTS user" +
                "( id INT PRIMARY KEY,\n" +
                "  userName VARCHAR(100) NOT NULL UNIQUE,\n" +
                "  name VARCHAR(100) NOT NULL,\n" +
                "  password VARCHAR(100) NOT NULL,\n" +
                "  isAdmin BOOLEAN NOT NULL,\n" +
                "  isAuthor BOOLEAN NOT NULL);\n";

        String sqlCreateComment= "CREATE TABLE IF NOT EXISTS comment" +
                "( id INT PRIMARY KEY,\n" +
                "  body LONGTEXT NOT NULL,\n" +
                "  author INT NOT NULL );" +
                "  ALTER TABLE public.comment\n" +
                "  ADD CONSTRAINT comment_fk FOREIGN KEY (author)\n" +
                "    REFERENCES user (id)\n" +
                "    ON DELETE NO ACTION\n" +
                "    ON UPDATE NO ACTION\n" +
                "    NOT DEFERRABLE;\n";

        String sqlCreateArticle= "CREATE TABLE IF NOT EXISTS article" +
                "( id INT PRIMARY KEY," +
                "  title VARCHAR(100) NOT NULL UNIQUE," +
                "  author INT NOT NULL," +
                "  body LONGTEXT NOT NULL," +
                "  releaseDate DATE NOT NULL);" +
                "ALTER TABLE article\n" +
                "  ADD CONSTRAINT article_fk FOREIGN KEY (author)\n" +
                "    REFERENCES user (id)\n" +
                "    ON DELETE NO ACTION\n" +
                "    ON UPDATE NO ACTION\n" +
                "    NOT DEFERRABLE;";

        String sqlCreateArticleComment= "CREATE TABLE IF NOT EXISTS article_comment" +
                "( idComment INT PRIMARY KEY," +
                " idArticle INT NOT NULL);" +
                "ALTER TABLE article_comment\n" +
                "  ADD CONSTRAINT article_comment_fk FOREIGN KEY (idArticle)\n" +
                "    REFERENCES article(id)\n" +
                "    ON DELETE NO ACTION\n" +
                "    ON UPDATE NO ACTION\n" +
                "    NOT DEFERRABLE;" +
                "ALTER TABLE article_comment\n" +
                "  ADD CONSTRAINT article_comment_fk1 FOREIGN KEY (idComment)\n" +
                "    REFERENCES comment(id)\n" +
                "    ON DELETE NO ACTION\n" +
                "    ON UPDATE NO ACTION\n" +
                "    NOT DEFERRABLE;";



        String sqlCreateArticleTag= "CREATE TABLE IF NOT EXISTS article_tag" +
                "( idArticle INT NOT NULL," +
                "  idTag INT NOT NULL," +
                "  PRIMARY KEY(idArticle, idTag));" +
                "ALTER TABLE article_tag\n" +
                "  ADD CONSTRAINT article_tag_fk FOREIGN KEY (idArticle)\n" +
                "    REFERENCES article(id)\n" +
                "    ON DELETE NO ACTION\n" +
                "    ON UPDATE NO ACTION\n" +
                "    NOT DEFERRABLE;" +
                "ALTER TABLE article_tag\n" +
                "  ADD CONSTRAINT article_tag_fk1 FOREIGN KEY (idTag)\n" +
                "    REFERENCES tag(id)\n" +
                "    ON DELETE NO ACTION\n" +
                "    ON UPDATE NO ACTION\n" +
                "    NOT DEFERRABLE;";


        try(Connection con = getConexion()){
            con.createQuery(sqlCreateTags).executeUpdate();
        }catch (Exception e) {
            System.out.println("Las Claves Foranes de tag ya estan creadas");
        }

        try(Connection con = getConexion()){
            con.createQuery(sqlCreateUsers).executeUpdate();
        }catch (Exception e){
            System.out.println("Las Claves Foranes de user ya estan creadas");
        }

        try(Connection con = getConexion()){
            con.createQuery(sqlCreateComment).executeUpdate();
        }catch (Exception e) {
            System.out.println("Las Claves Foranes de comment ya estan creadas");
        }
        
        try(Connection con = getConexion()){
            con.createQuery(sqlCreateArticle).executeUpdate();
        }catch (Exception e){
            System.out.println("Las Claves Foranes de article ya estan creadas");
        }

        try(Connection con = getConexion()){
            con.createQuery(sqlCreateArticleComment).executeUpdate();
        }catch (Exception e){
            System.out.println("Las Claves Foranes de article_comment ya estan creadas");
        }

        try(Connection con = getConexion()){
            con.createQuery(sqlCreateArticleTag).executeUpdate();
        }catch (Exception e){
            System.out.println("Las Claves Foranes de article_tag ya estan creadas");
        }

    }

}
