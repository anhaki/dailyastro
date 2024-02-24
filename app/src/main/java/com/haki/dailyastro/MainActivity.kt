package com.haki.dailyastro

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.haki.dailyastro.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        navView.itemIconTintList = null
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#2A2A2A")))

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_apod, R.id.navigation_daily
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val menuId = item.itemId

        if (menuId == R.id.navigation_favorite) {
            val uri = Uri.parse("dailyastro://favorite")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
        return true
    }

}