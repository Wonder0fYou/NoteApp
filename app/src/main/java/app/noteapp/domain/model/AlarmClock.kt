package app.noteapp.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alarms")
data class AlarmClock(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val alarmId: Int = 0,
    var description: String?,
    var isEnabled: Boolean,
    var time: Int,
    var vibration: Boolean,
    var deleteAfterUse: Boolean,
    var song: String,
    val dayOfTheWeek: Set<DayOfWeek>
)

enum class DayOfWeek{
    MONDAY {
        override fun toString(): String {
            return "monday"
        }
    },
    TUESDAY {
        override fun toString(): String {
            return "tuesday"
        }
    },
    WEDNESDAY {
        override fun toString(): String {
            return "wednesday"
        }
    },
    THURSDAY {
        override fun toString(): String {
            return "thursday"
        }
    },
    FRIDAY {
        override fun toString(): String {
            return "friday"
        }
    },
    SATURDAY {
        override fun toString(): String {
            return "saturday"
        }
    },
    SUNDAY {
        override fun toString(): String {
            return "sunday"
        }
    }
}
