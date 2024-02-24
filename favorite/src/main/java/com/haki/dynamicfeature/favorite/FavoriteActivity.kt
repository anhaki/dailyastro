package com.haki.dynamicfeature.favorite

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.haki.core.ui.AstronomyAdapter
import com.haki.dailyastro.R
import com.haki.dailyastro.di.FavModuleDependencies
import com.haki.dailyastro.ui.detail.DetailActivity
import com.haki.dynamicfeature.favorite.databinding.ActivityFavoriteBinding
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: ViewModelFactory

    private val favoriteViewModel: FavoriteViewModel by viewModels {
        factory
    }

    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFavComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    FavModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)

        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.title_favorite)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#2A2A2A")))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val astronomyAdapter = AstronomyAdapter()
        astronomyAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.ASTRO_EXTRA, selectedData)
            startActivity(intent)
        }

        favoriteViewModel.favorite.observe(this) {
            astronomyAdapter.setData(it)
        }

        with(binding.rvAstro) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = astronomyAdapter
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}