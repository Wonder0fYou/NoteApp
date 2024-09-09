package app.noteapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.noteapp.domain.model.AlarmClock
import app.noteapp.domain.repository.AlarmClockRepository
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

    private val _alarms = MutableStateFlow<List<AlarmClock>>(arrayListOf())
    val alarms: StateFlow<List<AlarmClock>> = _alarms.asStateFlow()
    private val _alarm = MutableStateFlow<AlarmClock?>(null)
    val alarm: StateFlow<AlarmClock?> = _alarm

    init {
        loadAlarms()
    }

    private fun loadAlarms(){
        viewModelScope.launch {
            alarmClockRepository.getAllAlarms().collect{ alarmList ->
                _alarms.value = alarmList
            }
        }
    }

    fun addAlarm(alarmClock: AlarmClock) {
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

    fun updateAlarm(alarmClock: AlarmClock) {
        viewModelScope.launch {
            alarmClockRepository.updateAlarm(alarmClock)
        }
    }
}