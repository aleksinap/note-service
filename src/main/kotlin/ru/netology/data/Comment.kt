package ru.netology.data

data class Comment(
    //    идентификатор комментария
    val cid: Int,
    //    идентификатор автора комментария
    val uid: Int,
    //    идентификатор заметки
    override val id: Int,
    //    идентификатор владельца заметки
    override val ownerId: Int,
    //    дата добавления комментария в формате unixtime
    val cdate: Int,
    //    текст комментария
    val message: String,
    //    идентификатор пользователя, в ответ на комментарий которого был оставлен
    //    текущий комментарий (если доступно)
    val replyTo: Int?,
    override val title: String,
    override val text: String,
    override val date: Int,
    override val comments: Int,
    override val readComments: Int,
    override val viewUrl: String,

    ) : Note(id, ownerId, title, text, date, comments, readComments, viewUrl)
