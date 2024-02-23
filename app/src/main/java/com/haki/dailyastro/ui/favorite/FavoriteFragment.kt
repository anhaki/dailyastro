package com.haki.dailyastro.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.haki.core.ui.AstronomyAdapter
import com.haki.dailyastro.databinding.FragmentFavoriteBinding
import com.haki.dailyastro.ui.daily.DailyViewModel
import com.haki.dailyastro.ui.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private val favoriteViewModel: FavoriteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        val astronomyAdapter = AstronomyAdapter()
        astronomyAdapter.onItemClick = { selectedData ->
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.ASTRO_EXTRA, selectedData)
            startActivity(intent)
        }

        favoriteViewModel.getFavorite().observe(requireActivity()){
            astronomyAdapter.setData(it)

        }

        with(binding.rvAstro) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = astronomyAdapter
        }

        return binding.root
    }
}