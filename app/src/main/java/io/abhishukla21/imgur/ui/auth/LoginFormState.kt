package io.abhishukla21.imgur.ui.auth

import android.net.Uri

/**
 * Data validation state of the login form.
 */
data class LoginFormState(
    val oauthurl: Uri
)