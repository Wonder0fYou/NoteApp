package com.example.cftapp.data.repository

import com.example.cftapp.data.database.dao.NoteDao
import com.example.cftapp.data.database.entity.toNoteItem
import com.example.cftapp.domain.entity.NoteItem
import com.example.cftapp.domain.entity.toEntity
import com.example.cftapp.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
): NoteRepository {

    override fun getNotes(): Flow<List<NoteItem>> {
        return noteDao.getAllNotes().map { list ->
            list.map { it.toNoteItem() }
        }
    }

    override suspend fun getNote(noteId: Int): NoteItem {
        return noteDao.getNoteById(noteId).toNoteItem()
    }

    override suspend fun deleteNote(noteId: Int) {
        noteDao.deleteNote(noteId)
    }

    override suspend fun insertNote(note: NoteItem) {
        noteDao.insertNote(note.toEntity())
    }

    override suspend fun updateNote(note: NoteItem) {
        noteDao.updateNote(note.toEntity())
    }
}