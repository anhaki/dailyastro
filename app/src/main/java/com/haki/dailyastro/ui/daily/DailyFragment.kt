package com.haki.dailyastro.ui.daily

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.haki.core.data.Resource
import com.haki.core.ui.AstronomyAdapter
import com.haki.dailyastro.R
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

            dailyViewModel.getAstro("2024-01-01", "2024-01-31")
                .observe(viewLifecycleOwner) { astro ->
                    if (astro != null) {
                        when (astro) {
                            is Resource.Loading -> {
                                showLoading(isLoad = true)
                            }

                            is Resource.Success -> {
                                showLoading(isLoad = false)
                                astronomyAdapter.setData(astro.data)
                            }

                            is Resource.Error -> {
                                showLoading(isLoad = false)
                                showSnackBar(astro.message.toString())
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

    private fun showSnackBar(msg: String) {
        Snackbar.make(
            requireActivity().findViewById(android.R.id.content),
            msg,
            Snackbar.LENGTH_LONG
        )
            .setAction(getString(R.string.close)) { }
            .setActionTextColor(ContextCompat.getColor(requireActivity(), R.color.main_orange))
            .show()
    }

    private fun showLoading(isLoad: Boolean) {
        if (isLoad) {
            binding.progress.visibility = View.VISIBLE
            binding.rvAstro.visibility = View.INVISIBLE
        } else {
            binding.progress.visibility = View.GONE
            binding.rvAstro.visibility = View.VISIBLE
        }
    }

}