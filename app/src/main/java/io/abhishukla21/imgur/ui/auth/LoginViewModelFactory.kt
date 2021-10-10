package io.abhishukla21.imgur.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.abhishukla21.imgur.repository.AuthRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class LoginViewModelFactory(
    private val authRepository: AuthRepository,
    private val compositeDisposable: CompositeDisposable
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                authRepository,
                compositeDisposable
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}