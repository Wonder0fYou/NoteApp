package app.presentation.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.entity.NoteItem
import app.domain.repository.NoteRepository
import app.presentation.note.model.NoteAction
import app.presentation.note.model.NoteState
import app.presentation.utils.currentDate
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
                _noteState.update {
                    _noteState.value.copy(
                        listItems = notesList.sortedWith(compareByDescending<NoteItem> { it.lastEdit }
                            .thenBy { if (searchWord.isEmpty()) it.title else it.title.contains(searchWord, ignoreCase = true)})
                    )
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
            is NoteAction.ChangeNote -> _noteState.update {
                if (action.selectedNote == noteState.value.selectedNote) {
                    noteState.value.copy(
                        selectedNote = action.selectedNote,
                        visible = false
                    )
                } else {
                    noteState.value.copy(
                        selectedNote = action.selectedNote,
                        visible = true
                    )
                }
            }
            is NoteAction.ChangeContent -> _noteState.update {
                if (action.content == noteState.value.selectedNote.content) {
                    noteState.value.copy(
                        content = action.content,
                        visible = false
                    )
                } else {
                    noteState.value.copy(
                        content = action.content,
                        visible = true
                    )
                }
            }
            is NoteAction.InputSearchWord -> _noteState.update {
                noteState.value.copy(
                    searchWord = action.searchWord
                )
            }
            is NoteAction.OpenDialogDelete -> _noteState.update {
                noteState.value.copy(
                    openDialogDelete = action.openDialog
                )
            }

            NoteAction.SaveNote -> {
                if (noteState.value.isAddItem) {
                    addNote()
                    _noteState.update {
                        noteState.value.copy(inputTitle = "", inputContent = "", checkEmpty = false)
                    }
                } else {
                    updateNote()
                    _noteState.update {
                        noteState.value.copy(
                            visible = false,
                            isAddItem = true
                        )
                    }
                }
            }
            NoteAction.DeleteNote -> {
                try {
                    deleteNote(noteState.value.selectedNote.noteId)
                    _noteState.update {
                        noteState.value.copy(openDialogDelete = false)
                    }
                    loadNote()
                } catch (_: Exception) {
                    loadNote()
                }
            }
        }
    }

    fun getNote(noteId: Int){
        viewModelScope.launch {
            val note = noteRepository.getNote(noteId)
            _noteState.update {
                noteState.value.copy(
                    selectedNote = note,
                    isAddItem = false
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
            title = noteState.value.selectedNote.title.trim(),
            content = noteState.value.selectedNote.content.trim(),
            lastEdit = noteState.value.lastEdit
        )
        viewModelScope.launch {
            noteRepository.updateNote(note)
        }
    }

    private fun deleteNote(noteId: Int) {
        viewModelScope.launch {
            noteRepository.deleteNote(noteId)
        }
    }
}