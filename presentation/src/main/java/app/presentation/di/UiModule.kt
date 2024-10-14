package app.presentation.di

import app.presentation.note.di.NoteComponent
import dagger.Module

@Module(subcomponents = [NoteComponent::class])
object UiModule