package ru.netology.data

open class Note(
    //    идентификатор заметки
    open val id: Int,
    //    идентификатор владельца заметки
    open val ownerId: Int,
    //    заголовок заметки
    open val title: String,
    //    текст заметки
    open val text: String,
    //    дата создания заметки в формате Unixtime
    open val date: Int,
    //    количество комментариев
    open val comments: Int,
    //    количество прочитанных комментариев (только при запросе информации о заметке текущего пользователя)
    open val readComments: Int,
    //    URL страницы для отображения заметки
    open val viewUrl: String
)
