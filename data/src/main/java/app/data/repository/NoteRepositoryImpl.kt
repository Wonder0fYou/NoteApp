package app.data.repository

import app.data.AppMapper
import app.data.database.dao.NoteDao
import app.domain.entity.NoteItem
import app.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao,
    private val appMapper: AppMapper
): NoteRepository {

    override fun getNotes(): Flow<List<NoteItem>> {
        return noteDao.getAllNotes().map { list ->
            list.map { appMapper.mapToNoteDomain(it) }
        }
    }

    override suspend fun getNote(noteId: Int): NoteItem {
        return appMapper.mapToNoteDomain(noteDao.getNoteById(noteId))
    }

    override suspend fun deleteNote(noteId: Int) {
        try {
            noteDao.deleteNote(noteId)
        } catch (_: Exception) {
            getNotes()
        }
    }

    override suspend fun insertNote(note: NoteItem) {
        try {
            val noteEntity = appMapper.mapToNoteEntity(note)
            noteDao.insertNote(noteEntity)
        } catch (_: Exception) {
            getNotes()
        }
    }

    override suspend fun updateNote(note: NoteItem) {
        try {
            val noteEntity = appMapper.mapToNoteEntity(note)
            noteDao.updateNote(noteEntity)
        } catch (_: Exception) {
            getNotes()
        }
    }
}