package com.example.newsapp.presentation.fragment.profile

import android.app.Activity
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.core.content.ContextCompat
import com.example.domain.model.Language
import com.example.domain.model.ThemeIndex
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentProfileBinding
import com.example.newsapp.util.SharedPrefManager
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val pref by lazy { SharedPrefManager(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfileBinding.bind(view)

        binding.switchBox.isChecked = pref.getThemeIndex()?.index ?: false
        if (pref.getLanguage()?.isEnglish == true){
            binding.englishButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.main_color))
            binding.russianButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.grey))
        }else{
            binding.englishButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.grey))
            binding.russianButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.main_color))
        }

        binding.switchBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
                pref.saveThemeIndex(ThemeIndex(true))
            }else{
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
                pref.saveThemeIndex(ThemeIndex(false))
            }
        }

        binding.russianButton.setOnClickListener {
            changeLanguage(requireActivity(), "ru")
            binding.englishButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.grey))
            binding.russianButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.main_color))
            pref.saveLanguage(Language((false)))
        }

        binding.englishButton.setOnClickListener {
            changeLanguage(requireActivity(), "en")
            binding.englishButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.main_color))
            binding.russianButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.grey))
            pref.saveLanguage(Language(true))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun changeLanguage(activity: Activity, langCode: String){
        val locale: Locale = Locale(langCode)
        Locale.setDefault(locale)
        val resources: Resources = activity.resources
        val config: Configuration = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }
}