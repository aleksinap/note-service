package ru.netology.data

data class Note(
    //    идентификатор заметки
    val id: Int,
    //    идентификатор владельца заметки
    val ownerId: Int,
    //    заголовок заметки
    val title: String,
    //    текст заметки
    val text: String,
    //    дата создания заметки в формате Unixtime
    val date: Int,
    //    количество комментариев
    var comments: Int,
    //    количество прочитанных комментариев (только при запросе информации о заметке текущего пользователя)
    val readComments: Int,
    //    URL страницы для отображения заметки
    val viewUrl: String
)
