package ru.netology.noteservice

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

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
        val lastComment = noteService.createComment(1,1,null,"message", "Texts")
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
        noteService.add("Title", "Text")
        noteService.createComment(2,2,null,"message", "Text")
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

    @Test
    fun deleteCommentTrue() {
        val noteService = NoteService()

        noteService.add("Title", "Text")
        noteService.createComment(1,1,null,"message", "guid")

        assertTrue(noteService.deleteComment(1,1))
    }

    @Test
    fun deleteTrue() {
        val noteService = NoteService()

        noteService.add("Title", "Text")

        assertTrue(noteService.delete(1))
    }

    @Test
    fun deleteFalse() {
        val noteService = NoteService()
        noteService.add("Title", "Text")
        assertFalse(noteService.delete(2))
    }

    @Test(expected = NoCommentsException::class)
    fun throwNoCommentsException() {
        val noteService = NoteService()

        noteService.add("Title", "Text")
        noteService.createComment(1,1,null,"message", "guid")

        noteService.deleteComment(6,1)
    }

    @Test
    fun get() {
        val noteService = NoteService()
        noteService.add("Title", "Text")
        noteService.add("Title", "Text")
        noteService.get(listOf(1,2), 1)
    }

    @Test(expected = GettingNotesException::class)
    fun getGettingNotesException() {
        val noteService = NoteService()
        noteService.get(listOf(1,2), 1)
    }
    @Test
    fun getById() {
        val noteService = NoteService()
        noteService.add("Title", "Text")
        noteService.getById(1, 1)
    }

    @Test
    fun edit() {
        val noteService = NoteService()
        noteService.add("Title", "Text")
        noteService.add("Title", "Text")
        noteService.add("Title", "Text")

        assertTrue(noteService.edit(1, "New title", "New text"))
    }

    @Test(expected = NoteNotFoundException::class)
    fun editShouldThrowNoteNotFoundException() {
        val noteService = NoteService()
        noteService.edit(1, "New title", "New text")
    }

    @Test(expected = NoteNotFoundException::class)
    fun editShouldThrowAnotherNoteNotFoundException() {
        val noteService = NoteService()
        noteService.add("Title", "Text")
        noteService.edit(2, "New title", "New text")
    }

    @Test
    fun editComment() {
        val noteService = NoteService()
        noteService.add("Title", "Text")
        noteService.createComment(1,1,null,"message", "guid")
        assertTrue(noteService.editComment(1,1,"New message"))
    }

    @Test(expected = NoCommentsException::class)
    fun editCommentShouldThrowNoCommentsException() {
        val noteService = NoteService()
        noteService.add("Title", "Text")
        noteService.createComment(1,1,null,"message", "guid")
        noteService.editComment(2,1,"New message")
    }

    @Test(expected = UserNotFoundException::class)
    fun editCommentShouldThrowUserNotFoundException() {
        val noteService = NoteService()
        noteService.add("Title", "Text")
        noteService.createComment(1,1,null,"message", "guid")
        noteService.editComment(1,2,"New message")
    }

    @Test
    fun restoreComment() {
        val noteService = NoteService()
        noteService.add("Title", "Text")
        noteService.add("Title", "Text")
        noteService.add("Title", "Text")
        noteService.createComment(1,1,null,"message", "guid")
        noteService.deleteComment(1,1)
        assertTrue(noteService.restoreComment(1,1))
    }
    @Test(expected = NoCommentsException::class)
    fun restoreCommentShouldThrowNoCommentsException() {
        val noteService = NoteService()
        noteService.add("Title", "Text")
        noteService.add("Title", "Text")
        noteService.add("Title", "Text")
        noteService.createComment(1,1,null,"message", "guid")
        noteService.deleteComment(1,1)
        noteService.restoreComment(2,1)
    }

    @Test(expected = UserNotFoundException::class)
    fun restoreCommentShouldThrowUserNotFoundException() {
        val noteService = NoteService()
        noteService.add("Title", "Text")
        noteService.add("Title", "Text")
        noteService.add("Title", "Text")
        noteService.createComment(1,1,null,"message", "guid")
        noteService.deleteComment(1,1)
        noteService.restoreComment(1,2)
    }
}