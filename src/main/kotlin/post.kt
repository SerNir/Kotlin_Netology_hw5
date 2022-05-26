data class Post(
    var id: Int,                        //Идентификатор записи.
    val ownerId: Int,                   //Идентификатор владельца стены, на которой размещена запись.
    val fromId: Int,                    //Идентификатор автора записи
    val date: Int,                      //Время публикации записи
    val text: String,                   //Текст записи.
    val comments: String,               //Информация о комментариях к записи, объект с полями:
    val copyright: String,              //Источник материала, объект с полями:
    val likes: Long,                    //Информация о лайках к записи, объект с полями:
    val reposts: Int,                   //Информация о репостах записи («Рассказать друзьям»), объект с полями:
    val views: Long,                    //Информация о просмотрах записи.
    val postType: String,               //Тип записи, может принимать следующие значения: post, copy, reply, postpone, suggest.
    val createdBy: Int = 0,             //Идентификатор администратора, который опубликовал запись (возвращается только для сообществ при запросе с ключом доступа администратора)
    val replyOwnerId: Int = 0,          //Идентификатор владельца записи, в ответ на которую была оставлена текущая.
    val replyPostId: Int = 0,           //Идентификатор записи, в ответ на которую была оставлена текущая.
    val friendsOnly: Boolean = false,   //если запись была создана с опцией «Только для друзей».
    val signedId: Int = 0,              //Идентификатор автора, если запись была опубликована от имени сообщества и подписана пользователем
    val canPin: Boolean = false,        //Информация о том, может ли текущий пользователь закрепить запись
    val canDelete: Boolean = false,     //Информация о том, может ли текущий пользователь удалить запись
    val isPinned: Boolean = false,      //Информация о том, что запись закреплена.
    val markAsAds: Boolean = false,     //Информация о том, содержит ли запись отметку "реклама"
    val isFavorite: Boolean = false,    //true, если объект добавлен в закладки у текущего пользователя.
    val donuts: Boolean = false,        //Информация о записи VK Donut:
    val postponed_id: Int = 0           //Идентификатор отложенной записи. Это поле возвращается тогда, когда запись стояла на таймере.
)

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
                    comments = "",
                    copyright = "",
                    likes = 123,
                    reposts = 12,
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