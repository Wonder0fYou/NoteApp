package app.presentation.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.entity.NoteItem
import app.domain.repository.NoteRepository
import app.presentation.note.model.NoteAction
import app.presentation.note.model.NoteState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class NoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository
): ViewModel(){

    private val _noteState = MutableStateFlow(NoteState())
    val noteState = _noteState.asStateFlow()

    init {
        loadNote()
    }

    private fun loadNote() {
        viewModelScope.launch {
            noteRepository.getNotes().collect { notesList: List<NoteItem> ->
                val searchWord = _noteState.value.searchWord
                if (searchWord.isNotEmpty()) {
                    val filteredAndSortedList = notesList
                        .filter { it.title.contains(searchWord, ignoreCase = true) }
                        .sortedBy { it.title }
                    _noteState.update {
                        _noteState.value.copy(
                            listItems = filteredAndSortedList
                        )
                    }
                } else {
                    _noteState.update {
                        _noteState.value.copy(
                            listItems = notesList.sortedWith(compareByDescending { it.lastEdit })
                        )
                    }
                }
            }
        }
    }

    fun onAction(action: NoteAction) {
        when(action) {
            is NoteAction.InputTitle -> _noteState.update {
                if (action.inputTitle.isBlank()) {
                    noteState.value.copy(
                        inputTitle = action.inputTitle,
                        checkEmpty = false
                    )
                } else {
                    noteState.value.copy(
                        inputTitle = action.inputTitle,
                        checkEmpty = true
                    )
                }
            }
            is NoteAction.InputContent -> _noteState.update {
                if (action.inputContent.isBlank()) {
                    noteState.value.copy(
                        inputContent = action.inputContent,
                        checkEmpty = false
                    )
                } else {
                    noteState.value.copy(
                        inputContent = action.inputContent,
                        checkEmpty = true
                    )
                }
            }
            is NoteAction.ChangeTitle -> _noteState.update {
                if (action.noteTitle == noteState.value.noteTitle) {
                    noteState.value.copy(
                        noteTitle = action.noteTitle,
                        visible = false
                    )
                } else {
                    noteState.value.copy(
                        noteTitle = action.noteTitle,
                        visible = true
                    )
                }
            }
            is NoteAction.ChangeContent -> _noteState.update {
                if (action.noteContent == noteState.value.noteContent) {
                    noteState.value.copy(
                        noteContent = action.noteContent,
                        visible = false
                    )
                } else {
                    noteState.value.copy(
                        noteContent = action.noteContent,
                        visible = true
                    )
                }
            }
            is NoteAction.InputSearchWord -> {
                _noteState.update {
                    noteState.value.copy(
                        searchWord = action.searchWord
                    )
                }
                loadNote()
            }
            is NoteAction.OpenDialogDelete -> _noteState.update {
                noteState.value.copy(
                    openDialogDelete = action.openDialog
                )
            }

            NoteAction.AddNote -> {
                addNote()
                _noteState.update {
                    noteState.value.copy(inputTitle = "", inputContent = "", checkEmpty = false)
                }
            }
            NoteAction.UpdateNote -> {
                updateNote()
                _noteState.update {
                    noteState.value.copy(
                        visible = false
                    )
                }
            }
            NoteAction.DeleteNote -> {
                val noteId = noteState.value.noteId
                deleteNote(noteId)
                _noteState.update {
                    noteState.value.copy(openDialogDelete = false)
                }
            }
        }
    }

    fun getNote(noteId: Int){
        viewModelScope.launch {
            val note = noteRepository.getNote(noteId)
            _noteState.update {
                noteState.value.copy(
                    noteId = note.noteId,
                    noteTitle = note.title,
                    noteContent = note.content,
                )
            }
        }
    }

    private fun addNote () {
        val note = NoteItem(
            title = noteState.value.inputTitle.trim(),
            content = noteState.value.inputContent.trim(),
            lastEdit = noteState.value.lastEdit
        )
        viewModelScope.launch {
            noteRepository.insertNote(note)
        }
    }

    private fun updateNote() {
        val note = NoteItem(
            noteId = noteState.value.noteId,
            title = noteState.value.noteTitle.trim(),
            content = noteState.value.noteContent.trim(),
            lastEdit = noteState.value.lastEdit
        )
        viewModelScope.launch {
            noteRepository.updateNote(note)
        }
    }

    private fun deleteNote(noteId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.deleteNote(noteId)
        }
    }
}