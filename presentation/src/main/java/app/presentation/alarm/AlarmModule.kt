package app.presentation.alarm

import app.domain.repository.AlarmClockRepository
import app.presentation.di.UiScope
import app.presentation.viewmodels.AlarmViewModel
import dagger.Module
import dagger.Provides

@Module
abstract class AlarmModule {
    @Module
    companion object {
        @Provides
        @UiScope
        fun provideAlarmViewModel(repository: AlarmClockRepository): AlarmViewModel {
            return AlarmViewModel(repository)
        }
    }
}