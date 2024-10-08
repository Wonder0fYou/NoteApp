package app.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import app.data.database.entity.AlarmClockItemEntity
import kotlinx.coroutines.flow.Flow

/**
 * The Data Access Object for the AlarmClock class.
 */
@Dao
interface AlarmDao {

    @Query("SELECT * FROM alarms ORDER BY description")
    fun getAllAlarms(): Flow<List<AlarmClockItemEntity>>

    @Query("SELECT * FROM alarms WHERE id = :alarmId")
    suspend fun getAlarmById (alarmId: Int): AlarmClockItemEntity

    @Query("DELETE FROM alarms WHERE id = :alarmId")
    suspend fun deleteAlarm (alarmId: Int)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAlarm (alarmClock: AlarmClockItemEntity)

    @Update
    suspend fun updateAlarm (alarmClock: AlarmClockItemEntity)
}