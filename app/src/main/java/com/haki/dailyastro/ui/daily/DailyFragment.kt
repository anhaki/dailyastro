package com.haki.dailyastro.ui.daily

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.haki.core.data.Resource
import com.haki.core.ui.AstronomyAdapter
import com.haki.dailyastro.databinding.FragmentDailyBinding
import com.haki.dailyastro.ui.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DailyFragment : Fragment() {
    private val dailyViewModel: DailyViewModel by viewModels()

    private lateinit var binding: FragmentDailyBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDailyBinding.inflate(inflater, container, false)


        if (activity != null) {

            val astronomyAdapter = AstronomyAdapter()
            astronomyAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.ASTRO_EXTRA, selectedData)
                startActivity(intent)
            }

            dailyViewModel.getAstro("2024-01-02", "2024-01-19").observe(viewLifecycleOwner) { astro ->
                if (astro != null) {
                    when (astro) {
                        is Resource.Loading -> {
                            Log.d("hacim", astro.data.toString())

                        }
                        is Resource.Success -> {
                            Log.d("wadaw", astro.data.toString())
//                            binding.progressBar.visibility = View.GONE
                            astronomyAdapter.setData(astro.data)

                        }

                        is Resource.Error -> {
                            Log.d("elor", astro.data.toString())

//                            binding.progressBar.visibility = View.GONE
//                            binding.viewError.root.visibility = View.VISIBLE
//                            binding.viewError.tvError.text =
//                                astro.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            }

            with(binding.rvAstro) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = astronomyAdapter
            }
        }
        return binding.root
    }

}