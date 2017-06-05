/**
 * Created by Jaime Viñas on 6/4/2017.
 */

data class User( val id: Int,
        val userName: String,
        val name: String,
        val password: String,
        val isAdmin: Boolean,
        val isAuthor: Boolean
) {
    val articles get() = Article.objects.filter { this == it.author }

}

