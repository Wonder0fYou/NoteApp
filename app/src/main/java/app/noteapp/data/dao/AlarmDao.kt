package app.noteapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import app.noteapp.domain.model.AlarmClock
import kotlinx.coroutines.flow.Flow

/**
 * The Data Access Object for the AlarmClock class.
 */
@Dao
interface AlarmDao {

    @Query("SELECT * FROM alarms ORDER BY description")
    fun getAllAlarms(): Flow<List<AlarmClock>>

    @Query("SELECT * FROM alarms WHERE id = :alarmId")
    suspend fun getAlarmById (alarmId: Int): AlarmClock

    @Query("DELETE FROM alarms WHERE id = :alarmId")
    suspend fun deleteAlarm (alarmId: Int)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAlarm (alarmClock: AlarmClock)

    @Update
    suspend fun updateAlarm (alarmClock: AlarmClock)
}