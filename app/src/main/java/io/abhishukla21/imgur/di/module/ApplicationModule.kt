package io.abhishukla21.imgur.di.module

import android.content.Context
import android.content.SharedPreferences
import android.hardware.usb.UsbEndpoint
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import dagger.Module
import dagger.Provides
import io.abhishukla21.imgur.data.BaseUrl
import io.abhishukla21.imgur.data.EndPoints
import io.abhishukla21.imgur.data.PrefKey
import io.abhishukla21.imgur.network.TokenAuthenticator
import io.abhishukla21.imgur.network.TokenInterceptor
import io.abhishukla21.imgur.network.service.ImgurService
import io.abhishukla21.imgur.repository.AuthRepository
import io.abhishukla21.imgur.repository.GalleryRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApplicationModule(private val appContext: Context) {

    @Provides
    @Named("application_context")
    fun getApplicationContext() = appContext

    @Provides
    @Named("base_url")
    fun provideBaseUrl(): String {
        return BaseUrl.BASE_URL
    }

    @Provides
    @Singleton
    fun provideImgurService(
        @Named("base_url") baseUrl: String,
        tokenAuthenticator: TokenAuthenticator,
        tokenInterceptor: TokenInterceptor,
        cache: Cache
    ): ImgurService {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(tokenInterceptor)
            .authenticator(tokenAuthenticator)
            .cache(cache)
            .build()
        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ImgurService::class.java)

    }

    @Provides
    @Singleton
    fun provideUserRepository(
        imgurService: ImgurService,
        sharedPreferences: SharedPreferences,
        tokenInterceptor: TokenInterceptor
    ) = AuthRepository(imgurService, sharedPreferences, tokenInterceptor)

    @Provides
    @Singleton
    fun provideSharedPreferences(
        @Named("application_context") appContext: Context,
        @Named("shared_preferences_file") sharedPrefFile: String,
        @Named("main_key_alias") mainKeyAlias: String
    ) = EncryptedSharedPreferences.create(
        sharedPrefFile,
        mainKeyAlias,
        appContext,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )


    @Provides
    @Named("shared_preferences_file")
    fun provideSharedPreferencesFile(): String {
        return "yes_imgur_shared_prefs"
    }

    @Provides
    @Named("main_key_alias")
    fun provideMainKeyAliasForCryptography(): String {
        return MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
    }

    @Provides
    fun provideCompositeDisposable() = CompositeDisposable()

    @Singleton
    @Provides
    fun provideTokenInterceptor(sharedPreferences: SharedPreferences): TokenInterceptor {
        val accessToken = sharedPreferences.getString(PrefKey.ACCESS_TOKEN, null) ?: ""
        return TokenInterceptor(accessToken)

    }

    @Singleton
    @Provides
    fun provideNetworkCache(@Named("application_context") context: Context): Cache {
        val cacheSize = 10 * 1024 * 1024L // 10 MB
        return Cache(context.cacheDir, cacheSize)
    }

    @Provides
    @Singleton
    fun provideGalleryRepository(imgurService: ImgurService) = GalleryRepository(imgurService)


}