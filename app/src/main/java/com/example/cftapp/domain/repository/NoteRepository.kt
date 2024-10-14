package com.example.cftapp.domain.repository

import com.example.cftapp.domain.entity.NoteItem
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun getNotes(): Flow<List<NoteItem>>

    suspend fun getNote(noteId: Int): NoteItem

    suspend fun deleteNote(noteId: Int)

    suspend fun insertNote(note: NoteItem)

    suspend fun updateNote(note: NoteItem)
}