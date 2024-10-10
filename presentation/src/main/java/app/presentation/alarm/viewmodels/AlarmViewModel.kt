package app.presentation.alarm.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.entity.AlarmClockItem
import app.domain.entity.DayOfWeek
import app.domain.repository.AlarmClockRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
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

    private val alarmItem: AlarmClockItem = voidItem.voidAlarmItem

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

    //add content item
    fun addAlarm() {
        addDayOfWeek(_listOfWeek.value)
        addIsEnabled(true)
        viewModelScope.launch {
            alarmClockRepository.insertAlarm(alarmItem)
        }
    }

    fun addDescription(string: String) {
        alarmItem.description = string
    }

    fun addIsEnabled(boolean: Boolean) {
        alarmItem.isEnabled = boolean
    }

    fun addTime(selectedHour: Int, selectedMinute: Int) {
        val time = selectedHour * 60 + selectedMinute
        alarmItem.minutesFromStartOfDay = time
    }

    fun addVibration(boolean: Boolean) {
        alarmItem.vibration = boolean
    }

    fun addDeleteAfterUse(boolean: Boolean) {
        alarmItem.deleteAfterUse = boolean
    }

    fun addSong(pathSong: String) {
        alarmItem.song = pathSong
    }

    private fun addDayOfWeek(dayOfWeek: Set<DayOfWeek>) {
        alarmItem.dayOfTheWeek = dayOfWeek
    }

    //delete item
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

object voidItem {
    val voidAlarmItem: AlarmClockItem = AlarmClockItem(
    description = "",
    isEnabled = false,
    minutesFromStartOfDay = 0,
    vibration = false,
    deleteAfterUse = false,
    song = "",
    dayOfTheWeek = emptySet()
    )
}