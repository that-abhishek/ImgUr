package io.abhishukla21.imgur.network.service

import io.abhishukla21.imgur.data.EndPoints
import io.abhishukla21.imgur.data.model.auth.request.RefreshAccessTokenRequest
import io.abhishukla21.imgur.data.model.auth.response.RefreshAccessTokenResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.POST
import javax.inject.Singleton

@Singleton
interface ImgurService {

    @POST(EndPoints.GET_TOKEN)
    fun refreshAccessToken(@Body refreshAccessTokenRequest: RefreshAccessTokenRequest): Single<RefreshAccessTokenResponse>

}