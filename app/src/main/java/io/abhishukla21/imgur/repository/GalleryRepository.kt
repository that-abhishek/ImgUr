package io.abhishukla21.imgur.repository

import android.content.SharedPreferences
import io.abhishukla21.imgur.data.model.gallery.entity.Album
import io.abhishukla21.imgur.network.TokenInterceptor
import io.abhishukla21.imgur.network.service.ImgurService
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GalleryRepository @Inject constructor(
    private val imgurService: ImgurService
) {

    fun searchGallery(
        sort: String = "time",
        window: String = "all",
        page: Int = 0,
        query: String
    ): Single<List<Album>> {
        return imgurService.searchGallery(sort, window, page, query)
            .map { it.data }
    }

}