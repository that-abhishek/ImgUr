package io.abhishukla21.imgur.network

import dagger.Lazy
import io.abhishukla21.imgur.repository.AuthRepository
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenAuthenticator @Inject constructor(private val authRepositoryWrapper: Lazy<AuthRepository>): Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        val refreshAccessTokenResponse = authRepositoryWrapper.get().refreshToken().blockingGet()
        return response.request.newBuilder()
            .addHeader("Authorization", "Bearer ${refreshAccessTokenResponse.accessToken}")
            .build()
    }
}