package com.example.newsapp.presentation.fragment.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.adapter.HomeAdapter
import com.example.newsapp.databinding.FragmentHomeBinding
import com.example.newsapp.presentation.fragment.detail.DetailActivity
import com.example.newsapp.presentation.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
    private val homeAdapter by lazy { HomeAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        binding.rv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = homeAdapter
        }

        viewModel.getRemoteNews()

        lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    is HomeState.Empty -> {
                        binding.pr.isVisible = false
                        binding.rv.isVisible = true
                    }
                    is HomeState.Loading -> {
                        binding.pr.isVisible = true
                        binding.rv.isVisible = false
                    }
                    is HomeState.Error -> {
                        binding.pr.isVisible = false
                        binding.rv.isVisible = true

                        Toast.makeText(
                            requireContext(),
                            state.message,
                            Toast.LENGTH_SHORT
                        ).show()

                        Log.d("@@@", "onViewCreated: ${state.message}")
                    }
                    is HomeState.Success -> {
                        binding.pr.isVisible = false
                        binding.rv.isVisible = true
                        homeAdapter.submitList(state.informList)
                    }
                }
            }
        }

        binding.btnSearch.setOnClickListener {
            val text = binding.editText.text.toString().trim()
            if (text.isNotBlank()) {
                viewModel.onEvent(HomeEvent.OnSearched(text))
            } else {
                Toast.makeText(requireContext(), "Please enter value", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnRandom.setOnClickListener {
            viewModel.onEvent(HomeEvent.OnRandom)
            binding.btnRandom.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.main_color
                )
            )
            binding.btnSports.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.grey
                )
            )
            binding.btnGaming.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.grey
                )
            )
        }

        binding.btnGaming.setOnClickListener {
            viewModel.onEvent(HomeEvent.OnGaming)
            binding.btnRandom.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.grey
                )
            )
            binding.btnSports.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.grey
                )
            )
            binding.btnGaming.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.main_color
                )
            )
        }

        binding.btnSports.setOnClickListener {
            viewModel.onEvent(HomeEvent.OnSports)
            binding.btnRandom.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.grey
                )
            )
            binding.btnSports.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.main_color
                )
            )
            binding.btnGaming.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.grey
                )
            )
        }

        homeAdapter.onClick = {
            val intent = Intent(requireActivity(), DetailActivity::class.java)
            val bundle = bundleOf("inform" to it)
            intent.putExtras(bundle)
            startActivity(intent)
        }

        homeAdapter.onSave = {inform ->

            lifecycleScope.launch{
                viewModel.saveNew(inform)
            }
            Toast.makeText(requireContext(), "Saved Successfully", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}