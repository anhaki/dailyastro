package com.haki.dailyastro.ui.apod

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.haki.core.data.Resource
import com.haki.core.ui.AstronomyAdapter
import com.haki.dailyastro.databinding.FragmentApodBinding
import com.haki.dailyastro.ui.daily.DailyViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class ApodFragment : Fragment() {
    private val apodViewModel: ApodViewModel by viewModels()

    private lateinit var binding: FragmentApodBinding

    // This property is only valid between onCreateView and
    // onDestroyView.

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentApodBinding.inflate(inflater, container, false)

        if (activity != null) {
            val simpleDateFormat1 = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            val simpleDateFormat2 = SimpleDateFormat("dd MMMM yyyy", Locale.US)
            val currentDate = simpleDateFormat1.format(Date())

//            astronomyAdapter.onItemClick = { selectedData ->
//                val intent = Intent(activity, DetailTourismActivity::class.java)
//                intent.putExtra(DetailTourismActivity.EXTRA_DATA, selectedData)
//                startActivity(intent)
//            }

            apodViewModel.getTodayAstro(currentDate).observe(viewLifecycleOwner) { astro ->
                if (astro != null) {
                    when (astro) {
                        is Resource.Loading -> {
                            Log.d("hacim", astro.data.toString())

                        }
                        is Resource.Success -> {
                            Log.d("wadaw", astro.data.toString())
                            val apod = astro.data?.first()
//                            binding.progressBar.visibility = View.GONE
                            binding.tvTitle.text = apod?.title
                            Glide.with(this)
                                .load(apod?.url)
                                .into(binding.ivApod)
                            binding.tvDate.text = "- ${simpleDateFormat2.format(simpleDateFormat1.parse(apod?.date!!) ?: "")} -"
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
        }

        return binding.root
    }
}