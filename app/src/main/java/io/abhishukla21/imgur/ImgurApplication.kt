package io.abhishukla21.imgur

import android.app.Application
import io.abhishukla21.imgur.di.component.ApplicationComponent
import io.abhishukla21.imgur.di.component.DaggerApplicationComponent
import io.abhishukla21.imgur.di.module.ApplicationModule

class ImgurApplication : Application() {
    lateinit var daggerApplicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        addDependencies()

    }

    private fun addDependencies() {
        daggerApplicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
        daggerApplicationComponent.inject(this)
    }

}