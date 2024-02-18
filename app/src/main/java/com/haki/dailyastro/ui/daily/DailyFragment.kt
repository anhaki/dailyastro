package com.haki.dailyastro.ui.daily

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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DailyFragment : Fragment() {
    private val dailyViewModel: DailyViewModel by viewModels()

    private var _binding: FragmentDailyBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

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

            val astronomyAdapter = AstronomyAdapter()
//            astronomyAdapter.onItemClick = { selectedData ->
//                val intent = Intent(activity, DetailTourismActivity::class.java)
//                intent.putExtra(DetailTourismActivity.EXTRA_DATA, selectedData)
//                startActivity(intent)
//            }

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

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}