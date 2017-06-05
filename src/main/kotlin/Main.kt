import database.H2Connection
import javax.xml.stream.events.Comment

/**
 * Created by Jaime Viñas on 6/4/2017.
 */

fun main(args: Array<String>) {
    testConnection()
}

fun test() {
    val u1 = User(1, "jaimevp54", "Jaime Viñas", "jaimevp54", true, true)
    val u2 = User(2, "jvinas", "Jaime Viñas", "jvinas", false, true)
    val u3 = User(3, "nini", "Indhira Torres", "nini", false, false)

    val t1 = Tag(1, "music")
    val t2 = Tag(2, "movies")
    val t3 = Tag(3, "games")

    val artc1 = Article(1, "Article 1", u1, "This is my body", arrayListOf(t1, t2))
    val artc2 = Article(2, "Article 2", u2, "This is my body", arrayListOf(t2, t3))
    val artc3 = Article(3, "Article 3", u2, "This is my body", arrayListOf(t3))

    val c1 = Article.Comment(1, artc2, "First Comment by u1", u1)
    val c2 = Article.Comment(2, artc2, "Second Comment by u1", u1)
    val c3 = Article.Comment(3, artc2, "First Comment by u3", u3)

    println(artc1.toString())
    println(artc2.toString())
    println(artc3.toString())
    println(t1.toString())
    println(t2.toString())
    println(t3.toString())

    println(t1.usedBy)
    println(t2.usedBy)
    println(t3.usedBy)

    println(artc1.comments)
    println(artc2.comments)
    println(artc3.comments)

}

fun testConnection() {
    val conn = H2Connection("jdbc:h2:~/h2db/test")
    conn.testConexion()


}