package io.abhishukla21.imgur.network.service

import io.abhishukla21.imgur.data.EndPoints
import io.abhishukla21.imgur.data.model.BaseResponse
import io.abhishukla21.imgur.data.model.auth.request.RefreshAccessTokenRequest
import io.abhishukla21.imgur.data.model.auth.response.RefreshAccessTokenResponse
import io.abhishukla21.imgur.data.model.gallery.entity.Album
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*
import javax.inject.Singleton

@Singleton
interface ImgurService {

    @POST(EndPoints.GET_TOKEN)
    fun refreshAccessToken(
        @Body refreshAccessTokenRequest: RefreshAccessTokenRequest
    ): Single<RefreshAccessTokenResponse>

    @GET(EndPoints.SEARCH_GALLERY)
    fun searchGallery(
        @Path("sort") sort: String,
        @Path("window") window: String,
        @Path("page") page: Int = 0,
        @Query("q") query: String
    ): Single<BaseResponse<List<Album>>>


}