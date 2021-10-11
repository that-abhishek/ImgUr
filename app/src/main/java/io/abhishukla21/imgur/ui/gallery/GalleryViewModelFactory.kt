package io.abhishukla21.imgur.ui.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.abhishukla21.imgur.di.ActivityScope
import io.abhishukla21.imgur.repository.GalleryRepository
import io.abhishukla21.imgur.ui.auth.LoginViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

@ActivityScope
class GalleryViewModelFactory @Inject constructor(
    private val galleryRepository: GalleryRepository,
    private val compositeDisposable: CompositeDisposable
) :
    ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GalleryViewModel::class.java)) {
            return GalleryViewModel(
                galleryRepository,
                compositeDisposable
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}