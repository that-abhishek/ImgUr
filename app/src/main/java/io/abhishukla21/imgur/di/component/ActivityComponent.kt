package io.abhishukla21.imgur.di.component

import androidx.appcompat.app.AppCompatActivity
import dagger.Component
import io.abhishukla21.imgur.di.ActivityScope
import io.abhishukla21.imgur.di.module.ActivityModule
import io.abhishukla21.imgur.ui.auth.AuthenticationActivity
import io.abhishukla21.imgur.ui.auth.LoginViewModel

@ActivityScope
@Component(modules = [ActivityModule::class], dependencies = [ApplicationComponent::class])
interface ActivityComponent {

    fun inject(activity: AuthenticationActivity)

}