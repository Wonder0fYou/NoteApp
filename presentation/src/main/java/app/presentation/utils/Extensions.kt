package app.presentation.utils

import java.util.Calendar
import java.util.Date

val currentDate: Date
    get() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, 0)
        return calendar.time
    }