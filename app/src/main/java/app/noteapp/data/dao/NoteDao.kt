package app.noteapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import app.noteapp.domain.model.Note
import kotlinx.coroutines.flow.Flow

/**
 * The Data Access Object for the Note class.
 */
@Dao
interface NoteDao {

    @Query("SELECT * FROM notes WHERE id = :noteId")
    suspend fun getNoteById (noteId: Int): Note

    @Query("SELECT * FROM notes ORDER BY title")
    fun getAllNotes(): Flow<List<Note>>

    @Query("DELETE FROM notes WHERE id = :noteId")
    suspend fun deleteNote(noteId: Int)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)
}