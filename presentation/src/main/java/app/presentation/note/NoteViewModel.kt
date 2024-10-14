package app.presentation.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.entity.NoteItem
import app.domain.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Date
import javax.inject.Inject

class NoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository
): ViewModel(){
    private val _notes = MutableStateFlow<List<NoteItem>>(arrayListOf())
    val notes: StateFlow<List<NoteItem>> = _notes.asStateFlow()
    private val _note = MutableStateFlow(NoteItem(0, "", "", Date()))
    var note: StateFlow<NoteItem> = _note

    private val _isVisible = MutableStateFlow(false)
    val isVisible: StateFlow<Boolean> = _isVisible.asStateFlow()

    private val _rememberTitle = MutableStateFlow("")
    val rememberTitle: StateFlow<String> = _rememberTitle.asStateFlow()
    private val _rememberContent = MutableStateFlow("")
    val rememberContent: StateFlow<String> = _rememberContent.asStateFlow()

    private val _checkEmpty = MutableStateFlow(false)
    val checkEmpty: StateFlow<Boolean> = _checkEmpty.asStateFlow()

    init {
        loadNote()
    }

    private fun loadNote() {
        viewModelScope.launch {
            noteRepository.getNotes().collect { notesList: List<app.domain.entity.NoteItem> ->
                _notes.value = notesList
            }
        }
    }

    fun addNote (note: NoteItem) {
        viewModelScope.launch {
            note.title.trim()
            note.content.trim()
            noteRepository.insertNote(note)
            withContext(Dispatchers.IO) {
                _notes.value = noteRepository.getNotes().first()
            }
            _rememberTitle.value = ""
            _rememberContent.value = ""
            _checkEmpty.value = false
        }
    }

    fun deleteNote(noteId: Int) {
        viewModelScope.launch {
            noteRepository.deleteNote(noteId)
            withContext(Dispatchers.IO) {
                _notes.value = noteRepository.getNotes().first()
            }
        }
    }

    fun getNote(noteId: Int) {
        viewModelScope.launch {
            val fetchedNote = noteRepository.getNote(noteId)
            _note.value = fetchedNote
            _isVisible.value = false
        }
    }

    fun updateNote(note: NoteItem) {
        viewModelScope.launch {
            note.title.trim()
            note.content.trim()
            noteRepository.updateNote(note)
            _isVisible.value = false
        }
    }

    fun onTitleChange(newTitle: String) {
        _isVisible.value = newTitle != _note.value.title
    }

    fun onContentChange(newContent: String) {
        _isVisible.value = newContent != _note.value.content
    }

    fun onRememberTitle(newTitle: String) {
        _rememberTitle.value = newTitle
    }

    fun onRememberContent(newContent: String) {
        _rememberContent.value = newContent
    }

    fun checkEmptyNote() {
        _checkEmpty.value = !(_rememberTitle.value.isEmpty() && _rememberContent.value.isEmpty())
    }
}