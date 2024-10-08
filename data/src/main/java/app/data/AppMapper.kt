package app.data

import app.data.database.entity.AlarmClockItemEntity
import app.data.database.entity.NoteItemEntity
import app.domain.entity.AlarmClockItem
import app.domain.entity.NoteItem

class AppMapper {

    fun mapToNoteEntity (noteItem: NoteItem): NoteItemEntity {
        return NoteItemEntity(
            noteId = noteItem.noteId,
            title = noteItem.title,
            content = noteItem.content
        )
    }

    fun mapToNoteDomain (noteItemEntity: NoteItemEntity): NoteItem {
        return NoteItem(
            noteId = noteItemEntity.noteId,
            title = noteItemEntity.title,
            content = noteItemEntity.content
        )
    }

    fun mapToAlarmEntity (alarmItem: AlarmClockItem): AlarmClockItemEntity {
        return AlarmClockItemEntity(
            alarmId = alarmItem.alarmId,
            description = alarmItem.description,
            isEnabled = alarmItem.isEnabled,
            time = alarmItem.time,
            vibration = alarmItem.vibration,
            deleteAfterUse = alarmItem.deleteAfterUse,
            song = alarmItem.song,
            dayOfTheWeek = alarmItem.dayOfTheWeek
        )
    }

    fun mapToAlarmDomain (alarmItemEntity: AlarmClockItemEntity): AlarmClockItem {
        return AlarmClockItem(
            alarmId = alarmItemEntity.alarmId,
            description = alarmItemEntity.description,
            isEnabled = alarmItemEntity.isEnabled,
            time = alarmItemEntity.time,
            vibration = alarmItemEntity.vibration,
            deleteAfterUse = alarmItemEntity.deleteAfterUse,
            song = alarmItemEntity.song,
            dayOfTheWeek = alarmItemEntity.dayOfTheWeek
        )
    }
}