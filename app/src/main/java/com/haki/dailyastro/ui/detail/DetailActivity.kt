package com.haki.dailyastro.ui.detail

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.haki.core.domain.model.Astronomy
import com.haki.dailyastro.R
import com.haki.dailyastro.databinding.ActivityDetailBinding
import com.haki.dailyastro.databinding.FragmentDailyBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getParcelableExtra(ASTRO_EXTRA, Astronomy::class.java)
        binding.tvTitle.text = title?.title
    }

    companion object{
        const val ASTRO_EXTRA = "ASTRO_EXTRA"
    }
}