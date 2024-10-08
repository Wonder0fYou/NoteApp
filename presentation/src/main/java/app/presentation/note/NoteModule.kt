package app.presentation.note

import app.domain.repository.NoteRepository
import app.presentation.di.UiScope
import app.presentation.viewmodels.NoteViewModel
import dagger.Module
import dagger.Provides
@Module
abstract class NoteModule {

    @Module
    companion object {
        @Provides
        @UiScope
        fun provideNoteViewModel(repository: NoteRepository): NoteViewModel {
            return NoteViewModel(repository)
        }
    }
}