package com.example.newsapp.presentation.fragment.main

import android.os.Bundle
import android.service.autofill.VisibilitySetterAction
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.fragment.app.FragmentPagerAdapter
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentMainBinding
import com.example.newsapp.presentation.fragment.categories.CategoryFragment
import com.example.newsapp.presentation.fragment.home.HomeFragment
import com.example.newsapp.presentation.fragment.profile.ProfileFragment
import com.example.newsapp.presentation.fragment.saved.SavedFragment

class MainFragment : Fragment(R.layout.fragment_main) {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)

        val viewPagerAdapter = object : FragmentPagerAdapter(requireActivity().supportFragmentManager){
            override fun getCount(): Int = 4

            override fun getItem(position: Int): Fragment {
                return when(position){
                    0 -> HomeFragment()
                    1 -> CategoryFragment()
                    2 -> SavedFragment()
                    3 -> ProfileFragment()
                    else -> Fragment()
                }
            }

        }

        binding.viewPager.adapter = viewPagerAdapter

        binding.navView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.homeFragment -> binding.viewPager.currentItem = 0
                R.id.categoryFragment -> binding.viewPager.currentItem = 1
                R.id.savedFragment -> binding.viewPager.currentItem = 2
                R.id.profileFragment -> binding.viewPager.currentItem = 3
            }
            true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}