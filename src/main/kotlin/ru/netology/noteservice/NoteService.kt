package ru.netology.noteservice

import ru.netology.data.Comment
import ru.netology.data.GenericPair
import ru.netology.data.Note
import java.lang.RuntimeException

class NoteNotFoundException(message: String) : RuntimeException(message)
class UserNotFoundException(message: String) : RuntimeException(message)
class AddingCommentException(message: String) : RuntimeException(message)
class GettingCommentsException(message: String) : RuntimeException(message)
class NoCommentsException(message: String) : RuntimeException(message)

class NoteService {
    private var pairs: MutableList<GenericPair<Note,MutableList<Comment>>> =
        emptyList<GenericPair<Note,MutableList<Comment>>>().toMutableList()

    //  Создает новую заметку у текущего пользователя.
    fun add(
        title: String,
        text: String,
        privacyView: List<String> = listOf("all"),
        privacyComment: List<String> = listOf("all"),
    ) : Int {
        if(pairs.isEmpty()){
            pairs.add(GenericPair(Note(1,1, title, text, 123456,0,0,"https://netology.ru"), emptyList<Comment>().toMutableList()))
        } else {
            pairs.add(GenericPair(Note(pairs.last().first.id + 1,1, title, text, 123456,0,0,"https://netology.ru"), emptyList<Comment>().toMutableList()))
        }
        return pairs.last().first.id
    }

    //  Добавляет новый комментарий к заметке.
    fun createComment(
        noteId: Int,
        ownerId: Int,
        replyTo: Int?,
        message: String,
        guid: String,
    ) : Int{
        if(pairs.isNotEmpty()){
            for(pair in pairs){
                if(pair.first.id == noteId && pair.first.ownerId == ownerId){
                    if(pair.second.isEmpty()){
                        pair.second.add(Comment(1,1,noteId,ownerId,123467,message, replyTo))
                    } else {
                        pair.second.add(Comment(pair.second.last().cid + 1,1,noteId,ownerId,123467,message, replyTo))
                    }
                    pair.first = pair.first.copy(comments = pair.first.comments + 1)
                    return pair.second.last().cid
                }

            }
            for(pair in pairs){
                if(pair.first.id != noteId){
                    throw NoteNotFoundException("Заметка с таким id не существует")
                } else {
                    if(pair.first.ownerId != ownerId) {
                        throw UserNotFoundException("Пользователя с таким id не существует")
                    }
                }
            }
        }
        throw AddingCommentException("Попытка добавить комметарий к несуществующей заметке")
        return pairs.last().second.last().cid
    }

    //    //  Удаляет заметку текущего пользователя.
//    fun delete(noteId: Int) : Boolean {
//        TODO("Создать функцию удаления заметки.")
//        return true
//    }
//
//    //    Удаляет комментарий к заметке.
//    fun deleteComment(commentId: Int, ownerId: Int) : Boolean {
//        TODO("Создать функцию удаления комментария к заметке.")
//        return true
//    }
//
//    //  Редактирует заметку текущего пользователя.
//    fun edit(
//        noteId: Int,
//        title: String,
//        text: String,
//        privacyView: List<String> = listOf("all"),
//        privacyComment: List<String> = listOf("all"),
//    ) : Boolean {
//        TODO("Создать функцию редактирования заметки.")
//        return true
//    }
//
//    //  Редактирует указанный комментарий у заметки.
//    fun editComment(commentId: Int, ownerId: Int, message: String) : Boolean {
//        TODO("Создать функцию редактирования указанного комментария к заметке.")
//        return true
//    }
//
//    //  Возвращает список заметок, созданных пользователем.
//    fun get(noteIds: List<Int>, userId: Int, offset: Int = 0, count: Int = 20, sort: Int = 0): MutableList<GenericPair<Note, MutableList<Comment>?>> {
//        TODO("Создать функцию возвращения списка заметок, созданных пользователем.")
//        return pairs
//    }
//
//    //  Возвращает заметку по её id.
//    fun getById(noteId: Int, ownerId: Int, needWiki: Boolean = false): MutableList<GenericPair<Note, MutableList<Comment>?>> {
//        TODO("Создать функцию возвращения заметки по её id.")
//        return pairs
//    }
//
//    //  Возвращает список комментариев к заметке.
    fun getComments(noteId: Int, ownerId: Int, sort: Int = 0, offset: Int = 0, count: Int = 20): MutableList<Comment> {
        var x: MutableList<Comment>
        if(pairs.isNotEmpty()){
            for(pair in pairs){
                if(pair.first.id == noteId && pair.first.ownerId == ownerId){
                    if(pair.second.isEmpty()){
                        throw NoCommentsException("У заметки с таким id нет комметариев")
                    } else {
                        x = pair.second
                    }
                    return x
                }
            }

            for(pair in pairs){
                if(pair.first.id != noteId){
                    throw NoteNotFoundException("Заметка с таким id не существует")
                } else {
                    if(pair.first.ownerId != ownerId) {
                        throw UserNotFoundException("Пользователя с таким id не существует")
                    }
                }
            }
        }
        throw GettingCommentsException("Попытка получить список комметариев к несуществующей заметке")
        return x
    }
//
//    //  Восстанавливает удалённый комментарий.
//    fun restoreComment(commentId: Int, ownerId: Int): Boolean {
//        TODO("Создать функцию восстановления удалённого комментария к заметке.")
//        return true
//    }
}