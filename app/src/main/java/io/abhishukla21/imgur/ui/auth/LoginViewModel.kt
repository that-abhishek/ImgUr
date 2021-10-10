package io.abhishukla21.imgur.ui.auth

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.abhishukla21.imgur.BuildConfig

import io.abhishukla21.imgur.data.*
import io.abhishukla21.imgur.repository.AuthRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class LoginViewModel(
    private val authRepository: AuthRepository,
    private val compositeDisposable: CompositeDisposable
) : ViewModel() {

    val _loginForm = MutableLiveData<LoginFormState>()

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun onCreate() {
        compositeDisposable.add(authRepository.getLoggedInUser()
            .subscribe({_loginResult.postValue(it)}, {})
        )
    }

    fun login() {
        val builtUri = Uri.parse(BaseUrl.BASE_URL)
            .buildUpon()
            .appendPath(EndPoints.AUTHORIZE)
            .appendQueryParameter(QueryParamKey.AUTH_CLIENT_ID, BuildConfig.IMGUR_CLIENT_ID)
            .appendQueryParameter(
                QueryParamKey.AUTH_RESPONSE_TYPE,
                QueryParamValue.AUTH_RESPONSE_TYPE_TOKEN
            )
            .build()
        _loginForm.postValue(LoginFormState(builtUri))


    }

    fun handleAuthorizationCallback(callbackUrl: Uri) {
        compositeDisposable.add(
            authRepository.saveAuthDataFromCallbackUri(callbackUrl)
                .subscribeOn(Schedulers.computation())
                .subscribe({ _loginResult.postValue(it) }, {})
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

}