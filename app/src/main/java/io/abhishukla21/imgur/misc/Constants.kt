package io.abhishukla21.imgur.data

object EndPoints {
    const val AUTHORIZE = "oauth2/authorize"
    const val GET_TOKEN = "oauth2/token"
    const val CURRENT_ACCOUNT = "3/account/me"
    const val SEARCH_GALLERY = "3/gallery/search/{sort}/{window}/{page}"
}

object BaseUrl {
    const val BASE_URL = "https://api.imgur.com/"
}

object QueryParamKey {
    const val AUTH_RESPONSE_TYPE = "response_type"
    const val AUTH_CLIENT_ID = "client_id"
    const val AUTH_STATE = "response_type"
}

object QueryParamValue {
    const val AUTH_RESPONSE_TYPE_CODE = "code"
    const val AUTH_RESPONSE_TYPE_TOKEN = "token"
    const val AUTH_RESPONSE_TYPE_PIN = "pin"
}

object PrefKey {
    const val ACCESS_TOKEN = "access_token"
    const val EXPIRES_IN = "expires_in"
    const val TOKEN_TYPE = "token_type"
    const val REFRESH_TOKEN = "refresh_token"
    const val ACCOUNT_USERNAME = "account_username"
    const val ACCOUNT_ID = "account_id"
    const val AUTHORIZATION_TIME = "auth_time"
}

object Header {
    const val KEY_AUTHORIZATION = "Authorization"
}

object MediaType {
    const val MEDIA_TYPE_VIDEO = "video/mp4"
    const val MEDIA_TYPE_IMAGE = "image/jpeg"
}