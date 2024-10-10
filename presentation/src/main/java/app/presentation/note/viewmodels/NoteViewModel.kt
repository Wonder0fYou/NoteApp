    package app.presentation.note.viewmodels

    import androidx.compose.material3.SnackbarHostState
    import androidx.compose.runtime.MutableState
    import androidx.compose.runtime.mutableStateOf
    import androidx.lifecycle.ViewModel
    import androidx.lifecycle.viewModelScope
    import app.domain.entity.NoteItem
    import app.domain.repository.NoteRepository
    import dagger.hilt.android.lifecycle.HiltViewModel
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

        private val _notes = MutableStateFlow<List<NoteItem>>(arrayListOf())
        val notes: StateFlow<List<NoteItem>> = _notes.asStateFlow()
        private val _note = MutableStateFlow<NoteItem?>(null)
        var note: StateFlow<NoteItem?> = _note

        private val _isVisible = MutableStateFlow(false)
        val isVisible: StateFlow<Boolean> = _isVisible.asStateFlow()

        private val _rememberTitle = MutableStateFlow("")
        val rememberTitle: StateFlow<String> = _rememberTitle.asStateFlow()
        private val _rememberContent = MutableStateFlow("")
        val rememberContent: StateFlow<String> = _rememberContent.asStateFlow()

        private val _snackBarHostState = mutableStateOf(SnackbarHostState())
        val snackBarHostState: MutableState<SnackbarHostState> = _snackBarHostState

        private val _popUpJoke = MutableStateFlow(false)
        val popUpJoke: StateFlow<Boolean> = _popUpJoke.asStateFlow()

        init {
            loadNote()
        }

        private fun loadNote() {
            viewModelScope.launch {
                noteRepository.getNotes().collect { notesList: List<NoteItem> ->
                    _notes.value = notesList
                }
            }
        }

        fun addNote (note: NoteItem) {
            viewModelScope.launch {
                note.title?.trim()
                note.content?.trim()
                noteRepository.insertNote(note)
                withContext(Dispatchers.IO) {
                    _notes.value = noteRepository.getNotes().first()
                }
                _rememberTitle.value = ""
                _rememberContent.value = ""
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
                note.title?.trim()
                note.content?.trim()
                noteRepository.updateNote(note)
                _isVisible.value = false
            }
        }

        fun onTitleChange(newTitle: String) {
            _isVisible.value = newTitle != _note.value?.title
        }

        fun onContentChange(newContent: String) {
            _isVisible.value = newContent != _note.value?.content
        }

        fun onRememberTitle(newTitle: String) {
            _rememberTitle.value = newTitle
        }

        fun onRememberContent(newContent: String) {
            _rememberContent.value = newContent
        }

        fun checkJoke(title: String = "", content: String = "") {
            _popUpJoke.value = title == "Анекдот" || content == "Анекдот"
        }
    }