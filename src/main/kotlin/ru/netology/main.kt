package ru.netology

import ru.netology.noteservice.NoteService

fun main() {
    val noteService = NoteService()
    noteService.add("Title", "Text")
    noteService.add("Title", "Text")
    noteService.add("Title", "Text")
    noteService.createComment(1,1,null,"message", "guid")
    noteService.createComment(1,1,null,"message", "guida")
//    noteService.createComment(1,1,null,"message", "guids")
    noteService.createComment(2,1,null,"message", "guids")

//    println(noteService.delete(2))
//    println(noteService.getComments(1,1))
    println(noteService.getComments(1,1))
    println(noteService.getComments(2,1))
    println(noteService.deleteComment(1,1))
//    println(noteService.getComments(1,1))
//    println(noteService.get(listOf(1,3), 1))
//    println(noteService.getById(1, 1))
//    noteService.edit(1, "New title", "New text")
//    println(noteService.get(listOf(1,2,3), 1))
//    noteService.editComment(1,1,"New message")
    println(noteService.getComments(1,1))
    println(noteService.getComments(2,1))
    println(noteService.restoreComment(1,1))
    println(noteService.getComments(1,1))
    println(noteService.getComments(2,1))
}