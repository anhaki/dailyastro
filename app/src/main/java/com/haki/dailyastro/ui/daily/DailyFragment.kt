package com.haki.dailyastro.ui.daily

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
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

    private var _binding: FragmentDailyBinding? = null
    private val binding get() = _binding!!

    private lateinit var theActivity: FragmentActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDailyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            theActivity = requireActivity()

            val astronomyAdapter = AstronomyAdapter()
            astronomyAdapter.onItemClick = { selectedData ->
                val intent = Intent(theActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.TITLE_EXTRA, selectedData.title)
                intent.putExtra(DetailActivity.EXPLANATION_EXTRA, selectedData.explanation)
                intent.putExtra(DetailActivity.PHOTO_EXTRA, selectedData.url)
                intent.putExtra(DetailActivity.DATE_EXTRA, selectedData.date)
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
    }

    private fun showSnackBar(msg: String) {
        Snackbar.make(
            theActivity.findViewById(android.R.id.content),
            msg,
            Snackbar.LENGTH_LONG
        )
            .setAction(getString(R.string.close)) { }
            .setActionTextColor(ContextCompat.getColor(theActivity, R.color.main_orange))
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}