package app.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.entity.AlarmClockItem
import app.domain.entity.DayOfWeek
import app.domain.repository.AlarmClockRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AlarmViewModel @Inject constructor(
    private val alarmClockRepository: AlarmClockRepository
): ViewModel() {

    private val _alarms = MutableStateFlow<List<AlarmClockItem>>(arrayListOf())
    val alarms: StateFlow<List<AlarmClockItem>> = _alarms.asStateFlow()
    private val _alarm = MutableStateFlow<AlarmClockItem?>(null)
    val alarm: StateFlow<AlarmClockItem?> = _alarm

    private val _userChoice = MutableStateFlow(1)
    val userChoice: StateFlow<Int> = _userChoice.asStateFlow()

    private val _listOfWeek = MutableStateFlow(setOf(DayOfWeek.MONDAY))
    val listOfWeek: StateFlow<Set<DayOfWeek>> = _listOfWeek.asStateFlow()

    init {
        loadAlarms()
    }

    private fun loadAlarms(){
        viewModelScope.launch {
            alarmClockRepository.getAllAlarms().collect{ alarmList: List<AlarmClockItem> ->
                _alarms.value = alarmList
            }
        }
    }

    fun addAlarm(alarmClock: AlarmClockItem) {
        viewModelScope.launch {
            alarmClockRepository.insertAlarm(alarmClock)
        }
    }

    fun deleteAlarm(alarmId: Int) {
        viewModelScope.launch {
            alarmClockRepository.deleteAlarm(alarmId)
        }
    }

    fun getAlarm(alarmId: Int) {
        viewModelScope.launch {
            val fetchedAlarm = alarmClockRepository.getAlarm(alarmId)
            _alarm.value = fetchedAlarm
        }
    }

    fun updateAlarm(alarmClock: AlarmClockItem) {
        viewModelScope.launch {
            alarmClockRepository.updateAlarm(alarmClock)
        }
    }

    fun plusDayOfWeek(dayOfWeek: DayOfWeek) {
        _listOfWeek.value = _listOfWeek.value.plus(dayOfWeek)
    }

    fun minusDayOfWeek(dayOfWeek: DayOfWeek) {
        _listOfWeek.value = _listOfWeek.value.minus(dayOfWeek)
    }
}