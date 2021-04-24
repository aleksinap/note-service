package ru.netology.noteservice

import ru.netology.data.Comment
import ru.netology.data.Note

class NoteService {
    private var notes: MutableList<Note> = emptyList<Note>().toMutableList()
    private var comments: MutableList<Comment> = emptyList<Comment>().toMutableList()

    //  Создает новую заметку у текущего пользователя.
    fun add(
        title: String,
        text: String,
        privacyView: List<String> = listOf("all"),
        privacyComment: List<String> = listOf("all"),
    ) : Int {
        val nid: Int = 0
        TODO("Создать функцию добавления заметки.")
        return nid
    }

    //  Добавляет новый комментарий к заметке.
    fun createComment(
        noteId: Int,
        ownerId: Int,
        reply_to: Int?,
        message: String,
        guid: String,
    ) : Int{
        val cid: Int = 0
        TODO("Создать функцию добавления нового комментария к заметке.")
        return cid
    }

    //  Удаляет заметку текущего пользователя.
    fun delete(noteId: Int) : Boolean {
        TODO("Создать функцию удаления заметки.")
        return true
    }

    //    Удаляет комментарий к заметке.
    fun deleteComment(commentId: Int, ownerId: Int) : Boolean {
        TODO("Создать функцию удаления комментария к заметке.")
        return true
    }

    //  Редактирует заметку текущего пользователя.
    fun edit(
        noteId: Int,
        title: String,
        text: String,
        privacyView: List<String> = listOf("all"),
        privacyComment: List<String> = listOf("all"),
    ) : Boolean {
        TODO("Создать функцию редактирования заметки.")
        return true
    }

    //  Редактирует указанный комментарий у заметки.
    fun editComment(commentId: Int, ownerId: Int, message: String) : Boolean {
        TODO("Создать функцию редактирования указанного комментария к заметке.")
        return true
    }

    //  Возвращает список заметок, созданных пользователем.
    fun get(noteIds: List<Int>, userId: Int, offset: Int = 0, count: Int = 20, sort: Int = 0): MutableList<Note> {
        TODO("Создать функцию возвращения списка заметок, созданных пользователем.")
        return notes
    }

    //  Возвращает заметку по её id.
    fun getById(noteId: Int, ownerId: Int, needWiki: Boolean = false): MutableList<Note> {
        TODO("Создать функцию возвращения заметки по её id.")
        return notes
    }

    //  Возвращает список комментариев к заметке.
    fun getComments(noteId: Int, ownerId: Int, sort: Int = 0, offset: Int = 0, count: Int = 20): Array<Comment> {
        var arr: Array<Comment> = comments.toTypedArray()
        TODO("Создать функцию списка комментариев к заметке.")
        return arr
    }

    //  Восстанавливает удалённый комментарий.
    fun restoreComment(commentId: Int, ownerId: Int): Boolean {
        TODO("Создать функцию восстановления удалённого комментария к заметке.")
        return true
    }
}