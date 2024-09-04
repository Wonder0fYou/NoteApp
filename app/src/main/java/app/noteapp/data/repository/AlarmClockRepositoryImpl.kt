package app.noteapp.data.repository

import app.noteapp.data.dao.AlarmDao
import app.noteapp.domain.model.AlarmClock
import app.noteapp.domain.repository.AlarmClockRepository

class AlarmClockRepositoryImpl (private val alarmDao: AlarmDao): AlarmClockRepository {

    override fun getAllAlarms() = alarmDao.getAllAlarms()

    override suspend fun getAlarm(alarmId: Int) = alarmDao.getAlarmById(alarmId)

    override suspend fun deleteAlarm(alarmId: Int) = alarmDao.deleteAlarm(alarmId)

    override suspend fun insertAlarm(alarmClock: AlarmClock) = alarmDao.insertAlarm(alarmClock)

    override suspend fun updateAlarm(alarmClock: AlarmClock) = alarmDao.updateAlarm(alarmClock)
}