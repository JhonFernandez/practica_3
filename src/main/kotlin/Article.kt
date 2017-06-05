import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Article(
        val id: Int,
        val title: String,
        val author: User,
        val body: String = "",
        val tags: ArrayList<Tag> = ArrayList(),
        val releaseDate: Date = Calendar.getInstance().time) {
    val comments get() = Comment.objects.filter { it.article == this }

    init {
        objects.add(this)
    }

    companion object {
        val objects: ArrayList<Article> = ArrayList()
    }

    class Comment(val id: Int,
                  val article: Article,
                  val body: String,
                  val author: User) {
        init {
            objects.add(this)
        }

        companion object {
            val objects: ArrayList<Comment> = ArrayList()
        }
    }

}
