package io.abhishukla21.imgur.ui.gallery

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.abhishukla21.imgur.data.model.gallery.entity.Album
import io.abhishukla21.imgur.repository.GalleryRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class GalleryViewModel(
    private val galleryRepository: GalleryRepository,
    private val compositeDisposable: CompositeDisposable
) : ViewModel() {

    val searchResultLiveData = MutableLiveData<List<Album>>()
    fun onCreate() {}

    fun searchGallery(query: String) {
        compositeDisposable.add(
            galleryRepository.searchGallery(query = query)
                .subscribeOn(Schedulers.io())
                .subscribe({ searchResultLiveData.postValue(it) }, {it.printStackTrace()})
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

}