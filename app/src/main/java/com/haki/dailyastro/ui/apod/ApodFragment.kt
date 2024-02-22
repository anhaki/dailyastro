package com.haki.dailyastro.ui.apod

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.haki.core.data.Resource
import com.haki.core.domain.model.Astronomy
import com.haki.core.ui.AstronomyAdapter
import com.haki.dailyastro.databinding.FragmentApodBinding
import com.haki.dailyastro.ui.daily.DailyViewModel
import com.haki.dailyastro.ui.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class ApodFragment : Fragment() {
    private val apodViewModel: ApodViewModel by viewModels()

    private lateinit var binding: FragmentApodBinding

    private lateinit var astroData : Astronomy

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

            binding.seeDetail.setOnClickListener {
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.ASTRO_EXTRA, astroData)
                startActivity(intent)
            }

            apodViewModel.getTodayAstro(currentDate).observe(viewLifecycleOwner) { astro ->
                if (astro != null) {
                    when (astro) {
                        is Resource.Loading -> {

                        }
                        is Resource.Success -> {
                            val apod = astro.data?.first()
//                            binding.progressBar.visibility = View.GONE
                            binding.tvTitle.text = apod?.title
                            Glide.with(this)
                                .load(apod?.url)
                                .into(binding.ivApod)
                            binding.tvDate.text = "- ${simpleDateFormat2.format(simpleDateFormat1.parse(apod?.date!!) ?: "")} -"

                            astroData = Astronomy(
                                date = apod.date,
                                hdurl = apod.hdurl,
                                explanation = apod.explanation,
                                title = apod.title,
                                url = apod.url,
                            )
                        }

                        is Resource.Error -> {
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