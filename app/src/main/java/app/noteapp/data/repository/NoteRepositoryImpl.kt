package app.noteapp.data.repository

import app.noteapp.data.dao.NoteDao
import app.noteapp.domain.model.Note
import app.noteapp.domain.repository.NoteRepository


/**
 * Repository module for handling data operations.
 *
 * Collecting from the Flows in [NoteDao] is main-safe.  Room supports Coroutines and moves the
 * query execution off of the main thread.
 */
class NoteRepositoryImpl (private val noteDao: NoteDao): NoteRepository {

    override fun getNotes() = noteDao.getAllNotes()

    override suspend fun getNote(noteId: Int) = noteDao.getNoteById(noteId)

    override suspend fun deleteNote(noteId: Int) = noteDao.deleteNote(noteId)

    override suspend fun insertNote(note: Note) = noteDao.insertNote(note)

    override suspend fun updateNote(note: Note) = noteDao.updateNote(note)

}