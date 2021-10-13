package io.abhishukla21.imgur.di.component

import dagger.Component
import io.abhishukla21.imgur.ui.MainActivity
import io.abhishukla21.imgur.di.ActivityScope
import io.abhishukla21.imgur.di.module.ActivityModule
import io.abhishukla21.imgur.ui.auth.AuthenticationActivity

@ActivityScope
@Component(modules = [ActivityModule::class], dependencies = [ApplicationComponent::class])
interface ActivityComponent {

    fun inject(activity: AuthenticationActivity)

    fun inject(activity: MainActivity)

}