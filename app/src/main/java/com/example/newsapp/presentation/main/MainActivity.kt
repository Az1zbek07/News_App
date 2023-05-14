package com.example.newsapp.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.navigation.ui.setupWithNavController
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.presentation.fragment.categories.CategoryFragment
import com.example.newsapp.presentation.fragment.home.HomeFragment
import com.example.newsapp.presentation.fragment.profile.ProfileFragment
import com.example.newsapp.presentation.fragment.saved.SavedFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()


    }

}