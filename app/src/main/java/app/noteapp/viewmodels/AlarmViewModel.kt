package app.noteapp.viewmodels

import androidx.lifecycle.ViewModel
import app.noteapp.domain.repository.AlarmClockRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AlarmViewModel @Inject constructor(
    private val alarmClockRepository: AlarmClockRepository
): ViewModel() {

}