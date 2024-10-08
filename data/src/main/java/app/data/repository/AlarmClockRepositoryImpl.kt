package app.data.repository

import app.data.AppMapper
import app.data.database.dao.AlarmDao
import app.domain.entity.AlarmClockItem
import app.domain.repository.AlarmClockRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AlarmClockRepositoryImpl @Inject constructor(
    private val alarmDao: AlarmDao,
    private val appMapper: AppMapper
): AlarmClockRepository {

    override fun getAllAlarms() = alarmDao.getAllAlarms().map { list ->
        list.map { appMapper.mapToAlarmDomain(it) }
    }

    override suspend fun getAlarm(alarmId: Int) = appMapper.mapToAlarmDomain(alarmDao.getAlarmById(alarmId))

    override suspend fun deleteAlarm(alarmId: Int) = alarmDao.deleteAlarm(alarmId)

    override suspend fun insertAlarm(alarmClock: AlarmClockItem) {
        val alarmEntity = appMapper.mapToAlarmEntity(alarmClock)
        alarmDao.insertAlarm(alarmEntity)
    }

    override suspend fun updateAlarm(alarmClock: AlarmClockItem) {
        val alarmEntity = appMapper.mapToAlarmEntity(alarmClock)
        alarmDao.updateAlarm(alarmEntity)
    }
}