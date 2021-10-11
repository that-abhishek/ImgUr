package io.abhishukla21.imgur

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.security.crypto.EncryptedSharedPreferences
import io.abhishukla21.imgur.di.component.DaggerActivityComponent
import io.abhishukla21.imgur.di.module.ActivityModule
import io.abhishukla21.imgur.ui.gallery.GalleryViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var galleryViewModel: GalleryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerActivityComponent.builder()
            .applicationComponent((application as ImgurApplication).daggerApplicationComponent)
            .activityModule(ActivityModule(this))
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        observeLiveData()
        galleryViewModel.onCreate()
        btn_search.setOnClickListener { galleryViewModel.searchGallery(edit_text_search_gallery.text.toString()) }
    }

    private fun observeLiveData() {
        galleryViewModel.searchResultLiveData.observe(this, {
            text_view_search_result.text = it.toString()
        })
    }
}