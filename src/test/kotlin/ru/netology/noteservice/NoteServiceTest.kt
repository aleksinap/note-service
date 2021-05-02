package ru.netology.noteservice

import org.junit.Test

import org.junit.Assert.*
import ru.netology.data.Comment
import kotlin.reflect.KClass

class NoteServiceTest {

    @Test
    fun addingToEmptyList() {
        val noteService = NoteService()
        val lastNote = noteService.add("Title", "Text")

        assertTrue(lastNote > 0)
    }

    @Test
    fun addingToNotEmptyList() {
        val noteService = NoteService()
        noteService.add("Title", "Text")
        val lastNote = noteService.add("Title", "Text")

        assertTrue(lastNote > 0)
    }


    @Test
    fun createComment() {
        val noteService = NoteService()
        noteService.add("Title", "Text")
        val lastComment = noteService.createComment(1,1,null,"message", "Text")
        assertTrue(lastComment > 0)
    }

    @Test
    fun createCommentToNotEmptyList() {
        val noteService = NoteService()
        noteService.add("Title", "Text")
        noteService.createComment(1,1,null,"message", "Text")
        val lastComment = noteService.createComment(1,1,null,"message", "Text")
        assertTrue(lastComment > 0)
    }

    @Test(expected = NoteNotFoundException::class)
    fun createCommentNoteNotFoundException() {
        val noteService = NoteService()
        noteService.add("Title", "Text")
        noteService.createComment(2,1,null,"message", "Text")
    }

    @Test(expected = UserNotFoundException::class)
    fun createCommentUserNotFoundException() {
        val noteService = NoteService()
        noteService.add("Title", "Text")
        noteService.createComment(1,2,null,"message", "Text")
    }

    @Test(expected = AddingCommentException::class)
    fun createCommentAddingCommentException() {
        val noteService = NoteService()
        noteService.createComment(1,2,null,"message", "Text")
    }

    @Test
    fun getComments() {
        val noteService = NoteService()
        noteService.add("Title", "Text")
        noteService.createComment(1,1,null,"message", "Text")
        assertTrue(noteService.getComments(1,1).isNotEmpty())
    }

    @Test(expected = NoCommentsException::class)
    fun getCommentsShouldThrowNoCommentsException() {
        val noteService = NoteService()
        noteService.add("Title", "Text")
        noteService.getComments(1,1)
    }

    @Test(expected = NoteNotFoundException::class)
    fun getCommentsShouldThrowNoteNotFoundException() {
        val noteService = NoteService()
        noteService.add("Title", "Text")
        noteService.getComments(2,1)
    }

    @Test(expected = UserNotFoundException::class)
    fun getCommentsShouldThrowUserNotFoundException() {
        val noteService = NoteService()
        noteService.add("Title", "Text")
        noteService.getComments(1,5)
    }
    @Test(expected = GettingCommentsException::class)
    fun getCommentsShouldThrowGettingCommentsException() {
        val noteService = NoteService()
        noteService.getComments(1,5)
    }
}