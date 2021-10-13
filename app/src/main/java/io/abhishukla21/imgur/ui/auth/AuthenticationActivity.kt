package io.abhishukla21.imgur.ui.auth

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.Observer
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import io.abhishukla21.imgur.ImgurApplication
import io.abhishukla21.imgur.ui.MainActivity
import io.abhishukla21.imgur.databinding.ActivityAuthenticationBinding

import io.abhishukla21.imgur.R
import io.abhishukla21.imgur.di.component.DaggerActivityComponent
import io.abhishukla21.imgur.di.module.ActivityModule
import javax.inject.Inject

class AuthenticationActivity : AppCompatActivity() {

    companion object {
        private val TAG = AuthenticationActivity::class.java.canonicalName
    }

    @Inject
    lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityAuthenticationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerActivityComponent
            .builder()
            .applicationComponent((application as ImgurApplication).daggerApplicationComponent)
            .activityModule(ActivityModule(this))
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)

        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val login = binding.login
        val loading = binding.loading


        loginViewModel._loginForm.observe(this@AuthenticationActivity, Observer {

            val customTabsIntent = CustomTabsIntent.Builder().build()
            Log.d(TAG, "launching url: ${it.oauthurl}")
            customTabsIntent.launchUrl(this, it.oauthurl)


        })

        loginViewModel.loginResult.observe(this@AuthenticationActivity, Observer {
            val loginResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                updateUiWithUser(loginResult.success)
            }
            setResult(Activity.RESULT_OK)

            //Complete and destroy login activity once successful
            finish()
        })

        login.setOnClickListener {
            loading.visibility = View.VISIBLE
            loginViewModel.login()
        }
        loginViewModel.onCreate()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val callbackUrl = intent?.data
        if (callbackUrl != null) {
            loginViewModel.handleAuthorizationCallback(callbackUrl)
        }
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        // TODO : initiate successful logged in experience
        startActivity(Intent(this, MainActivity::class.java))
        Toast.makeText(
            applicationContext,
            "$welcome $displayName",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
//        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}
