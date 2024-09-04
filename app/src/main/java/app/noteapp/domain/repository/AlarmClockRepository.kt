package app.noteapp.domain.repository

import app.noteapp.domain.model.AlarmClock
import kotlinx.coroutines.flow.Flow

interface AlarmClockRepository {

    fun getAllAlarms(): Flow<List<AlarmClock>>

    suspend fun getAlarm(alarmId: Int): AlarmClock

    suspend fun deleteAlarm(alarmId: Int)

    suspend fun insertAlarm(alarmClock: AlarmClock)

    suspend fun updateAlarm(alarmClock: AlarmClock)
}