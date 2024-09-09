package app.noteapp.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alarms")
data class AlarmClock(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val alarmId: Int = 0,
    var description: String = null.toString(),
    var isEnabled: Boolean = false,
    var time: Int,
    var vibration: Boolean = false,
    var deleteAfterUse: Boolean = false,
    var song: String = null.toString(),
    var dayOfTheWeek: DayOfWeek
)

enum class DayOfWeek{
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY
}
