package app.presentation.di

import app.presentation.alarm.AlarmComponent
import app.presentation.note.NoteComponent
import dagger.Module

@Module(subcomponents = [NoteComponent::class, AlarmComponent::class])
object UiModule {
}