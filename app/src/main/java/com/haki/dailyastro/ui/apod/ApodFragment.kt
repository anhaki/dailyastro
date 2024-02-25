package com.haki.dailyastro.ui.apod

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.haki.core.data.Resource
import com.haki.core.domain.model.Astronomy
import com.haki.dailyastro.R
import com.haki.dailyastro.databinding.FragmentApodBinding
import com.haki.dailyastro.ui.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

@AndroidEntryPoint
class ApodFragment : Fragment() {
    private val apodViewModel: ApodViewModel by viewModels()

    private lateinit var binding: FragmentApodBinding

    private lateinit var astroData: Astronomy

    private lateinit var date: String
    private lateinit var simpleDateFormat1: SimpleDateFormat
    private lateinit var simpleDateFormat2: SimpleDateFormat

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentApodBinding.inflate(inflater, container, false)

        if (activity != null) {
            simpleDateFormat1 = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            simpleDateFormat2 = SimpleDateFormat("dd MMMM yyyy", Locale.US)

            val usCalendar = Calendar.getInstance(TimeZone.getTimeZone("America/New_York"))
            simpleDateFormat1.timeZone = TimeZone.getTimeZone("America/New_York")
            date = simpleDateFormat1.format(usCalendar.time)

            binding.seeDetail.setOnClickListener {
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.ASTRO_EXTRA, astroData)
                startActivity(intent)
            }

            showApod(date)

        }
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    fun showApod(currentDate: String) {
        apodViewModel.getTodayAstro(currentDate).observe(viewLifecycleOwner) { astro ->
            if (astro != null) {
                when (astro) {
                    is Resource.Loading -> {
                        showLoading(true)
                    }

                    is Resource.Success -> {
                        val apod = astro.data?.first()
                        binding.tvTitle.text = apod?.title
                        Glide.with(this)
                            .load(apod?.url)
                            .into(binding.ivApod)
                        binding.tvDate.text =
                            "- ${simpleDateFormat2.format(simpleDateFormat1.parse(apod?.date!!) ?: "")} -"

                        astroData = Astronomy(
                            date = apod.date,
                            hdurl = apod.hdurl,
                            explanation = apod.explanation,
                            title = apod.title,
                            url = apod.url,
                        )

                        showLoading(false)
                    }

                    is Resource.Error -> {
                        showSnackBar(astro.message.toString())
                        showLoading(false)
                    }
                }
            }
        }
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
            binding.content.visibility = View.INVISIBLE
        } else {
            binding.progress.visibility = View.GONE
            binding.content.visibility = View.VISIBLE
        }
    }
}