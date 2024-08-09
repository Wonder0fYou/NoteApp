package app.noteapp.domain.repository

import app.noteapp.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun getNotes(): Flow<List<Note>>

    suspend fun getNote(noteId: Int): Note

    suspend fun deleteNote(noteId: Int)

    suspend fun insertNote(note: Note)

    suspend fun updateNote(note: Note)
}