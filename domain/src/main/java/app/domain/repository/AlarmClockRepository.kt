package app.domain.repository

import app.domain.entity.AlarmClockItem
import kotlinx.coroutines.flow.Flow

interface AlarmClockRepository {

    fun getAllAlarms(): Flow<List<AlarmClockItem>>

    suspend fun getAlarm(alarmId: Int): AlarmClockItem

    suspend fun deleteAlarm(alarmId: Int)

    suspend fun insertAlarm(alarmClock: AlarmClockItem)

    suspend fun updateAlarm(alarmClock: AlarmClockItem)
}