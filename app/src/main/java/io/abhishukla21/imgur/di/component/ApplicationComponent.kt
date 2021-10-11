package io.abhishukla21.imgur.di.component

import android.app.Application
import android.content.SharedPreferences
import dagger.Component
import io.abhishukla21.imgur.di.module.ApplicationModule
import io.abhishukla21.imgur.network.service.ImgurService
import io.abhishukla21.imgur.repository.AuthRepository
import io.abhishukla21.imgur.repository.GalleryRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Singleton

@Component(modules = [ApplicationModule::class])
@Singleton
interface ApplicationComponent {
    fun inject(application: Application)

    fun getSharedPref(): SharedPreferences

    fun getImgurService(): ImgurService

    fun getCompositeDisposable(): CompositeDisposable

    fun getUserRepository(): AuthRepository

    fun getGalleryRepository(): GalleryRepository
}