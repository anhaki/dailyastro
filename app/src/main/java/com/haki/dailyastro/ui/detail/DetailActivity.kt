package com.haki.dailyastro.ui.detail

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.haki.core.domain.model.Astronomy
import com.haki.dailyastro.R
import com.haki.dailyastro.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

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
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    companion object{
        const val ASTRO_EXTRA = "ASTRO_EXTRA"
    }
}