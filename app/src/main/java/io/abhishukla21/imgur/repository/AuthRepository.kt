package io.abhishukla21.imgur.repository

import android.content.SharedPreferences
import android.net.Uri
import io.abhishukla21.imgur.BuildConfig
import io.abhishukla21.imgur.data.PrefKey
import io.abhishukla21.imgur.data.model.auth.request.RefreshAccessTokenRequest
import io.abhishukla21.imgur.data.model.auth.response.RefreshAccessTokenResponse
import io.abhishukla21.imgur.network.TokenInterceptor
import io.abhishukla21.imgur.network.service.ImgurService
import io.abhishukla21.imgur.ui.auth.LoggedInUserView
import io.abhishukla21.imgur.ui.auth.LoginResult
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val imgurService: ImgurService,
    private val sharedPreferences: SharedPreferences,
    private val tokenInterceptor: TokenInterceptor
) {

    fun saveAuthDataFromCallbackUri(uri: Uri): Single<LoginResult> {

        val uriFragmentParts = uri.fragment?.split('&')
        val fragmentMap = mutableMapOf<String, String>()
        uriFragmentParts?.forEach {
            val pair = it.split('=')
            fragmentMap[pair[0]] = pair[1]
        }
        val prefEditor = sharedPreferences.edit()
        prefEditor.putString(PrefKey.ACCESS_TOKEN, fragmentMap[PrefKey.ACCESS_TOKEN])
        prefEditor.putLong(PrefKey.EXPIRES_IN, fragmentMap[PrefKey.EXPIRES_IN]?.toLong()!!)
        prefEditor.putString(PrefKey.TOKEN_TYPE, fragmentMap[PrefKey.TOKEN_TYPE])
        prefEditor.putString(PrefKey.REFRESH_TOKEN, fragmentMap[PrefKey.REFRESH_TOKEN])
        prefEditor.putString(PrefKey.ACCOUNT_USERNAME, fragmentMap[PrefKey.ACCOUNT_USERNAME])
        prefEditor.putString(PrefKey.ACCOUNT_ID, fragmentMap[PrefKey.ACCOUNT_ID])
        prefEditor.putLong(PrefKey.AUTHORIZATION_TIME, System.currentTimeMillis())
        prefEditor.apply()
        tokenInterceptor.updateAccessToken(fragmentMap[PrefKey.ACCESS_TOKEN] ?: "")
        val loginResult =
            if (fragmentMap[PrefKey.ACCOUNT_USERNAME]?.isNotEmpty() == true) LoginResult(
                success = LoggedInUserView(
                    fragmentMap[PrefKey.ACCOUNT_USERNAME] ?: ""
                )
            )
            else LoginResult(error = 0)
        return Single.just(loginResult)

    }

    fun getLoggedInUser(): Single<LoginResult> {
        var loginResult: LoginResult
        if (isUserLoggedIn()) {
            if (isAuthValid()) {
                val accountName = sharedPreferences.getString(PrefKey.ACCOUNT_USERNAME, null)
                loginResult = LoginResult(success = LoggedInUserView(accountName ?: ""))
            } else {
                return refreshToken().map { LoginResult(success = LoggedInUserView(it.accountUsername)) }
            }
        } else {
            loginResult = LoginResult(error = 0)
        }
        return Single.just(loginResult)

    }

    fun refreshToken(): Single<RefreshAccessTokenResponse> {
        val refreshToken = sharedPreferences.getString(PrefKey.REFRESH_TOKEN, null) ?: ""
        val clientId = BuildConfig.IMGUR_CLIENT_ID
        val clientSecret = BuildConfig.IMGUR_CLIENT_SECRET
        return imgurService.refreshAccessToken(
            RefreshAccessTokenRequest(
                refreshToken,
                clientId,
                clientSecret
            )
        ).map {
            val prefEditor = sharedPreferences.edit()
            prefEditor.putString(PrefKey.ACCESS_TOKEN, it.accessToken)
            prefEditor.putLong(PrefKey.EXPIRES_IN, it.expiresIn)
            prefEditor.putString(PrefKey.TOKEN_TYPE, it.tokenType)
            prefEditor.putString(PrefKey.REFRESH_TOKEN, it.refreshToken)
            prefEditor.putString(PrefKey.ACCOUNT_USERNAME, it.accountUsername)
            prefEditor.putLong(PrefKey.AUTHORIZATION_TIME, System.currentTimeMillis())
            prefEditor.apply()
            tokenInterceptor.updateAccessToken(it.accessToken)
            return@map it
        }
    }

    fun isAuthValid(): Boolean {
        val authTime = sharedPreferences.getLong(PrefKey.AUTHORIZATION_TIME, 0L)
        val expiry = sharedPreferences.getLong(PrefKey.EXPIRES_IN, 0L) * 1000 // sec to ms
        return authTime + expiry > System.currentTimeMillis()
    }

    fun isUserLoggedIn() =
        sharedPreferences.getString(PrefKey.ACCOUNT_ID, null)?.isNotBlank() ?: false

}