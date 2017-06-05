package database

/**
 * Created by Jaime Viñas on 6/4/2017.
 */
import java.sql.*
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by vacax on 27/05/16.
 */
class H2Connection(
        val URL: String,
        val username: String = "sa",
        val password: String = "") {

    fun Connection.open() {
    }

    fun getConnection(): Connection {
        return DriverManager.getConnection(URL, username, password)
    }

    init {
        try {
            Class.forName("org.h2.Driver")
        } catch (ex: ClassNotFoundException) {
            println("I'm supposed to log something here")
        }

        initDatabase()
    }


    fun initDatabase() {
        val conn = getConnection()

        val sql = "CREATE TABLE IF NOT EXISTS ESTUDIANTE\n" +
                "(\n" +
                "  MATRICULA INTEGER PRIMARY KEY NOT NULL,\n" +
                "  NOMBRE VARCHAR(100) NOT NULL,\n" +
                "  APELLIDO VARCHAR(100) NOT NULL,\n" +
                "  TELEFONO VARCHAR(25) NOT NULL,\n" +
                "  CARRERA VARCHAR(50) NOT NULL\n" +
                ");"
        val statement: Statement = conn.createStatement()
        statement.execute(sql)
        statement.close()

        conn.close()
    }

    fun testConexion() {
        try {
            DriverManager.getConnection(URL, "sa", "").close()
            println("Connection Success")
        } catch (ex: SQLException) {
            println(ex.message)
        }
    }


}