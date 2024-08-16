package app.noteapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import app.noteapp.domain.model.Note
import app.noteapp.domain.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository
): ViewModel(){

    private val _notes = MutableStateFlow<List<Note>>(arrayListOf())
    val notes: StateFlow<List<Note>> = _notes.asStateFlow()
    private val _note = MutableStateFlow<Note?>(null)
    var note: StateFlow<Note?> = _note

    init {
        viewModelScope.launch {
            noteRepository.getNotes().collect { notesList ->
                _notes.value = notesList
            }
        }
    }

    fun addNote (note: Note) {
        viewModelScope.launch {
            noteRepository.insertNote(note)
            withContext(Dispatchers.Main) {
                _notes.value = noteRepository.getNotes().first()
            }
        }
    }

    fun deleteNote(noteId: Int) {
        viewModelScope.launch {
            noteRepository.deleteNote(noteId)
            withContext(Dispatchers.Main) {
                _notes.value = noteRepository.getNotes().first()
            }
        }
    }

    fun getNote(noteId: Int) {
        viewModelScope.launch {
            val fetchedNote = noteRepository.getNote(noteId)
            _note.value = fetchedNote
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            noteRepository.updateNote(note)
        }
    }
}