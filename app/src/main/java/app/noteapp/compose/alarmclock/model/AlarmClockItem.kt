package app.noteapp.compose.alarmclock.model

import app.noteapp.domain.model.DayOfWeek

data class AlarmClockItem(
    val alarmId: Int = 0,
    var description: String?,
    var isEnabled: Boolean,
    var time: Int,
    var vibration: Boolean,
    var deleteAfterUse: Boolean,
    var song: String,
    val dayOfTheWeek: Set<DayOfWeek>
)