package ru.netology.data

data class Comment(
    //    идентификатор комментария
    val cid: Int,
    //    идентификатор автора комментария
    val uid: Int,
    //    идентификатор заметки
    val id: Int,
    //    идентификатор владельца заметки
    val ownerId: Int,
    //    дата добавления комментария в формате unixtime
    val cdate: Int,
    //    текст комментария
    val message: String,
    //    идентификатор пользователя, в ответ на комментарий которого был оставлен
    //    текущий комментарий (если доступно)
    val replyTo: Int?
 )
