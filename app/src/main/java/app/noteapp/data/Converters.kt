package app.noteapp.data

import androidx.room.TypeConverter
import app.noteapp.domain.model.DayOfWeek

class Converters {

    @TypeConverter
    fun fromDayOfWeekSet(dayOfWeek: Set<DayOfWeek>): String {
        return dayOfWeek.joinToString(",") { it.name }
    }

    @TypeConverter
    fun toDayOfWeekSet(dayOfWeekString: String): Set<DayOfWeek> {
        return dayOfWeekString.split(",").map { DayOfWeek.valueOf(it) }.toSet()
    }
}