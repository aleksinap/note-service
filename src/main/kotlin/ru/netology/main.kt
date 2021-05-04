package ru.netology

import ru.netology.noteservice.NoteService

fun main() {
    val noteService = NoteService()
    noteService.add("Title", "Text")
    noteService.add("Title", "Text")
    noteService.add("Title", "Text")
    noteService.createComment(1,1,null,"message", "Text")
    noteService.createComment(1,1,null,"message", "Text")
    noteService.createComment(1,1,null,"message", "Text")
    noteService.createComment(1,1,null,"message", "Text")
    println(noteService.getComments(1,1))
    println(noteService.getComments(2,1))

}