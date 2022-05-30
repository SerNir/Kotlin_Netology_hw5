data class Post(
    var id: Int,                        //Идентификатор записи.
    val ownerId: Int,                   //Идентификатор владельца стены, на которой размещена запись.
    val fromId: Int,                    //Идентификатор автора записи
    val date: Int,                      //Время публикации записи
    val text: String,                   //Текст записи.
    val comments: Comments,             //Информация о комментариях к записи, объект с полями:
    val copyright: String,             //Источник материала, объект с полями:
    val likes: Long,                    //Информация о лайках к записи, объект с полями:
    val reposts: Repost,                   //Информация о репостах записи («Рассказать друзьям»), объект с полями:
    val views: Long,                    //Информация о просмотрах записи.
    val postType: String,               //Тип записи, может принимать следующие значения: post, copy, reply, postpone, suggest.
    val createdBy: Int = 0                //Идентификатор администратора, который опубликовал запись (возвращается только для сообществ при запросе с ключом доступа администратора)
)

data class Repost(val count: Int = 0, val userReposts: Boolean = false)

data class Comments(val comment: String = "", val count: Int = 0)

object WallService {
    private var id = 0
    private var posts = emptyArray<Post>()

    fun addPost(post: Post): Post {
        id++
        val post = post.copy(id = id)
        posts += post
        return posts.last()
    }

    fun updatePost(post: Post): Boolean {
        var result = false
        for ((index, item) in posts.withIndex()) {
            if (post.id == item.id) {
                posts[index] = post.copy(
                    text = "",
                    copyright = "",
                    likes = 123,
                    views = 1002,
                    postType = "copy"
                )
                result = true
                break
            } else
                result = false
        }
        return result
    }
}