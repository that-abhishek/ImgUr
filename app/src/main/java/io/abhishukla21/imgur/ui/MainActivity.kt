package io.abhishukla21.imgur.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import io.abhishukla21.imgur.ImgurApplication
import io.abhishukla21.imgur.R
import io.abhishukla21.imgur.di.component.DaggerActivityComponent
import io.abhishukla21.imgur.di.module.ActivityModule
import io.abhishukla21.imgur.ui.gallery.GalleryViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    lateinit var naveHostFragment: NavHostFragment
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerActivityComponent.builder()
            .applicationComponent((application as ImgurApplication).daggerApplicationComponent)
            .activityModule(ActivityModule(this))
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
    }

    private fun setupView() {
        naveHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = naveHostFragment.navController
        bottom_navigation_main.setupWithNavController(navController)
    }


}