package app.data

import app.data.database.entity.NoteItemEntity
import app.domain.entity.NoteItem

class AppMapper {
    fun mapToNoteEntity (noteItem: NoteItem): NoteItemEntity {
        return NoteItemEntity(
            noteId = noteItem.noteId,
            title = noteItem.title,
            content = noteItem.content,
            lastEdit = noteItem.lastEdit.toUnixTimestamp()
        )
    }

    fun mapToNoteDomain (noteItemEntity: NoteItemEntity): NoteItem {
        return NoteItem(
            noteId = noteItemEntity.noteId,
            title = noteItemEntity.title,
            content = noteItemEntity.content,
            lastEdit = noteItemEntity.lastEdit.toDateFromUnixTimestamp()
        )
    }
}