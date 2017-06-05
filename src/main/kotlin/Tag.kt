/**
 * Created by Jaime Viñas on 6/4/2017.
 */
data class Tag(
        val id: Int,
        val name: String) {
    val usedBy get() = Article.objects.filter { it.tags.find { it == this } != null }

    init {
        objects.add(this)
    }

    companion object {
        val objects: ArrayList<Tag> = ArrayList()
    }
}

