package io.abhishukla21.imgur.data.model.auth.request

import com.google.gson.annotations.SerializedName

data class RefreshAccessTokenRequest(

    @SerializedName("refresh_token")
    val refreshToken: String,

    @SerializedName("client_id")
    val clientId: String,

    @SerializedName("client_secret")
    val clientSecret: String,

    @SerializedName("grant_type")
    val grantType: String = "refresh_token"

)
