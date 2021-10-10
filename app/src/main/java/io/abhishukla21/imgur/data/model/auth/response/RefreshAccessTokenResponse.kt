package io.abhishukla21.imgur.data.model.auth.response

import com.google.gson.annotations.SerializedName

data class RefreshAccessTokenResponse(
    @SerializedName("access_token")
    val accessToken: String,

    @SerializedName("refresh_token")
    val refreshToken: String,

    @SerializedName("expires_in")
    val expiresIn: Long,

    @SerializedName("token_type")
    val tokenType: String,


    @SerializedName("account_username")
    val accountUsername: String

)
