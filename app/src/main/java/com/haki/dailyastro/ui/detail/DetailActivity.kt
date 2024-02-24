package com.haki.dailyastro.ui.detail

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.haki.core.domain.model.Astronomy
import com.haki.dailyastro.R
import com.haki.dailyastro.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private val detailViewModel: DetailViewModel by viewModels()

    private lateinit var binding: ActivityDetailBinding
    private lateinit var dataDate: String
    private var isFav: Boolean = false
    private var menu: Menu? = null
    private lateinit var listData: List<Astronomy>

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.detail_information)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#2A2A2A")))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val dataDetail = intent.getParcelableExtra(ASTRO_EXTRA, Astronomy::class.java)
        binding.tvTitle.text = dataDetail?.title
        binding.tvExplanation.text = dataDetail?.explanation
        Glide.with(this)
            .load(dataDetail?.url)
            .into(binding.ivPhoto)

        dataDate = dataDetail?.date.toString()
        if (dataDetail != null) {
            listData = listOf(
                Astronomy(
                    date = dataDetail.date,
                    hdurl = dataDetail.hdurl,
                    explanation = dataDetail.explanation,
                    title = dataDetail.title,
                    url = dataDetail.url,
                )
            )
        }
    }

    private fun isFavorite(date: String) {
        detailViewModel.isFav(date).observe(this) { isFavorite ->
            isFav = if (isFavorite) {
                menu?.findItem(R.id.menu_favorite)?.setIcon(R.drawable.menu_star)
                true
            } else {
                menu?.findItem(R.id.menu_favorite)?.setIcon(R.drawable.menu_star_outline)
                false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        this.menu = menu
        isFavorite(dataDate)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val menuId = item.itemId

        if (menuId == R.id.menu_favorite) {
            isFav = if (isFav) {
                menu?.findItem(R.id.menu_favorite)?.setIcon(R.drawable.menu_star_outline)
                detailViewModel.deleteFavorite(dataDate)
                false
            } else {
                menu?.findItem(R.id.menu_favorite)?.setIcon(R.drawable.menu_star)
                detailViewModel.setAstronomyFavorite(listData)
                true
            }
        } else {
            onBackPressedDispatcher.onBackPressed()
        }
        return true
    }

    companion object {
        const val ASTRO_EXTRA = "ASTRO_EXTRA"
    }
}