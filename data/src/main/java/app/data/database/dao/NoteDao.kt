package app.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import app.data.database.entity.NoteItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes WHERE id = :noteId")
    suspend fun getNoteById (noteId: Int): NoteItemEntity

    @Query("SELECT * FROM notes ORDER BY title")
    fun getAllNotes(): Flow<List<NoteItemEntity>>

    @Query("DELETE FROM notes WHERE id = :noteId")
    suspend fun deleteNote(noteId: Int)

    @Insert
    suspend fun insertNote(note: NoteItemEntity)

    @Update
    suspend fun updateNote(note: NoteItemEntity)
}