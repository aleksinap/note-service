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
class GettingNotesException(message: String) : RuntimeException(message)

class NoteService {
    private var pairs = mutableListOf<GenericPair<Note,MutableList<Comment>>>()
    private var guids = mutableListOf<String>()
    private var deletedPairs = mutableListOf<GenericPair<Note,MutableList<Comment>>>()
    private var deletedComments = mutableListOf<Comment>()

    //  Создает новую заметку у текущего пользователя.
    fun add(
        title: String,
        text: String,
        privacyView: List<String> = listOf("all"),
        privacyComment: List<String> = listOf("all"),
    ) : Int {
        if(pairs.isEmpty()){
            pairs.add(GenericPair(Note(1,1, title, text, 123456,0,0,"https://netology.ru"), mutableListOf()))
        } else {
            pairs.add(GenericPair(Note(pairs.last().first.id + 1,1, title, text, 123456,0,0,"https://netology.ru"), mutableListOf()))
        }
        return pairs.last().first.id
    }

    //  Добавляет новый комментарий к заметке.
    fun createComment(
        noteId: Int,
        ownerId: Int,
        replyTo: Int?,
        message: String,
        guid: String
    ) : Int{
        if(pairs.isNotEmpty()){
            for(pair in pairs){
                if(pair.first.id == noteId && pair.first.ownerId == ownerId){
                    if(pair.second.isEmpty()){
                        pair.second.add(Comment(1,1,noteId,ownerId,123467,message, replyTo))
                    } else {
                        for(g in guids){
                            if(g == guid) throw AddingCommentException("Такой комметарий уже существует")
                        }
                        pair.second.add(Comment(pair.second.last().cid + 1,1,noteId,ownerId,123467,message, replyTo))
                    }
                    pair.first = pair.first.copy(comments = pair.first.comments + 1)
                    guids.add(guid)
                    return pair.second.last().cid
                }
            }
            throwUserNotFoundException(ownerId)
            throwNoteNotFoundException(noteId)
        }
        throw AddingCommentException("Попытка добавить комметарий к несуществующей заметке")
        return pairs.last().second.last().cid
    }

    //  Удаляет заметку текущего пользователя.
    fun delete(noteId: Int) : Boolean {
        for(pair in pairs){
            if(pair.first.id == noteId){
                deletedPairs.add(pair)
                pairs.remove(pair)
                return true
            }
        }
        return false
    }

    //    Удаляет комментарий к заметке.
    fun deleteComment(commentId: Int, ownerId: Int) : Boolean {
        for(i in pairs.indices){
            for(j in pairs[i].second.indices) {
                if(pairs[i].second[j].ownerId == ownerId && pairs[i].second[j].cid == commentId){
                    deletedComments.add(pairs[i].second[j])
                    pairs[i].second.remove(pairs[i].second[j])
                    return true
                }
            }
        }
        throwUserNotFoundException(ownerId)
        throwNoCommentsException(commentId)
        return false
    }

    //  Редактирует заметку текущего пользователя.
    fun edit(
        noteId: Int,
        title: String,
        text: String,
        privacyView: List<String> = listOf("all"),
        privacyComment: List<String> = listOf("all"),
    ) : Boolean {
        if(pairs.isNotEmpty()){
            for(pair in pairs){
                if(pair.first.id == noteId){
                    pair.first = pair.first.copy(title = title, text = text)
                    return true
                }
            }
            throwNoteNotFoundException(noteId)
        }
        throwNoteNotFoundException(noteId)
        return false
    }

    //  Редактирует указанный комментарий у заметки.
    fun editComment(commentId: Int, ownerId: Int, message: String) : Boolean {
        for(i in pairs.indices){
            for(j in pairs[i].second.indices) {
                if(pairs[i].second[j].ownerId == ownerId && pairs[i].second[j].cid == commentId){
                    pairs[i].second[j] = pairs[i].second[j].copy(message = message)
                    return true
                }
            }
        }
        throwUserNotFoundException(ownerId)
        throwNoCommentsException(commentId)
        return false
    }

    //  Возвращает список заметок, созданных пользователем.
    fun get(noteIds: List<Int>, userId: Int, offset: Int = 0, count: Int = 20, sort: Int = 0): MutableList<Note> {
        var list = mutableListOf<Note>()

        if(pairs.isNotEmpty()){
            for(i in noteIds.indices){
                for(j in pairs.indices) {
                    if(pairs[j].first.ownerId == userId && pairs[j].first.id == noteIds[i]) {
                        list.add(pairs[j].first)
                        break
                    }
                }
            }
            throwUserNotFoundException(userId)
            noteIds.forEach { throwNoteNotFoundException(it) }
            return list
        }
        throw GettingNotesException("Список заметок пуст!")
        return list
    }

    //  Возвращает заметку по её id.
    fun getById(noteId: Int, ownerId: Int, needWiki: Boolean = false): MutableList<Note> {
        return get(listOf(noteId), ownerId)
    }

    //  Возвращает список комментариев к заметке.
    fun getComments(
        noteId: Int,
        ownerId: Int,
        sort: Int = 0,
        offset: Int = 0,
        count: Int = 20
    ): MutableList<Comment> {
        var x: MutableList<Comment>
        if (pairs.isNotEmpty()) {
            for (pair in pairs) {
                if (pair.first.id == noteId && pair.first.ownerId == ownerId) {
                    if (pair.second.isEmpty()) {
                        throw NoCommentsException("У заметки с таким id нет комметариев")
                    } else {
                        x = pair.second
                    }
                    return x
                }
            }
            throwUserNotFoundException(ownerId)
            throwNoteNotFoundException(noteId)
        }
        throw GettingCommentsException("Попытка получить список комметариев к несуществующей заметке")
        return x
    }

    private fun throwNoteNotFoundException(noteId: Int) {
        var isIdEqual = false
        for (pair in pairs) {
            if (pair.first.id == noteId) isIdEqual = true
        }
        if (!isIdEqual) throw NoteNotFoundException("Заметка с таким id не существует")
    }

    private fun throwUserNotFoundException(ownerId: Int) {
        var isOwnerIdEqual = false
        for (pair in pairs) {
            if (pair.first.ownerId == ownerId) isOwnerIdEqual = true
        }
        if (!isOwnerIdEqual) throw UserNotFoundException("Пользователя с таким id не существует")
    }

    private fun throwNoCommentsException(commentId: Int) {
        var isCommentIdEqual = false
        for(i in pairs.indices){
            for(j in pairs[i].second.indices) {
                if(pairs[i].second[j].cid == commentId) isCommentIdEqual = true
            }
        }
        if (!isCommentIdEqual) throw NoCommentsException("Комментария с таким id не существует")
    }

    //  Восстанавливает удалённый комментарий.
    fun restoreComment(commentId: Int, ownerId: Int): Boolean {
        for(i in deletedComments.indices) {
            if(deletedComments[i].ownerId == ownerId && deletedComments[i].cid == commentId){
                for(j in pairs.indices){
                    if(pairs[i].first.ownerId == ownerId){
                        pairs[i].second.add(deletedComments[i])
                        deletedComments.remove(pairs[i].second[j])
                        return true
                    }
                }
            }
        }
        throwUserNotFoundException(ownerId)
        throwNoCommentsException(commentId)
        return false
    }
}