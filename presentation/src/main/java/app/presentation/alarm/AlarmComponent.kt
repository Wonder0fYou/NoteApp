package app.presentation.alarm

import app.presentation.di.UiScope
import dagger.Subcomponent

@Subcomponent(
    modules = [AlarmModule::class],
)
@UiScope
interface AlarmComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(
        ): AlarmComponent
    }
}
