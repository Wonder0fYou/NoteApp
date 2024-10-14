package com.example.cftapp.presentation.di

import com.example.cftapp.presentation.note.di.NoteComponent
import dagger.Module

@Module(subcomponents = [NoteComponent::class])
object UiModule