package app.presentation.note.di

import app.presentation.di.UiScope
import dagger.Subcomponent

@Subcomponent(
    modules = [NoteModule::class],
)
@UiScope
interface NoteComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(
        ): NoteComponent
    }
}